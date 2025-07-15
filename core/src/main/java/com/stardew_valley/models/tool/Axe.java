package com.stardew_valley.models.tool;

import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Tree;
import com.stardew_valley.models.tool.enums.AxeType;

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

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = AxeType.COPPER;
            case COPPER -> type = AxeType.IRON;
            case IRON -> type = AxeType.GOLD;
            case GOLD -> type = AxeType.IRIDIUM;
        }
    }

    public AxeType getType() {
        return type;
    }
}
