package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Item;
import com.stardew_valley.models.character.NPC.NPC;

import java.util.HashMap;
import java.util.Map;

public class NPCView {
    private final Stage stage;
    private final Skin skin;
    private final SpriteBatch batch;


    private final Map<String, NPCActor> npcs = new HashMap<>();
    private final Dialog dialogBox;
    private final Window giftMenu;
    private final Map<String, Label> friendshipLabels = new HashMap<>();
    private final Table questsTable;
    private final TextureRegion dialogSignTex;

    public NPCView(Stage stage, Skin skin, SpriteBatch batch) {
        this.stage = stage;
        this.skin = skin;
        this.batch = batch;

        dialogBox = new Dialog("Dialog", skin);
        dialogBox.setSize(400, 150);
        dialogBox.setPosition(Gdx.graphics.getWidth() / 2f - 200, 50);
        dialogBox.setVisible(false);
        stage.addActor(dialogBox);

        giftMenu = new Window("Give Gift", skin);
        giftMenu.setSize(300, 200);
        giftMenu.setPosition(Gdx.graphics.getWidth() / 2f - 150, Gdx.graphics.getHeight() / 2f - 100);
        giftMenu.setVisible(false);
        stage.addActor(giftMenu);

        int index = 0;
        for (String npcName : npcs.keySet()) {
            Label label = new Label("Friendship: 0", skin);
            label.setPosition(10, Gdx.graphics.getHeight() - 30 - 30 * index);
            friendshipLabels.put(npcName, label);
            stage.addActor(label);
            index++;
        }

        questsTable = new Table(skin);
        questsTable.setFillParent(true);
        stage.addActor(questsTable);

        dialogSignTex = AssetManager.getAssetManager().getCircleSign();


        stage.addActor(new Image(new TextureRegionDrawable(AssetManager.getAssetManager().getAbigailRight())));
        stage.addActor(new Image(new TextureRegionDrawable(AssetManager.getAssetManager().getAbigailRight())));
        stage.addActor(new Image(new TextureRegionDrawable(AssetManager.getAssetManager().getAbigailRight())));
        stage.addActor(new Image(new TextureRegionDrawable(AssetManager.getAssetManager().getAbigailRight())));

    }

    public void addNPC(String id, NPCActor npc) {
        npcs.put(id, npc);
        stage.addActor(npc);
    }

    public void showDialog(String npcId, String dialogText) {
        dialogBox.clear();
        dialogBox.text(dialogText);
        dialogBox.button("OK");
        dialogBox.setVisible(true);
    }

    public void showGiftMenu(String npcId, List<Item> inventoryItems) {
        giftMenu.clear();
        giftMenu.add(new Label("Select a gift:", skin)).pad(10).row();

//        for (Item item : inventoryItems) {
////            TextButton itemButton = new TextButton(item, skin);
////            itemButton.addListener(new InputListener() {
////                @Override
////                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
////                    giveGiftToNPC(npcId, item);
////                    giftMenu.setVisible(false);
////                    return true;
////                }
////            });
////            giftMenu.add(itemButton).pad(5).row();
//        }

        giftMenu.setVisible(true);
    }

    private void giveGiftToNPC(String npcId, String item) {

    }




    public static class NPCActor extends Actor {
        public final String name;
        public NPC npcObject;
        public int friendshipLevel = 0;
        public boolean hasDialog = false;
        private final Texture texture;

        public NPCActor(String name, String texturePath, float x, float y) {
            this.name = name;
            this.texture = new Texture(texturePath);
            setBounds(x, y, texture.getWidth(), texture.getHeight());
            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (hasDialog) {
                        System.out.println("Talk to " + name);
                    }
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, getX(), getY());
        }
    }
}
