package controllers;

import models.Result;
import models.data.Repository;

public class InventoryController extends Controller {
    InventoryController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result inventoryShow() {
        return null;
    }

    private Result inventoryTrash() {
        return null;
    }


}
