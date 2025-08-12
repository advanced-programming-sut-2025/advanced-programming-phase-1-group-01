package org.example;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    private final Map<String, Integer> usernameToIdMap = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public GameServer() throws IOException {
        server = new Server(6553600, 6553600);
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                try {
                    if (object instanceof Network.CreateLobbyRequest req) {
                        handleCreateLobby(connection, req);
                    } else if (object instanceof Network.JoinLobbyRequest req) {
                        handleJoinLobby(connection, req);
                    } else if (object instanceof Network.RequestLobbyList requestLobbyList) {
                        sendLobbyListToClient(connection, requestLobbyList.isForOnlinePlayersList);
                    } else if (object instanceof Network.JsonMessage msg && "userInfo".equals(msg.type)) {
                        handleUserInfo(connection, msg);
                    } else if (object instanceof Network.GameStart gameStart) {
                        handleGameStart(gameStart);
                    } else if (object instanceof Network.PlayerStatus playerStatus) {
                        handlePlayerStatus(connection, playerStatus);
                    } else if (object instanceof Network.LeaveLobbyRequest req) {
                        handleLeaveLobby(connection, req);
                    } else if (object instanceof Network.DeleteLobbyRequest req) {
                        handleDeleteLobby(connection, req);
                    } else if (object instanceof Network.RequestUsername req) {
                        handleRequestUsername(req);
                    } else if (object instanceof Network.AddFriendRequest req) {
                        handleAddFriend(req);
                    } else if (object instanceof Network.SendMessageEntry req) {
                        handleSendMessage(connection, req);
                    } else if (object instanceof Network.JsonMessage req && "gift".equals(req.type)) {
                        handleGift(req);
                    } else if (object instanceof Network.AddInventoryItem req) {
                        handleAddInventoryItem(connection, req);
                    } else if (object instanceof Network.AddReaction req) {
                        server.sendToAllTCP(req);
                    } else if (object instanceof Network.StartVoting req) {
                        server.sendToAllExceptTCP(connection.getID(), req);
                    } else if (object instanceof Network.Vote req) {
                        server.sendToAllExceptTCP(connection.getID(), req);
                    } else if (object instanceof Network.AddReaction req) {
                    server.sendToAllTCP(req);
                    } else if (object instanceof Network.StartVoting req) {
                        server.sendToAllExceptTCP(connection.getID(), req);
                    } else if (object instanceof Network.Vote req) {
                        server.sendToAllExceptTCP(connection.getID(), req);
                    } else if (object instanceof Network.TradeRequest req) {
                        int receiverId = usernameToIdMap.get(req.receiverUsername);
                        server.sendToTCP(receiverId, req);
                    } else if (object instanceof Network.TradeResponse req) {
                        int receiverId = usernameToIdMap.get(req.receiverUsername);
                        server.sendToTCP(receiverId, req);
                    } else if (object instanceof Network.HugEvent req) {
                        int senderId = usernameToIdMap.get(req.targetUsername);
                        server.sendToTCP(senderId, req);
                    } else if (object instanceof Network.MarriageEvent req) {
                        int senderId = usernameToIdMap.get(req.targetUsername);
                        server.sendToTCP(senderId, req);
                    } else if (object instanceof Network.ResponseMarriageEvent req) {
                        int senderId = usernameToIdMap.get(req.targetUsername);
                        server.sendToTCP(senderId, req);
                    }
                } catch (Exception e) {
                    System.out.println("Error handling message: " + e.getMessage());
                }
            }

            @Override
            public void disconnected(Connection connection) {
                Integer lobbyId = playerLobbyMap.remove(connection.getID());
                if (lobbyId != null) {
                    Lobby lobby = lobbies.get(lobbyId);
                    if (lobby != null) {
                        lobby.removePlayer(connection.getID());
                        if (lobby.getPlayerConnectionIds().isEmpty()) {
                            lobbies.remove(lobbyId);
                            System.out.println("Lobby removed due to empty after disconnect: " + lobby.getName());
                        }
                    }
                }
                connectionUsers.remove(connection.getID());
                System.out.println("Disconnected: " + connection.getID());
            }

            @Override
            public void connected(Connection connection) {
                System.out.println("Connected: " + connection.getID());
                connection.setTimeout(300000);
            }
        });

        server.start();
        server.bind(Network.PORT);

        System.out.println("Server started on port: " + Network.PORT);
    }


    private void handleCreateLobby(Connection connection, Network.CreateLobbyRequest req) {
        Lobby lobby = new Lobby(req.id, req.name, req.isPrivate, req.password, req.isVisible, connection.getID());
        lobbies.put(req.id, lobby);
        playerLobbyMap.put(connection.getID(), req.id);

        scheduler.schedule(() -> {
            if (lobby.getPlayerConnectionIds().size() <= 1) {
                if (lobbies.containsKey(lobby.getId())) {
                    lobbies.remove(lobby.getId());
                    System.out.println("Lobby removed due to inactivity: " + lobby.getName());
                }
            }
        }, 5, TimeUnit.MINUTES);

        Network.CreateLobbyResponse resp = new Network.CreateLobbyResponse();
        resp.success = true;
        resp.message = "Lobby created successfully";
        resp.lobbyId = req.id;
        connection.sendTCP(resp);
    }

    private void handleJoinLobby(Connection connection, Network.JoinLobbyRequest req) {
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
            lobby.addPlayer(connection.getID(), connectionUsers.get(connection.getID()));
            resp.success = true;
            resp.message = connectionUsers.get(connection.getID());
            resp.lobbyId = req.lobbyId;
        }
        connection.sendTCP(resp);
    }

    private void handleUserInfo(Connection connection, Network.JsonMessage msg) {
        String user = msg.json;
        connectionUsers.put(connection.getID(), user);

        JsonObject json = JsonParser.parseString(user).getAsJsonObject();
        String username = json.get("username").getAsString();

        usernameToIdMap.put(username, connection.getID());

        System.out.println("User info received: " + user);
    }

    private void handleGameStart(Network.GameStart gameStart) {
        Lobby lobby = lobbies.get(gameStart.lobbyId);
        if (lobby != null) {
            for (int id : lobby.getPlayerConnectionIds()) {
                server.sendToTCP(id, new Network.StartGameRequest());
            }
        }
    }

    private void handlePlayerStatus(Connection connection, Network.PlayerStatus playerStatus) {
        for (Lobby lobby : lobbies.values()) {
            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                for (int id : lobby.getPlayerConnectionIds()) {
                    if (id != connection.getID()) {
                        server.sendToTCP(id, playerStatus);
                        System.out.println("Relayed PlayerStatus from "
                            + playerStatus.username + " to connection " + id);
                    }
                }
                break;
            }
        }
    }

    private void handleLeaveLobby(Connection connection, Network.LeaveLobbyRequest req) {
        Lobby lobby = lobbies.get(req.lobbyId);
        Network.LeaveLobbyResponse resp = new Network.LeaveLobbyResponse();
        if (lobby == null) {
            resp.success = false;
            resp.message = "Lobby not found";
        } else {
            Integer playerId = connection.getID();
            Integer currentLobbyId = playerLobbyMap.get(playerId);
            if (currentLobbyId != null && currentLobbyId.equals(req.lobbyId)) {
                playerLobbyMap.remove(playerId);
                lobby.removePlayer(playerId);
                if (lobby.getPlayerConnectionIds().isEmpty()) {
                    lobbies.remove(req.lobbyId);
                    System.out.println("Lobby removed after player left: " + lobby.getName());
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
    }

    private void handleDeleteLobby(Connection connection, Network.DeleteLobbyRequest req) {
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

    private void handleRequestUsername(Network.RequestUsername req) {
        Network.ResponseUsername resp = new Network.ResponseUsername();
        resp.message = req.username;
        server.sendToAllTCP(resp);
    }

    private void handleAddFriend(Network.AddFriendRequest req) {
        Network.AddFriendResponse resp = new Network.AddFriendResponse();
        resp.newFriendUsername = req.selfUsername;
        resp.friendshipJson = req.friendshipJson;
        Integer friendId = usernameToIdMap.get(req.friendUsername);
        if (friendId != null) {
            server.sendToTCP(friendId, resp);
        }
    }

    private void handleSendMessage(Connection connection, Network.SendMessageEntry req) {
        if (!"message to all".equals(req.type)) {
            Integer receiverId = usernameToIdMap.get(req.receiverUsername);
            if (receiverId != null) {
                server.sendToTCP(receiverId, req);
            }
        } else {
            server.sendToAllExceptTCP(connection.getID(), req);
        }
    }

    private void handleGift(Network.JsonMessage req) {
        Integer receiverId = usernameToIdMap.get(req.receiver);
        if (receiverId != null) {
            server.sendToTCP(receiverId, req);
        }
    }

    private void handleAddInventoryItem(Connection connection, Network.AddInventoryItem req) {
        Integer userId = usernameToIdMap.get(req.username);
        if (userId != null && connection.getID() != userId) {
            server.sendToTCP(userId, req);
        }
    }


    private void sendLobbyListToClient(Connection connection, boolean isForOnlinePlayersList) {
        List<Network.LobbyInfo> lobbyInfos = new ArrayList<>();
        for (Lobby lobby : lobbies.values()) {
            if (!lobby.isVisible()) continue;

            List<String> playerJsons = lobby.getPlayerConnectionIds().stream()
                .map(connectionUsers::get)
                .filter(Objects::nonNull)
                .toList();

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
        response.isForOnlinePlayersList = isForOnlinePlayersList;
        connection.sendTCP(response);
    }

    public static void main(String[] args) throws IOException {
        try {
            new GameServer();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
