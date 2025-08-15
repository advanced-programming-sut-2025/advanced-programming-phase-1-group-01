package com.stardew_valley.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.shop.enums.TheStardropSaloonProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaloonView extends Window {

    private final Skin skin;
    private final Table itemTable;
    private final List<TheStardropSaloonProducts> items;

    public SaloonView(Stage stage) {
        super("The Saloon", AssetManager.getAssetManager().getSkin());
        this.skin = AssetManager.getAssetManager().getSkin();

        items = new ArrayList<>();
        for (TheStardropSaloonProducts item : TheStardropSaloonProducts.values()) {
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

        for (TheStardropSaloonProducts item : items) {
            Table row = createItemRow(item);
            itemTable.add(row).expandX().fillX().pad(5);
            itemTable.row();
        }
    }

    private Table createItemRow(TheStardropSaloonProducts item) {
        Table row = new Table(skin);

        Map<TheStardropSaloonProducts,Integer> product = Repository.getRepo().getCurrentGame().getTheStardropSaloon().getAllProducts();
        int quantity = product.get(item);

        Image itemImage = new Image(item.toItem().getTexture());

        if (quantity <= 0) {
            itemImage.setColor(0.3f, 0.3f, 0.3f, 1f);
        }

        Label nameLabel = new Label(item.getName(), skin);
        Image goldIcon = new Image(new Texture("shopping/gold.png"));
        Label priceLabel = new Label(item.getPrice() + "g", skin);

        TextButton buyButton = new TextButton("Buy", skin);

        buyButton.setDisabled(quantity <= 0);

        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (quantity <= 0) return;
                Player player = Repository.getRepo().getCurrentUser().getPlayer();
                player.setNumOfCoins(player.getNumOfCoins() - item.getPrice());
                player.getInventory().addItem(item.getName(), 1);
                Repository.getRepo().getCurrentGame().getTheStardropSaloon().updateProductPurchase(item,1);
                updateTable();
            }
        });

        row.add(itemImage).size(64).pad(5);
        row.add(nameLabel).pad(5).width(120);
        row.add(goldIcon).size(24).padRight(5);
        row.add(priceLabel).pad(5);
        row.add(buyButton).size(80, 40).pad(5);

        return row;
    }

}

