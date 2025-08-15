package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.stardew_valley.controllers.ShippingBinController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;

import java.util.List;

public class ShippingBinView extends GameWindow {
    private Table table;
    private Skin skin;
    private final SelectBox<String> items;
    private Label countLabel;
    private TextField count;
    private TextButton sell;
    private Label messageLabel;

    private ShippingBinController controller;

    public ShippingBinView(ShippingBinController controller, Stage stage) {
        super("Shipping Bin", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        this.controller = controller;
        table = new Table(getSkin());
        skin = AssetManager.getAssetManager().getSkin();
        items = new SelectBox<>(skin);
        countLabel = new Label("Count:", skin);
        count = new TextField("", skin);
        sell = new TextButton("Sell", skin);
        messageLabel = new Label("", skin);

        sell.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    controller.sell(items.getSelected(), Integer.parseInt(count.getText()), messageLabel);
                } catch (Exception ignored) {}
            }
        });

        table.add(items).pad(10);
        table.row();
        table.add(countLabel).pad(10);
        table.row();
        table.add(count).pad(10);
        table.row();
        table.add(sell).pad(10);
        table.row();
        table.add(messageLabel).pad(10);
        table.center();
        table.setFillParent(true);
        addActor(table);
    }

    @Override
    public void update() {
        Player player = repo.getCurrentGame().getCurrentPlayer();
        List<Slot> slotList = player.getInventory().getSlots();
        Array<String> slots = new Array<>();
        for (Slot s : slotList) {
            slots.add(s.getItem().getName() + " " + s.getQuantity() + "x");
        }
        items.setItems(slots);
    }

    public void reset() {
        count.setText("");
    }
}
