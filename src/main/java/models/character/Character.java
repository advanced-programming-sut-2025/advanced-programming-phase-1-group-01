package models.character;


import models.Position;

import java.util.Map;

public class Character {
    protected java.util.Map<Character, Integer> relationships;

    public Map<Character, Integer> getRelationships() {
        return relationships;
    }

    public void addRelationship(Character character, int level) {
        relationships.put(character, level);
    }

    public int getRelationshipLevel(Character character) {return relationships.get(character);}

    public void increaseRelationshipLevel(Character character, int level) {
        relationships.put(character, relationships.get(character) + level);
    }

    public void decreaseRelationshipLevel(Character character, int level) {
        relationships.put(character, relationships.get(character) - level);
    }

    public boolean isNearTo(Position firstPosition, Position secondPosition) {
        return (Math.abs(firstPosition.getX() - secondPosition.getX()) < 2 && Math.abs(firstPosition.getY() - secondPosition.getY()) < 2);
    }

    public void setRelationshipLevel(Character character, int level) {
        relationships.put(character, level);
    }
}