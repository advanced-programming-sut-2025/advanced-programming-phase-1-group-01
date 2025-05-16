package controllers;

import models.Result;
import models.data.Repository;
import models.enums.commands.DateAndTimeCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateAndTimeController extends Controller {
    public DateAndTimeController(Repository repo) {super(repo);}

    @Override
    public Result handleCommand(String commandLine) {
        if(commandLine.matches(DateAndTimeCommands.TIME.getRegex())) {
            return handleShowTime(commandLine);
        } else if(commandLine.matches(DateAndTimeCommands.DATE.getRegex())) {
            return handleShowDate(commandLine);
        } else if(commandLine.matches(DateAndTimeCommands.DATETIME.getRegex())) {
            return handleShowDateTime(commandLine);
        } else if(commandLine.matches(DateAndTimeCommands.DAY_OF_THE_WEEK.getRegex())) {
            return handleShowDayOfTheWeek(commandLine);
        } else if(commandLine.matches(DateAndTimeCommands.CHEAT_ADVANCE_TIME.getRegex())) {
            return handleCheatTime(commandLine);
        } else if(commandLine.matches(DateAndTimeCommands.CHEAT_ADVANCE_DATE.getRegex())) {
            return handleCheatDate(commandLine);
        } else return new Result(false , "invalid command");
    }


    private Result handleShowTime(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.TIME.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();
            return new Result(true , String.valueOf(hour));
        } else return new Result(false , "invalid command");
    }

    private Result handleShowDate(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.DATE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            int day = repo.getCurrentGame().getTimeManager().getNow().getDay();
            return new Result(true , String.valueOf(day));
        } else return new Result(false , "invalid command");
    }

    private Result handleShowDateTime(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.DATETIME.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            int year = repo.getCurrentGame().getTimeManager().getNow().getYear();
            String season = repo.getCurrentGame().getTimeManager().getNow().getSeason().name();
            int day = repo.getCurrentGame().getTimeManager().getNow().getDay();
            int hour = repo.getCurrentGame().getTimeManager().getNow().getHour();
            String output = year + "," + season + "-," + day + " : " + hour + "h";
            return new Result(true , output);
        } else return new Result(false , "invalid command");
    }

    private Result handleShowDayOfTheWeek(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.DAY_OF_THE_WEEK.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            String dayOfTheWeek = repo.getCurrentGame().getTimeManager().getNow().getWeekDay().name();
            return new Result(true , dayOfTheWeek);
        } else return new Result(false , "invalid command");
    }

    private Result handleCheatTime(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.CHEAT_ADVANCE_TIME.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            int hour = Integer.parseInt(matcher.group("hour"));
            repo.getCurrentGame().getTimeManager().getNow().advanceHour(hour);
            return new Result(true , "");
        } return new Result(false , "invalid command");
    }

    private Result handleCheatDate(String commandLine) {
        Pattern pattern = Pattern.compile(DateAndTimeCommands.DATE.getRegex());
        Matcher matcher = pattern.matcher(commandLine);

        if(matcher.matches()) {
            int day = Integer.parseInt(matcher.group("day"));
            repo.getCurrentGame().getTimeManager().getNow().advanceDay(day);
            return new Result(true , "");
        } return new Result(false , "invalid command");

    }
}
