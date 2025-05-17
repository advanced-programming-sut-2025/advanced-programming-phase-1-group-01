package models.tool;

import models.character.player.Player;
import models.enums.Direction;
import models.tool.enums.PickaxeType;

// related to mining.
public class Pickaxe extends Tool {
    private PickaxeType type;

    public Pickaxe() {
        name = "pickaxe";
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();


        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = PickaxeType.COPPER;
            case COPPER -> type = PickaxeType.IRON;
            case IRON -> type = PickaxeType.GOLD;
            case GOLD -> type = PickaxeType.IRIDIUM;
        }
    }

    public PickaxeType getType() {
        return type;
    }
}
