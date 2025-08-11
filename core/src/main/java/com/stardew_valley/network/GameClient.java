
package com.stardew_valley.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.stardew_valley.controllers.GameMenuController;
import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.controllers.VotingController;
import com.stardew_valley.models.LobbyData;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.MessageEntry;
import com.stardew_valley.models.Voting;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.relations.Friendship;
import com.stardew_valley.models.relations.Gift;
import com.stardew_valley.views.GameView;
import com.stardew_valley.views.LobbyView;
import com.stardew_valley.views.LoginMenuView;
import com.stardew_valley.views.ReactionView;

import java.io.IOException;
import java.util.List;

public class GameClient {
    private final Repository repo = Repository.getRepo();
    private LoginMenuView loginMenuView;
    private static GameClient instance;
    private final Client pClient;
    private int playerId;

    public GameClient() {
        pClient = new Client(6553600, 6553600);
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
                    Player player = LobbyController.getInstance().getRepository().getCurrentGame().getPlayerByUsername(playerStatus.username);
                    if (player != null) {
                        if (!playerStatus.username.equals(Repository.getRepo().getCurrentUser().getUsername())) {
                            player.setX(playerStatus.x);
                            player.setY(playerStatus.y);
                            player.setDirection(Player.numToDirection(playerStatus.direction));
                            player.setStateTime(playerStatus.stateTime);
                            player.setMoving(playerStatus.isWalking);
                            System.out.println("set " + playerStatus.username);
                            System.out.println("in " + Repository.getRepo().getCurrentUser().getUsername());
                        } else {
                            //System.out.println("that was yourself");
                        }
                    } else {
                        System.out.println("Warning: player not found for username: " + playerStatus.username);
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
                    Player.Reaction reaction = Player.Reaction.valueOf(req.reaction.toUpperCase());

                    GameView.setReaction(player, reaction.getReaction());
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

    public void addReaction(Player player, Player.Reaction reaction) {
        Network.AddReaction req = new Network.AddReaction();
        req.reaction = reaction.name();
        req.username = player.getUser().getUsername();

        pClient.sendTCP(req);
    }

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
        req.securityQuestionType = user.getSecurityQuestion().name();
        req.securityQuestionAnswer = user.getSecurityAnswer();
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


}
