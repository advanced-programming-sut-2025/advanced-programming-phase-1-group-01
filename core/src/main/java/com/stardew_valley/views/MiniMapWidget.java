package com.stardew_valley.views;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;

import java.util.List;

public class MiniMapWidget extends Widget {
    private final List<List<Tile>> tiles;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Window window;
    private final float tileSize = 0.5f;
    private final int buildingWidth;
    private final int buildingHeight;

    public interface TileClickListener {
        void onTileClick(int x, int y);
    }

    public MiniMapWidget(List<List<Tile>> tiles, int buildingWidth, int buildingHeight) {
        this.tiles = tiles;
        this.buildingWidth = buildingWidth;
        this.buildingHeight = buildingHeight;
    }

    @Override
    public float getPrefWidth() {
        return tiles.size() * tileSize;
    }

    @Override
    public float getPrefHeight() {
        return tiles.get(0).size() * tileSize;
    }

    public Window showWindow(TileClickListener listener) {
        window = new Window("Mini Map", AssetManager.getAssetManager().getSkin());
        window.setSize(300, 300);
        window.setPosition(50, 50);
        window.setMovable(false);

        Table content = new Table();
        content.setFillParent(true);

        Actor mapActor = new Actor() {
            {
                addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (button == Input.Buttons.LEFT) {
                            int fx = (int) (x / tileSize);
                            int fy = (int) ((getHeight() - y) / tileSize);

                            if (canPlant(fx, fy, buildingWidth, buildingHeight)) {
                                listener.onTileClick(fx, fy);
                            }
                            return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void draw(com.badlogic.gdx.graphics.g2d.Batch batch, float parentAlpha) {
                batch.end();
                shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                int width = tiles.size();
                int height = tiles.get(0).size();

                for (int i = 0; i < tiles.size(); i++) {
                    for (int j = 0; j < tiles.get(i).size(); j++) {
                        if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                            shapeRenderer.setColor(Color.RED);
                        } else if (tiles.get(i).get(j).getType() == TileType.FENCE) {
                            shapeRenderer.setColor(Color.BLUE);
                        } else {
                            shapeRenderer.setColor(Color.YELLOW);
                        }
                        shapeRenderer.rect(i * tileSize, j * tileSize, tileSize, tileSize);
                    }
                }

                shapeRenderer.end();
                batch.begin();
            }



        };

        content.add(mapActor).expandX().fill();

        TextButton closeButton = new TextButton("Close", AssetManager.getAssetManager().getSkin());
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.remove();
            }
        });

        window.add(content).expand().fill();
        window.row();
        window.add(closeButton).pad(5);

        return window;
    }

    public boolean canPlant(int fx, int fy, int width, int height) {
        for (int x = fx; x < fx + width; x++) {
            for (int y = fy; y < fy + height; y++) {
                if (x < 0 || y < 0 || x >= tiles.size() || y >= tiles.get(0).size())
                    return false;
                if (!tiles.get(x).get(y).isPlowed())
                    return false;
            }
        }
        return true;
    }
}
