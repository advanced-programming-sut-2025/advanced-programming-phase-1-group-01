package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.controllers.RelationshipController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Game;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Player;
import jdk.dynalink.NamedOperation;

public class GiftView extends GameWindow {
    private final Stack stack;

    private final Table mainTable;
    private final TextButton sendGiftButton;
    private final TextButton giftHistoryButton;
    private final TextButton exitButton;

    private final Table sendGiftTable;
    private final Image giftImage;
    private final Label giftName;
    private final Label quantityLabel;
    private final TextField giftQuantityField;
    private final TextButton sendButton;
    private final TextButton backButton;

    private Item giftItem;

    private final InventoryView inventoryView;

    {
        stack = new Stack();

        mainTable = new Table(getSkin());
        sendGiftButton = new TextButton("Send Gift", getSkin());
        giftHistoryButton = new TextButton("Gift History", getSkin());
        exitButton = new TextButton("Exit", getSkin());

        sendGiftTable = new Table(getSkin());
        giftImage = new Image();
        giftName = new Label("", getSkin());
        quantityLabel = new Label("Enter Quantity:", getSkin());
        giftQuantityField = new TextField("1", getSkin());
        sendButton = new TextButton("Send", getSkin());
        backButton = new TextButton(">", getSkin());
    }

    private Player friend;
    private final RelationshipController controller;

    public GiftView(RelationshipController controller, Stage stage, InventoryView inventoryView) {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        this.controller = controller;
        this.inventoryView = inventoryView;
        inventoryView.setGiftView(this);

        sendGiftButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(inventoryView);
                inventoryView.setPickingGift(true);
                inventoryView.setVisible(true);
                inventoryView.toFront();

                mainTable.setVisible(false);
                sendGiftTable.setVisible(true);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });

        mainTable.add(sendGiftButton).pad(10);
        mainTable.row();
        mainTable.add(giftHistoryButton).pad(10);
        mainTable.row();
        mainTable.add(exitButton).pad(10);
        mainTable.center();
        stack.add(mainTable);

        sendGiftTable.add(backButton).right().padLeft(700).size(90, 70);
        sendGiftTable.row();
        sendGiftTable.add(giftImage).pad(10).padTop(170);
        sendGiftTable.row();
        sendGiftTable.add(giftName).pad(10);
        sendGiftTable.row();
//        sendGiftTable.add(quantityLabel).pad(10);
        sendGiftTable.add(giftQuantityField).pad(10).size(100, 70).colspan(1);
        sendGiftTable.row();
        sendGiftTable.add(sendButton).pad(10);
        sendGiftTable.center();
        stack.add(sendGiftTable);
        sendGiftTable.setVisible(false);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainTable.setVisible(true);
                sendGiftTable.setVisible(false);
            }
        });

        sendButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(giftQuantityField.getText());
                } catch (Exception e) {
                    GameView.setMessage("Invalid Amount!");
                    return;
                }
                Result result = controller.gift(friend.getUser().getUsername(), giftName.getText().toString(), amount);
                GameView.setMessage(result.message());
                setVisible(false);
                if (result.success()) giftItem = null;
                stage.setKeyboardFocus(null);
            }
        });

        add(stack);
    }

    public void setFriend(Player friend) {
        this.friend = friend;
        getTitleLabel().setText("Gift Menu of Your Friend: " + friend.getUser().getUsername());
    }

    @Override
    public void update() {
        if (giftItem != null) {
            giftImage.setDrawable(new TextureRegionDrawable(giftItem.getTexture()));
            float aspectRatio = (float) giftItem.getTexture().getHeight() / giftItem.getTexture().getWidth();
            giftImage.setSize(200, 200 * aspectRatio);
            giftName.setText(giftItem.getName());
        } else {
            giftImage.setDrawable(null);
            giftName.setText("");
        }
    }

    public void setGiftItem(Item giftItem) {
        this.giftItem = giftItem;
    }
}
