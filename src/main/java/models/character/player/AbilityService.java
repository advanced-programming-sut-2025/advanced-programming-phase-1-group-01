package models.character.player;

// farming: every time harvest(animal-based or agricultural) we get 5 xp.
// mining: every time we eliminate a rock for each one we get 10 xp.
// hiking: every time we collect a foraging item we get 10 xp.
// fishing: every one we fish we get 5 xp.

public class AbilityService {
    private final Ability farming;
    private final Ability mining;
    private final Ability hiking;
    private final Ability fishing;
    private final Ability foraging;

    private final int LEVEL_0_XP_NEEDED = 50;
    private final int LEVEL_1_XP_NEEDED = 150;
    private final int LEVEL_2_XP_NEEDED = 250;
    private final int LEVEL_3_XP_NEEDED = 300;
    private final int LEVEL_4_XP_NEEDED = 350;

    private final int MAX_LEVEL = 4;
    private final int INITIAL_LEVEL = 0;

    public AbilityService() {
        this.farming = new Ability(AbilityType.FARMING, 0, 0);
        this.mining = new Ability(AbilityType.MINING, 0, 0);
        this.hiking = new Ability(AbilityType.HIKING, 0, 0);
        this.fishing = new Ability(AbilityType.FISHING, 0, 0);
        this.foraging = new Ability(AbilityType.FORAGING, 0, 0);
    }

    public Ability getFarming() {
        return farming;
    }

    public Ability getMining() {
        return mining;
    }

    public Ability getHiking() {
        return hiking;
    }

    public Ability getFishing() {
        return fishing;
    }

    public Ability getForaging() {
        return foraging;
    }
}
