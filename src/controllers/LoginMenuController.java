package controllers;

import models.data.Repository;
import models.Result;
import models.enums.Gender;

public class LoginMenuController extends Controller {
    LoginMenuController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    @Override
    protected Result menuExit() {
        System.exit(0);
        return null;
    }

    private Result register(String username, String password, String nickname, String email, Gender gender) {
        return null;
    }

    private String randomPassword() {
        return null;
    }

    private Result pickQuestion() {
        return null;
    }

    private Result login(String username, String password, boolean enteredStayLoggedIn) {
        return null;
    }

    private Result forgetPassword(String username) {
        return null;
    }

    private Result answer(String answer) {
        return null;
    }

    private boolean isUsernameValid(String username) {
        return false;
    }

    private boolean isEmailValid() {
        return false;
    }

    private boolean isPasswordValid(String password) {
        return false;
    }
}