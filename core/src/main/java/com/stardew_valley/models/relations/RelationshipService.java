package com.stardew_valley.models.relations;

import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.network.GameClient;

import java.util.LinkedHashMap;
import java.util.Map;

public class RelationshipService {
    private final Character character;
    private final Map<Character, Friendship> friendships;
    private Marriage marriage;
    private final Map<Character, Relationship> relationships;
    private final Map<Character, Trading> tradings;
    private com.stardew_valley.models.data.User partner;

    public RelationshipService(Character character) {
        this.partner = null;
        this.character = character;
        this.friendships = new LinkedHashMap<>();
        this.relationships = new LinkedHashMap<>();
        this.tradings = new LinkedHashMap<>();
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

    public void marry(Player partner) throws GaysMarriageException {
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
        Friendship friendship = new Friendship(character, friend);
        friendships.putIfAbsent(friend, friendship);
        if (!friend.getRelationService().isFriendWith(character)) {
            friend.getRelationService().getFriendships().putIfAbsent(character, friendship);
        }
//        if (friend instanceof Player player)
//            GameClient.getInstance().updateFriendshipRequest(player.getUser().getUsername());
    }

    public Friendship getFriendship(Character friend) {
        if (friendships.containsKey(friend)) return friendships.get(friend);
        addFriend(friend);
        return friendships.get(friend);
    }

    public void addTrader(Character friend) {
        tradings.putIfAbsent(friend, new Trading((Player) character, (Player) friend));
        if (!friend.getRelationService().haveTradeWith(character)) {
            friend.getRelationService().addTrader(character);
        }
    }

    public Trading getTrading(Character friend) {
        if (tradings.containsKey(friend)) return tradings.get(friend);
        addTrader(friend);
        return tradings.get(friend);
    }

    public boolean haveTradeWith(Character trader) {
        return tradings.containsKey(trader);
    }

    public Map<Character, Trading> getTradings() {
        return tradings;
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

    public void setPartner(com.stardew_valley.models.data.User partner) {
        this.partner = partner;
    }

    public com.stardew_valley.models.data.User getPartner() {
        return partner;
    }
}
