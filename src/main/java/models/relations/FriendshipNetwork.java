package models.relations;

import models.character.Character;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FriendshipNetwork {
    private final Set<Character> people;

    public FriendshipNetwork() {
        this.people = new LinkedHashSet<>();
    }

    public void addPerson(Character person) {
        people.add(person);
    }

    public Set<Character> getPeople() {
        return people;
    }
}