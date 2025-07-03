package com.stardew_valley.models.tool;

import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.tool.enums.HoeType;

// related to farming.
public class Hoe extends Tool {
    private HoeType type;

    public Hoe(Inventory inventory) {
        super(inventory);
        name = "hoe";
        type = HoeType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position position = player.getPosition();
        Position dirAppliedPosition = position.applyDirection(direction);

        Tile tile = inventory.getPlayer().getFarm().getTile(dirAppliedPosition);
        tile.plow();

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = HoeType.COPPER;
            case COPPER -> type = HoeType.IRON;
            case IRON -> type = HoeType.GOLD;
            case GOLD -> type = HoeType.IRIDIUM;
        }
    }

    public HoeType getType() {
        return type;
    }
}
