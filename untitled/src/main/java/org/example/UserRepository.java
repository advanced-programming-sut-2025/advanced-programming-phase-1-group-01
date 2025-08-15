package org.example;

import com.mongodb.client.*;
import org.bson.Document;

import java.security.MessageDigest;

public class UserRepository {
    private final MongoCollection<Document> users;

    public UserRepository() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("user_db");
        users = db.getCollection("users");
    }

    public void registerUserFull(String username, String password, String nickname, String email, String gender) {
        String passwordHash = hash(password);

        Document user = new Document("username", username)
            .append("passwordHash", passwordHash)
            .append("nickname", nickname)
            .append("email", email)
            .append("gender", gender);

        users.insertOne(user);
    }

    public boolean checkUserExists(String username) {
        return users.find(new Document("username", username)).first() != null;
    }

    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printAllUsers() {
        FindIterable<Document> allUsers = users.find();
        for (Document user : allUsers) {
            System.out.println("Username: " + user.getString("username"));
            System.out.println("Nickname: " + user.getString("nickname"));
            System.out.println("Email: " + user.getString("email"));
            System.out.println("Gender: " + user.getString("gender"));
            System.out.println("Password: " + user.getString("passwordHash"));
            System.out.println("---------------------------");
        }
    }

}
