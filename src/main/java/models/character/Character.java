package models.character;

import java.util.Map;

public class Character {
    private java.util.Map<Character, Integer> relationships;

    public Map<Character, Integer> getRelationships() {
        return relationships;
    }

    public void addRelationship(Character character, int level) {
        relationships.put(character, level);
    }
}