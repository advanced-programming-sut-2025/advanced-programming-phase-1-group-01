package models.farming;

import models.Item;

public class Fertilizer implements Item {
    FertilizerType type;

    @Override
    public String getName() {
        return type.toString();
    }
}