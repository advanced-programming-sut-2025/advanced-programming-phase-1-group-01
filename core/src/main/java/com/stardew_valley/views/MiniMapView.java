package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.player.Player;

import java.util.List;


public class MiniMapView extends GameWindow {

    private ShapeRenderer shapeRenderer;
    private Actor miniMapActor;
    private List<List<Tile>> tiles;
    private Player player;
    private boolean isMiniMapVisible = false; // پیش‌فرض مخفی

    public MiniMapView(Stage stage, List<List<Tile>> tiles, Player player) {
        super("Map", AssetManager.getAssetManager().getSkin(), "Letter", stage);
        this.tiles = tiles;
        this.player = player;
        this.shapeRenderer = new ShapeRenderer();

        createMiniMapActor();
    }

    public void toggleMiniMap() {
        isMiniMapVisible = !isMiniMapVisible;
    }

    private void createMiniMapActor() {
        final int tileSize = 2;

        miniMapActor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                if (!isMiniMapVisible) return;

                batch.end();
                shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                for (int y = 0; y < tiles.size(); y++) {
                    for (int x = 0; x < tiles.get(y).size(); x++) {
                        Tile tile = tiles.get(y).get(x);
                        shapeRenderer.setColor(getColorForTileType(tile.getType()));
                        shapeRenderer.rect(getX() + x * tileSize, getY() + y * tileSize, tileSize, tileSize);
                    }
                }

                int playerTileX = (int) player.getX() / 16;
                int playerTileY = (int) player.getY() / 16;

                shapeRenderer.setColor(Color.RED);
                float centerX = getX() + playerTileX * tileSize + tileSize / 2f;
                float centerY = getY() + playerTileY * tileSize + tileSize / 2f;
                shapeRenderer.circle(centerX, centerY, (int) (tileSize * 3));

                shapeRenderer.end();
                batch.begin();
            }

            @Override
            public float getWidth() {
                return tiles.get(0).size() * tileSize;
            }

            @Override
            public float getHeight() {
                return tiles.size() * tileSize;
            }
        };

        centerMiniMap();
        stage.addActor(miniMapActor);
    }

    private void centerMiniMap() {
        float centerX = (Gdx.graphics.getWidth() - miniMapActor.getWidth()) / 2f;
        float centerY = (Gdx.graphics.getHeight() - miniMapActor.getHeight()) / 2f;
        miniMapActor.setPosition(centerX, centerY);
    }

    private Color getColorForTileType(TileType type) {
        switch (type) {
            case GROUND:       return Color.GREEN;
            case RIVER:        return Color.BLUE;
            case MINE:         return Color.DARK_GRAY;
            case GREENHOUSE:   return Color.FOREST;
            case COTTAGE:      return Color.BROWN;
            case WALL:         return Color.LIGHT_GRAY;
            case FENCE:        return Color.ORANGE;
            case SHIPPING_BIN: return Color.YELLOW;
            case SHOP:         return Color.PURPLE;
            default:           return Color.GRAY;
        }
    }

    @Override
    public void update() {
    }

    public void setMiniMapVisible(boolean miniMapVisible) {
        isMiniMapVisible = miniMapVisible;
    }
}
