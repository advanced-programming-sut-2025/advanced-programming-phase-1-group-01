package controllers;

import models.Result;
import models.character.NPC.NPC;
import models.character.NPC.NPCVillage;
import models.character.player.Player;
import models.data.Repository;
import models.dateTime.Season;
import models.enums.commands.NPCCommands;
import models.weather.Weather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NPCController extends Controller {
    NPCController(Repository repository) {super(repository);}

    @Override
    public Result handleCommand(String commandLine) {
        if (commandLine.matches(NPCCommands.MEET_NPC.getRegex())) {
            return handleMeetNpc(commandLine);
        } else if (commandLine.matches(NPCCommands.GIFT_NPC.getRegex())) {
            return handleGiftNpc(commandLine);
        } else if (commandLine.matches(NPCCommands.FRIENDSHIP_NPC_LIST.getRegex())) {
            return handleFriendshipList(commandLine);
        } else if (commandLine.matches(NPCCommands.QUESTS_LIST.getRegex())) {
            return handleQuestList(commandLine);
        } else if (commandLine.matches(NPCCommands.QUEST_FINISH.getRegex())) {
            return handleQuestFinish(commandLine);
        } else return new Result(false, "Unknown command");
    }


    private Result handleMeetNpc(String commandLine) {
        Pattern pattern = Pattern.compile(NPCCommands.MEET_NPC.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String name = matcher.group("name");
            String message = matcher.group("message");

            NPC npc = repo.getCurrentGame().getNPCVillage().getNPCIdByName(name);
            Player player = repo.getCurrentGame().getCurrentPlayer();
            Weather weather = repo.getCurrentGame().getWeatherManager().getTodayWeather();
            Season season = repo.getCurrentGame().getTimeManager().getNow().getSeason();
            int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();
            if (npc == null) {
                return new Result(false, "NPC with name " + name + " not found");
            }
            return new Result(true, npc.talkWithPlayer(player, message, season, weather, hour));
        } else return new Result(false, "Unknown command");
    }

    private Result handleGiftNpc(String commandLine) {
        Pattern pattern = Pattern.compile(NPCCommands.GIFT_NPC.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            String name = matcher.group("name");
            String item = matcher.group("item");

            NPC npc = repo.getCurrentGame().getNPCVillage().getNPCIdByName(name);
            Player player = repo.getCurrentGame().getCurrentPlayer();

            if (npc == null) {
                return new Result(false, "NPC with name " + name + " not found");
            }

            return new Result(true, npc.giftNPC(player, item));
        } return new Result(false, "Unknown command");
    }

    private Result handleFriendshipList(String commandLine) {
        Pattern pattern = Pattern.compile(NPCCommands.FRIENDSHIP_NPC_LIST.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            NPCVillage npcVillage = repo.getCurrentGame().getNPCVillage();
            Player player = repo.getCurrentGame().getCurrentPlayer();

            return new Result(true, npcVillage.getListedNPCFriendships(player));

        } else return new Result(false, "Unknown command");
    }

    private Result handleQuestList(String commandLine) {
        Pattern pattern = Pattern.compile(NPCCommands.QUESTS_LIST.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            NPCVillage npcVillage = repo.getCurrentGame().getNPCVillage();
            Player player = repo.getCurrentGame().getCurrentPlayer();

            return new Result(true, npcVillage.getListedQuests(player));
        } else return new Result(false, "Unknown command");
    }

    private Result handleQuestFinish(String commandLine) {
        Pattern pattern = Pattern.compile(NPCCommands.QUEST_FINISH.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index"));

            Player player = repo.getCurrentGame().getCurrentPlayer();
            NPCVillage npcVillage = repo.getCurrentGame().getNPCVillage();

            return new Result(true, npcVillage.finishQuest(player, index));
        } else return new Result(false, "Unknown command");
    }
}
