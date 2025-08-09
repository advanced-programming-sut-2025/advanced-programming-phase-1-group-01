package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.controllers.RelationshipController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.relations.Gift;

import java.util.ArrayList;

public class GiftView extends GameWindow {
    private final Stack stack;

    private final Table mainTable;
    private final TextButton sendGiftButton;
    private final TextButton receivedGiftsButton;
    private final TextButton giftHistoryButton;
    private final TextButton exitButton;

    private final Table sendGiftTable;
    private final Image giftImage;
    private final Label giftName;
    private final Label quantityLabel;
    private final TextField giftQuantityField;
    private final TextButton sendButton;
    private final TextButton backButton1;
    private Item giftItem;

    private final Table receivedGiftsMainTable;
    private final ScrollPane receivedGiftsPane;
    private final Table receivedGiftsTable;
    private final java.util.List<Table> receivedGiftRows;
    private final TextField rateField;
    private final java.util.List<TextField> rateFields;
    private final java.util.List<TextButton> rateButtons;
    private final java.util.List<Label> receivedGiftDescriptions;
    private final TextButton backButton3;
    private java.util.List<Gift> receivedGifts;

    private final ScrollPane giftHistoryPane;
    private final Table giftHistoryTable;
    private final Label giftHistoryLabel;
    private final TextButton backButton2;

    private final InventoryView inventoryView;

    {
        stack = new Stack();

        mainTable = new Table(getSkin());
        sendGiftButton = new TextButton("Send Gift", getSkin());
        receivedGiftsButton = new TextButton("All Received Gifts", getSkin());
        giftHistoryButton = new TextButton("Gift History", getSkin());
        exitButton = new TextButton("Exit", getSkin());

        sendGiftTable = new Table(getSkin());
        giftImage = new Image();
        giftName = new Label("", getSkin());
        quantityLabel = new Label("Enter Quantity:", getSkin());
        giftQuantityField = new TextField("1", getSkin());
        sendButton = new TextButton("Send", getSkin());
        backButton1 = new TextButton(">", getSkin());

        receivedGiftsTable = new Table(getSkin());
        receivedGiftRows = new ArrayList<>();
        receivedGiftsMainTable = new Table(getSkin());
        receivedGiftsPane = new ScrollPane(receivedGiftsTable);
        rateField = new TextField("", getSkin());
        rateFields = new ArrayList<>();
        rateButtons = new ArrayList<>();
        receivedGiftDescriptions = new ArrayList<>();
        backButton3 = new TextButton(">", getSkin());

        giftHistoryTable = new Table(getSkin());
        giftHistoryLabel = new Label("", getSkin());
        giftHistoryPane = new ScrollPane(giftHistoryTable);
        backButton2 = new TextButton(">", getSkin());
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

        receivedGiftsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                receivedGiftsMainTable.setVisible(true);
                mainTable.setVisible(false);
                giftHistoryPane.setVisible(false);
                sendGiftTable.setVisible(false);
            }
        });

        giftHistoryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                giftHistoryPane.setVisible(true);
                mainTable.setVisible(false);
                sendGiftTable.setVisible(false);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stage.setKeyboardFocus(null);
            }
        });

        mainTable.add(sendGiftButton).pad(10);
        mainTable.row();
        mainTable.add(receivedGiftsButton).pad(10);
        mainTable.row();
        mainTable.add(giftHistoryButton).pad(10);
        mainTable.row();
        mainTable.add(exitButton).pad(10);
        mainTable.center();
        stack.add(mainTable);
        mainTable.toFront();

        sendGiftTable.add(backButton1).right().padLeft(700).size(90, 70);
        sendGiftTable.row();
        sendGiftTable.add(giftImage).pad(10).padTop(170);
        sendGiftTable.row();
        sendGiftTable.add(giftName).pad(10);
        sendGiftTable.row();
        sendGiftTable.add(giftQuantityField).pad(10).size(100, 70);
        sendGiftTable.row();
        sendGiftTable.add(sendButton).pad(10);
        sendGiftTable.center();
        stack.add(sendGiftTable);
        sendGiftTable.setVisible(false);

        backButton1.addListener(new ChangeListener() {
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
                Result result;
                if (!giftName.getText().toString().equalsIgnoreCase("sunflower")) {
                    result = controller.gift(friend.getUser().getUsername(), giftName.getText().toString(), amount);
                } else {
                    result = controller.flower(friend.getUser().getUsername());
                }

                GameView.setMessage(result.message());
                setVisible(false);
//                if (result.success()) giftItem = null;
                stage.setKeyboardFocus(null);
            }
        });

        receivedGiftsMainTable.add(rateField).pad(5).size(100, 60).center();
        receivedGiftsMainTable.add(backButton3).pad(5).size(90, 70).expandX().right();
        receivedGiftsMainTable.row();
        receivedGiftsMainTable.center();
        receivedGiftsPane.setScrollingDisabled(true, false);
        receivedGiftsMainTable.add(receivedGiftsPane);
        receivedGiftsMainTable.setVisible(false);
        stack.add(receivedGiftsMainTable);

        backButton3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainTable.setVisible(true);
                receivedGiftsMainTable.setVisible(false);
                stage.setKeyboardFocus(null);
            }
        });

        for (int i = 0; i < 200; i++) {
            rateFields.add(new TextField("", getSkin()));
            rateButtons.add(new TextButton("rate", getSkin()));
        }

        giftHistoryTable.add(backButton2).padLeft(700).padBottom(50).size(90, 70);
        giftHistoryTable.row();
        giftHistoryTable.add(giftHistoryLabel).center();
        giftHistoryPane.setScrollingDisabled(true, false);
        stack.add(giftHistoryPane);
        giftHistoryPane.setVisible(false);
        backButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainTable.setVisible(true);
                giftHistoryPane.setVisible(false);
            }
        });

        this.add(stack);
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

        String giftHistory;
        if (friend != null) {
            giftHistory = controller.giftHistory(friend.getUser().getUsername()).message();
            giftHistoryLabel.setText(giftHistory);
        }

        if (receivedGiftsPane.isVisible()) {
            getTitleLabel().setText("All Received Gifts");
        } else {
            if (friend != null) getTitleLabel().setText("Gift Menu of Your Friend: " + friend.getUser().getUsername());
        }

        if (receivedGiftsPane.isVisible()) {
            receivedGifts = controller.getAllReceivedGifts();

            receivedGiftsTable.clear();

            receivedGiftRows.clear();
            receivedGiftDescriptions.clear();

            for (int i = 0; i < Math.min(receivedGifts.size(), 200); i++) {
                receivedGiftRows.add(new Table(getSkin()));

                Gift gift = receivedGifts.get(i);

                receivedGiftDescriptions.add(new Label("", getSkin()));
//                TextField rateField = rateFields.get(i);
                TextButton rateButton = rateButtons.get(i);

                rateField.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        stage.setKeyboardFocus(rateField);
                        rateField.setCursorPosition(rateField.getText().length());
                        rateField.getOnscreenKeyboard().show(true);
                    }
                });

                receivedGiftRows.get(i).add(receivedGiftDescriptions.get(i)).pad(5);
//                receivedGiftRows.get(i).add(rateFields.get(i)).pad(5).size(90, 70);
                receivedGiftRows.get(i).add(rateButtons.get(i)).pad(5).size(100, 60);

//                stage.setKeyboardFocus(rateField);

                rateButtons.get(i).addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        int rate = 0;
                        try {
                            rate = Integer.parseInt(rateField.getText());
                        } catch (Exception e) {
                            GameView.setMessage("Invalid rate!");
                            return;
                        }

                        Result result = controller.giftRate(gift.giftNumber(), rate);
                        GameView.setMessage(result.message());
                    }
                });

                receivedGiftDescriptions.get(i).setText("Sender: %s, Gift: %s, Amount: %d, Rate: %d of 5".formatted(
                    gift.sender().getUser().getUsername(),
                    gift.item().getName(),
                    gift.amount(),
                    gift.rate()));
                receivedGiftDescriptions.get(i).setFontScale(0.7f);

                receivedGiftsTable.add(receivedGiftRows.get(i)).pad(5);
                receivedGiftsTable.row();
            }
        }
    }

    public void setGiftItem(Item giftItem) {
        this.giftItem = giftItem;
    }
}
