package com.stardew_valley.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
    import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.shop.enums.FishShopProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishingShopView extends Window {

    private final Skin skin;
    private final Table itemTable;
    private final List<FishShopProducts> items;

    public FishingShopView(Stage stage) {
        super("Fishing Shop", AssetManager.getAssetManager().getSkin());
        this.skin = AssetManager.getAssetManager().getSkin();

        items = new ArrayList<>();

        for (FishShopProducts item : FishShopProducts.values()) {
            items.add(item);
        }

        itemTable = new Table(skin);

        this.add(itemTable).expand().fill().pad(10);

        updateTable();

        stage.addActor(this);
        this.pack();
    }

    private void updateTable() {
        itemTable.clear();

        for (FishShopProducts item : items) {
            Table row = createItemRow(item);
            itemTable.add(row).expandX().fillX().pad(5);
            itemTable.row();
        }
    }

    private Table createItemRow(FishShopProducts item) {
        Table row = new Table(skin);

        Item itemTexture = Repository.getRepo().getCurrentGame().getCurrentPlayer().getInventory().getNewItem(item.getName());
        Image itemImage = new Image(itemTexture.getTexture());

        Map<FishShopProducts, Integer> products = Repository.getRepo().getCurrentGame().getFishShop().getFishShopProducts();
        int quantity = products.get(item);

        if (quantity <= 0) {
            itemImage.setColor(0.3f, 0.3f, 0.3f, 1f);
        }

        Label nameLabel = new Label(item.getName(), skin);
        Label priceLabel = new Label(item.getPrice() + "g", skin);

        TextButton buyButton = new TextButton("Buy", skin);

        buyButton.setDisabled(quantity <= 0);

        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (quantity <= 0) return;
                Player player = Repository.getRepo().getCurrentGame().getCurrentPlayer();
                player.setNumOfCoins(player.getNumOfCoins() - item.getPrice());
                player.getInventory().addItem(item.getName(), quantity);
                Repository.getRepo().getCurrentGame().getFishShop().updateProductPurchase(item);
                updateTable();
            }
        });

        row.add(itemImage).size(64).pad(5);
        row.add(nameLabel).pad(15).fill();
        row.add(priceLabel).pad(15).width(80);
        row.add(buyButton).size(80, 40).pad(15);

        return row;
    }
}
