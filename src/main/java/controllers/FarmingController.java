package controllers;

import models.Position;
import models.Result;
import models.building.Tile;
import models.character.player.InventorySlot;
import models.character.player.Player;
import models.data.Repository;
import models.enums.Direction;
import models.enums.commands.FarmingCommands;
import models.farming.*;
import models.foraging.ForagingCrop;
import models.foraging.ForagingTree;

public class FarmingController extends Controller {
    FarmingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        FarmingCommands matchedCommand = null;

        for (FarmingCommands cmd : FarmingCommands.values()) {
            if (commandLine.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        switch (matchedCommand) {
            case CRAFT_INFO:
                String name = commandLine.substring(commandLine.indexOf("-n") + 2).trim();
                return craftInfo(name);
            case PLANT:
                String seedName = commandLine.split("\\s+")[2];
                Direction direction = Direction.fromString(commandLine.substring(commandLine.indexOf("-d") + 2).trim());
                return plant(seedName, direction);
            case SHOW_PLANT:
                int x, y;
                try {
                    x = Integer.parseInt(commandLine.split("\\s+")[2]);
                    y = Integer.parseInt(commandLine.split("\\s+")[3]);
                } catch (NumberFormatException e) {
                    return new Result(false, "invalid x, y");
                }
                return showPlantInfo(new Position(x, y));
        }
        return new Result(false, "invalid command");
    }

    private Result craftInfo(String name) {
        FarmingEnum farmingConstant = null;

        if (CropInfo.fromString(name) != null) {
            farmingConstant = CropInfo.fromString(name);
        } else if (TreeInfo.fromString(name) != null) {
            farmingConstant = TreeInfo.fromString(name);
        } else if (ForagingCrop.fromString(name) != null) {
            farmingConstant = ForagingCrop.fromString(name);
        } else if (ForagingTree.fromString(name) != null) {
            farmingConstant = ForagingTree.fromString(name);
        } else {
            return new Result(false, "crop not found");
        }

        return new Result(true, farmingConstant.toString());
    }

    private Result plant(String seedName, Direction direction) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        InventorySlot slot = repo.getCurrentGame().getCurrentPlayer().getInventory().getSlot(seedName);
        Position appliedPosition = player.getPosition().applyDirection(direction);
        Tile tile = player.getFarm().getTile(appliedPosition);

        if (tile == null) {
            return new Result(false, "incorrect tile");
        } else if (slot == null) {
            return new Result(false, "seed not found");
        } else if (!tile.isPlowed()) {
            return new Result(false, "tile is not plowed");
        }

        Seed seed = (Seed) slot.getItem();
        slot.removeQuantity(1);
        repo.getCurrentGame().getFarmingManager().plant(seed, tile);
        Crop crop = (Crop) tile.getObject();

        return new Result(true, "%s planted in <%d, %d> successfully".formatted(crop.getName(), appliedPosition.x(), appliedPosition.y()));
    }

    private Result showPlantInfo(Position position) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Tile tile = player.getFarm().getTile(position);

        Crop crop;
        try {
            crop = (Crop) tile.getObject();
        } catch (ClassCastException e) {
            return new Result(false, "crop not found");
        }

        if (crop == null) {
            return new Result(false, "crop not found");
        }

        return new Result(true, crop.toString());
    }

    private Result fertilize() {
        return null;
    }

    private Result howMuchWater() {
        return null;
    }
}