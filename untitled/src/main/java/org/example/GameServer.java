package org.example;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
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
    private final List<UserInfo> signedInUsers = new ArrayList<>();
    private final Map<String, Map<String, File>> radioFiles = new HashMap<>();
    private final Map<String, List<Connection>> channelMembers = new HashMap<>();
    private final Map<String, Thread> sendingThreads = new HashMap<>();
    private final UserRepository userRepository = new UserRepository();

    public GameServer() throws IOException {
        server = new Server(65536000, 65536000);
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
                    } else if (object instanceof Network.RequestAddSignedInUser req) {
                        handleAddUserToSignedInUsers(req);
                    } else if (object instanceof Network.RequestCheckToLogin req) {
                        System.out.println("requestCheckToLogin in server");
                        handleCheckToLoginRequest(connection, req);
                    } else if (object instanceof Network.CTSReaction reaction) {
                        handleReaction(connection, reaction);
                    } else if (object instanceof Network.RequestStartGroupQuest req) {
                        handleStartQuest(req);
                    } else if (object instanceof Network.RequestAddAmount req) {
                        handleAddAmount(req);
                    } else if (object instanceof Network.UploadAudioRequest req) {
                        try {
                            handleUpload(req);
                            //sendSavedFile(req.hostPlayer, req.fileName);
//                            channelMembers.put(req.hostPlayer, new ArrayList<>());
//                            if (!channelMembers.get(req.hostPlayer).contains(connection)) {
//                                channelMembers.get(req.hostPlayer).add(connection);
//                            }
                        } catch (IOException e) {
                            System.out.println("Upload failed: " + e.getMessage());
                            connection.sendTCP("Upload failed: " + req.fileName);
                        }
                    } else if (object instanceof Network.RequestRadioFiles req) {
                            Map<String, File> userFiles = radioFiles.getOrDefault(req.hostPlayer, new HashMap<>());
                            Network.RadioFilesList response = new Network.RadioFilesList();
                            response.hostPlayer = req.hostPlayer;
                            response.fileNames = userFiles.keySet().toArray(new String[0]);

                            connection.sendTCP(response);
                            System.out.println("Sent radio file list to client for user: " + req.hostPlayer);
                    } else if (object instanceof Network.ChangeAudio req) {
                        channelMembers.putIfAbsent(req.hostPlayer, new ArrayList<>());
                        List<Connection> members = channelMembers.get(req.hostPlayer);
                        if (!members.contains(connection)) {
                            members.add(connection);
                        }
                        sendSavedFile(req.hostPlayer, req.fileName);
                    } else if (object instanceof Network.JoinRadioRequest req) {
                        channelMembers.values().forEach(list -> list.remove(connection));
                        channelMembers.computeIfAbsent(req.targetUsername, k -> new ArrayList<>()).add(connection);
                    } else if (object instanceof Network.NPCPosition position) {
                        System.out.println("333");
                        Network.NPCPositionResponse resp = new Network.NPCPositionResponse();
                        resp.adminPlayer = position.adminPlayer;
                        resp.x = position.x;
                        resp.y = position.y;
                        for (Lobby lobby : lobbies.values()) {
                            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                                for (int id : lobby.getPlayerConnectionIds()) {
                                    server.sendToTCP(id, resp);
                                    System.out.println("444");
                                }
                            }
                        }
                    } else if (object instanceof Network.SetObjectRequest req) {
                        Network.SetObjectResponse res = new Network.SetObjectResponse();
                        res.x = req.x;
                        res.y = req.y;
                        res.object = req.object;
                        for (Lobby lobby : lobbies.values()) {
                            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                                for (int id : lobby.getPlayerConnectionIds()) {
                                    if (id != connection.getID()) {
                                        server.sendToTCP(id, res);
                                        System.out.println("setObjectRequest in server");
                                    }
                                }
                            }
                        }
                    } else if (object instanceof Network.SetTileTypeRequest req) {
                    Network.SetTileTypeResponse res = new Network.SetTileTypeResponse();
                    res.x = req.x;
                    res.y = req.y;
                    res.typeNum = req.typeNum;
                    for (Lobby lobby : lobbies.values()) {
                        if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                            for (int id : lobby.getPlayerConnectionIds()) {
                                if (id != connection.getID()) {
                                    server.sendToTCP(id, res);
                                }
                            }
                        }
                    }
                } else if (object instanceof Network.SetTileMovableRequest req) {
                        Network.SetTileMovableResponse res = new Network.SetTileMovableResponse();
                        res.x = req.x;
                        res.y = req.y;
                        res.movable = req.movable;
                        for (Lobby lobby : lobbies.values()) {
                            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                                for (int id : lobby.getPlayerConnectionIds()) {
                                    if (id != connection.getID()) {
                                        server.sendToTCP(id, res);
                                    }
                                }
                            }
                        }
                    }

                    else if (object instanceof Network.SetTilePlowedRequest req) {
                        Network.SetTilePlowedResponse res = new Network.SetTilePlowedResponse();
                        res.x = req.x;
                        res.y = req.y;
                        res.plowed = req.plowed;
                        for (Lobby lobby : lobbies.values()) {
                            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                                for (int id : lobby.getPlayerConnectionIds()) {
                                    if (id != connection.getID()) {
                                        server.sendToTCP(id, res);
                                    }
                                }
                            }
                        }
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
        String userJson = msg.json;
        connectionUsers.put(connection.getID(), userJson);

        JsonObject json = JsonParser.parseString(userJson).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        String nickname = json.get("nickname").getAsString();
        String email = json.get("email").getAsString();
        String gender = json.get("gender").getAsString();

        usernameToIdMap.put(username, connection.getID());
        System.out.println("User info received: " + userJson);

        if (!userRepository.checkUserExists(username)) {
            userRepository.registerUserFull(username, password, nickname, email, gender);
            System.out.println("User saved to database: " + username);
        } else {
            System.out.println("User already exists: " + username);
        }
        userRepository.printAllUsers();
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
                        //System.out.println("Relayed PlayerStatus from "
//                            + playerStatus.username + " to connection " + id);
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

    private void addUserToSignedInUsers(String username, String password, String securityQuestionType, String securityQuestionAnswer) {
        UserInfo userInfo = new UserInfo(username, password, securityQuestionType, securityQuestionAnswer);
        if (!signedInUsers.contains(userInfo)) {
            signedInUsers.add(userInfo);
        }
    }

    private boolean isInSignedInUser(String username, String password) {
        return signedInUsers.stream()
            .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    private void handleAddUserToSignedInUsers(Network.RequestAddSignedInUser req) {
        String username = req.username;
        String password = req.password;
        String securityQuestionType = req.securityQuestionType;
        String securityQuestionAnswer = req.securityQuestionAnswer;
        addUserToSignedInUsers(username, password, securityQuestionType, securityQuestionAnswer);
    }

    private void handleCheckToLoginRequest(Connection connection, Network.RequestCheckToLogin req) {
        Network.ResponseCheckToLogin resp = new Network.ResponseCheckToLogin();
        resp.canLogin = isInSignedInUser(req.username, req.password);
        resp.username = req.username;
        resp.password = req.password;
        connection.sendTCP(resp);
    }

    private void handleReaction(Connection connection, Network.CTSReaction reaction) {
        Network.STCReaction reactionAnswer = new Network.STCReaction();
        reactionAnswer.username = reaction.username;
        reactionAnswer.isText = reaction.isText;
        reactionAnswer.text = reaction.text;
        reactionAnswer.reactionNum = reaction.reactionNum;
        for (Lobby lobby : lobbies.values()) {
            if (lobby.getPlayerConnectionIds().contains(connection.getID())) {
                for (int id : lobby.getPlayerConnectionIds()) {
                    if (id != connection.getID()) {
                        server.sendToTCP(id, reactionAnswer);
                    }
                }
                break;
            }
        }
    }

    private void handleStartQuest(Network.RequestStartGroupQuest request) {
        Lobby lobby = lobbies.get(request.lobbyId);
        Network.ResponseStartGroupQuest resp = new Network.ResponseStartGroupQuest();
        resp.questName = request.questName;
        resp.username = request.username;

        for (int id : lobby.getPlayerConnectionIds()) {
            server.sendToTCP(id, resp);
        }
    }

    private void handleAddAmount(Network.RequestAddAmount req) {
        Lobby lobby = lobbies.get(req.lobbyId);
        Network.ResponseAddAmount resp = new Network.ResponseAddAmount();
        resp.questName = req.questName;
        resp.amount = req.amount;
        resp.username = req.username;
        for (int id : lobby.getPlayerConnectionIds()) {
            server.sendToTCP(id, resp);
        }
    }

    private void handleUpload(Network.UploadAudioRequest req) throws IOException {
        File hostDir = new File("radio_uploads/" + req.hostPlayer);
        if (!hostDir.exists()) hostDir.mkdirs();

        File savedFile = new File(hostDir, req.fileName);
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(req.fileData);
        }

        radioFiles.computeIfAbsent(req.hostPlayer, k -> new HashMap<>())
            .put(req.fileName, savedFile);

        System.out.println("Received file " + req.fileName + " from host " + req.hostPlayer);
    }


    private void sendSavedFile(String hostPlayer, String fileName) {
        File file = radioFiles.getOrDefault(hostPlayer, new HashMap<>()).get(fileName);
        if (file == null || !file.exists()) {
            System.out.println("File not found for host " + hostPlayer + ": " + fileName);
            return;
        }

        Thread previousThread = sendingThreads.get(hostPlayer);
        if (previousThread != null && previousThread.isAlive()) {
            previousThread.interrupt();
            System.out.println("Stopped previous file for host " + hostPlayer);
        }

        Thread thread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(file)) {
                int sampleRate = 44100;
                int channels = 2;
                int bytesPerSample = 2;
                int chunkDurationMs = 100;
                int bytesPerChunk = (int)(sampleRate * channels * bytesPerSample * (chunkDurationMs / 1000.0));

                byte[] buffer = new byte[bytesPerChunk];
                int seq = 0;
                long startTime = System.currentTimeMillis();
                int read;

                while ((read = fis.read(buffer)) != -1) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Sending file interrupted: " + fileName + " for host " + hostPlayer);
                        return;
                    }

                    Network.AudioChunk chunk = new Network.AudioChunk();
                    chunk.hostPlayer = hostPlayer;
                    chunk.seq = seq;
                    chunk.data = Arrays.copyOf(buffer, read);
                    chunk.isLast = (fis.available() == 0);
                    chunk.fileName = fileName;

                    broadcastChunk(hostPlayer, chunk);

                    long expectedTime = startTime + (long) seq * chunkDurationMs;
                    seq++;
                    long now = System.currentTimeMillis();
                    long sleepTime = expectedTime - now;
                    if (sleepTime > 0) Thread.sleep(sleepTime);
                }

                System.out.println("Finished sending file " + fileName + " for host " + hostPlayer);
            } catch (InterruptedException e) {
                System.out.println("Sending file interrupted: " + fileName + " for host " + hostPlayer);
            } catch (Exception e) {
                System.out.println("Error sending file " + fileName + " for host " + hostPlayer);
                System.out.println("Error: " + e);
            }
        });

        sendingThreads.put(hostPlayer, thread);
        thread.start();
    }

    private void broadcastChunk(String hostPlayer, Network.AudioChunk chunk) {
        List<Connection> members = channelMembers.getOrDefault(hostPlayer, new ArrayList<>());
        for (Connection c : members) {
            c.sendTCP(chunk);
        }
    }






}
