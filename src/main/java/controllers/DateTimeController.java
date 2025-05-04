package controllers;

import models.Result;
import models.data.Repository;

public class DateTimeController extends Controller {
    public DateTimeController(Repository repo) {
        super(repo);
    }

    @Override
    public Result handleCommand(String commandLine) {
        return null;
    }

    private Result showTime() {
        return null;
    }

    private Result showDate() {
        return null;
    }

    private Result showDateTime() {
        return null;
    }

    private Result showWeekDay() {
        return null;
    }

    private Result showSeason() {
        return null;
    }

    private Result cheatAdvanceHour(int hour) {
        return null;
    }

    private Result cheatAdvanceDay(int day) {
        return null;
    }
}