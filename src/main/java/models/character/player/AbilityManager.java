package models.character.player;

import models.Game;
import models.cooking.CookingRecipes;
import models.crafting.enums.CraftingRecipes;

public class AbilityManager {
    private final Game game;

    public AbilityManager(Game game) {
        this.game = game ;
    }

    public void recipe(int newLevel, AbilityType abilityType) {
        Player player = game.getCurrentPlayer();
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
