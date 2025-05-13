package models.building;

import models.animal.*;
import models.farming.Plant;

import java.util.*;
import java.util.stream.Collectors;

public class Farm extends Maps {
    private static final int FARM_HEIGHT = 75;
    private static final int FARM_WIDTH = 75;
    private Lake lake;
    private Greenhouse greenhouse;
    private Cottage cottage;
    private Quarry quarry;
    private final List<AnimalHouse> shelters;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Farm(List<List<Tile>> tiles, Lake lake, Cottage cottage, Quarry quarry, Greenhouse greenhouse) {
        super(tiles);
        this.lake = lake;
        this.cottage = cottage;
        this.quarry = quarry;
        this.greenhouse = greenhouse;
        shelters = new ArrayList<>();
        animals = new ArrayList<>();
        plants = new ArrayList<>();
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

    public boolean isEmptyShelter(AnimalInfo animalInfo) {
        return shelters.stream()
                .anyMatch(shelter -> {
                    AnimalInfo shelterAnimalInfo = shelter.getAnimalInfo();
                    return shelterAnimalInfo == null
                            || (shelterAnimalInfo.equals(animalInfo) && shelter.hasEmptySpace());
                });
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }
}