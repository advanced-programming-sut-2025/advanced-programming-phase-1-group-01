package controllers;

import models.Position;
import models.Result;
import models.data.Repository;
import models.enums.Direction;
import models.farming.SeedType;

public class FarmingController extends Controller {
    FarmingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result showCropInfo(String name) {
        return null;
    }

    private Result plant(SeedType seedType, Direction dir) {
        return null;
    }

    private Result showPlantInfo(Position position) {
        return null;
    }

    private Result howMuchWater() {
        return null;
    }


}