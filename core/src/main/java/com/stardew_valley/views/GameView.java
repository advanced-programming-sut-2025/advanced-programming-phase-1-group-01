package com.stardew_valley.views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.CookingController;
import com.stardew_valley.controllers.CraftingController;
import com.stardew_valley.controllers.GameController;
import com.stardew_valley.controllers.ShopControllers.BlackSmithController;
import com.stardew_valley.controllers.ShopControllers.JojaMartController;
import com.stardew_valley.models.animal.Animal;
import com.stardew_valley.models.animal.AnimalInfo;
import com.stardew_valley.models.*;
import com.stardew_valley.models.building.Tile;
import com.stardew_valley.models.building.TileType;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.character.NPC.NPCQuest;
import com.stardew_valley.models.character.NPC.NPCVillage;
import com.stardew_valley.models.character.player.Energy;
import com.stardew_valley.models.character.player.MarriageRequest;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.models.cooking.CookingRecipes;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.dateTime.DateTime;
import com.stardew_valley.models.dateTime.Season;
import com.stardew_valley.models.character.player.Slot;
import com.stardew_valley.models.enums.AreaType;
import com.stardew_valley.models.enums.ArtisanStatus;
import com.stardew_valley.models.enums.ArtisanType;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.farming.Seed;
import com.stardew_valley.models.farming.Tree;
import com.stardew_valley.models.farming.TreeSource;
import com.stardew_valley.models.fish.FishInfo;
import com.stardew_valley.models.foraging.ForagingMineral;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.models.relations.Friendship;
import com.stardew_valley.models.shop.enums.Shop;
import com.stardew_valley.models.tool.Tool;
import com.stardew_valley.models.weather.Weather;

import java.util.Random;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GameView extends ScreenAdapter implements InputProcessor {
    private Stage stage;
    private final GameController controller;
    private final OrthographicCamera camera;
    private Player player;
    private final Batch batch;
    private final TextureRegion springBackground = AssetManager.getAssetManager().getSpringBackground();
    private final TextureRegion summerBackground = AssetManager.getAssetManager().getSummerBackground();
    private final TextureRegion fallBackground = AssetManager.getAssetManager().getFallBackground();
    private final TextureRegion winterBackground = AssetManager.getAssetManager().getWinterBackground();

    private final TextureRegion woodFence = AssetManager.getAssetManager().getWoodFence();
    private final TextureRegion house = AssetManager.getAssetManager().getHouse();
    private final TextureRegion greenhouse = AssetManager.getAssetManager().getGreenhouse();
    private final TextureRegion greenhouse_broken = AssetManager.getAssetManager().getGreenhouseBroken();
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

    private final List<NPC> npcs;

    private float globalDelta = 0f;

    private Dialog terminalDialog;
    private TextField textField;

    private Dialog pixelDialog;
    private boolean isPixelDialogVisible = false;

    private Dialog buildAreaDialog;
    private boolean isBuildAreaDialogVisible = false;

    private boolean isAnimalDialogVisible = false;
    private Dialog animalDialog;


    private boolean isDialogShown = false;

    private final TextureRegion highlightBox = AssetManager.getAssetManager().getBlackTexture();
    private final TextureRegion highlightBoxLight = AssetManager.getAssetManager().getWhiteTexture();

    private final DateTimeView dateTimeView;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Actor miniMapActor = null;

    private final static int TILE_SIZE = 16;

    private final float speed = 200f;

    private final WindowManager inventoryMenu;
    private final InventoryView inventoryView;
    private final SkillsView skillsView;
    private final SocialView socialView;
    private final MiniMapView miniMapView;
    private final SettingsView settingsView;

    private final ShippingBinView shippingBinView;
    private final FoodMenuView foodMenuView;
    private SaloonView saloon;
    private FishingShopView fishShop;
    private FishingWindow fishingWindow;
    private FishSelectWindow fishSelectWindow;

    private final FriendshipView friendshipView;
    private final TextButton friendshipsButton;

    private final GiftView giftView;

    private final NotificationsView notificationsView;

    private Image backgroundImage;
    private Image heartImage;
    private Label energyMessageLabel;

    private final EnergyView energyView;

    private final List<Area> areas = new ArrayList<>();
    private final List<Animal> animals = new ArrayList<>();

    private static Label messageLabel = new Label("", AssetManager.getAssetManager().getSkin());

    private float buff = 1f;
    private float buffTimer = 0f;
    private float maxEnergyTimer = 0f;
    private ShapeRenderer darknessRenderer;

    private float shakeTime = 0f;
    private float shakeDuration = 0f;
    private float shakeIntensity = 0f;
    private Vector3 originalCameraPos = new Vector3();

    public GameView(GameController controller) {
        stage = new Stage(new ScreenViewport());
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = controller.getRepo().getCurrentGame().getCurrentPlayer();
        npcs = controller.getRepo().getCurrentGame().getFarm().getNPCs();
        player.setAnimals(animals);
        batch = Main.getBatch();
        this.dateTimeView = new DateTimeView(controller.getDateTimeController());
        this.inventoryMenu = new WindowManager(stage);
        this.shippingBinView = new ShippingBinView(controller.getShippingBinController(), stage);
        this.foodMenuView = new FoodMenuView(stage);
        this.skillsView = new SkillsView(stage);
        this.inventoryView = new InventoryView(stage);
        this.socialView = new SocialView(stage);
        this.miniMapView = new MiniMapView(stage, Repository.getRepo().getCurrentGame().getFarm().getTiles(), Repository.getRepo().getCurrentGame().getCurrentPlayer());
        this.settingsView = new SettingsView(controller.getSettingsController(), stage);
        friendshipsButton = new TextButton("Friendships", AssetManager.getAssetManager().getSkin());
        giftView = new GiftView(controller.getRelationshipController(), stage, inventoryView);
        friendshipView = new FriendshipView(controller.getRelationshipController(), player.getRelationService(), stage, giftView);
        notificationsView = new NotificationsView(stage);
        this.energyView = new EnergyView(player);
        heartImage = new Image(AssetManager.getAssetManager().getHeart());
        backgroundImage = new Image(AssetManager.getAssetManager().getBackgroundMessage());
        messageLabel = new Label("", AssetManager.getAssetManager().getSkin());
        darknessRenderer = new ShapeRenderer();
        energyMessageLabel = new Label("", AssetManager.getAssetManager().getSkin());
//        messageLabel = new Label("", AssetManager.getAssetManager().getSkin());
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        createDialog();

        inventoryMenu.addWindow("Inventory", inventoryView);
        inventoryMenu.addWindow("Skills", skillsView);
        inventoryMenu.addWindow("Social", socialView);
        inventoryMenu.addWindow("Map", miniMapView);
        inventoryMenu.addWindow("Settings", settingsView);
        inventoryMenu.showWindow(inventoryView);

        stage.addActor(shippingBinView);
        stage.addActor(foodMenuView);

        stage.addActor(friendshipView);
        friendshipsButton.setSize(150, 80);
        friendshipsButton.getLabel().setFontScale(0.8f);
        friendshipsButton.setPosition(Gdx.graphics.getWidth() - 170, 20);
        stage.addActor(friendshipsButton);
        friendshipsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                friendshipView.setVisible(!friendshipView.isVisible());
                if (!friendshipView.isVisible()) stage.setKeyboardFocus(null);
            }
        });

        heartImage.setSize(64, 64);
        heartImage.setVisible(false);

        stage.addActor(heartImage);

        messageLabel.setPosition(60, 60);
        stage.addActor(messageLabel);
    }

    @Override
    public void render(float delta) {
        globalDelta = delta;
        updateGame(delta);
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1);
        camera.position.set(player.getPosition().x(), player.getPosition().y(), 0);
        camera.zoom = 0.5f;
        updateCameraShake(delta);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        checkGreenHouseActivated();
        drawWorld();
        dateTimeView.update();
        energyView.updateEnergy();
        energyView.render(delta);
        inventoryMenu.update();
        skillsView.update();
        friendshipView.update();
        notificationsView.update();
        giftView.update();
        foodMenuView.update();
        shippingBinView.update();
        eatFood();
//        notificationsView.update();
        batch.end();
        if (controller.getRepo().getCurrentGame().getTimeManager().getNow().getHour() >= 18) {
            drawDark(0.7f);
        }

        if (shakeTime < shakeDuration) {
            float currentIntensity = shakeIntensity * (1 - shakeTime / shakeDuration);
            float darknessAlpha = 0.8f * (currentIntensity / shakeIntensity);
            drawDark(darknessAlpha);
        }

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
            inventoryMenu.setVisible(!inventoryMenu.isVisible());
            return true;
        }
        if (i == Input.Keys.NUM_0) {
            controller.getFarmingController().cheatPlowNineTiles(player.getTilesPosition());
            return true;
        }

        if (i == Input.Keys.NUM_5) {
            Main.getMain().setScreen(new JojamartView(new JojaMartController(Repository.getRepo())));
            return true;
        }

        if (i == Input.Keys.NUM_6) {
            Main.getMain().setScreen(new BlacksmithView(new BlackSmithController(Repository.getRepo())));
            return true;
        }

        if (i == Input.Keys.NUM_7) {
            if (saloon == null) {
                saloon = new SaloonView(stage);
                saloon.setPosition(700, 300);
                saloon.setSize(650, 600);
                stage.addActor(saloon);
            } else {
                saloon.remove();
                saloon = null;
            }
            return true;
        }

        if (i == Input.Keys.NUM_8) {
            if (fishShop == null) {
                fishShop = new FishingShopView(stage);
                fishShop.setPosition(700, 300);
                fishShop.setSize(850, 600);
                stage.addActor(fishShop);
            } else {
                fishShop.remove();
                fishShop = null;
            }
            return true;
        }

        if (i == Input.Keys.U) {
            fishSelectWindow = new FishSelectWindow(stage);
            fishSelectWindow.setPosition(500, 160);
            fishSelectWindow.setSize(1000, 800);
            stage.addActor(fishSelectWindow);
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
                } else if (equippedSlot.getItem() instanceof TreeSource treeSource) {
                    controller.getFarmingController().plant(treeSource.getInfo().getName(), direction);
                }
            }
            return true;
        }

        if (button == 1) {
            Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
            int tileX = (int) (worldCoords.x / TILE_SIZE);
            int tileY = (int) (worldCoords.y / TILE_SIZE);
            for (Animal animal : animals) {
                System.out.println(animal.getPosition().x() + " " + animal.getPosition().y());
                System.out.println(tileX + " " + tileY);
                if (animal.getPosition().x() == tileY && animal.getPosition().y() == tileX) {
                    showAnimalActionDialog(stage, AssetManager.getAssetManager().getSkin(), animal);
                    return true;
                }
            }
            for (NPC npc : npcs) {
                System.out.println(npc.getPosition().x() + " " + npc.getPosition().y());
                if (npc.isInsidePlusIcon(worldCoords.x, worldCoords.y)) {
                    showNpcActionDialog(stage, AssetManager.getAssetManager().getSkin(), npc);
                }
                if (npc.isInsideChatIcon(worldCoords.x, worldCoords.y) && !npc.getMessage().isEmpty()) {
                    showSimpleDialog(stage, AssetManager.getAssetManager().getSkin(), "NPC Answer", npc.getMessage());
                }
            }
            for (Artisan artisan : player.getArtisans()) {
                if (artisan.isArtisanClicked(worldCoords.x, worldCoords.y)) {
                    showArtisanDialog(stage, AssetManager.getAssetManager().getSkin(), artisan);
                }
                if (artisan.isDoneClicked(worldCoords.x, worldCoords.y)) {
                    artisan.finish();
                }
            }
            if (player.getFarm().getTiles().get(tileX).get(tileY).getType() == TileType.GREENHOUSE) {
                showActivateCancelDialog();
            }
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
        drawBackgroundTile();

        drawBuilding();

        drawFence();

        drawDividingFences();

        drawHouseTop();

        drawNPCs();

        drawArtisan();

        drawShippingBin();

        drawShopping();

//        drawTileHighlights();

        //printTileTypeCounts();

        drawPlowedTiles();

        drawTileObjectsExceptTrees();

        drawAnimals();

        drawPlayers();

        drawEquippedTool();

        drawHouseTop();

        drawTrees();
    }

    private void drawEquippedTool() {
        if (player.getInventory().getEquippedSlot() == null) return;
        Tool tool = (player.getInventory().getEquippedSlot().getItem() instanceof Tool) ? (Tool) player.getInventory().getEquippedSlot().getItem() : null;

        if (tool != null) {
            batch.draw(tool.getTexture(), player.getX() + 7, player.getY() + 5, 14, 14);
        }
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

    private void drawTileObjectsExceptTrees() {
        for (List<Tile> row : controller.getRepo().getCurrentGame().getFarm().getTiles()) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && !(tile.getObject() instanceof Tree)) {
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

    private void drawArtisan() {
        for (Artisan artisan : player.getArtisans()) {
            artisan.draw(batch);
        }
    }

    private void drawTrees() {
        List<Tile> treeTiles = new ArrayList<>();
        for (List<Tile> row : controller.getRepo().getCurrentGame().getFarm().getTiles()) {
            for (Tile tile : row) {
                if (!tile.isEmpty() && tile.getObject() instanceof Tree) {
                    treeTiles.add(tile);
                }
            }
        }

        for (int i = treeTiles.size() - 1; i >= 0; i--) {
            Tile tile = treeTiles.get(i);
            Texture texture = tile.getObject().getTexture();
            float aspectRatio = (float) texture.getHeight() / texture.getWidth();
            float width = 28f;
            float height = width * aspectRatio;

            if (tile.getObject() instanceof ForagingMineral) {
                batch.draw(texture, tile.getPosition().x() * 16, tile.getPosition().y() * 16, width, height);
            } else {
                batch.draw(texture, tile.getPosition().y() * 16, tile.getPosition().x() * 16, width, height);
            }
        }
    }

    private void drawAnimals() {
        for (Animal animal : animals) {
            animal.update(globalDelta);
            animal.draw(batch);
        }
    }

    private void drawPlayers() {
        for (Player p : controller.getRepo().getCurrentGame().getPlayers())
            batch.draw(p.getCurrentFrame(), p.getX(), p.getY());
        //System.out.println((int) (player.getX() / 16) + " " + (int) (player.getY() / 16));
    }

    private void drawNPCs() {
        for (NPC npc : npcs) {
            npc.draw(batch);
            npc.update(globalDelta);
        }
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

            int greenhouseX = getTilePixel(FarmInitializer.getGreenhouseStartingPointX()) + Repository.getRepo().getCurrentGame().initializePositionReturn(i).x() - 80;
            int greenhouseY = getTilePixel(FarmInitializer.getGreenhouseStartingPointY()) + Repository.getRepo().getCurrentGame().initializePositionReturn(i).y() - 80;
            if (Repository.getRepo().getCurrentGame().getPlayers().size() > i && Repository.getRepo().getCurrentGame().getPlayers().get(i).isGreenHouseActivated()) {
                batch.draw(greenhouse, greenhouseX, greenhouseY);
            } else {
                batch.draw(greenhouse_broken, greenhouseX, greenhouseY);
            }


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

    private void checkGreenHouseActivated() {
        List<Player> players = controller.getRepo().getCurrentGame().getPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (!players.get(i).isGreenHouseActivated()) {
                 FarmInitializer.setSpecialTilesUnmovable(i);
            } else {
                FarmInitializer.setSpecialTilesMovable(i);
            }
        }
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

    private float stateTime = 0f;

    private void drawShippingBin() {
        stateTime += Gdx.graphics.getDeltaTime();
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int numRows = tiles.size();
        int numCols = tiles.get(0).size();

        Animation<TextureRegion> animation = AssetManager.getAssetManager().getShippingBinAnimation();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                Tile tile = tiles.get(row).get(col);
                if (tile.getType() == TileType.SHIPPING_BIN) {
                    batch.draw(currentFrame, getTilePixel(col), getTilePixel(row), 32f, 32f);
                }
            }
        }
    }

    private void drawShopping() {

        for (Shop shop: Shop.values()) {
            Position BL = shop.getBottomLeft();
            Texture texture = shop.getTexture();
            batch.draw(texture,getTilePixel(BL.x()),getTilePixel(BL.y()),160f,160f);
        }
     }

    private boolean isDialogOpen = false;

    private void checkPlayerNearShippingBin() {
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int numRows = tiles.size();
        int numCols = tiles.get(0).size();

        float playerX = player.getPosition().x();
        float playerY = player.getPosition().y();
        float maxDistance = 32f;

        boolean foundNearby = false;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = tiles.get(row).get(col);
                if (tile.getType() == TileType.SHIPPING_BIN) {
                    float tileX = col * TILE_SIZE;
                    float tileY = row * TILE_SIZE;
                    float dx = playerX - tileX;
                    float dy = playerY - tileY;
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);

                    if (distance < maxDistance) {
                        foundNearby = true;
                        if (!isDialogOpen) {
                            shippingBinView.setVisible(true);
                            shippingBinView.setTouchable(Touchable.enabled);
                            shippingBinView.reset();
                            isDialogOpen = true;
                        }
                        break;
                    }
                }
            }
            if (foundNearby) break;
        }

        if (!foundNearby && isDialogOpen) {
            shippingBinView.setVisible(false);
            shippingBinView.setTouchable(Touchable.disabled);
            isDialogOpen = false;
        }
    }

    public void hug() {
        boolean isNear = false;
        Player anotherPlayer = null;

        for (Player friend : controller.getRepo().getCurrentGame().getPlayers()) {
            if (player == friend) continue;
            if (player.isNearTo(friend)) {
                isNear = true;
                anotherPlayer = friend;
                break;
            }
        }

        if (!isNear) {
            showMessage("you should be near to another player!");
            return;
        }

        Friendship friendship = player.getRelationService().getFriendship(anotherPlayer);

        if (friendship.getLevel() < 2) {
            showMessage("you don't have enough level!");
            return;
        }

        DateTime currentTime = controller.getRepo().getCurrentGame().getTimeManager().getNow();

        if (friendship.getLastHugDay() != currentTime.getDay()) {
            friendship.setLastHugDay(currentTime.getDay());
            friendship.increaseXp(Friendship.HUG_XP);
        }

        int worldX = player.getPosition().x();
        int worldY = player.getPosition().y();

        Vector3 screenPos = camera.project(new Vector3(worldX, worldY, 0));

        heartImage.setPosition(screenPos.x - 20, screenPos.y + 60);
        heartImage.getColor().a = 1f;
        heartImage.setVisible(true);
        heartImage.clearActions();

        heartImage.addAction(Actions.sequence(
            Actions.delay(0.2f),
            Actions.parallel(
                Actions.moveBy(0, 50, 1f),
                Actions.fadeOut(1.5f)
            ),
            Actions.run(() -> heartImage.setVisible(false))
        ));

        showMessage("you hugged each other!");

    }

    private void marriage() {
        boolean isNear = false;
        Player anotherPlayer = null;

        for (Player friend : controller.getRepo().getCurrentGame().getPlayers()) {
            if (player == friend) continue;
            if (player.isNearTo(friend)) {
                isNear = true;
                anotherPlayer = friend;
                break;
            }
        }

        if (!isNear) {
            showMessage("you should be near to another player!");
            return;
        }

//        if (player.getGender() == Gender.FEMALE) {
//            showMessage("you are girl and you can't request marriage");
//            return;
//        }
//
//        if (anotherPlayer.getGender() == Gender.MALE) {
//            showMessage(anotherPlayer.getUser().getNickname() + "is a boy!");
//            return;
//        }
//
//        Friendship friendship = player.getRelationService().getFriendship(anotherPlayer);
//        if (friendship.getLevel() != 3) {
//            showMessage("you don't have enough level!");
//            return;
//        }
//
//        if (player.getInventory().getSlot("ring") == null) {
//            showMessage("you don't have ring in your inventory");
//            return;
//        }
        //send message
        MarriageRequest request = new MarriageRequest(player.getUser());
        anotherPlayer.addMarriageRequest(request);
        showMessage("your request sent to " + anotherPlayer.getUser().getNickname());
    }

    private Window marriageRequestsWindow = null;
    private boolean isNpressed = false;

    private void showMarriageRequestsWindow(Player currentPlayer) {
        if (!isNpressed) {
            marriageRequestsWindow = new Window("Marriage Requests", AssetManager.getAssetManager().getSkin());
            marriageRequestsWindow.setSize(1000, 600);
            marriageRequestsWindow.setPosition(500, 300);
            marriageRequestsWindow.setModal(true);
            marriageRequestsWindow.setMovable(true);

            for (MarriageRequest request : currentPlayer.getMarriageRequests()) {
                Table marriageRequestsTable = new Table();
                Label label = new Label(request.getFrom().getNickname() + " wants to marry you", AssetManager.getAssetManager().getSkin());
                TextButton acceptBtn = new TextButton("accept", AssetManager.getAssetManager().getSkin());
                TextButton rejectBtn = new TextButton("reject", AssetManager.getAssetManager().getSkin());
                acceptBtn.setSize(80, 80);
                rejectBtn.setSize(80, 80);

                Player anotherPlayer = request.getFrom().getPlayer();
                Friendship friendship = player.getRelationService().getFriendship(anotherPlayer);

                acceptBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentPlayer.removeMarriageRequest(request);
                        showMessage("you married with " + request.getFrom().getNickname());
                        friendship.setLevel(4);
                        anotherPlayer.getInventory().getSlot("ring").removeQuantity(1);
                        currentPlayer.getInventory().getSlot("ring").addQuantity(1);

                        currentPlayer.getRelationService().marry(anotherPlayer);
                        currentPlayer.updateOfMarriage(anotherPlayer);
                        marriageRequestsWindow.remove();
                        isNpressed = false;
                    }
                });

                rejectBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentPlayer.removeMarriageRequest(request);
                        showMessage("you rejected the request");
                        friendship.setLevel(0);
                        friendship.setXp(0);
                        double energy = anotherPlayer.getEnergy().getMaxEnergy();
                        anotherPlayer.getEnergy().setMaxEnergy(energy / 2);
                        anotherPlayer.getEnergy().setAmount(Math.min(100, energy));
                        anotherPlayer.setEnergyHalved(true);
                        marriageRequestsWindow.remove();
                        isNpressed = false;
                    }
                });

                marriageRequestsTable.add(label).pad(20);
                marriageRequestsTable.add(acceptBtn).pad(20);
                marriageRequestsTable.add(rejectBtn).pad(20);

                marriageRequestsWindow.add(marriageRequestsTable);
            }

            stage.addActor(marriageRequestsWindow);
            isNpressed = true;

        } else {
            if (marriageRequestsWindow != null) {
                marriageRequestsWindow.remove();
            }
            isNpressed = false;
        }
    }

    public void eatFood() {

        Item item = foodMenuView.getFoodItem();
        if (item == null) return;

        Texture foodTexture = item.getTexture();
        Image foodImage = new Image(foodTexture);
        foodImage.setVisible(false);
        stage.addActor(foodImage);

        int worldX = player.getPosition().x();
        int worldY = player.getPosition().y();

        Vector3 screenPos = camera.project(new Vector3(worldX, worldY, 0));

        foodImage.setPosition(screenPos.x - 10, screenPos.y + 60);
        foodImage.getColor().a = 1f;
        foodImage.setVisible(true);
        foodImage.clearActions();

        foodImage.addAction(Actions.sequence(
            Actions.delay(0.2f),
            Actions.parallel(
                Actions.moveBy(0, 50, 1f),
                Actions.fadeOut(2.5f)
            ),
            Actions.run(() -> foodImage.setVisible(false))
        ));

        showMessage("you eat the " + item.getName().toLowerCase());
        foodMenuView.setFoodItem(null);

        CookingRecipes foodEnergy = null;
        for (CookingRecipes recipe : CookingRecipes.values()) {
            if (recipe.getName().equalsIgnoreCase(item.getName())) {
                foodEnergy = recipe;
            }
        }

        player.getEnergy().increase(foodEnergy.getEnergy());

        if (foodEnergy.getName().equals(CookingRecipes.OMELET.getName())) {
            setBuff(2.5f);
            player.getEnergy().setMaxEnergy(player.getEnergy().getMaxEnergy() + 100);
            maxEnergyTimer = 0f;
            buffTimer = 0f;
        }
    }

    private void setBuff(float buff) {
        this.buff = buff;
    }

    boolean isShown = false;
    public void toggleFoodMenu() {
        if (!isShown) {
            foodMenuView.setVisible(true);
        }
        else {
            foodMenuView.setVisible(false);
        }
        isShown = !isShown;
    }

    private void showMessage(String text) {
        energyMessageLabel.setText(text);
        energyMessageLabel.setFontScale(0.8f);
        energyMessageLabel.pack();

        energyMessageLabel.setPosition(
            (Gdx.graphics.getWidth() - energyMessageLabel.getWidth()) / 2f,
            43
        );

        backgroundImage.setSize(energyMessageLabel.getWidth() + 30, energyMessageLabel.getHeight() + 20);
        backgroundImage.setPosition(
            energyMessageLabel.getX() - 15,
            energyMessageLabel.getY() - 10
        );

        energyMessageLabel.clearActions();
        backgroundImage.clearActions();

        if (!stage.getActors().contains(backgroundImage, true))
            stage.addActor(backgroundImage);

        if (!stage.getActors().contains(energyMessageLabel, true))
            stage.addActor(energyMessageLabel);

        energyMessageLabel.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.removeActor()
        ));

        backgroundImage.addAction(Actions.sequence(
            Actions.delay(2f),
            Actions.removeActor()
        ));
    }

    public void drawDark(float alpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void startCameraShake(float duration, float intensity) {
        shakeDuration = duration;
        shakeIntensity = intensity;
        shakeTime = 0f;
        originalCameraPos.set(player.getPosition().x(), player.getPosition().y(), 0);
    }

    public void updateCameraShake(float delta) {
        shakeTime += delta;
        if (shakeTime < shakeDuration) {
            float currentIntensity = shakeIntensity * (1 - shakeTime / shakeDuration);
            float offsetX = (float)((Math.random() - 0.5) * 2 * currentIntensity);
            float offsetY = (float)((Math.random() - 0.5) * 2 * currentIntensity);

            camera.position.set(originalCameraPos.x + offsetX, originalCameraPos.y + offsetY, 0);
        }
        else {
            camera.position.set(player.getPosition().x(), player.getPosition().y(), 0);
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


    private void drawBackgroundTile() {
        switch (Repository.getRepo().getCurrentGame().getTimeManager().getNow().getSeason()) {
            case WINTER:
                batch.draw(winterBackground, 0, 0);
            case SUMMER:
                batch.draw(summerBackground, 0, 0);
            case FALL:
                batch.draw(fallBackground, 0, 0);
            default:
                batch.draw(springBackground, 0, 0);
        }
    }

    public void updateGame(float delta) {
        controller.getRepo().getCurrentGame().getCurrentPlayer().updateStateTime(delta);
        handleMovement(delta);
        checkPlayerNearShippingBin();
    }

    private float faintingTimer = 0f;
    public void handleMovement(float delta) {
        if (friendshipView.isVisible()) return;

        boolean moving = false;

        maxEnergyTimer += delta;

        if (buffTimer > 5f) {
            setBuff(1f);
        }

        if (maxEnergyTimer > 20f) {
            player.getEnergy().setMaxEnergy(200f);
            if (player.getEnergy().getAmount() >= 200f) {
                player.getEnergy().setAmount(200f);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.GRAVE)) {
            toggleDialog();
        }

        if (isDialogShown) {
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            toggleBuildArea();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            handleAnimalDialogToggle(stage, AssetManager.getAssetManager().getSkin());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            showAnimalProductDialog();
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
            miniMapView.toggleMiniMap();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            controller.getRepo().getCurrentGame().getTimeManager().getNow().advanceHour();
        }


        if (player.getEnergy().hasPassedOut() && !player.isFainting()) {
            player.setFainting(true);
            return;
        }

        if (player.isFainting()) {
            faintingTimer += delta;
            float FAINTING_DURATION = 3.0f;
            if (faintingTimer >= FAINTING_DURATION) {
                player.setFainting(false);
                faintingTimer = 0f;
                controller.getSettingsController().nextTurn();
            }
            return;
        }

        Energy energy = player.getEnergy();
        double consumeAmount = 1.0 / 96.0;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            float nextX = player.getX() + speed * delta * buff;
            if (canMoveTo(nextX, player.getY())) {
                player.setX(nextX);
                player.setDirection(Direction.RIGHT);
                moving = true;
                energy.consume(consumeAmount);
                buffTimer += delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            float nextX = player.getX() - speed * delta * buff;
            if (canMoveTo(nextX, player.getY())) {
                player.setX(nextX);
                player.setDirection(Direction.LEFT);
                moving = true;
                energy.consume(consumeAmount);
                buffTimer += delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            float nextY = player.getY() + speed * delta * buff;
            if (canMoveTo(player.getX(), nextY)) {
                player.setY(nextY);
                player.setDirection(Direction.UP);
                moving = true;
                energy.consume(consumeAmount);
                buffTimer += delta;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            float nextY = player.getY() - speed * delta * buff;
            if (canMoveTo(player.getX(), nextY)) {
                player.setY(nextY);
                player.setDirection(Direction.DOWN);
                moving = true;
                energy.consume(consumeAmount);
                buffTimer += delta;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            hug();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            marriage();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            showMarriageRequestsWindow(player);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            toggleFoodMenu();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            startCameraShake(1f, 10f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            Main.getMain().setScreen(new CookingView(new CookingController(Repository.getRepo())));
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            Main.getMain().setScreen(new CraftingView(new CraftingController(Repository.getRepo())));
            return;
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
        selectBox.setItems(
            "A",
            "B",
            "Bee House",
            "Charcoal Kiln",
            "Cheese Press",
            "Dehydrator",
            "Fish Smoker",
            "Furnace",
            "Keg",
            "Loom",
            "Mayonnaise Machine",
            "Oil Maker",
            "Preserves Jar"
        );

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
                                togglePixelDialog(6, 7, "A");
                            break;
                        case "B":
                            if (!isPixelDialogVisible)
                                togglePixelDialog(4, 4, "B");
                            break;
                        case "Bee House":
                        case "Charcoal Kiln":
                        case "Cheese Press":
                        case "Dehydrator":
                        case "Fish Smoker":
                        case "Furnace":
                        case "Keg":
                        case "Loom":
                        case "Mayonnaise Machine":
                        case "Oil Maker":
                        case "Preserves Jar":
                            if (!isPixelDialogVisible)
                                togglePixelDialog(3, 6, selected);
                            break;
                        default:
                            if (!isPixelDialogVisible)
                                togglePixelDialog(1, 1, "Unknown");
                            break;
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
        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();
        int maxRows = tiles.size();
        int maxCols = tiles.get(0).size();

        if (row < 0 || col < 0 || row + height > maxRows || col + width > maxCols) {
            return false;
        }

        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                Tile tile = tiles.get(r).get(c);
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


    public ImageButton createPixelButton(TextureRegionDrawable drawable, int row, int col, int height,
                                         int width, String type) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = drawable.tint(Color.LIGHT_GRAY);
        style.imageDown = drawable.tint(Color.DARK_GRAY);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (type.equals("A") || type.equals("B")) {
                    if (isPlantableArea(row, col, height, width)) {
                        setObject(row, col, height, width, type);
                        areas.add(new Area(row, col, height, width, type.equals("A") ? AreaType.BARN : AreaType.CAGE));
                    }
                } else {
                    createArtisan(row, col, type);
                }

                pixelDialog.hide();
                isPixelDialogVisible = false;
            }
        });

        return button;
    }

    public void createArtisan(int row, int col, String type) {
        int height = 3;
        int width = 6;

        player.addArtisan(new Artisan(col * 16, row * 16, ArtisanType.fromString(type), controller.getRepo()));

        List<List<Tile>> tiles = controller.getRepo().getCurrentGame().getFarm().getTiles();

        for (int r = row; r <= row + height; r++) {
            for (int c = col; c <= col + width; c++) {
                Tile tile = tiles.get(r).get(c);
                tile.setMovable(false);
            }
        }


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

    public void showAnimalActionDialog(Stage stage, Skin skin, Animal animal) {
        Dialog dialog = new Dialog("Animal Actions", skin);
        dialog.setModal(true);
        dialog.setMovable(true);
        dialog.setResizable(false);

        Table content = dialog.getContentTable();
        content.defaults().pad(8).width(300).height(80);

        String[] actions = {
            "Feed",
            "Pet",
            "Release from Cage",
            "Sell",
        };

        for (String action : actions) {
            TextButton actionButton = new TextButton(action, skin, "default");
            actionButton.getLabel().setFontScale(1f);

            actionButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleAnimalAction(action, animal);
                    System.out.println("Action performed: " + action);
                }
            });

            content.add(actionButton).row();
        }

        Label infoLabel = new Label(
            "Friendship Level: " + animal.getFriendshipLevel() + "\n" +
                "Has Product: " + (animal.hasAnyProduct() ? "Yes" : "No") + "\n" +
                "Has Been Petted: " + (animal.isPetted() ? "Yes" : "No"),
            skin
        );
        infoLabel.setFontScale(1.1f);
        content.add(infoLabel).colspan(1).center().row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getLabel().setFontScale(1.1f);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getButtonTable().add(closeButton).pad(20).width(200).height(80);

        dialog.show(stage);

        dialog.setSize(800, 800);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }


    public void showNpcActionDialog(Stage stage, Skin skin, NPC npc) {
        Dialog dialog = new Dialog("NPC" + npc.getType().name() + " Interaction", skin);
        dialog.setModal(true);
        dialog.setMovable(true);
        dialog.setResizable(false);

        Table content = dialog.getContentTable();
        content.defaults().pad(10).width(300).height(80);


        String[] actions = {
            "Chat",
            "View Quests"
        };

        for (String action : actions) {
            TextButton actionButton = new TextButton(action, skin, "default");
            actionButton.getLabel().setFontScale(1.1f);

            actionButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleNpcAction(action, npc);
                    dialog.hide();
                }
            });

            content.add(actionButton).row();
        }

        Label friendshipLabel = new Label(
            "Friendship Level:" + npc.getFriendshipLevel(),
            skin
        );
        friendshipLabel.setFontScale(1.2f);
        content.add(friendshipLabel).center().colspan(1).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getLabel().setFontScale(1.1f);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getButtonTable().add(closeButton).pad(20).width(200).height(80);

        dialog.show(stage);
        dialog.setSize(800, 800);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }


    private void handleNpcAction(String action, NPC npc) {
        switch (action.toLowerCase()) {
            case "chat":
                handleTalkNpc(npc);
                break;
            case "view quests":
                showQuestDialog(stage, AssetManager.getAssetManager().getSkin(), npc, player);
                break;
        }
    }


    private void handleTalkNpc(NPC npc) {
        Skin skin = AssetManager.getAssetManager().getSkin();
        Dialog dialog = new Dialog("Talk to " + npc.getType().name(), skin);

        final TextField messageField = new TextField("", skin);
        messageField.setMessageText("Say something...");

        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String message = messageField.getText();
                sendMessageToNpc(npc, message);
                dialog.hide();
            }
        });


        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });


        Table content = new Table();
        content.pad(10);
        content.add(messageField).width(250).padBottom(10).row();
        content.add(okButton).pad(5);
        content.add(cancelButton).pad(5);

        dialog.getContentTable().add(content);
        dialog.show(stage);
    }


    private void sendMessageToNpc(NPC npc, String message) {
        System.out.println("Sending message: " + message);
        int hour = controller.getRepo().getCurrentGame().getTimeManager().getNow().getHour();
        Season season = controller.getRepo().getCurrentGame().getTimeManager().getNow().getSeason();
        Weather weather = controller.getRepo().getCurrentGame().getWeatherManager().getTodayWeather();
        npc.setMessage(npc.talkWithPlayer(player, message, season, weather, hour));
        System.out.println("Sent message: " + npc.getMessage());

    }


    private void handleAnimalAction(String action, Animal animal) {
        switch (action.toLowerCase()) {
            case "feed":
                handleFeedAnimal(animal);
                break;
            case "pet":
                handlePetAnimal(animal);
                break;
            case "release from cage":
                handleReleaseAnimal(animal);
                break;
            case "sell":
                handleSellAnimal(animal);
                break;
            case "collect products":
                handleCollectAnimalProduct(animal);
                break;
        }
    }

    private void handleFeedAnimal(Animal animal) {
        animal.feedByHay();
        System.out.println("Feeding animal: 1");
    }

    private void handlePetAnimal(Animal animal) {
        animal.handlePetting();
    }

    private void handleReleaseAnimal(Animal animal) {
        if (animal.isNearPlayer()) {
            animal.moveToOwner();
            System.out.println("Released animal: 2");
        }
    }

    private void handleSellAnimal(Animal animal) {
        animal.sellAnimal();
    }

    private void handleCollectAnimalProduct(Animal animal) {
        animal.collectProduct();
    }


    public void createAnimalDialog(final Stage stage, Skin skin) {
        animalDialog = new Dialog("Add Animal", skin);

        final SelectBox<String> animalSelectBox = new SelectBox<>(skin);
        animalSelectBox.setItems("cow", "rabbit", "hen", "goat", "sheep", "dinosaur", "duck", "pig");

        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selectedAnimal = animalSelectBox.getSelected();
                if (checkAreaValidation(selectedAnimal)) {
                    addAnimalToArea(selectedAnimal);
                }
                animalDialog.hide();
                isAnimalDialogVisible = false;
            }
        });

        animalDialog.getContentTable().add(animalSelectBox).padBottom(10).row();
        animalDialog.getContentTable().add(okButton);
        animalDialog.setSize(300, 200);
        animalDialog.setModal(true);
        animalDialog.setMovable(true);
    }

    public void handleAnimalDialogToggle(Stage stage, Skin skin) {
        if (!isAnimalDialogVisible) {
            if (animalDialog == null) {
                createAnimalDialog(stage, skin);
            }
            stage.addActor(animalDialog);
            animalDialog.show(stage);
            isAnimalDialogVisible = true;
        } else {
            animalDialog.hide();
            isAnimalDialogVisible = false;
        }
    }

    public boolean checkAreaValidation(String animalType) {
        switch (animalType) {
            case "cow":
            case "sheep":
            case "goat":
            case "dinosaur":
            case "pig":
                for (Area area : areas) {
                    if (area.type().equals(AreaType.BARN)) return true;
                }
                return false;
            default:
                for (Area area : areas) {
                    if (area.type().equals(AreaType.CAGE)) return true;
                }
                return false;
        }
    }

    private void showAnimalProductDialog() {
        Skin skin = AssetManager.getAssetManager().getSkin();
        Dialog dialog = new Dialog("Animal Products", skin);
        dialog.getContentTable().defaults().pad(10);

        boolean hasAnyProduct = false;

        Table productList = new Table();

        for (Animal animal : player.getAnimals()) {
            if (animal.hasAnyProduct()) {
                hasAnyProduct = true;

                String info = animal.getAnimalInfo().name();
                String product = animal.getAnimalProductType().toString();
                int x = (int) (animal.getX() / 16);
                int y = (int) (animal.getY() / 16);

                Label label = new Label(
                    info + " | " + product + " | (" + x + "," + y + ")",
                    skin
                );

                TextButton getButton = new TextButton("Get", skin);
                getButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        animal.collectProduct();
                        label.setText(info + " | Collected");
                        getButton.setDisabled(true);
                    }
                });

                productList.add(label).left().padRight(10);
                productList.add(getButton).right();
                productList.row();
            }
        }

        if (!hasAnyProduct) {
            productList.add(new Label("No products available", skin));
        }

        ScrollPane scrollPane = new ScrollPane(productList, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        dialog.getContentTable().add(scrollPane).width(700).height(450);
        dialog.row();

        dialog.button("Close", true);
        dialog.show(stage);
    }


    public void addAnimalToArea(String animalType) {
        boolean isBarnAnimal = switch (animalType) {
            case "cow", "sheep", "goat", "dinosaur", "pig" -> true;
            default -> false;
        };

        AreaType targetAreaType = isBarnAnimal ? AreaType.BARN : AreaType.CAGE;

        for (Area area : areas) {
            if (area.type().equals(targetAreaType)) {
                Position pos = getRandomInnerPosition(area);
                Animal animal = new Animal(getAnimalInfo(animalType), player, pos, pos.x() * 16, pos.y() * 16);
                animals.add(animal);
                return;
            }
        }
    }

    private Position getRandomInnerPosition(Area area) {
        Random random = new Random();
        int x = area.row() + 1 + random.nextInt(area.height() - 2);
        int y = area.col() + 1 + random.nextInt(area.width() - 2);
        return new Position(x, y);
    }


    private AnimalInfo getAnimalInfo(String animalType) {
        switch (animalType) {
            case "cow":
                return AnimalInfo.COW;
            case "sheep":
                return AnimalInfo.SHEEP;
            case "goat":
                return AnimalInfo.GOAT;
            case "dinosaur":
                return AnimalInfo.DINOSAUR;
            case "pig":
                return AnimalInfo.PIG;
            case "rabbit":
                return AnimalInfo.RABBIT;
            case "hen":
                return AnimalInfo.HEN;
            default:
                return AnimalInfo.DUCK;
        }
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
            //case SALE_BUCKET: return Color.PINK;
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
        messageLabel.setText(result.toString());
        messageLabel.addAction(Actions.sequence(
            Actions.delay(5f),
            Actions.run(() -> {
                messageLabel.setText("");
            })
        ));
    }

    public void showArtisanDialog(Stage stage, Skin skin, Artisan artisan) {
        Dialog dialog = new Dialog("Artisan Info", skin);

        Table content = dialog.getContentTable();

        Label descriptionLabel = new Label("This device can produce: " + String.join(", ", artisan.getItems()), skin);
        descriptionLabel.setWrap(true);
        descriptionLabel.setAlignment(Align.center);

        float maxWidth = Gdx.graphics.getWidth() * 0.6f;
        descriptionLabel.setWidth(maxWidth);
        content.add(descriptionLabel).width(maxWidth).pad(10);
        content.row();


        if (artisan.getStatus() == ArtisanStatus.WORKING) {
            String productInfo = "Product: " + artisan.getWorkingProduct();
            String timeInfo = "Time left (h): " + (artisan.getHoursLeft());

            Label productLabel = new Label(productInfo, skin);
            productLabel.setAlignment(Align.center);
            content.add(productLabel).pad(5);
            content.row();

            Label timeLabel = new Label(timeInfo, skin);
            timeLabel.setAlignment(Align.center);
            content.add(timeLabel).pad(5);
            content.row();
        }


        TextButton useButton = new TextButton("Use", skin);
        useButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
                showSelectItemDialog(stage, skin, artisan);
            }
        });

        dialog.button("Close", true);

        dialog.show(stage);
    }

    public void showSelectItemDialog(Stage stage, Skin skin, Artisan artisan) {
        Dialog dialog = new Dialog("Select Item", skin);

        Table content = dialog.getContentTable();


        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems(artisan.getItems().toArray(new String[0]));
        content.add(selectBox).width(Gdx.graphics.getWidth() * 0.6f).pad(10);
        content.row();


        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selectedItem = selectBox.getSelected();
                //onItemSelected(selectedItem);
                dialog.hide();
            }
        });

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getButtonTable().add(okButton).pad(10);
        dialog.getButtonTable().add(closeButton).pad(10);

        dialog.show(stage);
    }


    public void showSimpleDialog(Stage stage, Skin skin, String title, String message) {
        Dialog dialog = new Dialog(title, skin);

        Label label = new Label(message, skin);
        label.setWrap(true);
        label.setAlignment(Align.center);


        float maxWidth = Gdx.graphics.getWidth() * 0.6f;
        label.setWidth(maxWidth);

        dialog.getContentTable().add(label).width(maxWidth).pad(20);

        dialog.button("Ok", true);

        dialog.show(stage);
    }

    public void showQuestDialog(Stage stage, Skin skin, NPC npc, Player player) {
        Dialog dialog = new Dialog("Quests", skin);
        Table content = dialog.getContentTable();
        float maxWidth = Gdx.graphics.getWidth() * 0.7f;

        List<NPCQuest> quests = npc.getQuests();
        if (quests == null || quests.isEmpty()) {
            content.add(new Label("This NPC has no quests.", skin)).pad(20);
        } else {
            for (NPCQuest quest : quests) {
                Table questRow = new Table();

                String questInfo = "• " + quest.getQuestType() +

                    " [" +
                    (quest.isActive() ? "Active" : "Inactive") +
                    " / " +
                    (quest.isCompleted() ? "Completed" : "Not Completed") +
                    " / " +
                    (quest.getOwner() == null ? "No Owner" : "Has Owner") +
                    "]";

                Label label = new Label(questInfo, skin);
                label.setWrap(true);
                label.setWidth(maxWidth * 0.6f);
                questRow.add(label).width(maxWidth * 0.6f).left().pad(10);


                if (NPCVillage.canFinishQuest(player, quest.getQuestType().getMissionNumber(), npcs)) {
                    TextButton finishButton = new TextButton("Complete", skin);
                    finishButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            if (NPCVillage.finishQuest(player, quest.getQuestType().getMissionNumber(), npcs).equals("done")) {
                                npc.handleFinishingQuest();
                                System.out.println("Quest completed: " + quest.getQuestType());
                                dialog.hide();
                            }
                        }
                    });
                    questRow.add(finishButton).right().pad(10);
                }

                content.add(questRow).width(maxWidth).row();
            }
        }

        dialog.button("Close", false);
        dialog.show(stage);
    }


    public static Label getMessageLabel() {
        return messageLabel;
    }

    public static void setMessage(String message) {
        messageLabel.setText(message);
        messageLabel.addAction(Actions.sequence(
            Actions.delay(5f),
            Actions.run(() -> {
                messageLabel.setText("");
            })
        ));
    }

    public void showActivateCancelDialog() {
        Skin skin = AssetManager.getAssetManager().getSkin();

        Dialog dialog = new Dialog("Activate Greenhouse", skin);

        TextButton activateBtn = new TextButton("Activate", skin);
        activateBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.setGreenHouseActivated();
                dialog.hide();
            }
        });


        TextButton cancelBtn = new TextButton("Cancel", skin);
        cancelBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.hide();
            }
        });


        Table content = dialog.getContentTable();
        content.defaults().pad(10);
        content.add(activateBtn).width(150).row();
        content.add(cancelBtn).width(150).row();

        dialog.pack();
        dialog.show(stage);
    }

}


//'$' abandoned incomplete
//'@' should be added
