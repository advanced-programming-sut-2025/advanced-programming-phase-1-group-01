package controllers;

import models.Position;
import models.Result;
import models.data.Repository;
import models.enums.Direction;
import models.farming.*;
import models.foraging.ForagingCrop;
import models.foraging.ForagingTree;

public class FarmingController extends Controller {
    FarmingController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result showCropInfo(String name) {
        FarmingEnum farmingConstant = null;
        
        if (CropType.fromString(name) != null) {
            farmingConstant = CropType.fromString(name);            
        } else if (TreeType.fromString(name) != null) {
            farmingConstant = TreeType.fromString(name);
        } else if (ForagingCrop.fromString(name) != null) {
            farmingConstant = ForagingCrop.fromString(name);
        } else if (ForagingTree.fromString(name) != null) {
            farmingConstant = ForagingTree.fromString(name);
        } else {
            return new Result(false, "crop not found");
        }

        return new Result(true, farmingConstant.toString());
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