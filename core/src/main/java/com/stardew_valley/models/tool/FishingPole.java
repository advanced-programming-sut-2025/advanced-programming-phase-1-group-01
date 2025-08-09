package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.controllers.AnimalHusbandryController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.tool.enums.FishingPoleInfo;
import com.stardew_valley.views.GameView;
import org.w3c.dom.Text;

// related to fishing
public class FishingPole extends Tool {
    private FishingPoleInfo info;

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

    public void setInfo(FishingPoleInfo info) {
        this.info = info;
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position appliedPosition = player.getTilesPosition().applyDirection(direction);
        Tile tile = player.getFarm().getTile(appliedPosition);

        if (tile.getType() == TileType.RIVER) {
            Result result = AnimalHusbandryController.fish(getName());
            GameView.setMessage(result.message());
        } else {
            GameView.setMessage("You should be near of lake!");
        }

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

    @Override
    public String getName() {
        return info.getName();
    }
}
