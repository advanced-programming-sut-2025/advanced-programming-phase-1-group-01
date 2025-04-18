package models;

import models.character.player.Player;
import models.enums.Gender;

import java.util.List;

public class User {
    private Game game;
    private Player player;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;
    private List<String> securityAnswers;

    public User(String username, String password, String nickname, String email, Gender gender) {
        this.username = username;
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

    public List<String> getSecurityAnswer() {
        return securityAnswers;
    }

    public void addSecurityAnswer(String securityAnswer) {
        securityAnswers.add(securityAnswer);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
