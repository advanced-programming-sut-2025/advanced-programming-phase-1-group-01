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

    public void establishFriendship(Character p1, Character p2, int level) {
        p1.addRelationship(p2, level);
        p2.addRelationship(p1, level);
    }
}