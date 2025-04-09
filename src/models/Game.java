package models;

import models.dateTime.DateTime;
import models.dateTime.Season;
import models.dateTime.WeekDay;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, User> users;
    private DateTime currentDateTime;

    private final int DAY_START_HOUR = 9;
    private final int DAY_END_HOUR = 22;
    private final Season START_SEASON = Season.SPRING;
    private final int START_DAY = 1;
    private final WeekDay START_WEEKDAY = WeekDay.MONDAY;
    private final int START_YEAR = 2025;

    public Game() {
        users = new HashMap<>();
        currentDateTime = new DateTime(START_YEAR, START_SEASON, START_WEEKDAY, START_DAY, DAY_START_HOUR);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public DateTime getCurrentDateTime() {
        return currentDateTime;
    }
}