package models.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileManager {

    public static void saveLoginInfo(String username, String password) {
        Properties props = new Properties();
        props.setProperty("username", username);
        props.setProperty("password", password);

        try (FileWriter writer = new FileWriter("login.txt")) {
            props.store(writer, "Login Information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties loadLoginInfo() {
        Properties props = new Properties();
        try (FileReader reader = new FileReader("login.txt")) {
            props.load(reader);
            return props;
        } catch (IOException e) {
            return null;
        }
    }

    public static void clearLoginInfo() {
        java.io.File file = new java.io.File("login.txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
