package models.dateTime;

import java.util.List;

public class DateTime {
    private int year;
    private Season season;
    private WeekDay weekDay;
    private int day;
    private int hour;

    private List<Event> events;

    private static final int MAX_SEASON_OF_YEAR = 4;
    private static final int MAX_DAY_OF_SEASON = 28;
    private static final int MAX_HOUR_OF_DAY = 23;

    public DateTime(int year, Season season, WeekDay weekDay, int day, int hour) {
        this.year = year;
        this.season = season;
        this.weekDay = weekDay;
        this.day = day;
        this.hour = hour;
    }

    public void advanceHour() {
        hour++;

        if (hour > MAX_HOUR_OF_DAY) {
            hour = 0;
            advanceDay();
        }
    }

    public void advanceDay() {
        day++;

        if (day >= MAX_DAY_OF_SEASON) {
            day = 0;
            advanceSeason();
        }
    }

    public void advanceSeason() {
        switch (season) {
            case SPRING -> season = Season.SUMMER;
            case SUMMER -> season = Season.FALL;
            case FALL -> season = Season.WINTER;
            case WINTER -> season = Season.SPRING;
        }

        if (season == Season.WINTER) {
            advanceYear();
        }
    }

    public void advanceWeekDay() {
        switch (weekDay) {
            case MONDAY -> weekDay = WeekDay.TUESDAY;
            case TUESDAY -> weekDay = WeekDay.WEDNESDAY;
            case WEDNESDAY -> weekDay = WeekDay.THURSDAY;
            case THURSDAY -> weekDay = WeekDay.FRIDAY;
            case FRIDAY -> weekDay = WeekDay.SATURDAY;
            case SATURDAY -> weekDay = WeekDay.SUNDAY;
            case SUNDAY -> weekDay = WeekDay.MONDAY;
        }
    }

    public void advanceYear() {
        year++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return this.year == dateTime.year &&
                this.season == dateTime.season &&
                this.weekDay == dateTime.weekDay &&
                this.day == dateTime.day &&
                this.hour == dateTime.hour;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
