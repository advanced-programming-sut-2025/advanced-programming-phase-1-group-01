package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.tool.enums.FishingPoleInfo;
import org.w3c.dom.Text;

// related to fishing
public class FishingPole extends Tool {
    FishingPoleInfo info;

    public FishingPole(Inventory inventory) {
        super(inventory);
        name = "Fishing Pole";
        info = FishingPoleInfo.TRAINING;
    }

    @Override
    public int getBaseEnergyCost() {
        return info.getEnergyCost();
    }

    public FishingPoleInfo getInfo() {
        return info;
    }

    @Override
    public void use(Direction direction) {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Texture getTexture() {
        AssetManager am = AssetManager.getAssetManager();
        return switch (info) {
            case TRAINING -> am.getTrainingRod();
            case BAMBOO -> am.getBambooPole();
            case FIBERGLASS -> am.getFiberglassRod();
            case IRIDIUM -> am.getIridiumRod();
        };
    }
}
