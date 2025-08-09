package org.example;

import java.util.*;

public class Lobby {
    private final int id;
    private final String name;
    private final boolean isPrivate;
    private final String password;
    private final boolean isVisible;
    private boolean isInitialized = false;
    private String mapJson = null;

    final Map<Integer, String> userJsons = new HashMap<>();
    private final List<Integer> playerConnectionIds = new ArrayList<>();

    public Lobby(int id, String name, boolean isPrivate, String password, boolean isVisible) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
        this.password = password;
        this.isVisible = isVisible;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isPrivate() { return isPrivate; }
    public String getPassword() { return password; }
    public boolean isVisible() { return isVisible; }
    public boolean isInitialized() { return isInitialized; }
    public void setInitialized(boolean initialized) { isInitialized = initialized; }
    public String getMapJson() { return mapJson; }
    public void setMapJson(String mapJson) { this.mapJson = mapJson; }

    public synchronized boolean addPlayer(int connectionId, String userJson) {
        int maxPlayers = 4;
        if (playerConnectionIds.size() >= maxPlayers) return false;
        if (!playerConnectionIds.contains(connectionId)) {
            playerConnectionIds.add(connectionId);
            userJsons.put(connectionId, userJson);
            return true;
        }
        return false;
    }

    public synchronized boolean removePlayer(int connectionId) {
        boolean removed = playerConnectionIds.remove((Integer) connectionId);
        if (removed) {
            userJsons.remove(connectionId);
        }
        return removed;
    }

    public synchronized List<Integer> getPlayerConnectionIds() {
        return new ArrayList<>(playerConnectionIds);
    }

    public synchronized String getUserJson(int connectionId) {
        return userJsons.get(connectionId);
    }
}
