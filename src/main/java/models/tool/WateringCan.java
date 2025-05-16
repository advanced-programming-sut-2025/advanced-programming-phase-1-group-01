package models.tool;

import models.Position;
import models.building.Tile;
import models.character.player.Player;
import models.enums.Direction;
import models.farming.Plant;
import models.tool.enums.WateringCanType;

// related to farming.
public class WateringCan extends Tool {
    private WateringCanType type;
    private int waterAmount;
    private int capacity;

    private static final int INITIAL_CAPACITY = 100;

    public WateringCan() {
        capacity = INITIAL_CAPACITY;
        name = "watering can";
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
        Position position = player.getPosition();
        Position dirAppliedPosition = position.applyDirection(direction);

        Tile tile = inventory.getPlayer().getFarm().getTile(dirAppliedPosition);
        if (tile.getObject() instanceof Plant plant && !isEmpty()) {
            plant.water();
            removeWater();
        }

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    public boolean isEmpty() {
        return waterAmount == 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
