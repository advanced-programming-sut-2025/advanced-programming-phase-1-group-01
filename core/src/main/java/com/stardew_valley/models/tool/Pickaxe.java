package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.crafting.CraftingDevice;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.foraging.ForagingMineral;
import com.stardew_valley.models.ingredients.Stone;
import com.stardew_valley.models.tool.enums.PickaxeType;


public class Pickaxe extends Tool {
    private PickaxeType type;

    public Pickaxe(Inventory inventory) {
        super(inventory);
        name = "Pickaxe";
        type = PickaxeType.PRIMARY;
    }

    @Override
    public int getBaseEnergyCost() {
        return type.getEnergyCost();
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position position = player.getTilesPosition();
        Position appliedPosition = position.applyDirection(direction);

        Tile tile = player.getCurrentMap().getTile(appliedPosition);
        tile.unPlow();
        Item item = tile.getObject();
       if (item instanceof Stone || item instanceof ForagingMineral || item instanceof CraftingDevice) {
        tile.removeObject();
        player.getAbilityService().getMining().increaseXp(10);
        }

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = PickaxeType.COPPER;
            case COPPER -> type = PickaxeType.IRON;
            case IRON -> type = PickaxeType.GOLD;
            case GOLD -> type = PickaxeType.IRIDIUM;
        }
    }

    public PickaxeType getType() {
        return type;
    }

    @Override
    public Texture getTexture() {
        AssetManager am = AssetManager.getAssetManager();
        return switch (type) {
            case PRIMARY -> am.getPickaxe();
            case COPPER -> am.getCopperPickaxe();
            case IRON -> am.getSteelPickaxe();
            case GOLD -> am.getGoldPickaxe();
            case IRIDIUM -> am.getIridiumPickaxe();
        };
    }
}
