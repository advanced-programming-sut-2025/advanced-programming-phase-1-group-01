package models.tool;

import models.Item;
import models.Position;
import models.building.Tile;
import models.character.player.Inventory;
import models.character.player.Player;
import models.enums.Direction;
import models.farming.Tree;
import models.tool.enums.AxeType;

// related to foraging
public class Axe extends Tool {
    private AxeType type;

    public Axe(Inventory inventory) {
        super(inventory);
        name = "axe";
        type = AxeType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position position = player.getPosition();
        Tile tile = player.getCurrentMap().getTile(position);

        Item item = tile.getObject();
        if (item instanceof Tree) {
            tile.removeObject();
            boolean success = inventory.addItem("wood", 1);
            double energyCost = getEffectiveEnergyCost();
            inventory.getPlayer().getEnergy().consume(energyCost);
            if (success) {
                player.getAbilityService().getForaging().increaseXp(10);
            }
        }
    }
}