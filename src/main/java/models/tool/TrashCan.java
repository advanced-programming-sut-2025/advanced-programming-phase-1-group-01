package models.tool;

import models.enums.Direction;
import models.tool.enums.TrashCanType;

public class TrashCan extends Tool {
    private final TrashCanType type;

    public TrashCan() {
        this.type = TrashCanType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return 0;
    }

    @Override
    public void use(Direction direction) {
        // this method is not used for trash can
    }

    public TrashCanType getType() {
        return type;
    }

    public int getReturnValue(int price) {
        return price * type.getReturnValuePercentage() / 100;
    }
}
