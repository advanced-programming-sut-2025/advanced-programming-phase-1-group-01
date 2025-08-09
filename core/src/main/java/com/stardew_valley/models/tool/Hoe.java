package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.User;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.tool.enums.HoeType;

// related to farming.
public class Hoe extends Tool {
    private HoeType type;

    public Hoe(Inventory inventory) {
        super(inventory);
        name = "Hoe";
        type = HoeType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        User player = inventory.getPlayer();
        Position position = player.getTilesPosition();
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

    @Override
    public Texture getTexture() {
        AssetManager am = AssetManager.getAssetManager();
        return switch (type) {
            case PRIMARY -> am.getHoe();
            case COPPER -> am.getCopperHoe();
            case IRON -> am.getSteelHoe();
            case GOLD -> am.getGoldHoe();
            case IRIDIUM -> am.getIridiumHoe();
        };
    }
}
