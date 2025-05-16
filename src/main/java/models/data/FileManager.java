package models.data;

import models.enums.Gender;
import models.enums.SecurityQuestion;

import java.io.*;
import java.util.Properties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void saveToFile(String username, String password, String nickname, String email, String gender,String securityQuestion, String securityAnswer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Nickname: " + nickname + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("securityQuestion: " + securityQuestion + "\n");
            writer.write("securityAnswer: " + securityAnswer + "\n");
        } catch (IOException e) {
            System.err.println("error " + e.getMessage());
        }
    }

    public static User loadUserFromFile(Repository repo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String username = reader.readLine().split(": ", 2)[1];
            String password = reader.readLine().split(": ", 2)[1];
            String nickname = reader.readLine().split(": ", 2)[1];
            String email = reader.readLine().split(": ", 2)[1];
            String gender = reader.readLine().split(": ", 2)[1];
            String securityQuestion = reader.readLine().split(": ", 2)[1];
            String securityAnswer = reader.readLine().split(": ", 2)[1];

            User user = new User(username, password, nickname, email, Gender.valueOf(gender));
            user.setSecurityQuestion(SecurityQuestion.valueOf(securityQuestion));
            user.setSecurityAnswer(securityAnswer);
            return user;
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.err.println("error" + e.getMessage());
        }
        return null;
    }

    public static void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", false))) {
        } catch (IOException e) {
            System.err.println("error" + e.getMessage());
        }
    }

}
