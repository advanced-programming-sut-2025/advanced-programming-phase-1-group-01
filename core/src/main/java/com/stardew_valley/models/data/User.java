package com.stardew_valley.models.data;

import com.stardew_valley.models.Game;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.SecurityQuestion;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Game game;
    private Player player;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private String avatarPath;
    private int numOfPlayedGames;
    private int highestEarnedBalance;
    private String[] filesList;

    public User(String username, String password, String nickname, String email, Gender gender, SecurityQuestion securityQuestion, String securityAnswer, String avatarPath) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.avatarPath = avatarPath;
        player = new Player(this);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNumOfPlayedGames() {
        return numOfPlayedGames;
    }

    public void setNumOfPlayedGames(int numOfPlayedGames) {
        this.numOfPlayedGames = numOfPlayedGames;
    }

    public int getHighestEarnedBalance() {
        return highestEarnedBalance;
    }

    public void setHighestEarnedBalance(int highestEarnedBalance) {
        this.highestEarnedBalance = highestEarnedBalance;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String userToJson() {
        User user = this;
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"username\":\"").append(user.getUsername()).append("\",");
        sb.append("\"password\":\"").append(user.getPassword()).append("\",");
        sb.append("\"nickname\":\"").append(user.getNickname()).append("\",");
        sb.append("\"email\":\"").append(user.getEmail()).append("\",");

        sb.append("\"gender\":\"").append(user.getGender().toString()).append("\",");

        sb.append("\"securityQuestion\":\"").append(user.getSecurityQuestion().toString()).append("\",");
        sb.append("\"securityAnswer\":\"").append(user.getSecurityAnswer()).append("\",");

        sb.append("\"avatarPath\":\"").append(user.getAvatarPath()).append("\"");

        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", nickname='" + nickname + '\'' +
            ", email='" + email + '\'' +
            ", gender=" + gender +
            ", securityQuestion=" + securityQuestion +
            ", securityAnswer='" + securityAnswer + '\'' +
            ", avatarPath='" + avatarPath + '\'' +
            ", player=" + player +
            '}';
    }

    public void setFilesList(String[] filesList) {
        this.filesList = filesList;
    }

    public String[] getFilesList() {
        return filesList;
    }

}
