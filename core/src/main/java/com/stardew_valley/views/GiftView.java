package com.stardew_valley.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.controllers.RelationshipController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.relations.Gift;

import java.util.ArrayList;
import java.util.List;

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
    private final TextField giftQuantityField;
    private final TextButton sendButton;
    private final TextButton backButton1;
    private Item giftItem;

    private Character friend; // می‌تواند Player یا NPC باشد
    private final RelationshipController controller;
    private final InventoryView inventoryView;

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

    public GiftView(RelationshipController controller, Stage stage, InventoryView inventoryView) {
        super("Gift", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        this.controller = controller;
        this.inventoryView = inventoryView;
        inventoryView.setGiftView(this);

        // Main menu
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

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
                stage.setKeyboardFocus(null);
            }
        });

        mainTable.add(sendGiftButton).pad(10).row();
        mainTable.add(receivedGiftsButton).pad(10).row();
        mainTable.add(giftHistoryButton).pad(10).row();
        mainTable.add(exitButton).pad(10).row();
        mainTable.center();
        stack.add(mainTable);
        mainTable.toFront();

        // Send Gift Table
        sendGiftTable.add(backButton1).right().padLeft(700).size(90, 70).row();
        sendGiftTable.add(giftImage).pad(10).padTop(170).row();
        sendGiftTable.add(giftName).pad(10).row();
        sendGiftTable.add(giftQuantityField).pad(10).size(100, 70).row();
        sendGiftTable.add(sendButton).pad(10).row();
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
                if (friend == null || giftItem == null) {
                    GameView.setMessage("No gift or friend selected!");
                    return;
                }
                int amount;
                try {
                    amount = Integer.parseInt(giftQuantityField.getText());
                } catch (Exception e) {
                    GameView.setMessage("Invalid amount!");
                    return;
                }

                if (friend instanceof NPC) {
                    NPC npc = (NPC) friend;
                    npc.handleGifting(giftItem);
                    GameView.setMessage("Gift sent to NPC: " + npc.getType().getName());
                } else if (friend instanceof Player) {
                    Player player = (Player) friend;
                    Result result = controller.gift(player.getUser().getUsername(), giftItem.getName(), amount);
                    GameView.setMessage(result.message());
                }

                giftItem = null;
                setVisible(false);
                stage.setKeyboardFocus(null);
            }
        });

        this.add(stack);
    }

    public void setFriend(Character friend) {
        this.friend = friend;
        if (friend instanceof Player) {
            getTitleLabel().setText("Gift Menu of Your Friend: " + ((Player) friend).getUser().getUsername());
        } else if (friend instanceof NPC) {
            getTitleLabel().setText("Gift Menu of NPC: " + ((NPC) friend).getType().getName());
        }
    }

    public void setGiftItem(Item giftItem) {
        this.giftItem = giftItem;
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

        if (friend != null && friend instanceof Player player) {
            String giftHistory;
            if (friend != null) {
                giftHistory = controller.giftHistory(player.getUser().getUsername()).message();
                giftHistoryLabel.setText(giftHistory);
            }

            if (receivedGiftsPane.isVisible()) {
                getTitleLabel().setText("All Received Gifts");
            } else {
                if (friend != null)
                    getTitleLabel().setText("Gift Menu of Your Friend: " + player.getUser().getUsername());
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
//                    TextButton rateButton = rateButtons.get(i);

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
    }
}
