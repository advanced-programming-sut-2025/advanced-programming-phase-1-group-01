
package com.stardew_valley.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Sound;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.controllers.VotingController;
import com.stardew_valley.models.GroupQuest;
import com.stardew_valley.models.LobbyData;
import com.stardew_valley.models.character.player.*;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.MessageEntry;
import com.stardew_valley.models.Voting;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.ReactionType;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.relations.Friendship;
import com.stardew_valley.models.relations.Gift;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.LobbyView;
import com.stardew_valley.views.LoginMenuView;
import com.stardew_valley.views.ReactionView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameClient {
    private final Repository repo = Repository.getRepo();
    private LoginMenuView loginMenuView;
    private static GameClient instance;
    private final Client pClient;
    private int playerId;
    private Map<String, ByteArrayOutputStream> channelBuffers = new HashMap<>();
    private final Map<String, AudioDevice> channelDevices = new HashMap<>();

    public GameClient() {
        pClient = new Client(65536000, 65536000);
        Network.register(pClient);

        pClient.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                playerId = connection.getID();
                System.out.println("Connected with Id: " + playerId);

                Network.JsonMessage userInfoMsg = new Network.JsonMessage();
                userInfoMsg.type = "userInfo";
                userInfoMsg.json = Repository.getRepo().toUserInfoJson();
                pClient.sendTCP(userInfoMsg);
            }

            @Override
            public void received(com.esotericsoftware.kryonet.Connection connection, Object object) {
                //System.out.println(Repository.getRepo().getUsers().size());
                if (object instanceof Network.CreateLobbyResponse resp) {
                    System.out.println("Create Lobby Response: " + resp.message);
                } else if (object instanceof Network.JoinLobbyResponse resp) {
                    System.out.println("Join Lobby Response: " + resp.message);
                    User user = null;
                    user = Repository.fromUserInfoJson(resp.message);
                    Repository.getRepo().addUser(user);
                    System.out.println(LobbyController.getInstance().findLobbyById(resp.lobbyId).addUser(user));
                } else if (object instanceof Network.LobbyListResponse resp) {
                    for (Network.LobbyInfo n : resp.lobbies) {
                        for (String name : n.playerNames) {
                            System.out.println(name + "#");
                        }
                    }
                    LobbyController.getInstance().updateLobbyListFromNetwork(resp.lobbies, resp.isForOnlinePlayersList);
                } else if (object instanceof Network.StartGameRequest) {
                    Gdx.app.postRunnable(() -> {
                        LobbyData lobby = null;
                        lobby = LobbyData.findLobbyByUsername(
                            LobbyController.getInstance().getLobbies(),
                            LobbyController.getInstance().getRepository().getCurrentUser().getUsername()
                        );
                        LobbyView.startGame(lobby);
                    });
                } else if (object instanceof Network.PlayerStatus playerStatus) {
                    try {
                        Player player = LobbyController.getInstance().getRepository().getCurrentGame().getPlayerByUsername(playerStatus.username);
                        if (player != null) {
                            if (!playerStatus.username.equals(Repository.getRepo().getCurrentUser().getUsername())) {
                                player.setX(playerStatus.x);
                                player.setY(playerStatus.y);
                                player.setDirection(Player.numToDirection(playerStatus.direction));
                                player.setStateTime(playerStatus.stateTime);
                                player.setMoving(playerStatus.isWalking);
                                //System.out.println("set " + playerStatus.username);
                                //System.out.println("in " + Repository.getRepo().getCurrentUser().getUsername());
                            } else {
                                //System.out.println("that was yourself");
                            }
                        } else {
                            System.out.println("Warning: player not found for username: " + playerStatus.username);
                        }
                    } catch (Exception ignored) {

                    }
                } else if (object instanceof Network.LeaveLobbyResponse resp) {
                    System.out.println("Leave Lobby Response: " + resp.message);
                    if (resp.success) {
                        //Repository.getRepo().removeUserFromLobby(resp.lobbyId);
                        //LobbyController.getInstance().findLobbyById(resp.lobbyId).removeUser(/* شناسه کاربر فعلی */);
                    }
                } else if (object instanceof Network.DeleteLobbyResponse resp) {
                    System.out.println("Delete Lobby Response: " + resp.message);
                    if (resp.success) {
                        LobbyController.getInstance().deleteLobby(resp.lobbyId);
                    }

                } else if (object instanceof Network.ResponseUsername resp) {
                    System.out.println(resp.message);
//                } else if (object instanceof Network.AddFriendResponse resp) {
//                    String friendUsername = resp.newFriendUsername;
//                    String friendshipJson = resp.friendshipJson;
//
//                    Friendship newFriendship = Friendship.fromJson(friendshipJson);
//
//                    RelationshipService relationshipService = repo.getCurrentUser().getPlayer().getRelationService();
//
//                    Friendship friendship = relationshipService.getFriendship(repo.getUserByUsername(friendUsername).getPlayer());
//
//                    friendship.updateMessages(newFriendship.getMessages());
                } else if (object instanceof Network.SendMessageEntry req && "message".equals(req.type)) {
                    Player friend = repo.getUserByUsername(req.senderUsername).getPlayer();

                    Friendship friendship = repo.getCurrentUser().getPlayer().getRelationService().getFriendship(friend);

                    friendship.getMessages().put(new MessageEntry(friend, req.message), false);
                } else if (object instanceof Network.SendMessageEntry req && "notif".equals(req.type)) {
                    Player player = repo.getCurrentUser().getPlayer();

                    Player sender = repo.getUserByUsername(req.senderUsername).getPlayer();
                    String message = req.message;

                    player.getNotifications().put(new MessageEntry(sender, message), false);
                } else if (object instanceof Network.SendMessageEntry req && "message to all".equals(req.type)) {
                    Player sender = repo.getUserByUsername(req.senderUsername).getPlayer();

                    Friendship.getPublicMessages().add(new MessageEntry(sender, req.message));
                } else if (object instanceof Network.JsonMessage req && "gift".equals(req.type)) {
                    Gift gift = Gift.fromJson(req.json);

                    Player sender = gift.sender();
                    Friendship friendship = repo.getCurrentUser().getPlayer().getRelationService().getFriendship(sender);

                    friendship.getGifts().put(gift.giftNumber(), gift);
                } else if (object instanceof Network.AddInventoryItem req) {
                    Player player = repo.getCurrentUser().getPlayer();

                    Inventory inventory = player.getInventory();

                    req.itemName = req.itemName.toLowerCase().trim();

                    for (Slot slot : inventory.getSlots()) {
                        if (slot.getItem() != null && slot.getItem().getName().trim().equalsIgnoreCase(req.itemName)) {
                            slot.addQuantity(req.amount);
                            return;
                        }
                    }

                    if (inventory.hasCapacity()) {
                        inventory.addSlot(new Slot(inventory, req.itemName, req.amount));
                    }
                } else if (object instanceof Network.AddReaction req) {
                    Player player = repo.getUserByUsername(req.username).getPlayer();
//                    Player.Reaction reaction = Player.Reaction.valueOf(req.reaction.toUpperCase());
//
//                    GameView.setReaction(player, reaction.getReaction());
                } else if (object instanceof Network.StartVoting req) {
                    if (req.type.equals("ban player")) {
                        String username = req.votingUsername;
                        VotingController.startBanPlayerVoting(username);
                    } else if (req.type.equals("force terminate")) {
                        VotingController.startForceTerminateVoting();
                    }
                } else if (object instanceof Network.Vote req) {
                    VotingController.getCurrentVoting().vote(req.voterUsername, Voting.Vote.valueOf(req.vote));
                } else if (object instanceof Network.ResponseCheckToLogin resp) {
                    System.out.println("received response check to login");
                    loginMenuView.getController().login(List.of(resp.username, resp.password, "Yes"), loginMenuView.getMessageLabel());
                } else if (object instanceof Network.STCReaction resp) {
                    Player player = LobbyController.getInstance().getRepository().getCurrentGame().getPlayerByUsername(resp.username);
                    System.out.println(resp.username + " yessssssssssssss");
                    if (resp.isText) {
                        player.setReactionText(resp.text);
                    } else {
                        player.getReactionUI().setStarted(ReactionType.fromId(resp.reactionNum));
                    }
                } else if (object instanceof Network.ResponseStartGroupQuest resp) {
                    System.out.println("received response start group quest");
                    for (LobbyData lobbyData : LobbyController.getInstance().getLobbies()) {
                        if (lobbyData.getPlayers().stream().anyMatch(p -> p.getUsername().equals(Repository.getRepo().getCurrentUser().getUsername()))) {
                            lobbyData.isThatOne = true;
                            lobbyData.getGroupQuestList().stream().filter(q -> q.getType().name().equalsIgnoreCase(resp.questName)).findFirst().ifPresent(quest -> quest.addToGroup(resp.username));
                        }
                    }
                } else if (object instanceof Network.ResponseAddAmount resp) {
                    for (LobbyData lobbyData : LobbyController.getInstance().getLobbies()) {
                        if (lobbyData.getPlayers().stream().anyMatch(p -> p.getUsername().equals(Repository.getRepo().getCurrentUser().getUsername()))) {
                            lobbyData.getGroupQuestList().stream().filter(q -> q.getType().name().equals(resp.questName)).findFirst().ifPresent(quest -> quest.addAmount(resp.amount, resp.username));
                        }
                    }
                } else if (object instanceof Network.AudioChunk chunk) {
                    handleChunk(chunk);
                    System.out.println("dfsj");
                } else if (object instanceof Network.RadioFilesList list) {
                    System.out.println("list");
                    Repository.getRepo().getCurrentUser().setFilesList(list.fileNames);
                } else if (object instanceof Network.NPCPositionResponse resp) {
                    //System.out.println("222");
                    for (NPC npc : Repository.getRepo().getCurrentGame().getFarm().getNPCs()) {
                        if (npc.getType().name().equalsIgnoreCase(resp.adminPlayer)) {
                            //System.out.println("111");
                            npc.setHasWalk(resp.x, resp.y);
                            break;
                        }
                    }
                } else if (object instanceof Network.SetObjectResponse resp) {
                    try {
                        //System.out.println("set 00000000000000000000000000");
                        Repository.getRepo().getCurrentGame().getFarm().getTiles().get(resp.x).get(resp.y).setObjectC(FarmInitializer.getTileObjectFromNumber(resp.object));
                    } catch (Exception ignored) {

                    }
                } else if (object instanceof Network.SetTileTypeResponse resp) {
                    try {
                        Repository.getRepo().getCurrentGame().getFarm()
                            .getTiles().get(resp.x).get(resp.y)
                            .setTypeC(TileType.values()[resp.typeNum]);
                    } catch (Exception ignored) {

                    }
                } else if (object instanceof Network.SetTileMovableResponse resp) {
                    try {
                        Repository.getRepo().getCurrentGame().getFarm()
                            .getTiles().get(resp.x).get(resp.y)
                            .setMovableC(resp.movable);
                    } catch (Exception ignored) {

                    }
                } else if (object instanceof Network.SetTilePlowedResponse resp) {
                    try {
                        Repository.getRepo().getCurrentGame().getFarm()
                            .getTiles().get(resp.x).get(resp.y)
                            .setPlowedC(resp.plowed);
                    } catch (Exception ignored) {

                    }

                } else if (object instanceof Network.TradeRequest req) {
                    Player player = repo.getCurrentUser().getPlayer();
                    User user = repo.getUserByUsername(req.senderUsername);
                    player.getTradeProposalService().createProposal(req.senderUsername, req.receiverUsername);
                    player.setTradeRequester(user);
                } else if (object instanceof Network.TradeResponse req) {
                    Player player = repo.getCurrentUser().getPlayer();
                    int num = player.getTradeProposalService().findProposals(req.receiverUsername, req.senderUsername).size();
                    if (req.accepted) {
                        player.getTradeProposalService().acceptProposal(req.receiverUsername, req.senderUsername, num);
                        player.getTradeProposalService().setMessage("your request has been accepted");
                        player.getTradeProposalService().setMessageShown(false);
                    } else {
                        player.getTradeProposalService().rejectProposal(req.receiverUsername, req.senderUsername, num);
                        player.getTradeProposalService().setMessage("your request has been rejected");
                        player.getTradeProposalService().setMessageShown(false);
                    }
                } else if (object instanceof Network.HugEvent req) {
                    Player player = repo.getCurrentUser().getPlayer();
                    player.setHug(true);
                } else if (object instanceof Network.MarriageEvent req) {
                    MarriageRequest request = new MarriageRequest(repo.getUserByUsername(req.senderUsername));
                    Player player = repo.getCurrentUser().getPlayer();
                    player.addMarriageRequest(request);
                } else if (object instanceof Network.ResponseMarriageEvent req) {
                    Player player = repo.getCurrentUser().getPlayer();
                    player.setRelatedUser(player.getUser());
                    player.setResponse(req.success);
                } else if (object instanceof Network.ShareCoins res) {
                    for (Player p : Repository.getRepo().getCurrentGame().getPlayers()) {
                        if (p.getUser().getUsername().equalsIgnoreCase(res.name)) {
                            if (res.type.equalsIgnoreCase("coin")) {
                                p.setNumOfCoinsC(res.amount);
                            } else if (res.type.equalsIgnoreCase("quest")) {
                                p.setNumOfAbilityC(res.amount);
                            } else if (res.type.equalsIgnoreCase("ability")) {
                                p.setNumOfAbilityC(res.amount);
                            }
                        }
                    }
                }

            }


            @Override
            public void disconnected(com.esotericsoftware.kryonet.Connection connection) {
                System.out.println("Disconnected from the server");
            }
        });

        pClient.start();
        pClient.setTimeout(300000);
    }

    public void update() {
        for (User user : repo.getUsers().values()) {
//            if ((user.getUsername().equals("1") || user.getUsername().equals("2")) && !user.getUsername().equals(repo.getCurrentUser().getUsername()))
//                updateFriendshipRequest(user.getUsername());
        }
    }

    public void connect(String ip) throws IOException {
        pClient.connect(5000, ip, Network.PORT);
    }

    public void createLobby(String name, boolean isPrivate, String password, boolean isVisible, String admin, int id) {
        Network.CreateLobbyRequest req = new Network.CreateLobbyRequest();
        req.name = name;
        req.isPrivate = isPrivate;
        req.password = password;
        req.isVisible = isVisible;
        req.admin = admin;
        req.id = id;
        pClient.sendTCP(req);
    }

    public void joinLobby(int lobbyId, String password) {
        Network.JoinLobbyRequest req = new Network.JoinLobbyRequest();
        req.lobbyId = lobbyId;
        req.password = password;
        pClient.sendTCP(req);
    }

    public static synchronized GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
            try {
                instance.connect("127.0.0.1");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public void requestLobbyList(boolean isForOnlinePlayersList) {
        Network.RequestLobbyList req = new Network.RequestLobbyList();
        req.isForOnlinePlayersList = isForOnlinePlayersList;
        pClient.sendTCP(req);
    }


    public void gameStart(int lobbyId) {
        Network.GameStart req = new Network.GameStart();
        req.lobbyId = lobbyId;
        pClient.sendTCP(req);
    }


    public int getPlayerId() {
        return playerId;
    }

    public void sendStatus(Player player) {
        Network.PlayerStatus status = new Network.PlayerStatus();
        status.username = player.getUser().getUsername();
        status.x = player.getX();
        status.y = player.getY();
        status.direction = Player.directionToNum(player.getDirection());
        status.stateTime = player.getStateTime();
        status.isWalking = player.getMoving();
        pClient.sendTCP(status);

    }

    public void leaveLobby(int lobbyId) {
        Network.LeaveLobbyRequest req = new Network.LeaveLobbyRequest();
        req.lobbyId = lobbyId;
        pClient.sendTCP(req);
    }

    public void deleteLobby(int lobbyId) {
        Network.DeleteLobbyRequest req = new Network.DeleteLobbyRequest();
        req.lobbyId = lobbyId;
        pClient.sendTCP(req);
    }

    public void requestUsername() {
        Network.RequestUsername req = new Network.RequestUsername();
        req.username = "2";
        pClient.sendTCP(req);
    }

    public void updateFriendshipRequest(String friendUsername) {
        Network.AddFriendRequest req = new Network.AddFriendRequest();
        req.selfUsername = Repository.getRepo().getCurrentUser().getUsername();
        req.friendUsername = friendUsername;
        req.friendshipJson = repo.getCurrentUser().getPlayer().getRelationService().getFriendship(repo.getUserByUsername(friendUsername).getPlayer()).toJson();

        pClient.sendTCP(req);
    }

    public void sendMessageToFriend(String receiverUsername, String message) {
        Network.SendMessageEntry req = new Network.SendMessageEntry();
        req.type = "message";
        req.message = message;
        req.receiverUsername = receiverUsername;
        req.senderUsername = repo.getCurrentUser().getUsername();

        pClient.sendTCP(req);
    }

    public void sendNotification(String receiverUsername, String senderUsername, String message) {
        Network.SendMessageEntry req = new Network.SendMessageEntry();
        req.type = "notif";
        req.message = message;
        req.receiverUsername = receiverUsername;
        req.senderUsername = senderUsername;

        pClient.sendTCP(req);
    }

    public void sendPublicMessage(String message) {
        Network.SendMessageEntry req = new Network.SendMessageEntry();
        req.type = "message to all";
        req.message = message;
        req.senderUsername = repo.getCurrentUser().getUsername();

        pClient.sendTCP(req);
    }

    public void sendGift(Gift gift) {
        Network.JsonMessage req = new Network.JsonMessage();
        req.type = "gift";
        req.json = gift.toJson();
        req.receiver = gift.receiver().getUser().getUsername();

        pClient.sendTCP(req);
    }

    public void addInventoryItem(String itemName, int amount, String username) {
        Network.AddInventoryItem req = new Network.AddInventoryItem();
        req.itemName = itemName;
        req.amount = amount;
        req.username = username;

        pClient.sendTCP(req);
    }

//    public void addReaction(Player player, Player.Reaction reaction) {
//        Network.AddReaction req = new Network.AddReaction();
//        req.reaction = reaction.name();
//        req.username = player.getUser().getUsername();
//
//        pClient.sendTCP(req);
//    }

    public void startBanPlayerVoting(String username) {
        Network.StartVoting req = new Network.StartVoting();
        req.type = "ban player";
        req.votingUsername = username;

        pClient.sendTCP(req);
    }

    public void startForceTerminate() {
        Network.StartVoting req = new Network.StartVoting();
        req.type = "force terminate";

        pClient.sendTCP(req);
    }

    public void sendVote(Voting.Vote vote) {
        Network.Vote req = new Network.Vote();
        req.vote = vote.name();
        req.voterUsername = Repository.getRepo().getCurrentUser().getUsername();

        pClient.sendTCP(req);
    }

    public void sendAddRequest(User user) {
        Network.RequestAddSignedInUser req = new Network.RequestAddSignedInUser();
        req.username = user.getUsername();
        req.password = user.getPassword();
        if (user.getSecurityQuestion() != null) req.securityQuestionType = user.getSecurityQuestion().name();
        if (user.getSecurityAnswer() != null) req.securityQuestionAnswer = user.getSecurityAnswer();
        pClient.sendTCP(req);
    }

    public void sendCheckLoginRequest(String username, String password, LoginMenuView view) {
        System.out.println("sendCheckLoginRequest");
        this.loginMenuView = view;
        Network.RequestCheckToLogin req = new Network.RequestCheckToLogin();
        req.username = username;
        req.password = password;
        pClient.sendTCP(req);
    }

    public void sendReactionToServer(String username, boolean isText, String text, int reactionNum) {
        Network.CTSReaction req = new Network.CTSReaction();
        req.username = username;
        req.isText = isText;
        req.text = text;
        req.reactionNum = reactionNum;
        pClient.sendTCP(req);
        sendAddAmountRequest(1f, "REACT");
    }

    public void sendStartGroupQuest(int lobbyId, String questType) {
        LobbyData lobby = null;
        for (LobbyData lobbyData : LobbyController.getInstance().getLobbies()) {
            if (lobbyData.getPlayers().stream().anyMatch(p -> p.getUsername().equals(Repository.getRepo().getCurrentUser().getUsername()))) {
                lobby = lobbyData;
            }
        }
        if (lobby != null) {
            int attended = 0;
            for (GroupQuest groupQuest : lobby.getGroupQuestList()) {
                if (groupQuest.isInList(repo.getCurrentUser().getUsername())) {
                    attended++;
                }
            }
            if (attended <= 3) {
                Network.RequestStartGroupQuest req = new Network.RequestStartGroupQuest();
                req.lobbyId = lobbyId;
                req.questName = questType;
                req.username = repo.getCurrentUser().getUsername();
                pClient.sendTCP(req);
            }
        }
    }

    public void sendAddAmountRequest(float amount, String questName) {
        int lobbyId = -1;
        for (LobbyData lobbyData : LobbyController.getInstance().getLobbies()) {
            if (lobbyData.getPlayers().stream().anyMatch(p -> p.getUsername().equals(Repository.getRepo().getCurrentUser().getUsername()))) {
                lobbyId = lobbyData.getId();
            }
        }
        if (lobbyId != -1) {
            Network.RequestAddAmount req = new Network.RequestAddAmount();
            req.lobbyId = lobbyId;
            req.amount = amount;
            req.questName = questName;
            req.username = Repository.getRepo().getCurrentUser().getUsername();
            pClient.sendTCP(req);
        }
    }


    public void uploadAudioFile(String hostPlayer, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }

        byte[] fileData = Files.readAllBytes(file.toPath());


        Network.UploadAudioRequest req = new Network.UploadAudioRequest();
        req.hostPlayer = hostPlayer;
        req.fileName = file.getName();
        req.fileData = fileData;


        pClient.sendTCP(req);

        System.out.println("File " + file.getName() + " uploaded for host: " + hostPlayer);
    }

    public void handleChunk(Network.AudioChunk chunk) {
        channelBuffers.putIfAbsent(chunk.hostPlayer, new ByteArrayOutputStream());
        channelDevices.putIfAbsent(chunk.hostPlayer,
            Gdx.audio.newAudioDevice(44100, true));

        try {
            channelBuffers.get(chunk.hostPlayer).write(chunk.data);

            byte[] pcm = chunk.data;
            short[] samples = new short[pcm.length / 2];
            for (int i = 0; i < samples.length; i++) {
                int lsb = pcm[i * 2] & 0xFF;
                int msb = pcm[i * 2 + 1];
                samples[i] = (short) ((msb << 8) | lsb);
            }

            channelDevices.get(chunk.hostPlayer).writeSamples(samples, 0, samples.length);

        } catch (Exception e) {
            System.out.println("Error handling chunk from " + chunk.hostPlayer + ": " + e.getMessage());
            System.out.println(e.getMessage());
        }

        if (chunk.isLast) {
            AudioDevice device = channelDevices.get(chunk.hostPlayer);
            if (device != null) {
                device.dispose();
                channelDevices.remove(chunk.hostPlayer);
            }
            channelBuffers.remove(chunk.hostPlayer);
        }

    }

    public void requestRadioFiles(String hostPlayer) {
        Network.RequestRadioFiles req = new Network.RequestRadioFiles();
        req.hostPlayer = hostPlayer;
        pClient.sendTCP(req);
    }

    public void setFileForHost(String hostPlayer, String fileName) {
        Network.ChangeAudio req = new Network.ChangeAudio();
        req.hostPlayer = hostPlayer;
        req.fileName = fileName;
        pClient.sendTCP(req);
    }

    public void joinRadioRequest(String hostPlayer) {
        Network.JoinRadioRequest req = new Network.JoinRadioRequest();
        req.targetUsername = hostPlayer;
        pClient.sendTCP(req);
    }

    public void sendNPCPosition(float x, float y, String npcType) {
        Network.NPCPosition position = new Network.NPCPosition();
        position.adminPlayer = npcType;
        position.x = x;
        position.y = y;
        pClient.sendTCP(position);
    }

    public void sendTileObject(Network.SetObjectRequest request) {
        pClient.sendTCP(request);
    }

    public void sendTileType(Network.SetTileTypeRequest request) {
        pClient.sendTCP(request);
    }

    public void sendTilePlowed(Network.SetTilePlowedRequest request) {
        pClient.sendTCP(request);
    }

    public void sendTileMovable(Network.SetTileMovableRequest request) {
        pClient.sendTCP(request);
    }




    public void sendTradeRequest(String receiverUsername) {
        Network.TradeRequest req = new Network.TradeRequest();
        req.senderUsername = repo.getCurrentUser().getUsername();
        req.receiverUsername = receiverUsername;
        pClient.sendTCP(req);
    }

    public void sendTradeResponse(String receiverUsername, boolean response) {
        Network.TradeResponse req = new Network.TradeResponse();
        req.accepted = response;
        req.receiverUsername = receiverUsername;
        req.senderUsername = repo.getCurrentUser().getUsername();
        pClient.sendTCP(req);
    }

    public void sendHugAction(Player player) {
        if (player != null) {
            Network.HugEvent hug = new Network.HugEvent();
            hug.senderUsername = Repository.getRepo().getCurrentUser().getUsername();
            hug.targetUsername = player.getUser().getUsername();
            pClient.sendTCP(hug);
        }
    }

    public void sendMarriageEvent(Player player) {
        if (player != null) {
            Network.MarriageEvent marriage = new Network.MarriageEvent();
            marriage.senderUsername = Repository.getRepo().getCurrentUser().getUsername();
            marriage.targetUsername = player.getUser().getUsername();
            pClient.sendTCP(marriage);
        }
    }

    public void sendResponseMarriageEvent(Player player, boolean response) {
        if (player != null) {
            Network.ResponseMarriageEvent marriage = new Network.ResponseMarriageEvent();
            marriage.senderUsername = Repository.getRepo().getCurrentUser().getUsername();
            marriage.targetUsername = player.getUser().getUsername();
            marriage.success = response;
            pClient.sendTCP(marriage);
        }
    }

    public void searchLobbyById(String id) {
        Network.SearchLobbyRequest req = new Network.SearchLobbyRequest();
        req.id = id;
        pClient.sendTCP(req);
    }

    public void sendCoinForShare(int amount, String type) {
        Network.ShareCoins req = new Network.ShareCoins();
        req.amount = amount;
        req.name = repo.getCurrentUser().getUsername();
        req.type = type;
        pClient.sendTCP(req);
    }
}
