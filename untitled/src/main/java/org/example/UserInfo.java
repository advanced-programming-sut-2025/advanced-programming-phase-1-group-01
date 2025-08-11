package org.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserInfo {
    private String username;
    private String password;
    private String securityQuestionType;
    private String getSecurityQuestionAnswer;

    public UserInfo(String username, String password, String securityQuestionType, String getSecurityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestionType = securityQuestionType;
        this.getSecurityQuestionAnswer = getSecurityQuestionAnswer;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSecurityQuestionType() {
        return securityQuestionType;
    }

    public String getGetSecurityQuestionAnswer() {
        return getSecurityQuestionAnswer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestionType(String securityQuestionType) {
        this.securityQuestionType = securityQuestionType;
    }

    public void setGetSecurityQuestionAnswer(String getSecurityQuestionAnswer) {
        this.getSecurityQuestionAnswer = getSecurityQuestionAnswer;
    }
}
