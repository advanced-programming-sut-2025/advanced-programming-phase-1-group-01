package models.tool;

import models.Position;
import models.building.Tile;
import models.character.player.Player;
import models.enums.Direction;
import models.farming.Plant;

public class Scythe extends Tool {
    private static final int INITIAL_SCYTHE_USE_ENERGY = 2;

    @Override
    public int getBaseEnergyCost() {
        return INITIAL_SCYTHE_USE_ENERGY;
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position appliedPosition = player.getPosition().applyDirection(direction);
        Tile tile = player.getFarm().getTile(appliedPosition);

        if (tile.getObject() instanceof Plant plant) {
            if (plant.isFullyGrown()) {

            }
        }

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
