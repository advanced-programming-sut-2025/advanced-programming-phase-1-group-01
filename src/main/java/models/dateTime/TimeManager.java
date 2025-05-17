package models.dateTime;

import models.Game;
import models.animal.Animal;
import models.character.NPC.NPC;
import models.character.player.Player;
import models.crafting.UnripeProduct;

import java.util.*;

public class TimeManager {
    private List<DateTime> eventTimes;
    private Game game;
    private DateTime now;
    private final int START_HOUR_OF_DAY = 9;
    private final int END_HOUR_OF_DAY = 22;

    public static final int DAY_START_HOUR = 9;
    public static final int DAY_END_HOUR = 22;
    public static final Season START_SEASON = Season.SPRING;
    public static final int START_DAY = 1;
    public static final WeekDay START_WEEKDAY = WeekDay.MONDAY;
    public static final int START_YEAR = 2025;

    public TimeManager(Game game) {
        this.eventTimes = new ArrayList<>();
        this.game = game;
        this.now = new DateTime.Builder()
                .setYear(START_YEAR)
                .setSeason(START_SEASON)
                .setWeekDay(START_WEEKDAY)
                .setDay(START_DAY)
                .setHour(DAY_START_HOUR)
                .build();
    }

    public void skipToMorning() {
        if (now.getHour() != END_HOUR_OF_DAY) return;

        final int SLEEP_TIME = 11;

        for (int i = 0; i < SLEEP_TIME; i++) {
            now.advanceHour();
        }
    }

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

    public void prepareForNewDay() {
        game.getWeatherManager().prepareNewDayWeather();
        game.getWeatherManager().getTodayWeather().applyEffect(game);
        game.getForagingManager().prepareNewDayForaging();
        game.getFarmingManager().resetAllPlantsWatered();
        game.getFarmingManager().growAllPlants();

        game.getBlacksmith().resetDailyStock();
        game.getJojaMart().resetDailyStock();
        game.getPierreGeneralStore().resetDailyStock();
        game.getCarpenterShop().resetDailyStock();
        game.getFishShop().resetDailyStock();
        game.getMarnieRanch().resetDailyStock();
        game.getTheStardropSaloon().resetDailyStock();


        for (Player player : game.getPlayers()) {
            for (Animal animal: player.getFarm().getAnimals()) {
                animal.DailyResetAndStart();
            }

            if (player.getEnergy().hasPassedOut()) {
                player.getEnergy().setHasPassedOut(false);
                player.getEnergy().fillEnergyPassedOut();
            } else {
                player.getEnergy().fillEnergy();
            }

            if (player.isEnergyHalved()) {
                player.getEnergy().setMAX_ENERGY(player.getEnergy().getMAX_ENERGY() / 2);
            }

            player.increaseHalvedEnergy();
            for (UnripeProduct unripeProduct: player.getUnripeProducts()) {
                unripeProduct.advanceHourCounter();
            }
        }

        for (NPC npc : game.getNPCVillage().getNPCs()) {
            npc.resetForNewDay();
        }
    }
}