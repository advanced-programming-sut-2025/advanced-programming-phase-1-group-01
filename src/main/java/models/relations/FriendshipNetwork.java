package models.relations;

import models.character.Character;

import java.util.HashSet;
import java.util.Set;

public class FriendshipNetwork {
    private final Set<Character> people;

    public FriendshipNetwork() {
        this.people = new HashSet<>();
    }

    public void addPerson(Character person) {
        people.add(person);
    }

    public Set<Character> getPeople() {
        return people;
    }

    public static void establishFriendship(Character p1, Character p2, int level) {
        p1.addRelationship(p2, level);
        p2.addRelationship(p1, level);
    }

    public static void increaseFriendshipLevel(Character p1, Character p2, int level) {
        if (p1.getRelationshipLevel(p2) < 1000 + level) p1.increaseRelationshipLevel(p2, level);
        else p1.setRelationshipLevel(p2, 1000);

        if (p2.getRelationshipLevel(p1) < 1000 + level) p2.increaseRelationshipLevel(p1, level);
        else p2.setRelationshipLevel(p1, 1000);
    }

    public static void decreaseFriendshipLevel(Character p1, Character p2, int level) {
        if (p1.getRelationshipLevel(p2) >= level) p1.decreaseRelationshipLevel(p2, level);
        else p1.setRelationshipLevel(p2, 0);

        if (p2.getRelationshipLevel(p1) >= level) p2.decreaseRelationshipLevel(p1, level);
        else p2.setRelationshipLevel(p1, 0);
    }
}