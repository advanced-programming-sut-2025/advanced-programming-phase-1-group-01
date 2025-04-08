package models.animal;

import java.util.List;

public enum AnimalType {
    COW(1500, List.of(AnimalProduct.MILK, AnimalProduct.BIG_MILK), List.of(AnimalHouse.BARN, AnimalHouse.DELUXE_BARN, AnimalHouse.BIG_BARN)),
    DINOSAUR(14000, List.of(AnimalProduct.DINOSAUR_EGG), List.of(AnimalHouse.BIG_COOP)),
    DUCK(1200, List.of(AnimalProduct.DUCK_EGG, AnimalProduct.DUCK_FEATHER), List.of(AnimalHouse.BIG_COOP, AnimalHouse.DELUXE_COOP)),
    GOAT(4000, List.of(AnimalProduct.GOAT_MILK, AnimalProduct.BIG_GOAT_MILK), List.of(AnimalHouse.DELUXE_BARN, AnimalHouse.BIG_BARN)),
    HEN(800, List.of(AnimalProduct.EGG, AnimalProduct.BIG_EGG), List.of(AnimalHouse.BIG_COOP, AnimalHouse.COOP, AnimalHouse.DELUXE_COOP)),
    PIG(16000, List.of(AnimalProduct.TRUFFLE), List.of(AnimalHouse.DELUXE_BARN)),
    RABBIT(8000, List.of(AnimalProduct.RABBIT_WOOL, AnimalProduct.RABBIT_LEG), List.of(AnimalHouse.DELUXE_COOP)),
    SHEEP(8000, List.of(AnimalProduct.SHEEP_WOOL), List.of(AnimalHouse.DELUXE_BARN));

    private int price;
    private List<AnimalProduct> products;
    private List<AnimalHouse> houses;

    AnimalType(int price, List<AnimalProduct> products, List<AnimalHouse> houses) {
        this.price = price;
        this.products = products;
        this.houses = houses;
    }
}
