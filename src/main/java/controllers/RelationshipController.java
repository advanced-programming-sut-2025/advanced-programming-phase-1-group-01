package controllers;

import models.Game;
import models.InventoryItem;
import models.MessageEntry;
import models.Result;
import models.character.player.InventorySlot;
import models.character.player.Player;
import models.data.Repository;
import models.dateTime.DateTime;
import models.enums.commands.RelationshipCommands;
import models.relations.Friendship;
import models.relations.Gift;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
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
        }
        return new Result(true, "");
    }

    private Result showFriendships() {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Game game = repo.getCurrentGame();

        StringBuilder resultMsg = new StringBuilder();

        for (Player gamePlayer : game.players().values()) {
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

        InventorySlot slot = sender.getInventory().getSlot(itemName);
        InventoryItem item = slot.getItem();
        if (slot.getQuantity() <= amount) {
            slot.decreaseQuantity(amount);
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
}
