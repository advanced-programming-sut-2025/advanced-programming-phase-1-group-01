package models.dateTime;

public class DateTime {
    private int year;
    private Season season;
    private WeekDay weekDay;
    private int day;
    private int hour;

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

        if (hour >= 24) {
            hour = 0;
            advanceDay();
        }
    }

    public void advanceDay() {
    }

    public void advanceSeason() {
        switch (season) {
            case Season.SPRING -> season = Season.SUMMER;
            case Season.SUMMER -> season = Season.FALL;
            case Season.FALL -> season = Season.WINTER;
            case Season.WINTER -> season = Season.SPRING;
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
}
