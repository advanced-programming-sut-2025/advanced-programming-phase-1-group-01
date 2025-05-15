package models.tool;

import models.Position;
import models.building.Tile;
import models.character.player.Player;
import models.enums.Direction;
import models.tool.enums.HoeType;

// related to farming.
public class Hoe extends Tool {
    HoeType type;

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        // what tool does
        Player player = inventory.getPlayer();
        Position position = player.getPosition();
        Position dirAppliedPosition = position.applyDirection(direction);

        Tile tile = inventory.getPlayer().getFarm().getTile(dirAppliedPosition);
        tile.plow();

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
