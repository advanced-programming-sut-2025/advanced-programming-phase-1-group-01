package com.stardew_valley.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.Main;
import com.stardew_valley.models.data.FileManager;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.data.User;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;
import com.stardew_valley.models.enums.commands.LoginMenuCommands;
import com.stardew_valley.views.LoginMenuView;

import java.security.SecureRandom;
import java.util.List;


public class SignUpMenuController extends Controller {

    public SignUpMenuController(Repository repo) {

        super(repo);


        //D
        //adding four users to test
        if (getRepo().getUserByUsername("1") == null) {
            String path = getRandomAvatarPath();
            User user = new User("1", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            getRepo().addUser(user);
        }

        if (getRepo().getUserByUsername("2") == null) {
            String path = getRandomAvatarPath();
            User user = new User("2", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            getRepo().addUser(user);
        }

        if (getRepo().getUserByUsername("3") == null) {
            String path = getRandomAvatarPath();
            User user = new User("3", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            getRepo().addUser(user);
        }

        if (getRepo().getUserByUsername("4") == null) {
            String path = getRandomAvatarPath();
            User user = new User("4", "a", "a", "a", Gender.MALE, SecurityQuestion.QUESTION1, "a", path);

            getRepo().addUser(user);
        }
    }

    @Override
    public Result handleCommand(String command) {
        LoginMenuCommands matchedCommand = null;

        for (LoginMenuCommands cmd : LoginMenuCommands.values()) {
            if (command.matches(cmd.getRegex())) {
                matchedCommand = cmd;
                break;
            }
        }

        if (matchedCommand == null) {
            return new Result(false, "invalid command!");
        }

//        return switch (matchedCommand) {
//            case MENU_ENTER -> new Result(false, "You cannot navigate to other menus from here");
//            case MENU_EXIT -> menuExit();
//            case SHOW_CURRENT_MENU -> new Result(true, "now you are in login menu");
//            case REGISTER -> register(command);
//            case PICK_QUESTION -> pickQuestion(command);
//            case LOGIN -> login(command);
//            case FORGET_PASSWORD -> forgetPassword(command);
//            case ANSWER -> answer(command);
//            case LOAD_USER -> saveUser();
//        };
        return null;
    }

    @Override
    protected Result menuExit() {
        System.exit(0);
        return null;
    }

    public void register(List<String> data, Label messageLabel) {
        String username = data.get(0);
        String password = data.get(1);
        String confirmPassword = data.get(2);
        String nickname = data.get(3);
        String email = data.get(4);
        String genderStr = data.get(5);
        String securityQuestionStr = data.get(6);
        String answer = data.get(7);

        if (username.isEmpty()) {
            messageLabel.setText("Please fill username field.");
            return;
        }

        if (repo.getUserByUsername(username) != null) {
            handleUsernameTaken(username, messageLabel);
            return;
        }

        if (!isUsernameValid(username)) {
            messageLabel.setText("Username format is invalid!");
            return;
        }

        if (password.isEmpty()) {
            messageLabel.setText("Please fill password field.");
            return;
        }

        if (confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill confirm password field.");
            return;
        }

        String passwordValidationError = isPasswordValid(password);
        if (passwordValidationError != null) {
            messageLabel.setText(passwordValidationError);
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Re-entered password is incorrect.");
            return;
        }

        if (nickname.isEmpty()) {
            messageLabel.setText("Please fill nickname field.");
            return;
        }

        if (email.isEmpty()) {
            messageLabel.setText("Please fill email field.");
            return;
        }

        if (!isEmailValid(email)) {
            messageLabel.setText("Email format is invalid!");
            return;
        }

        if (answer.isEmpty()) {
            messageLabel.setText("Please fill answer field.");
            return;
        }

        SecurityQuestion securityQuestion = null;
        for (SecurityQuestion q : SecurityQuestion.values()) {
            if (q.getQuestion().equals(securityQuestionStr)) {
                securityQuestion = q;
                break;
            }
        }

        Gender gender = null;
        if (Gender.MALE.name().equals(genderStr)) {
            gender = Gender.MALE;
        }
        else  {
            gender = Gender.FEMALE;
        }

        String path = getRandomAvatarPath();
        User user = new User(username, password, nickname, email, gender, securityQuestion, answer,path);

        repo.addUser(user);
        messageLabel.setText("User successfully registered!");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(repo)));
            }
        },  //2
            0.01f
        );
    }

    private void handleUsernameTaken(String baseUsername, Label messageLabel) {

        String newUsername = baseUsername + (int) (Math.random() * 1000);
        while (repo.getUserByUsername(newUsername) != null) {
            newUsername = baseUsername + (int) (Math.random() * 1000);
        }

         messageLabel.setText("This username is already taken. How about this one : " + newUsername);
    }

    public void randomPassword(Label randomPasswordLabel) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!#$%^&*()_+-=[]{}|;:',.<>?/";
        String all = upper + lower + digits + symbols;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(symbols.charAt(random.nextInt(symbols.length())));

        for (int i = 4; i < 10; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        randomPasswordLabel.setText("random password: " + password);
    }

    public void login(Label messageLabel) {
        messageLabel.setText("Loading Login Menu...");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(repo)));
            }
        },  //2
            0.01f
        );
    }

    public void exit(Label messageLabel) {
        messageLabel.setText("Bye!");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        },  //2
            0.01f
        );
    }

    private boolean isUsernameValid(String username) {
        return true;
//            username.matches("^[a-zA-Z0-9\\-]+$");
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
    }

    private String isPasswordValid(String password) {
        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter.";
        }
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit.";
        }
        if (!password.matches(".*[?><,\"';:\\\\|\\[\\]{}+=)(*&^%$#!].*")) {
            return "Password must contain at least one special character.";
        }
        return null;
    }

    private String getRandomAvatarPath() {
        String[] avatars = {
            "avatars/avatar1.png",
            "avatars/avatar2.png",
            "avatars/avatar3.png",
            "avatars/avatar4.png",
            "avatars/avatar5.png",
            "avatars/avatar6.png",
            "avatars/avatar7.png",
            "avatars/avatar8.png",
        };
        return avatars[MathUtils.random(avatars.length - 1)];
    }
}
