package models.cooking;

public enum FoodsEnum {
    FRIED_EGG("Fried Egg", 50, null, -1, 35),
    BAKED_FISH("Baked Fish", 75, null, -1, 100),
    SALAD("Salad", 113, null, -1,110),
    OMELET("Omelet", 100, null, -1,125),
    PUMPKIN_PIE("Pumpkin Pie", 225, null,-1, 385),
    SPAGHETTI("Spaghetti", 75, null, -1, 120),
    PIZZA("Pizza", 150, null,-1, 300),
    TORTILLA("Tortilla", 50, null, -1, 50),
    MAKI_ROLL("Maki Roll", 100, null, -1,220),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", 200, "Max Energy", 5, 450),
    COOKIE("Cookie", 90, null, -1,140),
    HASH_BROWNS("Hash Browns", 90, "Farming", 5,120),
    PANCAKES("Pancakes", 90, "Foraging",11, 80),
    FRUIT_SALAD("Fruit Salad", 263, null, -1,450),
    RED_PLATE("Red Plate", 240, "Max Energy", 3,400),
    BREAD("Bread", 50, null, -1,60),
    SALMON_DINNER("Salmon Dinner", 125, null,-1, 300),
    VEGETABLE_MEDLEY("Vegetable Medley", 165, null,-1, 120),
    FARMERS_LUNCH("Farmer's Lunch", 200, "Farming",5, 150),
    SURVIVAL_BURGER("Survival Burger", 125, "Foraging",5, 180),
    DISH_O_THE_SEA("Dish O' the Sea", 150, "Fishing",5, 220),
    SEAFORM_PUDDING("Seaform Pudding", 175, "Fishing",10, 300),
    MINERS_TREAT("Miner's Treat", 125, "Mining",5, 200);

    private final String name;
    private final int energy;
    private final String buff;
    private final int buffTime;
    private final int sellPrice;

    FoodsEnum(String name, int energy, String buff, int buffTime, int sellPrice) {
        this.name = name;
        this.energy = energy;
        this.buff = buff;
        this.buffTime = buffTime;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public String getBuff() {
        return buff;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Foods toFood() {
        return new Foods(name, energy, buff, buffTime, sellPrice);
    }
}

