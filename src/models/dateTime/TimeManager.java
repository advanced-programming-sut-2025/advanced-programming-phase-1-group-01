package models.dateTime;

import models.Game;

public class TimeManager {
    private Game game;
    private DateTime now;

    private final int START_HOUR_OF_DAY = 9;
    private final int END_HOUR_OF_DAY = 22;

    public TimeManager(Game game) {
        this.game = game;
        this.now = game.getCurrentDateTime();
    }

    public void skipToMorning() {}


}
