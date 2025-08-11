package org.example;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private final Server server;
    private final Map<Integer, Lobby> lobbies = new HashMap<>();
    private final Map<Integer, Integer> playerLobbyMap = new HashMap<>();
    private final Map<Integer, String> connectionUsers = new HashMap<>();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public GameServer() throws IOException {
        server = new Server(6553600, 6553600);
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                //System.out.println("players received: " + connectionUsers.size());
                for (Integer connectionId : connectionUsers.keySet()) {
                    System.out.println("User: " + connectionUsers.get(connectionId));
                }
                if (object instanceof Network.CreateLobbyRequest req) {
                    Lobby lobby = new Lobby(req.id, req.name, req.isPrivate, req.password, req.isVisible, connection.getID());
                    lobbies.put(req.id, lobby);
                    scheduler.schedule(() -> {
                        if (lobby.getPlayerConnectionIds().size() <= 1) {
                            if (lobbies.containsValue(lobby)) {
                                lobbies.values().remove(lobby);
                            }
                            System.out.println("Lobby removed due to inactivity: " + lobby.getName());
                        }
                    }, 5, TimeUnit.MINUTES);
                    Network.CreateLobbyResponse resp = new Network.CreateLobbyResponse();
                    resp.success = true;
                    resp.message = "Lobby created successfully";
                    resp.lobbyId = req.id;
                    playerLobbyMap.put(connection.getID(), req.id);
                    connection.sendTCP(resp);
                } else if (object instanceof Network.JoinLobbyRequest req) {
                    Lobby lobby = lobbies.get(req.lobbyId);
                    Network.JoinLobbyResponse resp = new Network.JoinLobbyResponse();
                    System.out.println("lobby: test joined");
                    if (lobby == null) {
                        resp.success = false;
                        resp.message = "Lobby not found";
                    } else if (lobby.isPrivate() && !lobby.getPassword().equals(req.password)) {
                        resp.success = false;
                        resp.message = "Wrong password";
                    } else {
                        playerLobbyMap.put(connection.getID(), req.lobbyId);
                        lobbies.get(req.lobbyId).addPlayer(connection.getID(), connectionUsers.get(connection.getID()));
                        resp.success = true;
                        resp.message = connectionUsers.get(connection.getID());
                        resp.lobbyId = req.lobbyId;
                        connection.sendTCP(resp);
                    }
                    connection.sendTCP(resp);
                } else if (object instanceof Network.RequestLobbyList) {
                    System.out.println("request lobby received");
                    sendLobbyListToClient(connection);
                } else if (object instanceof Network.JsonMessage msg && "userInfo".equals(msg.type)) {
                    String user = msg.json;
                    connectionUsers.put(connection.getID(), user);
                    System.out.println("User info received: " + user);
                } else if (object instanceof Network.GameStart gameStart) {
                    Lobby lobby = lobbies.get(gameStart.lobbyId);
                    for (int id : lobby.getPlayerConnectionIds()) {
                        server.sendToTCP(id, new Network.StartGameRequest());
                    }
                } else if (object instanceof Network.PlayerStatus playerStatus) {
                    for (Lobby lobby : lobbies.values()) {
                        if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                            for (int id : lobby.getPlayerConnectionIds()) {
                                System.out.println("Player " + id + " is In game lobby");
                                if (id != connection.getID()) {
                                    server.sendToTCP(id, playerStatus);
                                    System.out.println("Relayed PlayerStatus from "
                                            + playerStatus.username + " to connection " + id);
                                }
                            }
                        }
                    }
                } else if (object instanceof Network.LeaveLobbyRequest req) {
                    Lobby lobby = lobbies.get(req.lobbyId);
                    Network.LeaveLobbyResponse resp = new Network.LeaveLobbyResponse();

                    if (lobby == null) {
                        resp.success = false;
                        resp.message = "Lobby not found";
                    } else {
                        Integer playerId = connection.getID();
                        Integer currentLobbyId = playerLobbyMap.get(playerId);

                        System.out.println(currentLobbyId != null ? currentLobbyId.toString() : "null");
                        if (currentLobbyId != null && currentLobbyId == req.lobbyId) {
                            playerLobbyMap.remove(playerId);
                            lobby.removePlayer(playerId);
                            if (lobby.getPlayerConnectionIds().isEmpty()) {
                                lobbies.remove(req.lobbyId);
                            }
                            resp.success = true;
                            resp.message = "Left lobby successfully";
                            resp.lobbyId = req.lobbyId;
                        } else {
                            resp.success = false;
                            resp.message = "Player not in the specified lobby";
                        }
                    }

                    connection.sendTCP(resp);
                } else if (object instanceof Network.DeleteLobbyRequest req) {
                    Network.DeleteLobbyResponse resp = new Network.DeleteLobbyResponse();

                    Lobby lobby = lobbies.get(req.lobbyId);
                    if (lobby == null) {
                        resp.success = false;
                        resp.message = "Lobby not found";
                    } else {
                        lobbies.remove(req.lobbyId);

                        resp.success = true;
                        resp.message = "Lobby deleted successfully";
                        resp.lobbyId = req.lobbyId;
                    }
                    connection.sendTCP(resp);
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
                    //System.out.println("User: " + connectionUsers.get(connectionId));
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
            System.out.println("Lobby: " + playerJsons.size());

            Network.LobbyInfo info = new Network.LobbyInfo();
            info.id = lobby.getId();
            info.name = lobby.getName();
            info.isPrivate = lobby.isPrivate();
            info.isVisible = lobby.isVisible();
            info.password = lobby.getPassword();
            info.playerNames = playerJsons.toArray(new String[0]);

            lobbyInfos.add(info);
        }

        Network.LobbyListResponse response = new Network.LobbyListResponse();
        response.lobbies = lobbyInfos.toArray(new Network.LobbyInfo[0]);
        connection.sendTCP(response);
    }

}

