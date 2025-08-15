package com.stardew_valley.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew_valley.models.character.player.Inventory;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.ArtisanStatus;
import com.stardew_valley.models.enums.ArtisanType;

import java.util.List;
import java.util.Map;

public class Artisan {
    private float x;
    private float y;
    private final ArtisanType type;
    private ArtisanStatus status = ArtisanStatus.EMPTY;
    private String workingProduct;
    private float progress;
    private int hourCounter = 0;
    private int totalHours = 0;
    private Repository repository;
    private final TextureRegion black = AssetManager.getAssetManager().getBlackRect();
    private final TextureRegion light = AssetManager.getAssetManager().getLightRect();
    private final TextureRegion done = AssetManager.getAssetManager().getDone();
    private final int id;

    public Artisan(float x, float y, ArtisanType type, Repository repository) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.repository = repository;
        this.id = repository.getCurrentGame().getCurrentPlayer().getArtisanId();
    }


    public List<String> getItems() {
        switch (type) {
            case BEE_HOUSE:
                return List.of("honey");
            case CHARCOAL_KILN:
                return List.of("coal");
            case CHEESE_PRESS:
                return List.of(
                    "cheese",
                    "goat cheese"
                );
            case DEHYDRATOR:
                return List.of("dried mushrooms",
                    "dried fruit",
                    "raisins");
            case FISH_SMOKER:
                return List.of("smoked fish");
            case FURNACE:
                return List.of("copper bar", "iron bar", "gold bar", "iridium bar");
            case KEG:
                return List.of(
                    "beer",
                    "vinegar",
                    "coffee",
                    "juice",
                    "mead",
                    "pale ale",
                    "wine"
                );
            case LOOM:
                return List.of("cloth");
            case MAYONNAISE_MACHINE:
                return List.of(
                    "mayonnaise",
                    "duck mayonnaise",
                    "dinosaur mayonnaise"
                );
            case OIL_MAKER:
                return List.of(
                    "truffle oil",
                    "oil"
                );
            case PRESERVES_JAR:
                return List.of("pickles", "jelly");
            default:
                return List.of();
        }
    }

    public int getProcessingTime(String product) {
        switch (product) {
            case "honey", "juice":
                return 96;
            case "coal", "smoked fish":
                return 1;
            case "cheese", "dinosaur mayonnaise", "duck mayonnaise", "mayonnaise", "goat cheese":
                return 3;
            case "dried mushrooms", "raisins", "dried fruit":
                return -1;
            case "copper bar", "cloth", "iridium bar", "gold bar", "iron bar":
                return 4;
            case "beer":
                return 24;
            case "vinegar", "oil", "mead":
                return 10;
            case "coffee":
                return 2;
            case "pale ale", "jelly":
                return 72;
            case "wine":
                return 168;
            case "truffle oil", "pickles":
                return 6;
            default:
                return 0;
        }

    }

    public List<Map<String, Integer>> getIngredients(String product) {
        switch (product) {
            case "coal":
                return List.of(Map.of("wood", 10))
                    ;
            case "cheese":
                return List.of(Map.of("milk", 1), Map.of("large milk", 1))
                    ;
            case "goat cheese":
                return List.of(Map.of("goat milk", 1), Map.of("large goat milk", 1))
                    ;
            case "dried mushrooms":
                return List.of(Map.of("purple mushroom", 5), Map.of("red mushroom", 5), Map.of("common mushroom", 5));
            case "dried fruit":
                return List.of(Map.of("apple", 5));
            case "raisins":
                return List.of(Map.of("grape", 10));
            case "smoked fish":
                return List.of(Map.of("fish", 1, "coal", 1));
            case "copper bar":
                return List.of(Map.of("copper ore", 5, "coal", 1));
            case "iron bar":
                return List.of(Map.of("iron ore", 5, "coal", 1));
            case "gold bar":
                return List.of(Map.of("gold ore", 5, "coal", 1));
            case "iridium bar":
                return List.of(Map.of("iridium ore", 5, "coal", 1));
            case "beer":
                return List.of(Map.of("wheat", 1))
                    ;
            case "vinegar":
                return List.of(Map.of("rice", 1));
            case "coffee":
                return List.of(Map.of("coffee bean", 5));
            case "juice", "pickles":
                return List.of(Map.of("carrot", 1));
            case "mead":
                return List.of(Map.of("honey", 1));
            case "pale ale":
                return List.of(Map.of("hops", 1));
            case "wine", "jelly":
                return List.of(Map.of("apple", 1));
            case "cloth":
                return List.of(Map.of("wool", 1));
            case "mayonnaise":
                return List.of(Map.of("egg", 1), Map.of("large egg", 1));
            case "duck mayonnaise":
                return List.of(Map.of("duck egg", 1));
            case "dinosaur mayonnaise":
                return List.of(Map.of("dinosaur egg", 1));
            case "truffle oil":
                return List.of(Map.of("truffle", 1));
            case "oil":
                return List.of(Map.of("corn", 1), Map.of("sunflower seeds", 1), Map.of("sunflower", 1));
            default:
                return List.of(Map.of());
        }

    }

    public TextureRegion getTextureRegionByType(ArtisanType type) {
        switch (type) {
            case BEE_HOUSE:
                return AssetManager.getAssetManager().getBeeHouse();
            case CHARCOAL_KILN:
                return AssetManager.getAssetManager().getCharcoalKiln();
            case CHEESE_PRESS:
                return AssetManager.getAssetManager().getCheesePress();
            case DEHYDRATOR:
                return AssetManager.getAssetManager().getDehydrator();
            case FISH_SMOKER:
                return AssetManager.getAssetManager().getFishSmoker();
            case FURNACE:
                return AssetManager.getAssetManager().getFurnace();
            case KEG:
                return AssetManager.getAssetManager().getKeg();
            case LOOM:
                return AssetManager.getAssetManager().getLoom();
            case MAYONNAISE_MACHINE:
                return AssetManager.getAssetManager().getMayonnaiseMachine();
            case OIL_MAKER:
                return AssetManager.getAssetManager().getOilMaker();
            case PRESERVES_JAR:
                return AssetManager.getAssetManager().getPreservesJar();
            default:
                return null;
        }
    }

    public float getProgressY() {
        return this.y + 100;
    }

    public float getProgressX() {
        return this.x - 6;
    }

    public void draw(Batch batch) {
        batch.draw(getTextureRegionByType(this.type), this.x, this.y);

        int onCount = Math.round((getPercentage() / 100f) * 10);

        if (status == ArtisanStatus.WORKING) {
            //System.out.println("meoooo");
            for (int i = 0; i < onCount; i++) {
                float x = getProgressX() + i * (black.getRegionWidth() + 1);
                batch.draw(light, x, getProgressY());
            }


            for (int i = onCount; i < 10; i++) {
                float x = getProgressX() + i * (black.getRegionWidth() + 1);
                batch.draw(black, x, getProgressY());
            }
        } else if (status == ArtisanStatus.FINISHED) {
            batch.draw(done, getProgressX(), getProgressY());
        }

    }

    public void advanceHourCounter() {
        if (status == ArtisanStatus.WORKING) {
            hourCounter++;

            if (hourCounter >= totalHours) {
                finish();
            }
        }
    }

    private void calculateTotalHour(String product) {
        int duration = getProcessingTime(product);
        if (duration == -1) {
            int currentTime = repository.getCurrentGame().getTimeManager().getNow().getHour();
            this.totalHours = 33 - currentTime;
        } else {
            this.totalHours = duration;
        }
    }

    private float getPercentage() {
        return (float) this.hourCounter / this.totalHours * 100;
    }

    public void setWorking(String product) {
        this.status = ArtisanStatus.WORKING;
        this.workingProduct = product;
        this.calculateTotalHour(product);
        this.hourCounter = 0;
    }

    public boolean isDoneClicked(float x, float y) {
        if (status == ArtisanStatus.FINISHED) {
            float px = getProgressX();
            float py = getProgressY();
            return x >= px && x < px + 16 && y >= py && y < py + 16;
        }
        return false;
    }

    public boolean isArtisanClicked(float x, float y) {
        float px = this.x;
        float py = this.y;
        return x >= px && x < px + 96 && y >= py && y < py + 48;
    }

    public ArtisanType getType() {
        return type;
    }

    public ArtisanStatus getStatus() {
        return status;
    }

    public String getWorkingProduct() {
        return workingProduct;
    }

    public int getHoursLeft() {
        return totalHours - hourCounter;
    }

    public int getId() {
        return id;
    }

    public void finish() {
        if (status == ArtisanStatus.WORKING) {
            repository.getCurrentGame().getCurrentPlayer().getInventory().addItem(workingProduct, 1);
            this.status = ArtisanStatus.FINISHED;
        }
    }

    public void addToInventory(Inventory inventory) {
        inventory.addItem(workingProduct, 1);
        this.status = ArtisanStatus.EMPTY;
        hourCounter = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
