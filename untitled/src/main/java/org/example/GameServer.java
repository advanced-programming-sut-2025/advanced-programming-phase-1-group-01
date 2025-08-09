package org.example;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.*;

public class GameServer {
    private final Server server;
    private final Map<Integer, Lobby> lobbies = new HashMap<>();
    private int lobbyCounter = 1;
    private final Map<Integer, Integer> playerLobbyMap = new HashMap<>();
    private final Map<Integer, String> connectionUsers = new HashMap<>();


    public GameServer() throws IOException {
        server = new Server(6553600, 6553600);
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof Network.CreateLobbyRequest req) {
                    int newId = lobbyCounter++;
                    Lobby lobby = new Lobby(newId, req.name, req.isPrivate, req.password, req.isVisible);
                    lobbies.put(newId, lobby);

                    Network.CreateLobbyResponse resp = new Network.CreateLobbyResponse();
                    resp.success = true;
                    resp.message = "Lobby created successfully";
                    resp.lobbyId = newId;
                    connection.sendTCP(resp);
                } else if (object instanceof Network.JoinLobbyRequest req) {
                    Lobby lobby = lobbies.get(req.lobbyId);
                    Network.JoinLobbyResponse resp = new Network.JoinLobbyResponse();

                    if (lobby == null) {
                        resp.success = false;
                        resp.message = "Lobby not found";
                    } else if (lobby.isPrivate() && !lobby.getPassword().equals(req.password)) {
                        resp.success = false;
                        resp.message = "Wrong password";
                    } else {
                        playerLobbyMap.put(connection.getID(), req.lobbyId);
                        resp.success = true;
                        resp.message = "Joined lobby " + lobby.getName();
                    }
                    connection.sendTCP(resp);
                } else if (object instanceof Network.RequestLobbyList) {
                    sendLobbyListToClient(connection);
                } else if (object instanceof Network.JsonMessage msg && "userInfo".equals(msg.type)) {
                    String user = msg.json;
                    connectionUsers.put(connection.getID(), user);
                    System.out.println("User info received: " + user);
                }


            }



            @Override
            public void disconnected(Connection connection) {
                Integer lobbyId = playerLobbyMap.remove(connection.getID());
                if (lobbyId != null) {
                    Lobby lobby = lobbies.get(lobbyId);
                    if (lobby != null) {
                        lobby.userJsons.remove(connection.getID());
                    }
                }
                System.out.println("Disconnected: " + connection.getID());
            }


            @Override
            public void connected(Connection connection) {
                System.out.println("Connected: "+ connection.getID());
                for (Integer connectionId : connectionUsers.keySet()) {
                    System.out.println("User: " + connectionUsers.get(connectionId));
                }
            }
        });

        server.start();
        server.bind(Network.PORT);

        System.out.println("Server started on port: " + Network.PORT);
    }

    public static void main(String[] args) throws IOException {
        try {
            new GameServer();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sendLobbyListToClient(Connection connection) {
        List<Network.LobbyInfo> lobbyInfos = new ArrayList<>();

        for (Lobby lobby : lobbies.values()) {
            List<String> playerJsons = lobby.getPlayerConnectionIds().stream()
                    .map(connectionUsers::get)
                    .filter(Objects::nonNull)
                    .toList();

            Network.LobbyInfo info = new Network.LobbyInfo();
            info.id = lobby.getId();
            info.name = lobby.getName();
            info.isPrivate = lobby.isPrivate();
            info.isVisible = lobby.isVisible();
            info.playerNames = playerJsons.toArray(new String[0]);

            lobbyInfos.add(info);
        }

        Network.LobbyListResponse response = new Network.LobbyListResponse();
        response.lobbies = lobbyInfos.toArray(new Network.LobbyInfo[0]);
        connection.sendTCP(response);
    }

}

