package models.tool;

import models.Item;
import models.Position;
import models.building.Tile;
import models.building.TileObject;
import models.character.player.Inventory;
import models.character.player.Player;
import models.enums.Direction;
import models.foraging.ForagingMineral;
import models.ingredients.Stone;
import models.tool.enums.PickaxeType;

// related to mining.
public class Pickaxe extends Tool {
    private final PickaxeType type;

    public Pickaxe(Inventory inventory) {
        super(inventory);
        name = "pickaxe";
        type = PickaxeType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position position = player.getPosition();
        Position appliedPosition = position.applyDirection(direction);

        Tile tile = player.getCurrentMap().getTile(appliedPosition);
        tile.unPlow();
        Item item = tile.getObject();
//        if (item instanceof Stone || item instanceof ForagingMineral) {
        tile.removeObject();
        player.getAbilityService().getMining().increaseXp(10);
//        }

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
