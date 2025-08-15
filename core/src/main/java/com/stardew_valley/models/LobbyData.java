package com.stardew_valley.models;



//import com.stardew_valley.models.data.User;

//import com.stardew_valley.models.character.player.User;

import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.GroupQuestType;

import java.util.ArrayList;
import java.util.List;

public class LobbyData {

    private final String name;
    private final List<User> players = new ArrayList<>();
    private final boolean isPrivate;
    private final boolean isVisible;
    private final String password;
    private final int id;
    private User admin;
    private final int maxPlayers = 8;
    private final long createdTime;
    private List<GroupQuest> groupQuestList = new ArrayList<>();

    public boolean isThatOne;

    public LobbyData(String name, boolean isPrivate, boolean isVisible, String password, int id, User admin) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.isVisible = isVisible;
        this.password = password;
        this.id = id;
        this.admin = admin;
        this.players.add(admin);
        this.createdTime = System.currentTimeMillis();
        createAllGroupQuests();
        System.out.println("LobbyData created000000000000000000000000000000");
    }

    public String getName() { return name; }
    public List<User> getPlayers() { return players; }
    public boolean isPrivate() { return isPrivate; }
    public boolean isVisible() { return isVisible; }
    public String getPassword() { return password; }
    public int getId() { return id; }
    public User getAdmin() { return admin; }
    public int getMaxPlayers() { return maxPlayers; }
    public long getCreatedTime() { return createdTime; }

    public boolean addUser(User user) {
        if (players.size() >= maxPlayers) return false;
        if (!players.contains(user)) {
            players.add(user);
            return true;
        }
        return false;
    }

    public boolean removePlayer(User player) {
        boolean removed = players.remove(player);
        if (removed && player.equals(admin) && !players.isEmpty()) {
            admin = players.get(0);
        }
        return removed;
    }

    public boolean canStartGame() {
        return players.size() >= 2 && admin != null;
    }

    public boolean isFull() {
        return players.size() >= maxPlayers;
    }

    public boolean checkPassword(String input) {
        if (!isPrivate) return true;
        System.out.println(password);
        System.out.println(input);
        return password != null && password.equals(input);
    }

    public List<Player> getPlayersReadyToPlay() {
        List<Player> ready = new ArrayList<>();
        for (User player : players) {
            ready.add(player.getPlayer());
        }
        return ready;
    }

    public static LobbyData findLobbyByUsername(List<LobbyData> lobbies, String username) {
        for (LobbyData lobby : lobbies) {
            for (User player : lobby.getPlayers()) {
                if (player.getUsername().equals(username)) {
                    return lobby;
                }
            }
        }
        return null;
    }

    private void createAllGroupQuests() {
        List<GroupQuest> quests = new ArrayList<>();
        for (GroupQuestType type : GroupQuestType.values()) {
            quests.add(new GroupQuest(type));
        }
        groupQuestList = quests;
    }

    public List<GroupQuest> getGroupQuestList() {
        return groupQuestList;
    }

}
