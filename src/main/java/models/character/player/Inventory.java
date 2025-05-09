package models.character.player;

import models.Equipment;
import models.farming.Crop;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Player player;
    private Map<String, Crop> crops;
    private Map<String, Equipment> equipments;

    public Inventory(Player player) {
        this.player = player;
        this.crops = new HashMap<>();
        this.equipments = new HashMap<>();
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, Crop> getCrops() {
        return crops;
    }

    public Map<String, Equipment> getEquipments() {
        return equipments;
    }
}
