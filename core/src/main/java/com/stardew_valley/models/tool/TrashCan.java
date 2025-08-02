package com.stardew_valley.models.tool;

import com.badlogic.gdx.graphics.Texture;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.tool.enums.TrashCanType;

public class TrashCan extends Tool {
    private TrashCanType type;

    public TrashCan(Inventory inventory) {
        super(inventory);
        this.type = TrashCanType.PRIMARY;
        name = "Trash Can";
    }

    @Override
    public int getBaseEnergyCost() {
        return 0;
    }

    @Override
    public void use(Direction direction) {
        // this method is not used for trash can
    }

    @Override
    public void upgrade() {
        switch (type) {
            case PRIMARY -> type = TrashCanType.COPPER;
            case COPPER -> type = TrashCanType.IRON;
            case IRON -> type = TrashCanType.GOLD;
            case GOLD -> type = TrashCanType.IRIDIUM;
        }
    }

    public TrashCanType getType() {
        return type;
    }

    public int getReturnValue(int price) {
        return price * type.getReturnValuePercentage() / 100;
    }

    @Override
    public Texture getTexture() {
        AssetManager am = AssetManager.getAssetManager();
        return switch (type) {
            case PRIMARY -> am.getTrashCan();
            case COPPER -> am.getTrashCanCopper();
            case IRON -> am.getTrashCanSteel();
            case GOLD -> am.getTrashCanGold();
            case IRIDIUM -> am.getTrashCanIridium();
        };
    }
}
