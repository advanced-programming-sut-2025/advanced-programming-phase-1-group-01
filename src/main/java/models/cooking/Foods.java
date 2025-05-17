package models.cooking;

import models.Item;

public class Foods implements Item {
    String name;
    int energy;
    String buff;
    int buffTime;
    int sellPrice;

    public Foods(String name, int energy, String buff, int buffTime, int sellPrice) {
        this.name = name;
        this.energy = energy;
        this.buff = buff;
        this.buffTime = buffTime;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return sellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public String getBuff() {
        return buff;
    }

    public int buffTime() {
        return buffTime;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
