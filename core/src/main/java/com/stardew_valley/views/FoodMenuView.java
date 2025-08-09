package com.stardew_valley.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;

import java.util.Map;

public class FoodMenuView extends GameWindow {

    private final Table table;
    private final Skin skin;
    private final SelectBox<String> foodSelection;
    private final TextButton eatButton;
    private Item foodItem;
    private final Label messageLabel;

    public FoodMenuView() {
        super("Food Menu", AssetManager.getAssetManager().getSkin(), "Letter");
        skin = AssetManager.getAssetManager().getSkin();

        table = new Table(skin);
        add(table);
        setSize(400f,350f);
        setPosition(800f,400f);

        foodSelection = new SelectBox<>(skin);
        eatButton = new TextButton("Eat", skin);
        messageLabel = new Label("", skin);
        foodItem = null;
        messageLabel.setAlignment(Align.center);

        eatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String itemStr = foodSelection.getSelected();
                Item item = repo.getCurrentUser().getPlayer().getInventory().getNewItem(itemStr);
                Slot slot = repo.getCurrentUser().getPlayer().getInventory().getSlot(itemStr);
                setFoodItem(item);
                Player player = repo.getCurrentUser().getPlayer();
                player.removeFood(itemStr);
                slot.removeQuantity(1);
                closeWindow();
            }
        });

        table.add(foodSelection).center().pad(15).row();
        table.add(eatButton).center().pad(15).row();
        table.add(messageLabel).center().pad(15).row();
    }

    @Override
    public void update() {
        updateFoodSelection();
    }

    private void updateFoodSelection() {
        Player player = repo.getCurrentUser().getPlayer();
        Map<String, Integer> foods = player.getFoods();

        Array<String> foodNames = new Array<>();
        for (String foodName : foods.keySet()) {
            foodNames.add(foodName);
        }
        foodSelection.setItems(foodNames);
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Item getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(Item foodItem) {
        this.foodItem = foodItem;
    }

    public void closeWindow() {
        this.setVisible(false);
    }
}
