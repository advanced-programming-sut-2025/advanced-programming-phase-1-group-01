package models.tool;

import models.enums.Direction;
import models.tool.enums.TrashCanType;

public class TrashCan extends Tool {
    private TrashCanType type;

    public TrashCan() {
        this.type = TrashCanType.PRIMARY;
        name = "trash can";
    }

    @Override
    public int getBaseEnergyCost() {
        return 0;
    }

    @Override
    public void use(Direction direction) {
        // this method is not used for trash can
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = TrashCanType.COPPER;
            case COPPER -> type = TrashCanType.IRON;
            case IRON -> type = TrashCanType.GOLD;
            case GOLD -> type = TrashCanType.IRIDIUM;
        }
    }

    public TrashCanType getType() {
        return type;
    }

    public int getReturnValue(int price) {
        return price * type.getReturnValuePercentage() / 100;
    }
}
