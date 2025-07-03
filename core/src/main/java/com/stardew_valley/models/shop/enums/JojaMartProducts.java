package com.stardew_valley.models.shop.enums;

import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.shop.JojaMartItem;

public enum JojaMartProducts {
        joja_cola("Joja Cola", 75, -1, Season.SPECIAL),
        ancient_seed("Ancient Seed", 500, 1, Season.SPECIAL),
        grass_starter("Grass Starter", 125, -1, Season.SPECIAL),
        sugar("Sugar", 125, -1, Season.SPECIAL),
        wheat_flour("Wheat Flour", 125, -1, Season.SPECIAL),
        rice("Rice", 250, -1, Season.SPECIAL),

        // Spring Stock
        parsnip_seeds("Parsnip Seeds", 25, 5, Season.SPRING),
        bean_starter("Bean Starter", 75, 5, Season.SPRING),
        cauliflower_seeds("Cauliflower Seeds", 100, 5, Season.SPRING),
        potato_seeds("Potato Seeds", 62, 5, Season.SPRING),
        strawberry_seeds("Strawberry Seeds", 100, 5, Season.SPRING),
        tulip_bulb("Tulip Bulb", 25, 5, Season.SPRING),
        kale_seeds("Kale Seeds", 87, 5, Season.SPRING),
        coffee_beans_spring("Coffee Beans", 200, 1, Season.SPRING),
        carrot_seeds("Carrot Seeds", 5, 10, Season.SPRING),
        rhubarb_seeds("Rhubarb Seeds", 100, 5, Season.SPRING),
        jazz_seeds("Jazz Seeds", 37, 5, Season.SPRING),

        // Summer Stock
        tomato_seeds("Tomato Seeds", 62, 5, Season.SUMMER),
        pepper_seeds("Pepper Seeds", 50, 5, Season.SUMMER),
        wheat_seeds_summer("Wheat Seeds", 12, 10, Season.SUMMER),
        summer_squash_seeds("Summer Squash Seeds", 10, 10, Season.SUMMER),
        radish_seeds("Radish Seeds", 50, 5, Season.SUMMER),
        melon_seeds("Melon Seeds", 100, 5, Season.SUMMER),
        hops_starter("Hops Starter", 75, 5, Season.SUMMER),
        poppy_seeds("Poppy Seeds", 125, 5, Season.SUMMER),
        spangle_seeds("Spangle Seeds", 62, 5, Season.SUMMER),
        starfruit_seeds("Starfruit Seeds", 400, 5, Season.SUMMER),
        coffee_beans_summer("Coffee Beans", 200, 1, Season.SUMMER),
        sunflower_seeds_summer("Sunflower Seeds", 125, 5, Season.SUMMER),

        // Fall Stock
        corn_seeds("Corn Seeds", 187, 5, Season.FALL),
        eggplant_seeds("Eggplant Seeds", 25, 5, Season.FALL),
        pumpkin_seeds("Pumpkin Seeds", 125, 5, Season.FALL),
        broccoli_seeds("Broccoli Seeds", 15, 5, Season.FALL),
        amaranth_seeds("Amaranth Seeds", 87, 5, Season.FALL),
        grape_starter("Grape Starter", 75, 5, Season.FALL),
        beet_seeds("Beet Seeds", 20, 5, Season.FALL),
        yam_seeds("Yam Seeds", 75, 5, Season.FALL),
        bok_choy_seeds("Bok Choy Seeds", 62, 5, Season.FALL),
        cranberry_seeds("Cranberry Seeds", 300, 5, Season.FALL),
        sunflower_seeds_fall("Sunflower Seeds", 125, 5, Season.FALL),
        fairy_seeds("Fairy Seeds", 250, 5, Season.FALL),
        rare_seed("Rare Seed", 1000, 1, Season.FALL),
        wheat_seeds_fall("Wheat Seeds", 12, 5, Season.FALL),

        // Winter Stock
        powdermelon_seeds("Powdermelon Seeds", 20, 10, Season.WINTER);

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
