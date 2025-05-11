package models.animal;

import models.Position;
import models.Size;
import models.building.Building;

import java.util.ArrayList;

public class AnimalHouse extends Building {
    private AnimalHouseType houseType;
    private AnimalType animalType;
    private int capacity;
    private final ArrayList<Animal> animals;

    public AnimalHouse(AnimalHouseType houseType, Position position) {
        super(position, new Size(houseType.getSize().getWidth(), houseType.getSize().getHeight()));
        this.houseType = houseType;
        this.capacity = houseType.getCapacity();
        this.animals = new ArrayList<>();
    }

    public boolean hasEmptySpace() {
        return capacity > 0;
    }

    public void reduceCapacity() {
        this.capacity -= 1;
    }

    public void increaseCapacity() {
        this.capacity += 1;
    }

    public void setHouseType(AnimalHouseType houseType) {
        this.houseType = houseType;
    }

    public AnimalHouseType getHouseType() {
        return houseType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
