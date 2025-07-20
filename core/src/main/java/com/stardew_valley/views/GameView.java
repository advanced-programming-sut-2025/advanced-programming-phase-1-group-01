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
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.AssetManager;
import com.stardew_valley.models.Result;
import com.stardew_valley.models.character.player.IncompleteMovement;
import com.stardew_valley.models.character.player.Player;


public class GameView extends ScreenAdapter implements InputProcessor {
    private Stage stage;
    private GameController controller;
    private final OrthographicCamera camera;
    private Player player;
    private final Batch batch;
    private final TextureRegion background = AssetManager.getAssetManager().getSpringBackground();
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


    private void drawSpringBackgroundTile() {
        batch.draw(background, 0, 0);
    }

    public void updateGame(float delta) {

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
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), -1, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), 0, -1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            incompleteMovement = new IncompleteMovement(player.getPosition(), 0, 1);
        }
    }
}


//'$' abandoned incomplete
//'@' should be added
