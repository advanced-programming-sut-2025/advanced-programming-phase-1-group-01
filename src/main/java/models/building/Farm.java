package models.building;

import models.Position;
import models.animal.*;
import models.character.player.Player;
import models.dateTime.Season;
import models.initializer.CreateShelter;
import models.weather.Weather;
import models.farming.Plant;


import java.util.*;
import java.util.stream.Collectors;

public class Farm extends Maps {
    private Lake lake;
    private Greenhouse greenhouse;
    private Cottage cottage;
    private Quarry quarry;
    private final List<AnimalHouse> shelters;
    private final List<Animal> animals;

    public Farm(List<List<Tile>> tiles, Lake lake, Cottage cottage, Quarry quarry, Greenhouse greenhouse) {
        super(tiles);
        this.lake = lake;
        this.cottage = cottage;
        this.quarry = quarry;
        this.greenhouse = greenhouse;
        shelters = new ArrayList<>();
        animals = new ArrayList<>();
    }

    public Lake getLake() {
        return lake;
    }

    public void setLake(Lake lake) {
        this.lake = lake;
    }

    public Greenhouse getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Quarry getQuarry() {
        return quarry;
    }

    public void setQuarry(Quarry quarry) {
        this.quarry = quarry;
    }

    public List<Animal> getAnimals() { return animals; }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addShelter(AnimalHouse shelter) {
        shelters.add(shelter);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void removeShelter(AnimalHouse shelter) {
        shelters.remove(shelter);
    }

    public Map<Animal, Integer> getBarnAnimals() {
        return animals.stream()
                .filter(animal -> animal.getAnimalInfo().getMainHouseType() == AnimalHouseType.BARN)
                .collect(Collectors.toMap(
                        animal -> animal,
                        animal -> 1
                ));
    }

    public Map<Animal, Integer> getCoopAnimals() {
        return animals.stream()
                .filter(animal -> animal.getAnimalInfo().getMainHouseType() == AnimalHouseType.COOP)
                .collect(Collectors.toMap(
                        animal -> animal,
                        animal -> 1
                ));
    }

    public AnimalHouse findEmptyShelter(AnimalInfo animalInfo) {
        Optional<AnimalHouse> preferred = shelters.stream()
                .filter(shelter -> {
                    AnimalInfo shelterAnimalInfo = shelter.getAnimalInfo();
                    return shelterAnimalInfo != null
                            && shelterAnimalInfo.equals(animalInfo)
                            && shelter.hasEmptySpace();
                })
                .findFirst();

        return preferred.orElseGet(() -> shelters.stream()
                .filter(shelter -> shelter.getAnimalInfo() == null)
                .findFirst()
                .orElse(null));

    }


    public void addAnimalToShelter(Animal animal) {
        AnimalInfo animalInfo = animal.getAnimalInfo();

        Optional<AnimalHouse> matchingShelter = shelters.stream()
                .filter(shelter -> animalInfo.equals(shelter.getAnimalInfo()))
                .filter(AnimalHouse::hasEmptySpace)
                .findFirst();

        if (matchingShelter.isPresent()) {
            matchingShelter.get().addAnimal(animal);
            return;
        }

        Optional<AnimalHouse> emptyShelter = shelters.stream()
                .filter(shelter -> shelter.getAnimalInfo() == null)
                .findFirst();

        if (emptyShelter.isPresent()) {
            AnimalHouse shelter = emptyShelter.get();
            shelter.setAnimalType(animalInfo);
            shelter.addAnimal(animal);
        }
    }

    public String getFormattedProducts() {
        HashMap<Animal, Integer> products = new HashMap<>();
        for (Animal animal : animals) {
            if (animal.hasAnyProduct()) {
                products.put(animal, products.getOrDefault(animal, 0) + 1);
            }
        }
        StringBuilder productsBuilder = new StringBuilder();
        for (Animal animal : products.keySet()) {
            productsBuilder.append(animal.getAnimalInfo().name().toLowerCase().replace("_", " ")).append(": ").append(products.get(animal)).append("\n");
        }
        return productsBuilder.toString();
    }

    public List<AnimalProduct> collectProductsByType(AnimalProductType desiredType) {
        Map<ProductQuality, AnimalProduct> productMap = new HashMap<>();

        for (Animal animal : animals) {
            if (animal.hasAnyProduct() && animal.getAnimalProductType() == desiredType) {
                AnimalProductType type = animal.getAnimalProductType();
                ProductQuality quality = animal.getAnimalProductQuality();

                productMap.merge(
                        quality,
                        new AnimalProduct(type, quality, 1),
                        (existingProduct, newProduct) -> {
                            existingProduct.increaseAmount(1);
                            return existingProduct;
                        }
                );
            }
        }

        return new ArrayList<>(productMap.values());
    }

    public void addCollectedProductsToInventory(List<AnimalProduct> products) {
        for (AnimalProduct product : products) {
            //add to inventory
            return;
        }
    }


//    public String printMap(int x, int y, int size) {
//        if (x + size > tiles.size() || y + size > tiles.get(x).size()) {
//            return "invalid map";
//        }
//        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                Tile tile = tiles.get(i + x).get(j + y);
//                if (tile.getObject() == null) {
//                    output.append(tile.getType().getSymbol());
//                } else {
//                    output.append(tile.getObject().getSymbol());
//                }
//            }
//            output.append("\n");
//        }
//        return output.toString();
//    }

    private AnimalHouseType stringToAnimalHouseType(String animalType) {
        return switch (animalType) {
            case "coop" -> AnimalHouseType.COOP;
            case "big_coop" -> AnimalHouseType.BIG_COOP;
            case "deluxe_coop" -> AnimalHouseType.DELUXE_COOP;
            case "barn" -> AnimalHouseType.BARN;
            case "big_barn" -> AnimalHouseType.BIG_BARN;
            case "deluxe_barn" -> AnimalHouseType.DELUXE_BARN;
            default -> null;
        };
    }

    public String buildShelter(Position position, String houseType) {
        AnimalHouseType animalHouseType = stringToAnimalHouseType(houseType);

        if (animalHouseType == null) return "Invalid animal house";
        if (!CreateShelter.isEmptyPlace(tiles, position.x(), position.y(), animalHouseType.getSize().getHeight())) {
            return "Invalid shelter position";
        }

        shelters.add(CreateShelter.createShelter(position, this, animalHouseType));

        return "Shelter created";
    }

    public boolean isAnimalNameUnique(String animalName) {
        for (Animal animal : animals) {
            if (animal.getAnimalName().equals(animalName)) return false;
        }
        return true;
    }

    public Animal fineAnimalByName(String animalName) {
        for (Animal animal : animals) {
            if (animal.getAnimalName().equals(animalName)) return animal;
        }
        return null;
    }

    public String moveAnimal(String animalName, Position position) {
        Animal animal = fineAnimalByName(animalName);
        if (animal == null) return "Invalid animal name";
        Tile tile = tiles.get(position.x()).get(position.y());
        if (tile == null) return "Invalid position";
        if (tile.getType() != TileType.GROUND || tile.getObject() != null) return "Invalid tile";
        animal.moveAnimal(position);
        return "Animal moved";
    }

    public String feedByHay(String animalName, Player player) {
        Animal animal = fineAnimalByName(animalName);
        if (animal == null) return "Invalid animal name";
        if (!animal.getIsHungry()) return "Animal is not hungry";
        if (player.getInventory().getSlot("hay") == null) return "You have no hay";
        player.getInventory().getSlot("hay").removeQuantity(1);
        animal.feedByHay();
        return "Animal feed by hay";
    }

    public String collectProduct(String animalName, Player player, Season season) {
        Animal animal = fineAnimalByName(animalName);
        if (animal == null) return "Invalid animal name";
        if (!animal.hasAnyProduct()) return "No produce for this animal";
        if (animal.getAnimalInfo() == AnimalInfo.COW || animal.getAnimalInfo() == AnimalInfo.GOAT) {
            if (player.getInventory().getSlot("milkPail") == null) return "You have no milk pail";
        } else if (animal.getAnimalInfo() == AnimalInfo.SHEEP) {
            if (player.getInventory().getSlot("scissors") == null) return "You have no scissors";
        } else if (animal.getAnimalInfo() == AnimalInfo.PIG) {
            if (season == Season.WINTER) return "Pigs don't produce in winter";
        }
        animal.collectProduct();
        return "Product collected";
    }

    public String sellAnimal(String animalName, Player player) {
        Animal animal = fineAnimalByName(animalName);
        if (animal == null) return "Invalid animal name";
        int income = animal.calculateSellPrice();
        player.increase(income);
        sellAnimal(animal);
        return "Animal is sold";
    }

    private void sellAnimal(Animal animal) {
        animal.getShelter().removeAnimal(animal);
        removeAnimal(animal);
    }

    public String friendshipCheatCode(String animalName, int amount) {
        Animal animal = fineAnimalByName(animalName);
        if (animal == null) return "Invalid animal name";
        animal.advanceFriendshipLevel(amount);
        return "Friendship cheated";
    }
    public Map<Plant, Tile> getPlantsToTilesMap() {
        Map<Plant, Tile> map = new LinkedHashMap<>();
        for (List<Tile> row : tiles) {
            for (Tile tile : row) {
                if (tile.getObject() instanceof Plant plant) map.put(plant, tile);
            }
        }
        return map;
    }
}