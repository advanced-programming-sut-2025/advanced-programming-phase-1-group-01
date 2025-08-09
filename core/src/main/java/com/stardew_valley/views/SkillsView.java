package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Ability;
import com.stardew_valley.models.character.player.AbilityService;
import com.stardew_valley.models.character.player.AbilityType;

public class SkillsView extends GameWindow {
    private Skin skin;
    private Table skillsTable;
    private AbilityService abilityService;

    private final Label farmingAbilityLabel;
    private final Image farmingAbilityImage;
    private final Label fishingAbilityLabel;
    private final Image fishingAbilityImage;
    private final Label foragingAbilityLabel;
    private final Image foragingAbilityImage;
    private final Label miningAbilityLabel;
    private final Image miningAbilityImage;
    private final Drawable redIconDrawable;
    private final Drawable yellowIconDrawable;
    private final Drawable emptyRedIconDrawable;
    private Drawable emptyYellowIconDrawable;

    private Image[] farmingIcons = new Image[4];
    private Image[] fishingIcons = new Image[4];
    private Image[] foragingIcons = new Image[4];
    private Image[] miningIcons = new Image[4];

    public SkillsView() {
        super("Skills", AssetManager.getAssetManager().getSkin(), "Letter");
        skin = AssetManager.getAssetManager().getSkin();

        skillsTable = new Table(skin);
        add(skillsTable).expand().fill().padTop(30);

        abilityService = repo.getCurrentGame().getCurrentPlayer().getAbilityService();

        farmingAbilityLabel = new Label("Farming", skin);
        farmingAbilityImage = new Image(AssetManager.getAssetManager().getFarmingAbility());

        fishingAbilityLabel = new Label("Fishing", skin);
        fishingAbilityImage = new Image(AssetManager.getAssetManager().getFishingAbility());

        foragingAbilityLabel = new Label("Foraging", skin);
        foragingAbilityImage = new Image(AssetManager.getAssetManager().getForagingAbility());

        miningAbilityLabel = new Label("Mining", skin);
        miningAbilityImage = new Image(AssetManager.getAssetManager().getMiningAbility());

        redIconDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getAssetManager().getRedIcon()));
        yellowIconDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getAssetManager().getYellowIcon()));
        emptyRedIconDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getAssetManager().getEmptyRedIcon()));
        emptyYellowIconDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getAssetManager().getEmptyYellowIcon()));

        for (int i = 0; i < 4; i++) {
            farmingIcons[i] = new Image(emptyRedIconDrawable);
            fishingIcons[i] = new Image(emptyRedIconDrawable);
            foragingIcons[i] = new Image(emptyRedIconDrawable);
            miningIcons[i] = new Image(emptyRedIconDrawable);
        }

        Label hoverLabel = new Label("", skin);
        hoverLabel.setVisible(false);
        hoverLabel.setPosition(430,450);
        this.addActor(hoverLabel);
        setupAbilityTooltip(farmingAbilityImage, abilityService.getFarming(), hoverLabel);
        setupAbilityTooltip(fishingAbilityImage, abilityService.getFishing(), hoverLabel);
        setupAbilityTooltip(foragingAbilityImage, abilityService.getForaging(), hoverLabel);
        setupAbilityTooltip(miningAbilityImage, abilityService.getMining(), hoverLabel);

    }

    @Override
    public void update() {
        updateIcons(AbilityType.FARMING, farmingIcons);
        updateIcons(AbilityType.FISHING, fishingIcons);
        updateIcons(AbilityType.FORAGING, foragingIcons);
        updateIcons(AbilityType.MINING, miningIcons);

        skillsTable.clear();

        // Farming
        skillsTable.add(farmingAbilityLabel).pad(10);
        skillsTable.add(farmingAbilityImage).pad(10);
        addIconsToTable(farmingIcons);
        drawProgressBar(AbilityType.FARMING);
        skillsTable.row();

        // Fishing
        skillsTable.add(fishingAbilityLabel).pad(10);
        skillsTable.add(fishingAbilityImage).pad(10);
        addIconsToTable(fishingIcons);
        drawProgressBar(AbilityType.FISHING);
        skillsTable.row();

        // Foraging
        skillsTable.add(foragingAbilityLabel).pad(10);
        skillsTable.add(foragingAbilityImage).pad(10);
        addIconsToTable(foragingIcons);
        drawProgressBar(AbilityType.FORAGING);
        skillsTable.row();

        // Mining
        skillsTable.add(miningAbilityLabel).pad(10);
        skillsTable.add(miningAbilityImage).pad(10);
        addIconsToTable(miningIcons);
        drawProgressBar(AbilityType.MINING);
        skillsTable.row();
    }

    private void addIconsToTable(Image[] icons) {
        for (Image icon : icons) {
            skillsTable.add(icon).pad(10);
        }
    }

    private void updateIcons(AbilityType abilityType, Image[] icons) {
        int level = 0;
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
        }

        for (int i = 0; i < 4; i++) {
            if (i < level) {
                icons[i].setDrawable(i == 3 ? yellowIconDrawable : redIconDrawable);
            } else {
                icons[i].setDrawable(i == 3 ? emptyYellowIconDrawable : emptyRedIconDrawable);
            }
        }
    }

    private void drawProgressBar(AbilityType abilityType) {
        Ability ability = null;
        switch (abilityType) {
            case FARMING:
                ability = abilityService.getFarming();
                break;
            case FISHING:
                ability = abilityService.getFishing();
                break;
            case FORAGING:
                ability = abilityService.getForaging();
                break;
            case MINING:
                ability = abilityService.getMining();
                break;
        }

            ProgressBar levelXpBar = new ProgressBar(0, ability.getMaxXp(), 1, false, getSkin());
            levelXpBar.setValue(ability.getXp());
            skillsTable.add(levelXpBar).pad(10).colspan(4).fillX();
    }

    private Timer.Task hideTask;

    private void setupAbilityTooltip(final Image abilityImage, final Ability ability, final Label hoverLabel) {
        abilityImage.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverLabel.setText(
                    ability.getAbilityType().name() +
                        "\nLevel: " + ability.getLevel() +
                        "\nXP: " + ability.getXp()
                );
                hoverLabel.pack();
                hoverLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                hoverLabel.setVisible(false);
            }
        });
    }
}
