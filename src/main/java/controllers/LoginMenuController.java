package controllers;

import models.data.FileManager;
import models.data.Repository;
import models.Result;
import models.data.User;
import models.enums.Gender;
import models.enums.SecurityQuestion;
import models.enums.commands.LoginMenuCommands;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController extends Controller {

    public LoginMenuController(Repository repo) {

        super(repo);
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

        return switch (matchedCommand) {
            case MENU_ENTER -> new Result(false, "You cannot navigate to other menus from here");
            case MENU_EXIT -> menuExit();
            case SHOW_CURRENT_MENU -> new Result(true, "now you are in login menu");
            case REGISTER -> register(command);
            case PICK_QUESTION -> pickQuestion(command);
            case LOGIN -> login(command);
            case FORGET_PASSWORD -> forgetPassword(command);
            case ANSWER -> answer(command);
        };
    }

    @Override
    protected Result menuExit() {
        System.exit(0);
        return null;
    }

    private Result register(String command) {
        String username = extractValue(command,"-u","-p");
        String nickname = extractValue(command,"-n","-e");
        String email = extractValue(command,"-e","-g");
        String gender = extractValue(command,"-g",null);

        String passwordAndreEnterPassword = extractValue(command,"-p","-n");
        String[] passwordParts = passwordAndreEnterPassword.split(" ");
        String password = passwordParts[0];
        String reEnterPassword = passwordParts[1];

        if (passwordAndreEnterPassword.equals("random")) {
            return randomPassword(command);
        }

        if (repo.getUserByUsername(username) != null) {
            return handleUsernameTaken(username);
        }

        if (!isUsernameValid(username)) {
            return new Result(false, "Username format is invalid!");
        }

        if (!isEmailValid(email)) {
            return new Result(false, "Email format is invalid!");
        }

        if (isPasswordValid(password) != null) {
            return new Result(false, isPasswordValid(password));
        }

        if (!password.equals(reEnterPassword)) {
            return new Result(false, "Re-entered password is incorrect.");
        }

        new User(username,password,nickname,email,Gender.valueOf(gender));
        return new Result(true, """
                User registered successfully!
                please pick a question :
                1.What is your dream job?
                2.What is your favorite color?
                3.What is your favorite team?
                
                """);
    }

    private Result handleUsernameTaken(String baseUsername) {

        String newUsername = baseUsername + (int) (Math.random() * 1000);
        while (repo.getUserByUsername(newUsername) != null) {
            newUsername = baseUsername + (int) (Math.random() * 1000);
        }

        return new Result(false, "This username is already taken. How about this one: " + newUsername);
    }

    private Result randomPassword(String command) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()_+-=[]{}|;:',.<>?/";
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

        return new Result(true,"You can use this random password :" + password);
    }

    private Result pickQuestion(String command) {
        String questionNumberStr = extractValue(command,"-q","-a");
        int questionNumber = Integer.parseInt(questionNumberStr);
        String answer = extractValue(command,"-a","-c");
        String answerConfirm = extractValue(command,"-c",null);

        if (questionNumber > 3) {
            return new Result(false, "Please select a number between 1 and 3");
        }

        if (!answer.equals(answerConfirm)) {
            return new Result(false, "Your answer does not match with confirmation answer.");
        }

        User user = repo.getCurrentUser();

        switch (questionNumber) {
            case 1:
                user.setSecurityQuestion(SecurityQuestion.QUESTION1);
                break;
            case 2:
                user.setSecurityQuestion(SecurityQuestion.QUESTION2);
                break;
            case 3:
                user.setSecurityQuestion(SecurityQuestion.QUESTION3);
                break;
        }

        user.setSecurityAnswer(answer);
        return new Result(true,"Your security question and answer are saved.");
    }

    private Result login(String command) {
        String username = extractValue(command,"-u","-p");
        String password = extractValue(command,"-p","-s");
        String stayLoggedIn = extractValue(command,"-s",null);

        User user = repo.getUserByUsername(username);

        if (user == null) {
            return new Result(false, "User not found!");
        }

        if (!user.getPassword().equals(password)) {
            return new Result(false, "Wrong password!");
        }

        if (stayLoggedIn.equals("yes")) {
            FileManager.saveLoginInfo(username, password);
        }

        repo.setCurrentUser(user);
        return new Result(true,"You are logged in!");
    }

    private Result forgetPassword(String command) {
        String username = extractValue(command,"-u",null);

        if (repo.getUserByUsername(username) == null) {
            return new Result(false, "User not found!");
        }

        User user = repo.getUserByUsername(username);

        return new Result(true,user.getSecurityQuestion().getQuestion());
    }

    private Result answer(String command) {
        String answer = extractValue(command, "-a",null);

        if (answer == null) {
            return new Result(false, "Answer not found!");
        }

        User user = repo.getCurrentUser();
        if (!user.getSecurityAnswer().equals(answer)) {
            return new Result(false, "Wrong answer!");
        }

        return new Result(true,"Your password is : " + user.getPassword());
    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9\\-]+$");
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
        if (!password.matches("[?><,\"';:\\\\/|\\\\\\[\\]{}+=)(*&^%$#!-]")) {
            return "Password must contain at least one special character.";
        }
        return null;
    }

    private String extractValue(String command, String startFlag, String endFlag) {
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