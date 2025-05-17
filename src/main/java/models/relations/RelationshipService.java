package models.relations;

import models.character.Character;
import models.character.player.Player;
import models.data.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class RelationshipService {
    private final Character character;
    private final Map<Character, Friendship> friendships;
    private Marriage marriage;
    private final Map<Character, Relationship> relationships;
    private final Map<Character, Trading> tradingships;
    private User partner;


    public RelationshipService(Character character) {
        this.partner = null;
        this.character = character;
        this.friendships = new LinkedHashMap<>();
        this.relationships = new LinkedHashMap<>();
        this.tradingships = new LinkedHashMap<>();
    }

    public Map<Character, Relationship> getRelationships() {
        return relationships;
    }

    public Relationship getRelationshipWith(Character character) {
        return relationships.get(character);
    }

    public boolean hasRelationshipWith(Character character) {
        return relationships.containsKey(character);
    }

    public void marry(Player partner) {
        if (marriage != null) return;
        marriage = new Marriage((Player) character, partner);
        if (!partner.getRelationService().isMarried()) {
            partner.getRelationService().marry((Player) character);
        }
    }

    public Marriage getMarriage() {
        return marriage;
    }

    public boolean isMarried() {
        return marriage != null && marriage.getHusband() != null && marriage.getWife() != null;
    }

    public void addFriend(Character friend) {
        friendships.putIfAbsent(friend, new Friendship((Player) character, (Player) friend));
        if (!friend.getRelationService().isFriendWith(character)) {
            friend.getRelationService().addFriend(character);
        }
    }

    public Friendship getFriendship(Character friend) {
        if (friendships.containsKey(friend)) return friendships.get(friend);
        addFriend(friend);
        return friendships.get(friend);
    }

    public void addTrader(Character friend) {
        tradingships.putIfAbsent(friend, new Trading((Player) character, (Player) friend));
        if (!friend.getRelationService().haveTradeWith(character)) {
            friend.getRelationService().addTrader(character);
        }
    }

    public Trading getTrading(Character friend) {
        if (tradingships.containsKey(friend)) return tradingships.get(friend);
        addTrader(friend);
        return tradingships.get(friend);
    }

    public boolean haveTradeWith(Character trader) {
        return friendships.containsKey(trader) && tradingships.containsKey(trader);
    }

    public Map<Character, Trading> getTradingships() {
        return tradingships;
    }

    public Map<Character, Friendship> getFriendships() {
        return friendships;
    }

    public boolean isFriendWith(Character friend) {
        return friendships.containsKey(friend);
    }

    public Character getCharacter() {
        return character;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    public User getPartner() {
        return partner;
    }
}