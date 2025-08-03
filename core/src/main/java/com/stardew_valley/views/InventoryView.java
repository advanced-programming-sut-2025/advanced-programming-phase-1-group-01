package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.tool.enums.BackpackType;

import java.util.ArrayList;
import java.util.List;

public class InventoryView extends Window {
    private final Table slotTable;
    private final List<Stack> slotStacks;
    private final ScrollPane scrollPane;
    private final Table mainTable;
    private Slot selectedSlot;
    private Image lastHighlightedBackground;
    private Image trashCan;
    private TextButton equipSlotButton;
    private Label slotInfoLabel;

    private final Inventory inventory;

    public InventoryView() {
        super("Inventory", AssetManager.getAssetManager().getSkin(), "Letter");

        Skin skin = AssetManager.getAssetManager().getSkin();
        this.inventory = Repository.getRepo().getCurrentUser().getPlayer().getInventory();

        slotTable = new Table(skin);
        slotStacks = new ArrayList<>();
        scrollPane = new ScrollPane(slotTable, skin);
        mainTable = new Table(skin);
        equipSlotButton = new TextButton("equip", skin);
        slotInfoLabel = new Label("", skin);

        scrollPane.setForceScroll(false, true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        equipSlotButton.setVisible(false);

        this.setSize(900, 600);
        this.setPosition(Gdx.graphics.getWidth() / 2f - 400, Gdx.graphics.getHeight() / 2f - 300);
        this.setMovable(true);
        this.setVisible(false);

        mainTable.add(scrollPane).expand().fill().padTop(30);
        this.add(mainTable).size(700, 450).padBottom(40);
        trashCan = new Image(AssetManager.getAssetManager().getTrashCan());

        trashCan.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getSelectedSlot() != null) {
                    inventory.removeSlot(getSelectedSlot());
                    update();
                    setSelectedSlot(null);
                    resetLastHighlightedBackground();
                }
            }
        });
        this.add(trashCan).pad(400, 20, 0, -30).size(60, 70);
        this.row();
        this.add(slotInfoLabel);
        equipSlotButton.getLabel().setScale(0.2f);
        this.add(equipSlotButton).size(150, 80)/*.padBottom(100)*/;
        equipSlotButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if ("equip".equals(equipSlotButton.getLabel().getText().toString())) {
                    inventory.setEquippedSlot(selectedSlot);
                    equipSlotButton.getLabel().setText("unequip");
                } else if ("unequip".equals(equipSlotButton.getLabel().getText().toString())) {
                    inventory.setEquippedSlot(null);
                    equipSlotButton.getLabel().setText("equip");
                }
            }
        });

        for (int i = 0; i < inventory.getCapacity() - 1; i++) {
            Stack slotStack = new Stack();

            Image background = new Image(skin.getDrawable("selectListBoard"));
            Image itemImage = new Image();

            itemImage.setVisible(false);

            slotStack.add(background);
            slotStack.add(itemImage);

            slotStacks.add(slotStack);
            slotTable.add(slotStack).size(90, 90).pad(10);
            if ((i + 1) % 6 == 0) slotTable.row();
        }
    }

    public Table getSlotTable() {
        return slotTable;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void update() {
        scrollPane.setScrollingDisabled(true, inventory.getCapacity() == BackpackType.SMALL.getCapacity() || inventory.getCapacity() == BackpackType.BIG.getCapacity());

        for (Stack stack : slotStacks) {
            Image itemImage = (Image) stack.getChildren().get(1);
            itemImage.setDrawable(null);
            itemImage.setVisible(false);
        }

        for (int i = 0; i < inventory.getSlots().size(); i++) {
            Slot slot = inventory.getSlots().get(i);

            Image itemImage = (Image) slotStacks.get(i).getChildren().get(1);
            Image background = (Image) slotStacks.get(i).getChildren().get(0);

            itemImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedSlot = slot;
                    highlightSlot(background);
                    if (inventory.getEquippedSlot() != slot) {
                        equipSlotButton.getLabel().setText("equip");
                    } else {
                        equipSlotButton.getLabel().setText("unequip");
                    }
                }
            });

            if (slot.getItem() != null) {
                itemImage.setDrawable(new TextureRegionDrawable(new TextureRegion(slot.getItem().getTexture())));
                itemImage.setVisible(true);
            } else {
                itemImage.setVisible(false);
            }
        }

        if (selectedSlot != null) {
            Item item = selectedSlot.getItem();
            slotInfoLabel.setText(item.getName() + " (" + selectedSlot.getQuantity() + ")");
        }
    }

    private void highlightSlot(Image background) {
        if (lastHighlightedBackground != null) {
            resetLastHighlightedBackground();
        }

        background.setColor(1, 1, 0.5f, 1); // یه رنگ زرد کمرنگ
        lastHighlightedBackground = background;
        equipSlotButton.setVisible(true);
    }

    public Slot getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(Slot selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public void resetLastHighlightedBackground() {
        lastHighlightedBackground.setColor(1, 1, 1, 1);
        equipSlotButton.setVisible(false);
    }
}
