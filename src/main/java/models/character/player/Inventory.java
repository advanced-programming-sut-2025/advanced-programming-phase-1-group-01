package models.character.player;

import models.farming.Crop;
import models.tool.Tool;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Player player;
    private Map<String, Crop> crops;
    private Map<String, Tool> tools;
    private Tool equippedTool;

    public Inventory(Player player) {
        this.player = player;
        this.crops = new HashMap<>();
        this.tools = new HashMap<String, Tool>();
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, Crop> getCrops() {
        return crops;
    }

    public Map<String, Tool> getTools() {
        return tools;
    }

    public Tool getEquippedTool() {
        return equippedTool;
    }

    public void setEquippedTool(Tool equippedTool) {
        this.equippedTool = equippedTool;
    }
}
