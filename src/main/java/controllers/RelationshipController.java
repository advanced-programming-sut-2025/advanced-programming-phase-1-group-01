package controllers;

import models.Game;
import models.MessageEntry;
import models.Result;
import models.character.player.Player;
import models.data.Repository;
import models.data.User;
import models.enums.commands.RelationshipCommands;
import models.relations.Friendship;
import models.relations.RelationshipService;

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
        if (receiver == null) {
            return new Result(false, "player not found");
        }

        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();
        Friendship friendship = currentPlayer.getRelationService().getFriendship(receiver);

        friendship.sendMessage(currentPlayer, message);
        receiver.addNotification(currentPlayer, "You have a new message from " + currentPlayer.getUser().getUsername());

        return new Result(true, "your message sent");
    }

    private Result talkHistory(String username) {
        Player friend = repo.getUserByUsername(username).getPlayer();
        Player currentPlayer = repo.getCurrentGame().getCurrentPlayer();

        Map<MessageEntry, Boolean> messages = currentPlayer.getRelationService().getFriendship(friend).getMessages();


    }
}
