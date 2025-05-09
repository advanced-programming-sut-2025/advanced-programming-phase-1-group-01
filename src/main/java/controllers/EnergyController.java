package controllers;

import models.Result;
import models.character.player.Energy;
import models.data.Repository;
import models.enums.commands.EnergyCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnergyController extends Controller {
    EnergyController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String command) {
        EnergyCommands matchedCommand = null;

        for (EnergyCommands cmd : EnergyCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command!");
        }

        switch (matchedCommand) {
            case ENERGY_SHOW:
                return showEnergy();
            case CHEAT_ENERGY:
                return cheatEnergySet(command);
            case ENERGY_UNLIMITED:
                cheatEnergyUnlimited();
        }
        return null;
    }

    Energy energy = repo.getCurrentGame().getCurrentPlayer().getEnergy();

    private Result showEnergy() {
        return new Result(true, energy.toString());
    }

    private Result cheatEnergySet(String command) {

        String valueStr = extractValue(command,"-v",null);
        int value = Integer.parseInt(valueStr);
        energy.setAmount(value);

        return new Result(true,"Sets the energy to a specified value");
    }

    private void cheatEnergyUnlimited() {
        energy.setAmount(Double.MAX_VALUE);
        new Result(true, "Sets the energy to a unlimited value");
    }

    public static String extractValue(String command, String startFlag, String endFlag) {
        String patternString;

        if (endFlag != null) {
            patternString = startFlag + " (.*?) " + endFlag;
        }

        else {
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
