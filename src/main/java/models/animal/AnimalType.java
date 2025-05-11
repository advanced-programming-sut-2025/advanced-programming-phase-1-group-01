package models.animal;

import java.util.List;
import java.util.Objects;

public enum AnimalType {
    COW(1500, List.of(AnimalProductType.MILK, AnimalProductType.BIG_MILK), List.of(AnimalHouseType.BARN, AnimalHouseType.DELUXE_BARN, AnimalHouseType.BIG_BARN)),
    DINOSAUR(14000, List.of(AnimalProductType.DINOSAUR_EGG), List.of(AnimalHouseType.BIG_COOP)),
    DUCK(1200, List.of(AnimalProductType.DUCK_EGG, AnimalProductType.DUCK_FEATHER), List.of(AnimalHouseType.BIG_COOP, AnimalHouseType.DELUXE_COOP)),
    GOAT(4000, List.of(AnimalProductType.GOAT_MILK, AnimalProductType.BIG_GOAT_MILK), List.of(AnimalHouseType.DELUXE_BARN, AnimalHouseType.BIG_BARN)),
    HEN(800, List.of(AnimalProductType.EGG, AnimalProductType.BIG_EGG), List.of(AnimalHouseType.BIG_COOP, AnimalHouseType.COOP, AnimalHouseType.DELUXE_COOP)),
    PIG(16000, List.of(AnimalProductType.TRUFFLE), List.of(AnimalHouseType.DELUXE_BARN)),
    RABBIT(8000, List.of(AnimalProductType.RABBIT_WOOL, AnimalProductType.RABBIT_LEG), List.of(AnimalHouseType.DELUXE_COOP)),
    SHEEP(8000, List.of(AnimalProductType.SHEEP_WOOL), List.of(AnimalHouseType.DELUXE_BARN));

    private int price;
    private List<AnimalProductType> products;
    private List<AnimalHouseType> houses;

    AnimalType(int price, List<AnimalProductType> products, List<AnimalHouseType> houses) {
        this.price = price;
        this.products = products;
        this.houses = houses;
    }

    public List<AnimalProductType> getProducts() {
        return products;
    }

    public AnimalProductType getNormalProduct() {
        return products.get(0);
    }

    public AnimalProductType getWealthyProduct() {
        if (products.size() > 1) {
            return products.get(1);
        } else return products.get(0);
    }

    public AnimalHouseType getMainHouseType() {
        return houses.stream()
                .map(AnimalHouseType::getBaseType)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(AnimalHouseType.COOP);
    }
}
