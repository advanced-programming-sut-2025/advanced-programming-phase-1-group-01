package com.stardew_valley.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    public static final int PORT = 54555;

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

        kryo.register(JsonMessage.class);
        kryo.register(InitStatusRequest.class);
        kryo.register(InitStatusResponse.class);
        kryo.register(CreateLobbyRequest.class);
        kryo.register(CreateLobbyResponse.class);
        kryo.register(JoinLobbyRequest.class);
        kryo.register(JoinLobbyResponse.class);
        kryo.register(RequestLobbyList.class);
        kryo.register(LobbyInfo.class);
        kryo.register(LobbyListResponse.class);
        kryo.register(String.class);
        kryo.register(LobbyInfo[].class);
        kryo.register(String[].class);
        kryo.register(StartGameRequest.class);


    }

    public static class JsonMessage {
        public String type;
        public String json;
    }

    public static class InitStatusRequest {
    }

    public static class InitStatusResponse {
        public boolean isInitialized;
    }

    public static class CreateLobbyRequest {
        public String name;
        public boolean isPrivate;
        public String password;
        public boolean isVisible;
        public String admin;
    }

    public static class CreateLobbyResponse {
        public boolean success;
        public String message;
        public int lobbyId;
    }

    public static class JoinLobbyRequest {
        public int lobbyId;
        public String password;
    }

    public static class JoinLobbyResponse {
        public boolean success;
        public String message;
        public int lobbyId;
    }

    public static class RequestLobbyList {

    }

    public static class LobbyInfo {
        public int id;
        public String name;
        public boolean isPrivate;
        public boolean isVisible;
        public int playerCount;
        public String[] playerNames;
    }

    public static class LobbyListResponse {
        public LobbyInfo[] lobbies;
    }

    public static class StartGameRequest {}
}

