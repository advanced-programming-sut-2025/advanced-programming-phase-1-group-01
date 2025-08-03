package com.stardew_valley.views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.models.*;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Plant;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.foraging.ForagingMineral;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.tool.Tool;

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

    private Dialog pixelDialog;
    private boolean isPixelDialogVisible = false;

    private Dialog buildAreaDialog;
    private boolean isBuildAreaDialogVisible = false;


    private boolean isDialogShown = false;

    private final TextureRegion highlightBox = AssetManager.getAssetManager().getBlackTexture();
    private final TextureRegion highlightBoxLight = AssetManager.getAssetManager().getWhiteTexture();

    private final DateTimeView dateTimeView;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Actor miniMapActor = null;

    private final static int TILE_SIZE = 16;

    private final float speed = 200f;

    private final InventoryView inventoryView;

    public GameView(GameController controller) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = controller.getRepo().getCurrentGame().getCurrentPlayer();
        batch = Main.getBatch();
        this.dateTimeView = new DateTimeView(controller.getDateTimeController());
        this.inventoryView = new InventoryView();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        createDialog();

        stage.addActor(inventoryView);
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
        inventoryView.update();
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
        if (i == Input.Keys.ESCAPE) {
            inventoryView.setVisible(!inventoryView.isVisible());
            return true;
        }

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);

            float dx = mousePos.x - player.getX();
            float dy = mousePos.y - player.getY();

            Direction direction;

            float angle = (float) Math.atan2(dy, dx);
            float degree = (float) Math.toDegrees(angle);
            if (degree < 0) degree += 360;

            if (degree >= 337.5 || degree < 22.5)
                direction = Direction.RIGHT;
            else if (degree >= 22.5 && degree < 67.5)
                direction = Direction.UP_RIGHT;
            else if (degree >= 67.5 && degree < 112.5)
                direction = Direction.UP;
            else if (degree >= 112.5 && degree < 157.5)
                direction = Direction.UP_LEFT;
            else if (degree >= 157.5 && degree < 202.5)
                direction = Direction.LEFT;
            else if (degree >= 202.5 && degree < 247.5)
                direction = Direction.DOWN_LEFT;
            else if (degree >= 247.5 && degree < 292.5)
                direction = Direction.DOWN;
            else // (degree >= 292.5 && degree < 337.5)
                direction = Direction.DOWN_RIGHT;


            Slot equippedSlot = player.getInventory().getEquippedSlot();
            if (equippedSlot != null) {
                if (equippedSlot.getItem() instanceof Tool tool) {
                    tool.use(direction);
                } else if (equippedSlot.getItem() instanceof Seed seed) {
                    controller.getFarmingController().plant(seed.getName(), direction);
                }
            }
            return true;
        }
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

        drawHouseTop();

        drawNPCs();

//        drawTileHighlights();

        //printTileTypeCounts();

        drawPlowedTiles();

        drawTileObjects();

        drawPlayer();
    }

    private void drawPlowedTiles() {
        for (List<Tile> row : controller.getRepo().getCurrentGame().getFarm().getTiles()) {
            for (Tile tile : row) {
                if (tile.isEmpty() && tile.isPlowed()) {
                    batch.draw(AssetManager.getAssetManager().getPlowedTile(), tile.getPosition().y() * 16, tile.getPosition().x() * 16);
                }
            }
        }
    }

    private void drawTileObjects() {
        for (List<Tile> row : controller.getRepo().getCurrentGame().getFarm().getTiles()) {
            for (Tile tile : row) {
                if (!tile.isEmpty()) {
//                    Sprite objSprite = new Sprite(tile.getObject().getTexture());
//                    objSprite.setSize(16, 16);
//                    batch.draw(objSprite, tile.getPosition().y() * 16, tile.getPosition().x() * 16);
//                    objSprite.setRegionWidth(16);
//                    objSprite.setPosition(tile.getPosition().y() * 16, tile.getPosition().x() * 16);
//                    objSprite.draw(batch);
                    Texture texture = tile.getObject().getTexture();
                    float aspectRatio = (float) texture.getHeight() / texture.getWidth();
                    float width = 16f;
                    float height = width * aspectRatio;

                    if (tile.getObject() instanceof ForagingMineral) {
                        batch.draw(texture, tile.getPosition().x() * 16, tile.getPosition().y() * 16, width, height);
                    } else {
                        batch.draw(texture, tile.getPosition().y() * 16, tile.getPosition().x() * 16, width, height);
                    }
                }
            }
        }
    }

    private void drawPlayer() {
        batch.draw(player.getCurrentFrame(), player.getX(), player.getY());
//        System.out.println((int) (player.getX() / 16) + " " + (int) (player.getY() / 16));
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

    private void drawBuilding() {
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            toggleBuildArea();
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
        int tileX = (int) (x / 16);
        int tileY = (int) (y / 16);

        Tile tile = controller.getRepo().getCurrentGame().getFarm().getTile(tileX, tileY);

        return tile != null && tile.isMovable();
    }


    public void toggleBuildArea() {
        if (!isBuildAreaDialogVisible) {
            buildAreaDialog = createDropdownDialog(AssetManager.getAssetManager().getSkin());
            buildAreaDialog.show(stage);
            isBuildAreaDialogVisible = true;
        } else {
            buildAreaDialog.hide();
            isBuildAreaDialogVisible = false;
        }
    }

    public Dialog createDropdownDialog(Skin skin) {
        Dialog dialog = new Dialog("Select Item", skin);

        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems("A", "B");

        dialog.getContentTable().add(selectBox).pad(10).row();

        dialog.button("Cancel", false);
        dialog.button("OK", true);

        dialog.key(Input.Keys.ENTER, true);
        dialog.key(Input.Keys.ESCAPE, false);

        dialog = new Dialog(dialog.getTitleLabel().getText().toString(), skin) {
            @Override
            protected void result(Object object) {
                if (Boolean.TRUE.equals(object)) {
                    String selected = selectBox.getSelected();
                    switch (selected) {
                        case "A":
                            if (!isPixelDialogVisible)
                                togglePixelDialog(4, 4, "A");
                        default:
                            if (!isPixelDialogVisible)
                                togglePixelDialog(6, 7, "B");
                    }
                } else {
                    System.out.println("Selection cancelled");
                }
                isBuildAreaDialogVisible = false;
            }
        };

        dialog.getContentTable().add(selectBox).pad(10).row();
        dialog.button("Cancel", false);
        dialog.button("OK", true);
        dialog.key(Input.Keys.ENTER, true);
        dialog.key(Input.Keys.ESCAPE, false);

        return dialog;
    }


    public void togglePixelDialog(int height, int width, String type) {
        if (!isPixelDialogVisible) {
            pixelDialog = createPixelDialog(AssetManager.getAssetManager().getSkin(), height, width, type);
            pixelDialog.show(stage);
            isPixelDialogVisible = true;
        } else {
            pixelDialog.hide();
            isPixelDialogVisible = false;
        }
    }


    public Dialog createPixelDialog(Skin skin, int height, int width, String type) {
        Dialog dialog = new Dialog("Pixel Grid", skin);


        Table grid = createPixelGrid(height, width, type);
        ScrollPane scrollPane = new ScrollPane(grid);

        dialog.getContentTable().add(scrollPane).size(900, 1000);
        return dialog;
    }

    public Table createPixelGrid(int height, int width, String type) {
        Table grid = new Table();

        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int rowCount = tiles.size();
        int colCount = tiles.get(0).size();

        System.out.println("rowCount: " + rowCount);
        System.out.println("colCount: " + colCount);

        for (int row = rowCount - 1; row >= 0; row--) {
            for (int col = 0; col < colCount; col++) {
                boolean plantable = isPlantableTile(row, col);
                TextureRegionDrawable drawable = plantable ? new TextureRegionDrawable(highlightBoxLight) : new TextureRegionDrawable(highlightBox);
                ImageButton pixel = createPixelButton(drawable, row, col, height, width, type);
                grid.add(pixel).size(10, 10).pad(0.5f);
            }
            grid.row();
        }

        return grid;
    }

    private void setObject(int row, int col, int height, int width, String type) {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();

        for (int r = row; r <= row + height; r++) {
            for (int c = col; c <= col + width; c++) {

                boolean isTop = (r == row);
                boolean isBottom = (r == row + height);
                boolean isLeft = (c == col);
                boolean isRight = (c == col + width);

                if (isTop || isBottom || isLeft || isRight) {
                    if (r >= 0 && r < tiles.size() && c >= 0 && c < tiles.get(r).size()) {
                        Tile tile = tiles.get(r).get(c);
                        System.out.println(r + " " + c);

                        switch (type) {
                            case "A":
                                tile.setObject(new CageFence());
                                tile.setMovable(false);
                                break;
                            default:
                                tile.setObject(new BarnFence());
                                tile.setMovable(false);
                        }
                    } else {
                        System.out.println("Index out of bounds: r=" + r + " c=" + c);
                    }
                }
            }
        }
    }


    private boolean isPlantableArea(int row, int col, int height, int width) {
        for (int r = row; r < height; r++) {
            for (int c = col; c < width; c++) {
                Tile tile = controller.getRepo().getCurrentGame().getFarm().getTiles().get(r).get(c);
                if (!tile.isEmpty() || tile.getType() != TileType.GROUND) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPlantableTile(int row, int col) {
        Tile tile = controller.getRepo().getCurrentGame().getFarm().getTiles().get(row).get(col);
        return tile.isEmpty() && tile.getType() == TileType.GROUND;
    }


    public ImageButton createPixelButton(TextureRegionDrawable drawable, int row, int col, int height, int width, String type) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = drawable.tint(Color.LIGHT_GRAY);
        style.imageDown = drawable.tint(Color.DARK_GRAY);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPlantableArea(row, col, height, width)) {
                    System.out.println("can plant");
                    setObject(row, col, height, width, type);
                }
                pixelDialog.hide();
                isPixelDialogVisible = false;
            }
        });

        return button;
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

        miniMapActor.setPosition(20, 20);
        stage.addActor(miniMapActor);
    }


    private Color getColorForTileType(TileType type) {
        switch (type) {
            case GROUND:
                return Color.GREEN;
            case RIVER:
                return Color.BLACK;
            case MINE:
                return Color.GRAY;
            case GREENHOUSE:
                return Color.FOREST;
            case COTTAGE:
                return Color.BROWN;
            case WALL:
                return Color.DARK_GRAY;
            case SALE_BUCKET:
                return Color.PINK;
            case FENCE:
                return Color.BLUE;
            default:
                return Color.LIGHT_GRAY;
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
        terminalDialog.getContentTable().add(textField).width(500).pad(10);

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
        Result result = controller.handleCommand(input);
        System.out.println("[" + result.success() + "] " + result.message());
    }

}


//'$' abandoned incomplete
//'@' should be added
