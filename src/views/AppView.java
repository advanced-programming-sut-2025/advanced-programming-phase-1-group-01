package views;

import java.util.Scanner;

public class AppView {
    private static final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
