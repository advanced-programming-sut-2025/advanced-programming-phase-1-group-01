package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.FarmingController;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.player.IncompleteMovement;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.initializer.FarmInitializer;

import java.util.List;


public class GameView extends ScreenAdapter implements InputProcessor {
    private Stage stage;
    private GameController controller;
    private final OrthographicCamera camera;
    private Player player;
    private final Batch batch;
    private final TextureRegion background = AssetManager.getAssetManager().getSpringBackground();
    private final TextureRegion woodFence = AssetManager.getAssetManager().getWoodFence();
    private final TextureRegion house = AssetManager.getAssetManager().getHouse();
    private final TextureRegion greenhouse = AssetManager.getAssetManager().getGreenhouse();
    private final TextureRegion lake = AssetManager.getAssetManager().getLake();
    private final TextureRegion mine = AssetManager.getAssetManager().getMine();

    private final static int TILE_SIZE = 16;


    private final float speed = 100f;
    private Vector2 vectorPosition;
    private IncompleteMovement incompleteMovement;




    public GameView(GameController controller) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = controller.getRepo().getCurrentGame().getCurrentPlayer();
        batch = Main.getBatch();
        vectorPosition = new Vector2(player.getPosition().x(), player.getPosition().y());
        incompleteMovement = new IncompleteMovement((int) player.getPosition().x() / 16, (int) player.getPosition().y() / 16);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        updateGame(delta);
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1);
        camera.position.set(player.getPosition().x(), player.getPosition().y(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        drawWorld();
        batch.begin();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
    }


    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    private void drawWorld() {
        drawSpringBackgroundTile();

        drawBuilding();

        drawPlayer();

        drawFence();
    }

    private void drawPlayer() {
        batch.draw(player.getCurrentFrame(), player.getPosition().x(), player.getPosition().y());
    }

    private void drawBuilding () {
        for (int i = 0; i < 4; i++) {
            batch.draw(mine, getTilePixel(FarmInitializer.getMineStartingPointX() + FarmInitializer.getAdditionalX(i)), getTilePixel(FarmInitializer.getMineStartingPointY() + FarmInitializer.getAdditionalY(i)));
            batch.draw(mine, getTilePixel(FarmInitializer.getHouseStartingPointX() + FarmInitializer.getAdditionalX(i)), getTilePixel(FarmInitializer.getHouseStartingPointY() + FarmInitializer.getAdditionalY(i)));
            batch.draw(mine, getTilePixel(FarmInitializer.getLakeStartingPointX() + FarmInitializer.getAdditionalX(i)), getTilePixel(FarmInitializer.getLakeStartingPointY() + FarmInitializer.getAdditionalY(i)));
            batch.draw(mine, getTilePixel(FarmInitializer.getGreenhouseStartingPointX() + FarmInitializer.getAdditionalX(i)), getTilePixel(FarmInitializer.getGreenhouseStartingPointY() + FarmInitializer.getAdditionalY(i)));
        }
    }

    private void drawFence() {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        for (List<Tile> tileList : tiles) {
            for (Tile tile : tileList) {
                if (tile.getType() == TileType.FENCE) {
                    int tileX = getTilePixel(tiles.indexOf(tileList));
                    int tileY = getTilePixel(tileList.indexOf(tile));
                    batch.draw(woodFence, tileX, tileY);
                }
            }
        }
    }

    private int getTilePixel(int tileCol) {
        return tileCol * TILE_SIZE;
    }


    private void drawSpringBackgroundTile() {
        batch.draw(background, 0, 0);
    }

    public void updateGame(float delta) {
        controller.getRepo().getCurrentGame().getCurrentPlayer().updateStateTime(delta);
    }

    public void handleMovement(float delta) {
        if (incompleteMovement.isHasIncompleteMovement()) {
            Vector2 direction = new Vector2(incompleteMovement.getVectorPosition()).sub(vectorPosition).nor();
            vectorPosition.add(direction.scl(speed * delta));
            incompleteMovement.checkDestination(vectorPosition);
            return;
        }

        //@ check if is allowed

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), 1, 0);
            player.setDirection(Direction.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), -1, 0);
            player.setDirection(Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), 0, -1);
            player.setDirection(Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), 0, 1);
            player.setDirection(Direction.DOWN);
        }
    }
}


//'$' abandoned incomplete
//'@' should be added
