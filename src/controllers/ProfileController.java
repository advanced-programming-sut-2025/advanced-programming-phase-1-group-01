package controllers;

import models.Result;
import models.data.Repository;

public class ProfileController extends Controller {
    public ProfileController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result changeUsername(String username) {
        return null;
    }

    private Result changeNickname(String nickname) {
        return null;
    }

    private Result changeEmail(String email) {
        return null;
    }

    private Result changePassword(String oldPassword, String newPassword) {
        return null;
    }

    private Result showUserInfo() {
        return null;
    }
}
