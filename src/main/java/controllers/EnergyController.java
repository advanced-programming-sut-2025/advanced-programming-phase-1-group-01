package controllers;

import models.Result;
import models.character.player.Energy;
import models.data.Repository;
import models.enums.commands.EnergyCommands;

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
                return cheatEnergyUnlimited();
        }
        return new Result(false, "invalid command");
    }


    private Result showEnergy() {
        Energy energy = repo.getCurrentGame().getCurrentPlayer().getEnergy();
        return new Result(true, energy.toString());
    }

    private Result cheatEnergySet(String command) {
        Energy energy = repo.getCurrentGame().getCurrentPlayer().getEnergy();
        String[] commandParts = command.split(" ");
        String valueStr = commandParts[3];
        int value = Integer.parseInt(valueStr);
        energy.setAmount(value);

        return new Result(true, "Sets the energy to a specified value");
    }

    private Result cheatEnergyUnlimited() {
        Energy energy = repo.getCurrentGame().getCurrentPlayer().getEnergy();
        energy.setAmount(Double.POSITIVE_INFINITY);
        return new Result(true, "Sets the energy to a unlimited value");
    }
}
