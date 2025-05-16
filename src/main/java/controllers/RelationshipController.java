package controllers;

import models.Game;
import models.Item;
import models.MessageEntry;
import models.Result;
import models.character.player.Inventory;
import models.character.player.Slot;
import models.character.player.Player;
import models.data.Repository;
import models.enums.Gender;
import models.enums.commands.RelationshipCommands;
import models.dateTime.DateTime;
import models.relations.Friendship;
import models.relations.Gift;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelationshipController extends Controller {
    RelationshipController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        RelationshipCommands matchedCommand = null;

        for (RelationshipCommands cmd : RelationshipCommands.values()) {
            if (commandLine.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command");
        }

        String username;
        switch (matchedCommand) {
            case SHOW_FRIENDSHIPS:
                return showFriendships();
            case TALK:
                username = commandLine.split("\\s+")[2];
                String message = commandLine.substring(commandLine.lastIndexOf("-m") + 2).trim();
                return talk(username, message);
            case TALK_HISTORY:
                username = commandLine.split("\\s+")[3];
                return talkHistory(username);
            case HUG:
                username = commandLine.split("\\s+")[2];
                return hug(username);
            case GIFT:
                username = commandLine.split("\\s+")[2];
                String item = commandLine.substring(commandLine.indexOf("-i") + 2, commandLine.indexOf("-a") - 1).trim();
                int amount;
                try {
                    amount = Integer.parseInt(commandLine.substring(commandLine.indexOf("-a") + 2).trim());
                } catch (NumberFormatException e) {
                    return new Result(false, "invalid amount");
                }
                return gift(username, item, amount);
            case GIFT_RATE:
                int giftNumber, rate;
                try {
                    giftNumber = Integer.parseInt(commandLine.substring(commandLine.indexOf("-i") + 2, commandLine.indexOf("-r") - 1).trim());
                    rate = Integer.parseInt(commandLine.substring(commandLine.indexOf("-r") + 2).trim());
                } catch (NumberFormatException e) {
                    return new Result(false, "invalid number");
                }
                return giftRate(giftNumber, rate);
            case GIFT_LIST:
                return giftList();
            case GIFT_HISTORY:
                username = commandLine.split("\\s+")[3];
                return giftHistory(username);
            case FLOWER:
                username = commandLine.split("\\s+")[2];
                return flower(username);
            case ASK_MARRIAGE:
                username = commandLine.split("\\s+")[3];
                String ring = commandLine.split("\\s+")[5];
                return askMarriage(username, ring);
            case RESPOND_MARRIAGE:
                username = commandLine.split("\\s+")[3];
                String respond = commandLine.split("\\s+")[1];
                return respondMarriage(username, respond);
            case START_TRADE:
                return startTrade();
            case TRADE_REQUEST:
                username = commandLine.split("\\s+")[2];
                String itemName = commandLine.split("\\s+")[6];
                String amountStr = commandLine.split("\\s+")[8];
                String priceStr = commandLine.split("\\s+")[10];
                return tradeRequest(username, itemName, amountStr, priceStr);
            case TRADE_OFFER:
                username = commandLine.split("\\s+")[2];
                String itemName1 = commandLine.split("\\s+")[6];
                String amountStr1 = commandLine.split("\\s+")[8];
                String itemName2 = commandLine.split("\\s+")[10];
                String amountStr2 = commandLine.split("\\s+")[12];
                return tradeOffer(username, itemName1, amountStr1, itemName2, amountStr2);
        }
        return new Result(true, "");
    }

    private Result showFriendships() {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Game game = repo.getCurrentGame();

        StringBuilder resultMsg = new StringBuilder();

        for (Player gamePlayer : game.getPlayers()) {
            if (player.getRelationService().isFriendWith(gamePlayer)) {
                resultMsg.append("%s : %d\n".formatted(gamePlayer.getUser().getUsername(), player.getRelationService().getFriendship(gamePlayer).getLevel()));
            } else {
                resultMsg.append("not friend with %s !\n".formatted(gamePlayer.getUser().getUsername()));
            }
        }

        return new Result(true, resultMsg.toString());
    }

    private Result talk(String username, String message) {
        Player receiver = repo.getUserByUsername(username).getPlayer();
        Player sender = repo.getCurrentGame().getCurrentPlayer();
        if (receiver == null) {
            return new Result(false, "player not found");
        } else if (!sender.isNearTo(receiver)) {
            return new Result(false, "you should be near of %s".formatted(receiver));
        }

        Friendship friendship = sender.getRelationService().getFriendship(receiver);

        friendship.sendMessage(sender, message);
        receiver.addNotification(sender, "You have a new message from " + sender.getUser().getUsername());

        friendship.increaseXp(Friendship.TALK_XP);
        return new Result(true, "your message sent to " + receiver.getUser().getUsername());
    }

    private Result talkHistory(String username) {
        Player friend = repo.getUserByUsername(username).getPlayer();
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();

        Map<MessageEntry, Boolean> messages = currentPlayer.getRelationService().getFriendship(friend).getMessages();

        StringBuilder resultMsg = new StringBuilder();

        List<MessageEntry> keys = new ArrayList<>(messages.keySet());
        for (MessageEntry messageEntry : messages.keySet()) {
            resultMsg.append("%s : \"%s\"".formatted(messageEntry.sender(), messageEntry.message()));
            if (messageEntry.sender() == friend) {
                messages.put(messageEntry, true);
            }
            if (keys.indexOf(messageEntry) != keys.size() - 1) {
                resultMsg.append("\n");
            }
        }
        return new Result(true, resultMsg.toString());
    }

    private Result hug(String username) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();

        if (friend == null) {
            return new Result(false, "player not found");
        } else if (!currentPlayer.isNearTo(friend)) {
            return new Result(false, "you should be near to " + friend.getUser().getNickname());
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(friend);

        if (friendship.getLevel() < 2) {
            return new Result(false, "you are not enough level");
        }

        DateTime currentTime = repo.getCurrentGame().getTimeManager().getNow();

        if (currentPlayer.getRelationService().getLastHugDate() == currentTime.getDay()) {
            return new Result(false, "You can only hug once per day.");
        }

        currentPlayer.setLastHugDate(currentTime.getDay());
        friendship.increaseXp(Friendship.HUG_XP);
        return new Result(true, "you hugged each other! jooon");
    }

    private Result gift(String username, String itemName, int amount) {
        Player receiver = repo.getUserByUsername(username).getPlayer();
        Player sender = repo.getCurrentGame().getCurrentPlayer();
        Friendship friendship = sender.getRelationService().getFriendship(receiver);
        if (receiver == null) {
            return new Result(false, "player not found");
        }

        if (!sender.isNearTo(receiver)) {
            return new Result(false, "you should be near of %s".formatted(receiver));
        }

        Slot slot = sender.getInventory().getSlot(itemName);
        Item item = slot.getItem();
        if (slot == null) {
            return new Result(false, "item not found");
        }
        if (slot.getQuantity() <= amount) {
            slot.removeQuantity(amount);
            receiver.getInventory().addItem(itemName, amount);
        } else {
            return new Result(false, "not enough item");
        }

        DateTime now = repo.getCurrentGame().getTimeManager().getNow();
        friendship.addGift(sender, receiver, item, amount, now);
        receiver.addNotification(sender, "%s sent you a gift! %d number of %s".formatted(sender.getUser().getUsername(), amount, itemName));
        return new Result(true, "your gift sent to " + receiver.getUser().getUsername() + " successfully");
    }

    private Result giftRate(int giftNumber, int rate) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Gift gift = null;
        for (Friendship friendship : currentPlayer.getRelationService().getFriendships().values()) {
            gift = friendship.getGift(giftNumber);
        }

        if (gift == null) {
            return new Result(false, "gift not found");
        } else if (gift.receiver() != currentPlayer) {
            return new Result(false, "your are not receiver of this gift");
        }

        if (gift.setRate(rate)) {
            Player sender = gift.sender();
            Friendship friendship = currentPlayer.getRelationService().getFriendship(sender);

            if (gift.getGiftXp() >= 0) {
                friendship.increaseXp(gift.getGiftXp());
            } else {
                friendship.decreaseXp(gift.getGiftXp());
            }

            return new Result(true, "gift rated successfully");
        } else {
            return new Result(false, "invalid rate");
        }
    }

    private Result giftList() {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        List<Gift> receivedGifts = new ArrayList<>();

        for (Friendship friendship : currentPlayer.getRelationService().getFriendships().values()) {
            receivedGifts.addAll(friendship.getReceivedGifts(currentPlayer));
        }

        StringBuilder resultMsg = new StringBuilder();

        for (Gift gift : receivedGifts) {
            resultMsg.append(gift);
            if (receivedGifts.indexOf(gift) != receivedGifts.size() - 1) {
                resultMsg.append("\n");
            }
        }

        return new Result(true, resultMsg.toString());
    }

    private Result giftHistory(String username) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player otherPlayer = repo.getUserByUsername(username).getPlayer();
        if (otherPlayer == null) {
            return new Result(false, "player not found");
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(otherPlayer);

        StringBuilder resultMsg = new StringBuilder();

        for (Gift gift : friendship.getGifts().values()) {
            resultMsg.append(gift);
            if (friendship.getGifts().values().stream().toList().indexOf(gift) != friendship.getGifts().size() - 1) {
                resultMsg.append("\n");
            }
        }

        return new Result(true, resultMsg.toString());
    }

    private Result flower(String username) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();

        if (friend == null) {
            return new Result(false, "player not found");
        }
        if (!currentPlayer.isNearTo(friend)) {
            return new Result(false, "you should be near to " + friend.getUser().getNickname());
        }

        String flower = "flower";

        if (currentPlayer.getInventory().getSlot(flower) == null) {
            return new Result(false, "you don't have flower in your inventory");
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(friend);

        if (friendship.getLevel() != 2 || friendship.getXp() != friendship.getMaxXp()) {
            return new Result(false, "you have not enough level and xp");
        }

        currentPlayer.getInventory().getSlot(flower).removeQuantity(1);
        friend.getInventory().getSlot(flower).addQuantity(1);

        friendship.flower();
        return new Result(true, "you hugged each other! jooon");
    }

    private Result askMarriage(String username, String ring) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();

        if (friend == null) {
            return new Result(false, "player not found");
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(friend);
        if (friendship == null || friendship.getLevel() != 3) {
            return new Result(false, "you have not enough level");
        }

        if (!currentPlayer.isNearTo(friend)) {
            return new Result(false, "you should be near to " + friend.getUser().getUsername());
        }

        if (currentPlayer.getGender() == Gender.FEMALE) {
            return new Result(false, "you are girl and you can't request marriage");
        }

        if (currentPlayer.getInventory().getSlot(ring) == null) {
            return new Result(false, "you don't have ring in your inventory");
        }

        friendship.sendMessage(friend,"would you marry me?");
        friend.setPartner(repo.getCurrentUser());
        return new Result(true, "your request send to " + friend.getUser().getNickname());
    }

    private Result respondMarriage(String username, String respond) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();

        if (friend == null) {
            return new Result(false, "player not found");
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(friend);
        if (friendship == null || friendship.getLevel() != 3) {
            return new Result(false, "you have not enough level");
        }

        if (currentPlayer.getPartner().equals(friend.getUser())) {
            return new Result(false, "you don't have request from " + friend.getPartner().getNickname());
        }

        String ring = "ring";

        if (respond.equals("reject")) {
            friendship.setLevel(0);
            friendship.setXp(0);
            double energy = friend.getEnergy().getMAX_ENERGY();
            friend.getEnergy().setMAX_ENERGY(energy/2);
            //7 roooz
            return new Result(true, currentPlayer + "reject" + friend.getUser().getUsername() + "request for marriage");
        }

        else if (respond.equals("accept")) {
            friendship.setLevel(4);
            friend.getInventory().getSlot(ring).removeQuantity(1);
            currentPlayer.getInventory().getSlot(ring).addQuantity(1);

            currentPlayer.getRelationService().marry(friend);
            currentPlayer.updateOfMarriage(friend);
            //har rooz 50 energy
            return new Result(true, currentPlayer + "accept" + friend.getUser().getUsername() + "request for ring");
        }
        return null;
    }

    private Result startTrade() {
        Game currentGame = repo.getCurrentGame();
        List<Player> players = currentGame.getPlayers();

        StringBuilder info = new StringBuilder();
        info.append("List of players:\n");

        for (Player player : players) {
            info.append("- ").append(player.getUser().getUsername()).append("\n");
        }

        return new Result(true, info.toString());
    }

    private Result tradeRequest(String username, String itemName, String amountStr, String priceStr) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();
        int amount;

        if (friend == null) {
            return new Result(false, "player not found");
        }

        Item item = Inventory.getNewItem(itemName);

        if (item == null) {
            return new Result(false, "item not found");
        }

        if (!amountStr.matches("^\\d+$")) {
            return new Result(false, "invalid amount " + amountStr);
        }

        else {
            amount = Integer.parseInt(amountStr);
        }

        int price = Integer.parseInt(priceStr);

        if (price > currentPlayer.getNumOfCoins()) {
            return new Result(false, "you have not enough coins");
        }

        //send message
        return new Result(true, "your request trade sent to " + friend.getUser().getUsername());
    }

    private Result tradeOffer(String username, String itemName, String amountStr, String itemName2, String amountStr2) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Player friend = repo.getUserByUsername(username).getPlayer();
        int amount;

        if (friend == null) {
            return new Result(false, "player not found");
        }

        Item item = Inventory.getNewItem(itemName);

        if (item == null) {
            return new Result(false, "item not found");
        }

        if (!amountStr.matches("^\\d+$")) {
            return new Result(false, "invalid amount " + amountStr);
        }

        else {
            amount = Integer.parseInt(amountStr);
        }

        Inventory inventory = currentPlayer.getInventory();
        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "you don't have slot " + itemName2);
        }

        if (slot.getQuantity() < amount) {
            return new Result(false, "you have not enough " + itemName2 );
        }

        //send message

        return new Result(true, "your offer trade sent to " + friend.getUser().getUsername());
    }
}
