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
        kryo.register(GameStart.class);
        kryo.register(PlayerStatus.class);
        kryo.register(LeaveLobbyRequest.class);
        kryo.register(LeaveLobbyResponse.class);
        kryo.register(DeleteLobbyRequest.class);
        kryo.register(DeleteLobbyResponse.class);


        kryo.register(RequestUsername.class);
        kryo.register(ResponseUsername.class);
        kryo.register(AddFriendRequest.class);
        kryo.register(AddFriendResponse.class);
        kryo.register(SendMessageEntry.class);
        kryo.register(AddInventoryItem.class);
        kryo.register(AddReaction.class);
        kryo.register(Vote.class);
        kryo.register(StartVoting.class);
        kryo.register(RequestAddSignedInUser.class);
        kryo.register(RequestCheckToLogin.class);
        kryo.register(ResponseCheckToLogin.class);
        kryo.register(CTSReaction.class);
        kryo.register(STCReaction.class);
        kryo.register(RequestStartGroupQuest.class);
        kryo.register(ResponseStartGroupQuest.class);
        kryo.register(RequestAddAmount.class);
        kryo.register(ResponseAddAmount.class);
        kryo.register(UploadAudioRequest.class);
        kryo.register(JoinRadioRequest.class);
        kryo.register(AudioChunk.class);
        kryo.register(RadioChannelList.class);
        kryo.register(ChangeAudio.class);
        kryo.register(byte[].class);
        kryo.register(RequestRadioFiles.class);
        kryo.register(RadioFilesList.class);
    }

    public static class JsonMessage {
        public String type;
        public String json;
        public String receiver;
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
        public int id;
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
        public boolean isForOnlinePlayersList;
    }

    public static class LobbyInfo {
        public int id;
        public String name;
        public boolean isPrivate;
        public boolean isVisible;
        public int playerCount;
        public String[] playerNames;
        public String password;
    }

    public static class LobbyListResponse {
        public LobbyInfo[] lobbies;
        public boolean isForOnlinePlayersList;
    }

    public static class StartGameRequest {}

    public static class GameStart {
        public int lobbyId;
    }

    public static class PlayerStatus {
        public String username;
        public float x;
        public float y;
        public int direction;
        public float stateTime;
        public boolean isWalking;
    }

    public static class LeaveLobbyRequest {
        public int lobbyId;
    }

    public static class LeaveLobbyResponse {
        public boolean success;
        public String message;
        public int lobbyId;
    }

    public static class DeleteLobbyRequest {
        public int lobbyId;
    }

    public static class DeleteLobbyResponse {
        public boolean success;
        public String message;
        public int lobbyId;
    }




    public static class RequestUsername {
        public String username;
    }

    public static class ResponseUsername {
        public String message;
    }

    public static class AddFriendRequest {
        public String selfUsername;
        public String friendUsername;
        public String friendshipJson;
    }

    public static class AddFriendResponse {
        public String newFriendUsername;
        public String friendshipJson;
    }

    public static class SendMessageEntry {
        public String type;
        public String senderUsername;
        public String receiverUsername;
        public String message;
    }

    public static class AddInventoryItem {
        public String itemName;
        public int amount;
        public String username;
    }

    public static class AddReaction {
        public String username;
        public String reaction;
    }

    public static class Vote {
        public String vote;
        public String voterUsername;
    }

    public static class StartVoting {
        public String type;
        public String votingUsername;
    }

    public static class RequestAddSignedInUser {
        public String username;
        public String password;
        public String securityQuestionType;
        public String securityQuestionAnswer;
    }

    public static class RequestCheckToLogin {
        public String username;
        public String password;
    }

    public static class ResponseCheckToLogin {
        public boolean canLogin;
        public String username;
        public String password;
    }

    public static class CTSReaction {
        public String username;
        public boolean isText;
        public String text;
        public int reactionNum;
    }

    public static class STCReaction {
        public String username;
        public boolean isText;
        public String text;
        public int reactionNum;
    }

    public static class RequestStartGroupQuest {
        public int lobbyId;
        public String questName;
        public String username;
    }

    public static class ResponseStartGroupQuest {
        public String questName;
        public String username;
    }

    public static class RequestAddAmount {
        public int lobbyId;
        public int amount;
        public String questName;
        public String username;
    }

    public static class ResponseAddAmount {
        public String questName;
        public int amount;
        public String username;
    }

    public static class UploadAudioRequest {
        public String hostPlayer;
        public String fileName;
        public byte[] fileData;
    }

    public static class JoinRadioRequest {
        public String targetUsername;
        public String currentUsername;
    }

    public static class AudioChunk {
        public String hostPlayer;
        public byte[] data;
        public int seq;
        public boolean isLast;
        public String fileName;
    }

    public static class RadioChannelList {
        public String[] channelNames;
    }

    public static class ChangeAudio {
        public String hostPlayer;
        public String fileName;
    }

    public static class RequestRadioFiles {
        public String hostPlayer;
    }

    public static class RadioFilesList {
        public String hostPlayer;
        public String[] fileNames;
    }
}
