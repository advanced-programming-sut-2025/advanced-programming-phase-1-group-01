package models.character.player;

// farming: every time harvest(animal-based or agricultural) we get 5 xp.
// mining: every time we eliminate a rock for each one we get 10 xp.
// hiking: every time we collect a foraging item we get 10 xp.
// fishing: every one we fish we get 5 xp.

import models.cooking.CookingRecipes;
import models.crafting.enums.CraftingRecipes;

public class AbilityService {
    private final Player player;
    private final Ability farming;
    private final Ability mining;
    private final Ability hiking;
    private final Ability fishing;
    private final Ability foraging;

    public AbilityService(Player player) {
        this.player = player;
        this.farming = new Ability(AbilityType.FARMING);
        this.mining = new Ability(AbilityType.MINING);
        this.hiking = new Ability(AbilityType.HIKING);
        this.fishing = new Ability(AbilityType.FISHING);
        this.foraging = new Ability(AbilityType.FORAGING);
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

    public void recipe(int newLevel, AbilityType abilityType) {
        switch (abilityType) {
            case FARMING:
                if (newLevel == 1) {
                    // Crafting recipes
                    player.addCraftingRecipe(CraftingRecipes.SPRINKLER.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.BEE_HOUSE.toRecipe());
                    // Cooking recipes
                    player.addCookingRecipe(CookingRecipes.FARMERS_LUNCH.toRecipe());
                } else if (newLevel == 2) {
                    player.addCraftingRecipe(CraftingRecipes.QUALITY_SPRINKLER.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.PRESERVES_JAR.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.CHEESE_PRESS.toRecipe());
                } else if (newLevel == 3) {
                    player.addCraftingRecipe(CraftingRecipes.IRIDIUM_SPRINKLER.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.KEG.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.LOOM.toRecipe());
                    player.addCraftingRecipe(CraftingRecipes.OIL_MAKER.toRecipe());
                }
                break;

            case MINING:
                if (newLevel == 1) {
                    player.addCraftingRecipe(CraftingRecipes.CHERRY_BOMB.toRecipe());
                    player.addCookingRecipe(CookingRecipes.MINERS_TREAT.toRecipe());
                } else if (newLevel == 2) {
                    player.addCraftingRecipe(CraftingRecipes.BOMB.toRecipe());
                } else if (newLevel == 3) {
                    player.addCraftingRecipe(CraftingRecipes.MEGA_BOMB.toRecipe());
                }
                break;

            case FORAGING:
                if (newLevel == 1) {
                    player.addCraftingRecipe(CraftingRecipes.CHARCOAL_KLIN.toRecipe());
                } else if (newLevel == 2) {
                    player.addCookingRecipe(CookingRecipes.VEGETABLE_MEDLEY.toRecipe());
                } else if (newLevel == 3) {
                    player.addCookingRecipe(CookingRecipes.SURVIVAL_BURGER.toRecipe());
                } else if (newLevel == 4) {
                    player.addCraftingRecipe(CraftingRecipes.MYSTIC_TREE_SEED.toRecipe());
                }
                break;

            case FISHING:
                if (newLevel == 1) {
                    player.addCraftingRecipe(CraftingRecipes.FISH_SMOKER.toRecipe());
                } else if (newLevel == 2) {
                    player.addCookingRecipe(CookingRecipes.DISH_O_THE_SEA.toRecipe());
                } else if (newLevel == 3) {
                    player.addCookingRecipe(CookingRecipes.SEAFORM_PUDDING.toRecipe());
                }
                break;

            default:
                break;
        }
    }
}
