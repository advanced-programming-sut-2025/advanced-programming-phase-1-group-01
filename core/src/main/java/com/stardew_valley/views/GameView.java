package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class GameView extends ScreenAdapter implements InputProcessor {
    private Stage stage;
    private final GameController controller;
    private final OrthographicCamera camera;
    private final Player player;
    private final Batch batch;
    private final TextureRegion background = AssetManager.getAssetManager().getSpringBackground();
    private final TextureRegion woodFence = AssetManager.getAssetManager().getWoodFence();
    private final TextureRegion house = AssetManager.getAssetManager().getHouse();
    private final TextureRegion greenhouse = AssetManager.getAssetManager().getGreenhouse();
    private final TextureRegion lake = AssetManager.getAssetManager().getLake();
    private final TextureRegion mine = AssetManager.getAssetManager().getMine();
    private final TextureRegion lakeWater = AssetManager.getAssetManager().getLakeWater();
    private final TextureRegion houseTop = AssetManager.getAssetManager().getHouseTop();

    private final TextureRegion sebastianHouseTexture = AssetManager.getAssetManager().getNpcHouse1Full();
    private final TextureRegion abigailHouseTexture = AssetManager.getAssetManager().getNpcHouse2Full();
    private final TextureRegion leahHouseTexture = AssetManager.getAssetManager().getNpcHouse3Full();
    private final TextureRegion harveyHouseTexture = AssetManager.getAssetManager().getNpcHouse4Full();

    private final TextureRegion abigailRightTexture = AssetManager.getAssetManager().getAbigailRight();
    private final TextureRegion sebastianLeftTexture = AssetManager.getAssetManager().getSebastianLeft();
    private final TextureRegion leahUpTexture = AssetManager.getAssetManager().getLeahUp();
    private final TextureRegion harveyDownTexture = AssetManager.getAssetManager().getHarveyDown();

    private final TextureRegion sebastianHouseTopTexture = AssetManager.getAssetManager().getNpcHouse1Top();
    private final TextureRegion abigailHouseTopTexture = AssetManager.getAssetManager().getNpcHouse2Top();
    private final TextureRegion leahHouseTopTexture = AssetManager.getAssetManager().getNpcHouse3Top();
    private final TextureRegion harveyHouseTopTexture = AssetManager.getAssetManager().getNpcHouse4Top();

    private Dialog terminalDialog;
    private TextField textField;


    private boolean isDialogShown = false;



    private final TextureRegion highlightBox = AssetManager.getAssetManager().getBlackTexture();
    private final DateTimeView dateTimeView;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Actor miniMapActor = null;



    private final static int TILE_SIZE = 16;


    private final float speed = 200f;


    private final EnergyView energyView;


    public GameView(GameController controller) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = controller.getRepo().getCurrentGame().getCurrentPlayer();
        batch = Main.getBatch();
        this.dateTimeView = new DateTimeView(controller.getDateTimeController());
        this.energyView = new EnergyView(player);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        createDialog();
    }

    @Override
    public void render(float delta) {
        updateGame(delta);
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1);
        camera.position.set(player.getPosition().x(), player.getPosition().y(), 0);
        camera.zoom = 0.5f;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld();
        dateTimeView.update();
        energyView.updateEnergy();
        energyView.render(delta);
        batch.end();

        stage.act(delta);
        stage.draw();
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

        drawFence();

        drawDividingFences();

        drawPlayer();

        drawHouseTop();

        drawNPCs();

//        drawTileHighlights();

        //printTileTypeCounts();
    }

    private void drawPlayer() {
        batch.draw(player.getCurrentFrame(), player.getX(), player.getY());
        //System.out.println((int)(player.getX() / 16) + " " + (int)(player.getY() / 16));
    }

    private void drawNPCs() {
        int sebastianX = getTilePixel(FarmInitializer.getSebastianStartingPointX());
        int sebastianY = getTilePixel(FarmInitializer.getSebastianStartingPointY());
        batch.draw(sebastianLeftTexture, sebastianX, sebastianY);

        int abigailX = getTilePixel(FarmInitializer.getAbigailStartingPointX());
        int abigailY = getTilePixel(FarmInitializer.getAbigailStartingPointY());
        batch.draw(abigailRightTexture, abigailX, abigailY);

        int leahX = getTilePixel(FarmInitializer.getLeahStartingPointX());
        int leahY = getTilePixel(FarmInitializer.getLeahStartingPointY());
        batch.draw(leahUpTexture, leahX, leahY);

        int harveyX = getTilePixel(FarmInitializer.getHarveyStartingPointX());
        int harveyY = getTilePixel(FarmInitializer.getHarveyStartingPointY());
        batch.draw(harveyDownTexture, harveyX, harveyY);
    }

    private void drawBuilding () {
        for (int i = 0; i < 4; i++) {
            int mineX = getTilePixel(FarmInitializer.getMineStartingPointX() + FarmInitializer.getAdditionalX(i));
            int mineY = getTilePixel(FarmInitializer.getMineStartingPointY() + FarmInitializer.getAdditionalY(i) - 1);
            batch.draw(mine, mineX, mineY);

            int houseX = getTilePixel(FarmInitializer.getHouseStartingPointX() + FarmInitializer.getAdditionalX(i));
            int houseY = getTilePixel(FarmInitializer.getHouseStartingPointY() + FarmInitializer.getAdditionalY(i));
            batch.draw(house, houseX, houseY);

            int lakeX = getTilePixel(FarmInitializer.getLakeStartingPointX() + FarmInitializer.getAdditionalX(i));
            int lakeY = getTilePixel(FarmInitializer.getLakeStartingPointY() + FarmInitializer.getAdditionalY(i));
            batch.draw(lakeWater, lakeX + 16, lakeY + 16);
            batch.draw(lake, lakeX, lakeY);


            int greenhouseX = getTilePixel(FarmInitializer.getGreenhouseStartingPointX() + FarmInitializer.getAdditionalX(i));
            int greenhouseY = getTilePixel(FarmInitializer.getGreenhouseStartingPointY() + FarmInitializer.getAdditionalY(i));
            batch.draw(greenhouse, greenhouseX, greenhouseY);

            //System.out.println("Lake position: " + lakeX / 16 + ", " + lakeY / 16);

        }

        int sebastianX = getTilePixel(FarmInitializer.getSebastianCottageStartingPointX());
        int sebastianY = getTilePixel(FarmInitializer.getSebastianCottageStartingPointY());
        batch.draw(sebastianHouseTexture, sebastianX, sebastianY);

        int abigailX = getTilePixel(FarmInitializer.getAbigailCottageStartingPointX());
        int abigailY = getTilePixel(FarmInitializer.getAbigailCottageStartingPointY());
        batch.draw(abigailHouseTexture, abigailX, abigailY);

        int leahX = getTilePixel(FarmInitializer.getLeahCottageStartingPointX());
        int leahY = getTilePixel(FarmInitializer.getLeahCottageStartingPointY());
        batch.draw(leahHouseTexture, leahX, leahY);

        int harveyX = getTilePixel(FarmInitializer.getHarveyCottageStartingPointX());
        int harveyY = getTilePixel(FarmInitializer.getHarveyCottageStartingPointY());
        batch.draw(harveyHouseTexture, harveyX, harveyY);



    }

    private void drawHouseTop() {
        for (int i = 0; i < 4; i++) {
            int houseX = getTilePixel(FarmInitializer.getHouseStartingPointX() + FarmInitializer.getAdditionalX(i));
            int houseY = getTilePixel(FarmInitializer.getHouseStartingPointY() + FarmInitializer.getAdditionalY(i));
            batch.draw(houseTop, houseX, houseY + 100);
        }

        int sebastianX = getTilePixel(FarmInitializer.getSebastianCottageStartingPointX());
        int sebastianY = getTilePixel(FarmInitializer.getSebastianCottageStartingPointY());
        batch.draw(sebastianHouseTopTexture, sebastianX, sebastianY + 70);

        int abigailX = getTilePixel(FarmInitializer.getAbigailCottageStartingPointX());
        int abigailY = getTilePixel(FarmInitializer.getAbigailCottageStartingPointY());
        batch.draw(abigailHouseTopTexture, abigailX, abigailY + 70);

        int leahX = getTilePixel(FarmInitializer.getLeahCottageStartingPointX());
        int leahY = getTilePixel(FarmInitializer.getLeahCottageStartingPointY());
        batch.draw(leahHouseTopTexture, leahX, leahY + 70);

        int harveyX = getTilePixel(FarmInitializer.getHarveyCottageStartingPointX());
        int harveyY = getTilePixel(FarmInitializer.getHarveyCottageStartingPointY());
        batch.draw(harveyHouseTopTexture, harveyX, harveyY + 70);
    }


    private void drawFence() {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int numRows = tiles.size();
        int numCols = tiles.get(0).size();


        for (int col = 0; col < numCols; col++) {
            Tile topTile = tiles.get(0).get(col);
            if (topTile.getType() == TileType.FENCE) {
                batch.draw(woodFence, getTilePixel(col), getTilePixel(0));
            }

            Tile bottomTile = tiles.get(numRows - 1).get(col);
            if (bottomTile.getType() == TileType.FENCE) {
                batch.draw(woodFence, getTilePixel(col), getTilePixel(numRows - 1));
            }
        }

        for (int row = 1; row < numRows - 1; row++) {
            Tile leftTile = tiles.get(row).get(0);
            if (leftTile.getType() == TileType.FENCE) {
                batch.draw(woodFence, getTilePixel(0), getTilePixel(row));
            }

            Tile rightTile = tiles.get(row).get(numCols - 1);
            if (rightTile.getType() == TileType.FENCE) {
                batch.draw(woodFence, getTilePixel(numCols - 1), getTilePixel(row));
            }
        }
    }

    private void drawDividingFences() {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int numRows = tiles.size();
        int numCols = tiles.get(0).size();

        int thirdRow = numRows / 3;
        int thirdCol = numCols / 3;

        for (int yOffset = 1; yOffset <= 2; yOffset++) {
            int y = yOffset * thirdRow;
            for (int col = 0; col < numCols; col++) {
                Tile tile = tiles.get(y).get(col);
                if (tile.getType() == TileType.FENCE) {
                    batch.draw(woodFence, getTilePixel(col), getTilePixel(y));
                }
            }
        }

        for (int xOffset = 1; xOffset <= 2; xOffset++) {
            int x = xOffset * thirdCol;
            for (int row = 0; row < numRows; row++) {
                Tile tile = tiles.get(row).get(x);
                if (tile.getType() == TileType.FENCE) {
                    batch.draw(woodFence, getTilePixel(x), getTilePixel(row));
                }
            }
        }
    }


    public void printTileTypeCounts() {
        Map<TileType, Integer> tileCounts = new EnumMap<>(TileType.class);

        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();

        for (List<Tile> row : tiles) {
            for (Tile tile : row) {
                TileType type = tile.getType();
                tileCounts.put(type, tileCounts.getOrDefault(type, 0) + 1);
            }
        }

        for (Map.Entry<TileType, Integer> entry : tileCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void drawTileHighlights() {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int numRows = tiles.size();
        int numCols = tiles.get(0).size();

        Color originalColor = batch.getColor();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = tiles.get(row).get(col);
                TileType type = tile.getType();

                Color highlightColor = getColorForTileType(tile.isMovable());
                if (highlightColor != null) {
                    //batch.setColor(highlightColor);
                    batch.draw(woodFence, getTilePixel(col), getTilePixel(row));
                }
            }
        }

        batch.setColor(originalColor);
    }

    private Color getColorForTileType(boolean type) {
        if (type) {
            return null;
        } else {
            return Color.BLACK;
//            case GROUND: return new Color(0f, 1f, 0f, 0.3f);
//            case RIVER: return new Color(0f, 0.5f, 1f, 0.3f);
//            case MINE: {
//                //System.out.println("meow");
//                return Color.BLACK;
//            }
//            case GREENHOUSE: return new Color(0f, 1f, 0.5f, 0.3f);
//            case COTTAGE: return new Color(0.6f, 0.3f, 0.1f, 0.3f);
//            case WALL: return new Color(0.4f, 0.4f, 0.4f, 0.3f);
//            case SALE_BUCKET: return new Color(1f, 0f, 0f, 0.3f);
//            case FENCE: return new Color(1f, 1f, 0f, 0.3f);
//            default: return null;
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
        handleMovement(delta);
    }

    public void handleMovement(float delta) {
        boolean moving = false;

        if (Gdx.input.isKeyJustPressed(Input.Keys.GRAVE)) {
            toggleDialog();
        }

        if (isDialogShown) {
            return;
        }

        /*

            g = cc, advance hour
            space = toggle minimap
            f = set fainting
            w = up
            s = down
            d = right
            a = left
            h = toggle terminal

        */


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (miniMapActor == null) {
                showMiniMap(stage);
            } else {
                miniMapActor.remove();
                miniMapActor = null;
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            controller.getRepo().getCurrentGame().getTimeManager().getNow().advanceHour();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            player.setFainting(true);
        }

        if (player.isFainting()) {
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            float nextX = player.getX() + speed * delta;
            if (canMoveTo(nextX, player.getY())) {
                player.setX(nextX);
                player.setDirection(Direction.RIGHT);
                moving = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            float nextX = player.getX() - speed * delta;
            if (canMoveTo(nextX, player.getY())) {
                player.setX(nextX);
                player.setDirection(Direction.LEFT);
                moving = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            float nextY = player.getY() + speed * delta;
            if (canMoveTo(player.getX(), nextY)) {
                player.setY(nextY);
                player.setDirection(Direction.UP);
                moving = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            float nextY = player.getY() - speed * delta;
            if (canMoveTo(player.getX(), nextY)) {
                player.setY(nextY);
                player.setDirection(Direction.DOWN);
                moving = true;
            }
        }

        player.setMoving(moving);

    }

    private boolean canMoveTo(float x, float y) {
        int tileX = (int)(x / 16);
        int tileY = (int)(y / 16);

        Tile tile = controller.getRepo().getCurrentGame().getFarm().getTile(tileX, tileY);

        return tile != null && tile.isMovable();
    }


    public void showMiniMap(Stage stage) {
        final int tileSize = 2;
        final List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();

        miniMapActor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
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

                int playerTileX = (int)player.getX() / 16;
                int playerTileY = (int)player.getY() / 16;

                shapeRenderer.setColor(Color.RED);
                float centerX = getX() + playerTileX * tileSize + tileSize / 2f;
                float centerY = getY() + playerTileY * tileSize + tileSize / 2f;
                shapeRenderer.circle(centerX, centerY, (int)(tileSize * 3));

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

        miniMapActor.setPosition(20, 20);
        stage.addActor(miniMapActor);
    }






    private Color getColorForTileType(TileType type) {
        switch (type) {
            case GROUND: return Color.GREEN;
            case RIVER: return Color.BLACK;
            case MINE: return Color.GRAY;
            case GREENHOUSE: return Color.FOREST;
            case COTTAGE: return Color.BROWN;
            case WALL: return Color.DARK_GRAY;
            case SALE_BUCKET: return Color.PINK;
            case FENCE: return Color.BLUE;
            default: return Color.LIGHT_GRAY;
        }
    }

    private void createDialog() {
        Skin skin = AssetManager.getAssetManager().getSkin();

        terminalDialog = new Dialog("Enter command", skin) {
            @Override
            protected void result(Object object) {
                if ((boolean) object) {
                    String input = textField.getText();
                    handleInput(input);
                    isDialogShown = false;
                }
            }
        };

        terminalDialog.setModal(true);

        textField = new TextField("", skin);
        terminalDialog.getContentTable().add(textField).width(200).pad(10);

        terminalDialog.row();
        terminalDialog.button("Ok", true).pad(10);

        terminalDialog.pack();
    }


    private void toggleDialog() {
        if (!isDialogShown) {
            terminalDialog.show(stage);
            isDialogShown = true;

            terminalDialog.setObject(terminalDialog.getButtonTable().getCells().first().getActor(), true);
        } else {
            terminalDialog.hide();
            isDialogShown = false;
        }
    }

    private void handleInput(String input) {
        System.out.println("handleInput: " + input);
    }

}


//'$' abandoned incomplete
//'@' should be added
