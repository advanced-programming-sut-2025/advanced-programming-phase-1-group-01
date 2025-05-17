package models.shop.enums;

import models.dateTime.Season;
import models.shop.JojaMartItem;

public enum JojaMartProducts {
        // Permanent Stock
        JOJA_COLA("Joja Cola", 75, -1, Season.SPECIAL),
        ANCIENT_SEED("Ancient Seed", 500, 1, Season.SPECIAL),
        GRASS_STARTER("Grass Starter", 125, -1, Season.SPECIAL),
        SUGAR("Sugar", 125, -1, Season.SPECIAL),
        WHEAT_FLOUR("Wheat Flour", 125, -1, Season.SPECIAL),
        RICE("Rice", 250, -1, Season.SPECIAL),

        // Spring Stock
        PARSNIP_SEEDS("Parsnip Seeds", 25, 5, Season.SPRING),
        BEAN_STARTER("Bean Starter", 75, 5, Season.SPRING),
        CAULIFLOWER_SEEDS("Cauliflower Seeds", 100, 5, Season.SPRING),
        POTATO_SEEDS("Potato Seeds", 62, 5, Season.SPRING),
        STRAWBERRY_SEEDS("Strawberry Seeds", 100, 5, Season.SPRING),
        TULIP_BULB("Tulip Bulb", 25, 5, Season.SPRING),
        KALE_SEEDS("Kale Seeds", 87, 5, Season.SPRING),
        COFFEE_BEANS_SPRING("Coffee Beans", 200, 1, Season.SPRING),
        CARROT_SEEDS("Carrot Seeds", 5, 10, Season.SPRING),
        RHUBARB_SEEDS("Rhubarb Seeds", 100, 5, Season.SPRING),
        JAZZ_SEEDS("Jazz Seeds", 37, 5, Season.SPRING),

        // Summer Stock
        TOMATO_SEEDS("Tomato Seeds", 62, 5, Season.SUMMER),
        PEPPER_SEEDS("Pepper Seeds", 50, 5, Season.SUMMER),
        WHEAT_SEEDS_SUMMER("Wheat Seeds", 12, 10, Season.SUMMER),
        SUMMER_SQUASH_SEEDS("Summer Squash Seeds", 10, 10, Season.SUMMER),
        RADISH_SEEDS("Radish Seeds", 50, 5, Season.SUMMER),
        MELON_SEEDS("Melon Seeds", 100, 5, Season.SUMMER),
        HOPS_STARTER("Hops Starter", 75, 5, Season.SUMMER),
        POPPY_SEEDS("Poppy Seeds", 125, 5, Season.SUMMER),
        SPANGLE_SEEDS("Spangle Seeds", 62, 5, Season.SUMMER),
        STARFRUIT_SEEDS("Starfruit Seeds", 400, 5, Season.SUMMER),
        COFFEE_BEANS_SUMMER("Coffee Beans", 200, 1, Season.SUMMER),
        SUNFLOWER_SEEDS_SUMMER("Sunflower Seeds", 125, 5, Season.SUMMER),

        // Fall Stock
        CORN_SEEDS("Corn Seeds", 187, 5, Season.FALL),
        EGGPLANT_SEEDS("Eggplant Seeds", 25, 5, Season.FALL),
        PUMPKIN_SEEDS("Pumpkin Seeds", 125, 5, Season.FALL),
        BROCCOLI_SEEDS("Broccoli Seeds", 15, 5, Season.FALL),
        AMARANTH_SEEDS("Amaranth Seeds", 87, 5, Season.FALL),
        GRAPE_STARTER("Grape Starter", 75, 5, Season.FALL),
        BEET_SEEDS("Beet Seeds", 20, 5, Season.FALL),
        YAM_SEEDS("Yam Seeds", 75, 5, Season.FALL),
        BOK_CHOY_SEEDS("Bok Choy Seeds", 62, 5, Season.FALL),
        CRANBERRY_SEEDS("Cranberry Seeds", 300, 5, Season.FALL),
        SUNFLOWER_SEEDS_FALL("Sunflower Seeds", 125, 5, Season.FALL),
        FAIRY_SEEDS("Fairy Seeds", 250, 5, Season.FALL),
        RARE_SEED("Rare Seed", 1000, 1, Season.FALL),
        WHEAT_SEEDS_FALL("Wheat Seeds", 12, 5, Season.FALL),

        // Winter Stock
        POWDERMELON_SEEDS("Powdermelon Seeds", 20, 10, Season.WINTER);

        private final String name;
        private final int price;
        private final int dailyLimit;
        private final Season season;

        JojaMartProducts(String name, int price, int dailyLimit, Season season) {
            this.name = name;
            this.price = price;
            this.dailyLimit = dailyLimit;
            this.season = season;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getDailyLimit() {
            return dailyLimit;
        }

        public Season getSeason() {
            return season;
        }

        public JojaMartItem toItem() {
                return new JojaMartItem(name, price, season);
        }
}
