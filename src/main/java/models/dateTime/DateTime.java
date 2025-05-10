package models.dateTime;

import java.util.List;

public class DateTime implements Cloneable {
    private int year;
    private Season season;
    private WeekDay weekDay;
    private int day;
    private int hour;

    private TimeManager timeManager;
    private List<Event> events;

    private static final int MAX_SEASON_OF_YEAR = 4;
    private static final int MAX_DAY_OF_SEASON = 28;
    private static final int MAX_HOUR_OF_DAY = 23;

    public DateTime(Builder builder) {
        this.year = builder.getYear();
        this.season = builder.getSeason();
        this.weekDay = builder.getWeekDay();
        this.day = builder.getDay();
        this.hour = builder.getHour();
    }

    public void advanceHour() {
        hour++;

        if (hour == TimeManager.DAY_END_HOUR) {
            timeManager.skipToMorning();
        }

        if (hour > MAX_HOUR_OF_DAY) {
            hour = 0;
            advanceDay();
        }
    }

    public DateTime advanceDay() {
        day++;
        timeManager.prepareForNewDay();

        if (day >= MAX_DAY_OF_SEASON) {
            day = 0;
            advanceSeason();
        }
        return this;
    }

    private void advanceSeason() {
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

    private void advanceWeekDay() {
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

    private void advanceYear() {
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

    public int getYear() {
        return year;
    }

    public Season getSeason() {
        return season;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public DateTime clone() {
        try {
            DateTime cloned = (DateTime) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }

    public static class Builder {
        private int year;
        private Season season;
        private WeekDay weekDay;
        private int day;
        private int hour;

        public int getYear() {
            return year;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Season getSeason() {
            return season;
        }

        public Builder setSeason(Season season) {
            this.season = season;
            return this;
        }

        public WeekDay getWeekDay() {
            return weekDay;
        }

        public Builder setWeekDay(WeekDay weekDay) {
            this.weekDay = weekDay;
            return this;
        }

        public int getDay() {
            return day;
        }

        public Builder setDay(int day) {
            this.day = day;
            return this;
        }

        public int getHour() {
            return hour;
        }

        public Builder setHour(int hour) {
            this.hour = hour;
            return this;
        }

        public DateTime build() {
            return new DateTime(this);
        }
    }
}
