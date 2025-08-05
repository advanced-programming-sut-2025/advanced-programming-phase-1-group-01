package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.AbilityService;
import com.stardew_valley.models.character.player.AbilityType;
import com.stardew_valley.models.character.player.Player;

public class SkillsView extends GameWindow {
    private Skin skin;
    private AbilityService abilityService;
    private Label farmingAbilityLabel;
    private Image farmingAbilityImage;
    private Label fishingAbilityLabel;
    private Image fishingAbilityImage;
    private Label foragingAbilityLabel;
    private Image foragingAbilityImage;
    private Label miningAbilityLabel;
    private Image miningAbilityImage;

    public SkillsView() {
        super("Skills", AssetManager.getAssetManager().getSkin(), "Letter");
        skin = AssetManager.getAssetManager().getSkin();
        abilityService = repo.getCurrentGame().getCurrentPlayer().getAbilityService();

        farmingAbilityLabel = new Label("Farming ", skin);
        farmingAbilityImage = new Image(AssetManager.getAssetManager().getFarmingAbility());
        fishingAbilityLabel = new Label("Fishing ", skin);
        fishingAbilityImage = new Image(AssetManager.getAssetManager().getFishingAbility());
        foragingAbilityLabel = new Label("Foraging ", skin);
        foragingAbilityImage = new Image(AssetManager.getAssetManager().getForagingAbility());
        miningAbilityLabel = new Label("Mining ", skin);
        miningAbilityImage = new Image(AssetManager.getAssetManager().getMiningAbility());

        this.add(farmingAbilityLabel).pad(10);
        this.add(farmingAbilityImage).pad(10);
        drawIcons(AbilityType.FARMING);
        this.row();
        this.add(fishingAbilityLabel).pad(10);
        this.add(fishingAbilityImage).pad(10);
        drawIcons(AbilityType.FISHING);
        this.row();
        this.add(foragingAbilityLabel).pad(10);
        this.add(foragingAbilityImage).pad(10);
        drawIcons(AbilityType.FORAGING);
        this.row();
        this.add(miningAbilityLabel).pad(10);
        this.add(miningAbilityImage).pad(10);
        drawIcons(AbilityType.MINING);
        this.row();
    }

    @Override
    public void update() {
    }

    public void drawIcons(AbilityType abilityType) {
        int level;
        switch (abilityType) {
            case FARMING:
                level = abilityService.getFarming().getLevel();
                break;
            case FISHING:
                level = abilityService.getFishing().getLevel();
                break;
            case FORAGING:
                level = abilityService.getForaging().getLevel();
                break;
            case MINING:
                level = abilityService.getMining().getLevel();
                break;
            default:
                level = 0;
        }

        for (int i = 1; i <= level; i++) {
            Image redIcon = new Image(AssetManager.getAssetManager().getRedIcon());
            Image yellowIcon = new Image(AssetManager.getAssetManager().getYellowIcon());
            if (i == 4) {
                this.add(yellowIcon).pad(10);
            }
            else {
                this.add(redIcon).pad(10);
            }
        }

        for (int i = level + 1; i <= 4; i++) {
            Image emptyRedIcon = new Image(AssetManager.getAssetManager().getEmptyRedIcon());
            Image emptyYellowIcon = new Image(AssetManager.getAssetManager().getEmptyYellowIcon());
            if (i == 4) {
                this.add(emptyYellowIcon).pad(10);
            }
            else {
                this.add(emptyRedIcon).pad(10);
            }
        }
    }
}
