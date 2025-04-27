package models.dateTime;

import models.Game;

import java.util.*;

public class TimeManager {
    private List<DateTime> eventTimes;
    private Game game;
    private DateTime now;

    private final int START_HOUR_OF_DAY = 9;
    private final int END_HOUR_OF_DAY = 22;

    private final int DAY_START_HOUR = 9;
    private final int DAY_END_HOUR = 22;
    private final Season START_SEASON = Season.SPRING;
    private final int START_DAY = 1;
    private final WeekDay START_WEEKDAY = WeekDay.MONDAY;
    private final int START_YEAR = 2025;

    public TimeManager(Game game) {
        this.eventTimes = new ArrayList<>();
        this.game = game;
        this.now = game.getCurrentDateTime();
    }

    public void skipToMorning() {}

    public List<DateTime> getEventTimes() {
        return eventTimes;
    }

    public void addEvent(DateTime dateTime, Event event) {
        dateTime.addEvent(event);
        event.trigger();
    }
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public DateTime getNow() {
        return now;
    }

    public void setNow(DateTime now) {
        this.now = now;
        updateEvents();
    }

    public void updateEvents() {

    }
}