package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Crop;
import com.stardew_valley.models.farming.Fruit;
import com.stardew_valley.models.farming.Plant;
import com.stardew_valley.models.foraging.Foraging;
import com.stardew_valley.models.foraging.ForagingCrop;
import org.w3c.dom.Text;

public class Scythe extends Tool {
    private static final int INITIAL_SCYTHE_USE_ENERGY = 2;

    public Scythe(Inventory inventory) {
        super(inventory);
        name = "Scythe";
    }

    @Override
    public int getBaseEnergyCost() {
        return INITIAL_SCYTHE_USE_ENERGY;
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position appliedPosition = player.getTilesPosition().applyDirection(direction);
        Tile tile = player.getFarm().getTile(appliedPosition);

        Item product = null;
        if (tile.getObject() instanceof Plant plant && plant.hasProduct()) {
            product = plant.getProduct();
            if (product instanceof Crop crop) {
                crop.setPlanted(false);
                if (crop.getInfo().isOneTime()) {
                    tile.removeObject();
                }
            }
            player.getInventory().addItem(product.getName(), 1);
        } else if (tile.getObject() instanceof Foraging foraging) {
            product = foraging;
            tile.removeObject();
            player.getInventory().addItem(product.getName(), 1);
        }

        if (product instanceof Fruit || (product instanceof Crop crop && !crop.getInfo().isOneTime())) {
            player.getAbilityService().getFarming().increaseXp(5);
        } else if (product instanceof Crop crop && crop.getInfo().isOneTime()) {
            player.getAbilityService().getFarming().increaseXp(5);
            player.getAbilityService().getHiking().increaseXp(10);
        }


        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Texture getTexture() {
        return AssetManager.getAssetManager().getScythe();
    }
}
