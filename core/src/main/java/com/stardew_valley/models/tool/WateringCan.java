package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Plant;
import com.stardew_valley.models.tool.enums.WateringCanType;

// related to farming.
public class WateringCan extends Tool {
    private WateringCanType type;
    private int waterAmount;
    private int capacity;

    private static final int INITIAL_CAPACITY = 100;

    public WateringCan(Inventory inventory) {
        super(inventory);
        capacity = INITIAL_CAPACITY;
        name = "Watering Can";
        type = WateringCanType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    public void addWater(int amount) {
        if (amount > 0) waterAmount += amount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void removeWater() {
        waterAmount--;
    }

    public void fillCan() {
        waterAmount = capacity;
    }

    public void emptyCan() {
        waterAmount = 0;
    }

    public WateringCanType getType() {
        return type;
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position position = player.getTilesPosition();
        Position dirAppliedPosition = position.applyDirection(direction);

        Tile tile = inventory.getPlayer().getFarm().getTile(dirAppliedPosition);
        if (tile.getObject() instanceof Plant plant && !this.isEmpty()) {
            if (!plant.isWatered()) removeWater();
            plant.water();
        } else if (tile.getType() == TileType.RIVER) {
            this.fillCan();
        }

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = WateringCanType.COPPER;
            case COPPER -> type = WateringCanType.IRON;
            case IRON -> type = WateringCanType.GOLD;
            case GOLD -> type = WateringCanType.IRIDIUM;
        }
    }

    public boolean isEmpty() {
        return waterAmount <= 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().getWateringCan();
    }
}
