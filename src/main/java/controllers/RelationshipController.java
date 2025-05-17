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
import models.relations.*;
import models.character.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                return tradeRequest(commandLine);
            case TRADE_OFFER:
                return tradeOffer(commandLine);
            case TRADE_RESPONSE:
                return tradeResponse(commandLine);
            case TRADE_LIST:
                return tradeInWait();
            case TRADE_HISTORY:
                return tradeAll();
        }
        return new Result(true, "");
    }

    private Result showFriendships() {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        Game game = repo.getCurrentGame();

        StringBuilder resultMsg = new StringBuilder();

        for (Player gamePlayer : game.getPlayers()) {
            if (gamePlayer == player) {
                continue;
            }
            if (player.getRelationService().isFriendWith(gamePlayer)) {
                resultMsg.append("%s level : %d\n".formatted(gamePlayer.getUser().getUsername(), player.getRelationService().getFriendship(gamePlayer).getLevel()));
                resultMsg.append("%s xp : %d\n".formatted(gamePlayer.getUser().getUsername(), player.getRelationService().getFriendship(gamePlayer).getXp()));
            } else {
                resultMsg.append("not friend with %s !\n".formatted(gamePlayer.getUser().getUsername()));
            }
        }

        return new Result(true, resultMsg.toString());
    }

    private Result talk(String username, String message) {
        Player sender = repo.getCurrentGame().getCurrentPlayer();
        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }
        Player receiver = repo.getUserByUsername(username).getPlayer();
        if (!sender.isNearTo(receiver)) {
            return new Result(false, "you should be near of %s".formatted(receiver.getUser().getNickname()));
        }

        Friendship friendship = sender.getRelationService().getFriendship(receiver);

        friendship.sendMessage(sender, message);
        receiver.addNotification(sender, "You have a new message from " + sender.getUser().getUsername());

        DateTime currentTime = repo.getCurrentGame().getTimeManager().getNow();
        if (sender.getRelationService().getFriendship(receiver).getLastTalkDay() == currentTime.getDay()) {
            return new Result(false, "You can only talk once per day.");
        }

        friendship.setLastTalkDay(currentTime.getDay());
        friendship.increaseXp(Friendship.TALK_XP);

        if (sender.getRelationService().getMarriage() != null) {
            if (sender.getRelationService().getMarriage().getPartner(receiver) != null) {
                if (sender.getRelationService().getMarriage().getLastRelation() != currentTime.getDay()) {
                    sender.getEnergy().increase(50);
                    receiver.getEnergy().increase(50);
                    sender.getRelationService().getMarriage().setLastRelation(currentTime.getDay());
                }
            }
        }

        return new Result(true, "your message sent to " + receiver.getUser().getUsername());
    }

    private Result talkHistory(String username) {
        Player friend = repo.getUserByUsername(username).getPlayer();
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();

        Map<MessageEntry, Boolean> messages = currentPlayer.getRelationService().getFriendship(friend).getMessages();

        StringBuilder resultMsg = new StringBuilder();

        List<MessageEntry> keys = new ArrayList<>(messages.keySet());
        for (MessageEntry messageEntry : messages.keySet()) {
            resultMsg.append("%s : \"%s\"".formatted(messageEntry.sender().getUser().getNickname(), messageEntry.message()));
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

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }

        Player friend = repo.getUserByUsername(username).getPlayer();

        if (!currentPlayer.isNearTo(friend)) {
            return new Result(false, "you should be near to " + friend.getUser().getNickname());
        }

        Friendship friendship = currentPlayer.getRelationService().getFriendship(friend);

        if (friendship.getLevel() < 2) {
            return new Result(false, "you are not enough level");
        }

        DateTime currentTime = repo.getCurrentGame().getTimeManager().getNow();

        if (currentPlayer.getRelationService().getFriendship(friend).getLastHugDay() == currentTime.getDay()) {
            return new Result(false, "You can only hug once per day.");
        }

        currentPlayer.getRelationService().getFriendship(friend).setLastHugDay(currentTime.getDay());
        friendship.increaseXp(Friendship.HUG_XP);

        if (currentPlayer.getRelationService().getMarriage() != null) {
            if (currentPlayer.getRelationService().getMarriage().getPartner(friend) != null) {
                if (currentPlayer.getRelationService().getMarriage().getLastRelation() != currentTime.getDay()) {
                    currentPlayer.getEnergy().increase(50);
                    friend.getEnergy().increase(50);
                    currentPlayer.getRelationService().getMarriage().setLastRelation(currentTime.getDay());
                }
            }
        }
        return new Result(true, "you hugged each other! jooon");
    }

    private Result gift(String username, String itemName, int amount) {
        Player sender = repo.getCurrentGame().getCurrentPlayer();
        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }
        Player receiver = repo.getUserByUsername(username).getPlayer();
        Friendship friendship = sender.getRelationService().getFriendship(receiver);

        if (!sender.isNearTo(receiver)) {
            return new Result(false, "you should be near of %s".formatted(receiver.getUser().getNickname()));
        }

        Slot slot = sender.getInventory().getSlot(itemName);

        if (slot == null) {
            return new Result(false, "item not found");
        }
        Item item = slot.getItem();
        if (slot.getQuantity() <= amount) {
            slot.removeQuantity(amount);
            receiver.getInventory().addItem(itemName, amount);
        } else {
            return new Result(false, "not enough item");
        }

        DateTime now = repo.getCurrentGame().getTimeManager().getNow();
        friendship.addGift(sender, receiver, item, amount, now);
        receiver.addNotification(sender, "%s sent you a gift! %d number of %s".formatted(sender.getUser().getUsername(), amount, itemName));

        if (sender.getRelationService().getFriendship(receiver).getLastGiftDay() == now.getDay()) {
            return new Result(false, "You can only send gift once per day.");
        }

        friendship.setLastGiftDay(now.getDay());

        if (sender.getRelationService().getMarriage() != null) {
            if (sender.getRelationService().getMarriage().getPartner(receiver) != null) {
                if (sender.getRelationService().getMarriage().getLastRelation() != now.getDay()) {
                    sender.getEnergy().increase(50);
                    receiver.getEnergy().increase(50);
                    sender.getRelationService().getMarriage().setLastRelation(now.getDay());
                }
            }
        }

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

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }

        Player friend = repo.getUserByUsername(username).getPlayer();
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

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }
        Player friend = repo.getUserByUsername(username).getPlayer();

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

        friendship.sendMessage(friend, "would you marry me?");
        friend.setPartner(repo.getCurrentUser());
        return new Result(true, "your request send to " + friend.getUser().getNickname());
    }

    private Result respondMarriage(String username, String respond) {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }
        Player friend = repo.getUserByUsername(username).getPlayer();

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
            double energy = friend.getEnergy().getMaxEnergy();
            friend.getEnergy().setMaxEnergy(energy/2);
            currentPlayer.setEnergyHalved();
            return new Result(true, currentPlayer + "reject" + friend.getUser().getUsername() + "request for marriage");
        } else if (respond.equals("accept")) {
            friendship.setLevel(4);
            friend.getInventory().getSlot(ring).removeQuantity(1);
            currentPlayer.getInventory().getSlot(ring).addQuantity(1);

            currentPlayer.getRelationService().marry(friend);
            currentPlayer.updateOfMarriage(friend);
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
            if (currentGame.getCurrentPlayer().equals(player)) {
                continue;
            }
            info.append("- ").append(player.getUser().getUsername()).append("\n");
        }

        return new Result(true, info.toString());
    }

    private Result tradeRequest(String command) {
        String username = extractValue(command, "-u", "-t");
        String itemName = extractValue(command, "-i", "-a");
        String amountStr = extractValue(command, "-a", "-p");
        String priceStr = extractValue(command, "-p", null);

        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        int amount;

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }
        Player friend = repo.getUserByUsername(username).getPlayer();

        Item item = currentPlayer.getInventory().getNewItem(itemName);

        if (item == null) {
            return new Result(false, "item not found");
        }

        if (!amountStr.matches("^\\d+$")) {
            return new Result(false, "invalid amount " + amountStr);
        } else {
            amount = Integer.parseInt(amountStr);
        }

        int price = Integer.parseInt(priceStr);

        if (price > currentPlayer.getNumOfCoins()) {
            return new Result(false, "you have not enough coins");
        }

        String message = currentPlayer.getUser().getUsername() + " wants to buy an item from you!\n"
                + "Requested item: " + amount + " × " + item.getName() + "\n"
                + "Offered price: " + price + " coins\n"
                + "Do you accept the request? (yes/no)";

        tradeTalk(username, message);

        RequestTrade trade = new RequestTrade(currentPlayer, friend, item, amount, price);
        currentPlayer.getRelationService().getTrading(friend).addPendingTrade(trade);


        return new Result(true, "your request trade sent to " + friend.getUser().getUsername());
    }

    private Result tradeOffer(String command) {
        String username = extractValue(command, "-u", "-t");
        String itemName = extractValue(command, "-i", "-a");
        String amountStr = extractValue(command, "-a", "-ti");
        String targetName = extractValue(command, "-ti", "-ta");
        String targetAmountStr = extractValue(command, "-ta", null);
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        int amount;
        int targetAmount;

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }

        Player friend = repo.getUserByUsername(username).getPlayer();

        Item item = currentPlayer.getInventory().getNewItem(itemName);
        Item itemTarget = currentPlayer.getInventory().getNewItem(targetName);

        if (item == null) {
            return new Result(false, "item not found");
        }

        if (itemTarget == null) {
            return new Result(false, "item target not found");
        }

        if (!amountStr.matches("^\\d+$") || !targetAmountStr.matches("^\\d+$")) {
            return new Result(false, "invalid amount ");
        }

        else {
            amount = Integer.parseInt(amountStr);
            targetAmount = Integer.parseInt(targetAmountStr);
        }

        Inventory inventory = currentPlayer.getInventory();
        Slot slot = inventory.getSlot(itemName);

        if (slot == null) {
            return new Result(false, "you don't have slot " + targetName);
        }

        if (slot.getQuantity() < amount) {
            return new Result(false, "you have not enough " + targetName);
        }

        String message = currentPlayer.getUser().getUsername() + " wants to trade with you!\n"
                + "They are offering: " + amount + " × " + item.getName() + "\n"
                + "In exchange for: " + targetAmountStr + " × " + itemTarget.getName() + "\n"
                + "Do you accept the trade? (yes/no)";

        tradeTalk(username, message);

        OfferTrade trade = new OfferTrade(currentPlayer, friend, item, amount, itemTarget, targetAmount);
        currentPlayer.getRelationService().getTrading(friend).addPendingTrade(trade);


        return new Result(true, "your offer trade sent to " + friend.getUser().getUsername());
    }

    private Result tradeTalk(String username, String message) {
        Player sender = repo.getCurrentGame().getCurrentPlayer();

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }

        Player receiver = repo.getUserByUsername(username).getPlayer();
        Trading trading = sender.getRelationService().getTrading(receiver);

        trading.sendMessage(sender, message);
        receiver.addNotification(sender, "You have a new message from " + sender.getUser().getNickname());
        return new Result(true, "your message send to " + receiver.getUser().getNickname());
    }

    private Result tradeTalkHistory(String username) {
        Player friend = repo.getUserByUsername(username).getPlayer();

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "player not found");
        }

        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();

        Map<MessageEntry, Boolean> messages = currentPlayer.getRelationService().getTrading(friend).getMessages();

        StringBuilder resultMsg = new StringBuilder();

        List<MessageEntry> keys = new ArrayList<>(messages.keySet());
        for (MessageEntry messageEntry : messages.keySet()) {
            resultMsg.append("%s : \"%s\"".formatted(messageEntry.sender().getUser().getNickname(), messageEntry.message()));
            if (messageEntry.sender() == friend) {
                messages.put(messageEntry, true);
            }
            if (keys.indexOf(messageEntry) != keys.size() - 1) {
                resultMsg.append("\n");
            }
        }
        return new Result(true, resultMsg.toString());
    }

    private Result tradeInWait() {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Map<Integer, Trade> pendingTradesMap = new HashMap<>();

        for (Trading trading : currentPlayer.getRelationService().getTradingships().values()) {
            Map<Integer, Trade> pendingTrades = trading.getPendingTrades(); // فرض: map از id به Trade
            pendingTradesMap.putAll(pendingTrades);
        }

        if (pendingTradesMap.isEmpty()) {
            return new Result(true, "You have no pending trades.");
        }

        StringBuilder result = new StringBuilder("Pending Trades:\n");

        for (Trade trade : pendingTradesMap.values()) {
            String senderName = trade.getSender().getUser().getUsername();
            String receiverName = trade.getReceiver().getUser().getUsername();
            String tradeInfo = "";

            if (trade instanceof RequestTrade reqTrade) {
                tradeInfo = String.format("RequestTrade [ID: %d] from %s to %s: Wants %d × %s for %d coins",
                        trade.getId(), senderName, receiverName, reqTrade.getAmount(), reqTrade.getItem().getName(), reqTrade.getPrice());
            } else if (trade instanceof OfferTrade offerTrade) {
                tradeInfo = String.format("OfferTrade [ID: %d] from %s to %s: Offers %d × %s for %d × %s",
                        trade.getId(), senderName, receiverName, offerTrade.getAmount(), offerTrade.getItem().getName(),
                        offerTrade.getSuggestionamount(), offerTrade.getSuggestionitem().getName());
            }

            result.append(tradeInfo).append("\n");
        }

        return new Result(true, result.toString());
    }

    private Result tradeAll() {
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Map<Integer, Trade> allTradesMap = new HashMap<>();

        for (Trading trading : currentPlayer.getRelationService().getTradingships().values()) {
            Map<Integer, Trade> trades = trading.getAllTrades();
            if (trades != null) {
                allTradesMap.putAll(trades);
            }
        }

        if (allTradesMap.isEmpty()) {
            return new Result(true, "You have no trades.");
        }

        StringBuilder result = new StringBuilder("All Trades:\n");

        for (Trade trade : allTradesMap.values()) {
            String senderName = trade.getSender().getUser().getUsername();
            String receiverName = trade.getReceiver().getUser().getUsername();
            String tradeInfo = "";

            if (trade instanceof RequestTrade reqTrade) {
                tradeInfo = String.format("RequestTrade [ID: %d] from %s to %s: Wants %d × %s for %d coins",
                        trade.getId(), senderName, receiverName, reqTrade.getAmount(), reqTrade.getItem().getName(), reqTrade.getPrice());
            } else if (trade instanceof OfferTrade offerTrade) {
                tradeInfo = String.format("OfferTrade [ID: %d] from %s to %s: Offers %d × %s for %d × %s",
                        trade.getId(), senderName, receiverName, offerTrade.getAmount(), offerTrade.getItem().getName(),
                        offerTrade.getSuggestionamount(), offerTrade.getSuggestionitem().getName());
            }

            result.append(tradeInfo).append("\n");
        }

        return new Result(true, result.toString());
    }



    private Result tradeResponse(String command) {
        boolean isAccept = command.contains("-accept");
        String idStr = extractValue(command, "-i", null);

        if (!idStr.matches("\\d+")) {
            return new Result(false, "invalid id");
        }
        int id = Integer.parseInt(idStr);
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Trade foundTrade = null;
        Character tradePartner = null;

        for (Map.Entry<Character, Trading> entry : currentPlayer.getRelationService().getTradingships().entrySet()) {
            Trade trade = entry.getValue().getPendingTrade(id);
            if (trade != null && trade.getReceiver().equals(currentPlayer)) {
                foundTrade = trade;
                tradePartner = entry.getKey();
                break;
            }
        }

        if (foundTrade == null) {
            return new Result(false, "trade not found or not for you");
        }

        Player sender = foundTrade.getSender();
        Player receiver = foundTrade.getReceiver();
        Inventory senderInventory = sender.getInventory();
        Inventory receiverInventory = receiver.getInventory();
        Trading senderTrading = sender.getRelationService().getTrading(receiver);

        Friendship friendship = sender.getRelationService().getFriendship(receiver);
        if (isAccept) {
            if (foundTrade instanceof RequestTrade reqTrade) {

                Slot slot = receiverInventory.getSlot(reqTrade.getItem().getName());
                if (slot == null || slot.getQuantity() < reqTrade.getAmount()) {
                    return new Result(false, "you don't have enough item to complete the trade");
                }

                Slot slot2 = senderInventory.getSlot(reqTrade.getItem().getName());
                slot.removeQuantity(reqTrade.getAmount());
                slot.addQuantity(reqTrade.getAmount());

                sender.consumeCoins(reqTrade.getPrice());
                receiver.increaseCoins(reqTrade.getPrice());
            }

            else if (foundTrade instanceof OfferTrade offerTrade) {

                Slot senderSlot = senderInventory.getSlot(offerTrade.getItem().getName());
                Slot receiverSlot = receiverInventory.getSlot(offerTrade.getSuggestionitem().getName());

                if (senderSlot == null || senderSlot.getQuantity() < offerTrade.getAmount()) {
                    return new Result(false, "sender doesn't have enough item");
                }

                if (receiverSlot == null || receiverSlot.getQuantity() < offerTrade.getSuggestionamount()) {
                    return new Result(false, "you don't have enough item");
                }

                senderSlot.removeQuantity(offerTrade.getAmount());
                receiverSlot.addQuantity(offerTrade.getAmount());

                receiverSlot.removeQuantity(offerTrade.getSuggestionamount());
                senderSlot.addQuantity(offerTrade.getSuggestionamount());
            }

            senderTrading.sendMessage(receiver, "Your trade has been accepted.");
            sender.addNotification(receiver, "Trade accepted by " + receiver.getUser().getNickname());
            friendship.increaseXp(Friendship.DEAL_SUCCESS_XP);
        }

        else {
            senderTrading.sendMessage(receiver, "Your trade has been rejected.");
            sender.addNotification(receiver, "Trade rejected by " + receiver.getUser().getNickname());
            friendship.decreaseXp(Friendship.DEAL_FAILURE_XP);
        }

        currentPlayer.getRelationService().getTrading((Character) tradePartner).removePendingTrade(id);

        return new Result(true, "trade " + (isAccept ? "accepted" : "rejected") + " successfully.");
    }

    private String extractValue(String command, String startFlag, String endFlag) {
        String patternString;

        if (endFlag != null) {
            patternString = startFlag + " (.*?) " + endFlag;
        } else {
            patternString = startFlag + " (.*)";
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }
}
