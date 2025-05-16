package controllers;

import models.Item;
import models.Result;
import models.character.player.Inventory;
import models.character.player.Player;
import models.character.player.Slot;
import models.data.Repository;
import models.enums.Direction;
import models.enums.commands.ToolCommands;
import models.tool.Tool;

public class ToolController extends Controller {
    ToolController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        ToolCommands matchedCommand = null;

        for (ToolCommands command : ToolCommands.values()) {
            if (commandLine.matches(command.getRegex())) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        String toolName;
        switch (matchedCommand) {
            case TOOLS_EQUIP:
                toolName = commandLine.substring(commandLine.indexOf("equip") + 5).trim();
                return equipTool(toolName);
            case TOOLS_SHOW_CURRENT:
                return showCurrentTool();
            case TOOLS_SHOW_AVAILABLE:
                return showAvailableTools();
            case TOOLS_UPGRADE:
                toolName = commandLine.substring(commandLine.indexOf("upgrade") + 7).trim();
                return upgradeTool(toolName);
            case TOOLS_USE:
                Direction direction = Direction.fromString(commandLine.substring(commandLine.indexOf("-d") + 2).trim());
                return useTool(direction);
        }
        return new Result(false, "invalid command");
    }

    private Result equipTool(String toolName) {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();

        Slot slot = inventory.getSlot(toolName);

        if (slot == null || !(slot.getItem() instanceof Tool)) {
            return new Result(false, "tool not found");
        }

        inventory.setEquippedSlot(slot);
        return new Result(true, "tool equipped successfully");
    }

    private Result showCurrentTool() {
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();
        Slot equippedSlot = inventory.getEquippedSlot();

        if (equippedSlot == null || !(equippedSlot.getItem() instanceof Tool)) {
            return new Result(false, "not a tool equipped");
        }

        return new Result(true, equippedSlot.getItem().getName());
    }

    private Result showAvailableTools() {
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();
        StringBuilder availableTools = new StringBuilder();

        for (Tool tool : inventory.getTools()) {
            availableTools.append(tool.getName());
            if (inventory.getTools().indexOf(tool) != inventory.getTools().size() - 1) {
                availableTools.append("\n");
            }
        }

        return new Result(true, availableTools.toString());
    }

    private Result upgradeTool(String toolName) {
        // player is in Blacksmith ? ok : nok

        return null;
    }

    private Result useTool(Direction direction) {
        Inventory inventory = repo.getCurrentGame().getCurrentPlayer().getInventory();
        Slot equippedSlot = inventory.getEquippedSlot();

        if (equippedSlot == null || !(equippedSlot.getItem() instanceof Tool tool)) {
            return new Result(false, "not a tool equipped");
        }

        tool.use(direction);
        return new Result(true, "tool used successfully");
    }
}
