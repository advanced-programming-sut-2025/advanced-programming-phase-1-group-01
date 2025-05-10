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
            case GIFT_LIST:
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
}
