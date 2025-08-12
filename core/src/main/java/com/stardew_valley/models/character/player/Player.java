package com.stardew_valley.models.character.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew_valley.models.*;
import com.stardew_valley.models.animal.Animal;
import com.stardew_valley.models.animal.AnimalHouse;
import com.stardew_valley.models.animal.AnimalInfo;
import com.stardew_valley.models.building.*;
import com.stardew_valley.models.building.Building;
import com.stardew_valley.models.building.Farm;
import com.stardew_valley.models.character.Character;
import com.stardew_valley.models.cooking.CookingRecipe;
import com.stardew_valley.models.cooking.CookingRecipes;
import com.stardew_valley.models.crafting.*;
import com.stardew_valley.models.crafting.enums.AllCraftedProductsType;
import com.stardew_valley.models.crafting.enums.CraftingRecipes;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.enums.Color;
import com.stardew_valley.models.enums.Direction;
import com.stardew_valley.models.enums.Gender;
import com.stardew_valley.models.enums.ReactionType;
import com.stardew_valley.models.relations.RelationshipService;
import com.stardew_valley.models.data.User;
import com.stardew_valley.network.GameClient;

import java.util.*;
import java.util.List;

public class Player extends Character {
    private Game game;
    private final User user;
    private final Gender gender;
    private Position position;
    private float x;
    private float y;
    private Direction direction;
    private Farm farm;
    private int numOfCoins;
    private Inventory inventory;
    private Refrigerator refrigerator;
    private Energy energy;
    private AbilityService abilityService;
    private RelationshipService relationshipService;
    private final Map<MessageEntry, Boolean> notifications;
    private static final int INITIAL_PLAYER_X = 0;
    private static final int INITIAL_PLAYER_Y = 0;
    private final List<CraftedProducts> craftedProducts = new ArrayList<>();
    private final List<Item> readyToHarvest = new ArrayList<>();
    private Maps currentMap;
    private boolean isEnergyHalved = false;
    private int halvedEnergyCounter = 0;
    private final Set<CraftingRecipe> craftingRecipes;
    private final Set<CookingRecipe> cookingRecipes;
    private Farm partnerFarm ;
    private final List<CraftingDevice> craftingDevices = new ArrayList<>();
    private final List<UnripeProduct> unripeProducts = new ArrayList<>();
    private int buffDaysCounter = 0;
    private boolean hasEnergyBuff = false;
    private final List<Integer> tommorrowMoney = new ArrayList<>();
    private float stateTime = 0f;
    private boolean isMoving = false;
    private final List<Artisan> artisans = new ArrayList<>();
    private int artisanId = 0;
    private int id;
    private String mapJson;
    private ReactionUI reaction;

    List<Animal> animals;

    private final float totalFaintingTime = 2f;
    private float faintingTime = 0f;
    private boolean whileFainting = false;

    private float globalDelta = 0f;
    private Map<String,Integer> foods;


    public Player(User user) {
        this.user = user;
        position = new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y);
        direction = Direction.UP;
        numOfCoins = 0;
        inventory = new Inventory(this);
        refrigerator = new Refrigerator(this);
        energy = new Energy();
        abilityService = new AbilityService(this);
        relationshipService = new RelationshipService(this);
        gender = user.getGender();
        notifications = new LinkedHashMap<>();
        craftingRecipes = new HashSet<>();
        cookingRecipes = new HashSet<>();
        foods = new HashMap<>();
        initializeRecipes();
        reaction = new ReactionUI();
    }

    public Player(Game game, User user) {
        this.game = game;
        this.user = user;
        position = new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y);
        direction = Direction.UP;
        numOfCoins = 0;
        inventory = new Inventory(this);
        refrigerator = new Refrigerator(this);
        energy = new Energy();
        abilityService = new AbilityService(this);
        relationshipService = new RelationshipService(this);
        gender = user.getGender();
        notifications = new LinkedHashMap<>();
        craftingRecipes = new HashSet<>();
        cookingRecipes = new HashSet<>();
        foods = new HashMap<>();
        initializeRecipes();
    }

    public Position getPosition() {
        return position;
    }

    public Position getTilesPosition() {
        return new Position(position.x() / 16, position.y() / 16);
    }

    public void setPosition(Position position) {
        this.x = position.x();
        this.y = position.y();
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    private boolean syncingCoins = false;

    public void setNumOfCoins(int numOfCoins) {
        if (!syncingCoins && relationshipService.getMarriage() != null) {
            Player partner = relationshipService.getMarriage().getPartner(this);
            syncingCoins = true;
            partner.setNumOfCoins(numOfCoins);
            syncingCoins = false;
        }

        this.numOfCoins = numOfCoins;
    }

    public void increaseCoins(int amount) {
        setNumOfCoins(this.numOfCoins + amount);
    }

    public boolean consumeCoins(int amount) {
        if (this.numOfCoins < amount)
            return false;

        setNumOfCoins(this.numOfCoins - amount);
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public AbilityService getAbilityService() {
        return abilityService;
    }

    public void setAbilityService(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    public RelationshipService getRelationService() {
        return relationshipService;
    }

    public void setRelationService(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public Gender getGender() {
        return gender;
    }

    public Map<MessageEntry, Boolean> getNotifications() {
        return notifications;
    }

    public void addNotification(Player sender, String message) {
        notifications.put(new MessageEntry(sender, message), false);
        GameClient.getInstance().sendNotification(this.getUser().getUsername(), sender.getUser().getUsername(), message);
    }

    public void readNotification(MessageEntry notification) {
        for (Map.Entry<MessageEntry, Boolean> entry : notifications.entrySet()) {
            if (entry.getKey() == notification) {
                entry.setValue(true);
            }
        }
    }

    public void readAllNotifications() {
        for (MessageEntry key : notifications.keySet()) {
            if (!notifications.get(key)) {
                readNotification(key);
            }
        }
    }

    public boolean isNearTo(Player p) {
        return Math.abs(p.getPosition().x() - position.x()) <= 32 && Math.abs(p.getPosition().y() - position.y()) <= 32;
    }

    public boolean isNearTo(Position position) {
        return Math.abs(this.position.x() - position.x()) <= 1 && Math.abs(this.position.y() - position.y()) <= 1;
    }

    public boolean isNearToSellBucket(int playerX, int playerY) {
        int dx = Math.abs(playerX - 50);
        int dy = Math.abs(playerY - 8);
        int dx2 = Math.abs(playerX - 70);
        int dy2 = Math.abs(playerY - 68);
        double distance1 = Math.sqrt(dx * dx + dy * dy);
        double distance2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        return distance1 <= 2 || distance2 <= 2;
    }

    public boolean isPlayerNearBuilding(Building building) {
        Position buildingPos = building.getPosition();
        Size size = building.getSize();

        int x1 = buildingPos.x() - 1;
        int y1 = buildingPos.y() - 1;
        int x2 = buildingPos.x() + size.getWidth();
        int y2 = buildingPos.y() + size.getHeight();

        return this.position.x() >= x1 && this.position.x() <= x2 &&
                this.position.y() >= y1 && this.position.y() <= y2;
    }

    public Set<CraftingRecipe> getCraftingRecipes() {
        return craftingRecipes;
    }

    public void addCraftingRecipe(CraftingRecipe recipe) {
        craftingRecipes.add(recipe);
    }

    public boolean haveCraftingRecipes(CraftingRecipe recipe) {
        return craftingRecipes.contains(recipe);
    }

    public boolean haveCookingRecipe(CookingRecipe recipe) {
        return cookingRecipes.contains(recipe);
    }

    public Set<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public void initializeRecipes() {
        inventory.addItem("egg",5);
        inventory.addItem("milk",5);
        addCookingRecipe(CookingRecipes.FRIED_EGG.toRecipe());
        addCookingRecipe(CookingRecipes.BAKED_FISH.toRecipe());
        addCookingRecipe(CookingRecipes.OMELET.toRecipe());
        addCraftingRecipe(CraftingRecipes.BEE_HOUSE.toRecipe());
        addCraftingRecipe(CraftingRecipes.KEG.toRecipe());
        addCraftingRecipe(CraftingRecipes.LOOM.toRecipe());
    }

    public void addCookingRecipe(CookingRecipe recipe) {
        cookingRecipes.add(recipe);
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void setPartnerFarm(Farm farm) {
        this.partnerFarm = farm;
    }

    public void updateOfMarriage(Player player) {
        numOfCoins += player.getNumOfCoins();
        player.setNumOfCoins(numOfCoins);
        setPartnerFarm(player.getFarm());
    }

    private AnimalInfo stringToAnimalInfo(String string) {
        return switch (string) {
            case "cow" -> AnimalInfo.COW;
            case "dinosaur" -> AnimalInfo.DINOSAUR;
            case "duck" -> AnimalInfo.DUCK;
            case "goat" -> AnimalInfo.GOAT;
            case "hen" -> AnimalInfo.HEN;
            case "pig" -> AnimalInfo.PIG;
            case "rabbit" -> AnimalInfo.RABBIT;
            case "sheep" -> AnimalInfo.SHEEP;
            default -> null;
        };
    }

    public TextureRegion getCurrentFrame() {
        AssetManager manager = AssetManager.getAssetManager();

        if (whileFainting) {
            updateFaintingStateTime();
            if (faintingTime > totalFaintingTime) {
                whileFainting = false;
                faintingTime = 0;
            }
            return manager.getFaintingAnimation().getKeyFrame(faintingTime, false);
        }

        switch (direction) {
            case UP:
                return isMoving
                    ? manager.get_Alex_0_walking_up_animation().getKeyFrame(stateTime, true)
                    : manager.get_Alex_0_walking_up_animation().getKeyFrame(0, true);

            case DOWN:
                return isMoving
                    ? manager.get_Alex_0_walking_down_animation().getKeyFrame(stateTime, true)
                    : manager.get_Alex_0_walking_down_animation().getKeyFrame(0, true);

            case LEFT:
                return isMoving
                    ? manager.get_Alex_0_walking_left_animation().getKeyFrame(stateTime, true)
                    : manager.get_Alex_0_walking_left_animation().getKeyFrame(0, true);

            case RIGHT:
            default:
                return isMoving
                    ? manager.get_Alex_0_walking_right_animation().getKeyFrame(stateTime, true)
                    : manager.get_Alex_0_walking_right_animation().getKeyFrame(0, true);
        }
    }


    public String buyAnimal(String animalType, String animalName) {
        AnimalInfo animalInfo = stringToAnimalInfo(animalType);
        if (animalInfo == null) return "Invalid animal type";
        if (!farm.isAnimalNameUnique(animalName)) return "Animal name is not unique";
        AnimalHouse shelter = farm.findEmptyShelter(animalInfo);
        if (shelter == null) return "No empty shelter";
        if (numOfCoins < animalInfo.getPrice()) return "Insufficient funds";
        farm.addAnimalToShelter(new Animal(animalInfo, animalName, this, shelter));
        farm.addAnimal(new Animal(animalInfo, animalName, this, shelter));
        return "Animal added successfully";
    }

    public String petAnimal(String animalName) {
        Animal animal = farm.fineAnimalByName(animalName);
        if (animal == null) return "Animal not found";
        if (!isNearTo(animal.getPosition())) return "Animal is not near to you found";
        animal.petting();
        return "Successfully petted the animal";
    }

    public String getFormattedAnimals() {
        StringBuilder animals = new StringBuilder();
        int maxLength = farm.getAnimals().stream()
                .mapToInt(animal -> animal.getAnimalName().length())
                .max()
                .orElse(0);

        animals.append("Animal Type, Animal Name, Has Been Petted, Is Hungry, Friendship Level:\n");
        for (Animal animal : farm.getAnimals()) {
            String formattedAnimalType = Color.YELLOW_BOLD.colorize(String.format("%-" + 8 + "s", animal.getAnimalInfo()
                    .name().toLowerCase()));
            animals.append(formattedAnimalType);
            String formattedAnimalName = Color.GREEN.colorize(String.format("%-" + maxLength + "s", animal.getAnimalName()));
            animals.append(formattedAnimalName);
            animals.append(animal.hasBeenPetted());
            animals.append(animal.isHungry());
            animals.append(animal.getFriendshipLevel());
            animals.append("\n");
        }
        return animals.toString();
    }

    public void addCraftedProduct(CraftedProducts craftedProduct) {
        craftedProducts.add(craftedProduct);
    }

    public void setCurrentMap(Maps currentMap) {
        this.currentMap = currentMap;
    }

    public Maps getCurrentMap() {
        return currentMap;
    }

    //@ l get artisan by name, error

    //@ l get Item

    //@ l check if are not close
    //@ l add to unripe list

    //@ l conditions and get

    public String makeAUnripeProduct() {
        return null;
    }

    public void setEnergyHalved(boolean energyHalved) {
        this.isEnergyHalved = energyHalved;
    }

    public boolean isEnergyHalved() {
        return isEnergyHalved;
    }

    public void increaseHalvedEnergy() {
        halvedEnergyCounter++;
        if (halvedEnergyCounter >= 7) {
            setEnergyHalved(false);
            energy.setMaxEnergy(energy.getMaxEnergy() * 2);
            halvedEnergyCounter = 0;
        }
    }

    public void setPartner(User partner) {
        relationshipService.setPartner(partner);
    }

    public User getPartner() {
        return relationshipService.getPartner();
    }


    public void energyBuff(int hour) {

    }

    public void getTomorrowMoney() {
        if (game.getTimeManager().getNow().getHour() == 9) {
            for (int money : tommorrowMoney) {
                increaseCoins(money);
                tommorrowMoney.remove(money);
            }
        }
    }

    public void abilityBuff(AbilityType abilityType) {
        switch (abilityType) {
            case FARMING -> abilityService.getFarming().increaseXp(10);
            case FORAGING -> abilityService.getForaging().increaseXp(10);
            case FISHING -> abilityService.getFishing().increaseXp(10);
            case MINING -> abilityService.getMining().increaseXp(10);
        }
    }

    public boolean hasEnoughItem(String itemName, int quantity) {
        return inventory.getSlot(itemName) != null && inventory.getSlot(itemName).getQuantity() >= quantity;

    }

    public String useArtisan(String artisanName, String itemName) {
        String[] items = itemName.split(" ");
        switch (artisanName) {
            case "honey" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("-")) return "Invalid item name";
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.HONEY), 96);
                    unripeProduct.setHarvestHours(game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);

                } else return "Invalid artisan";

            }
            case "cheese" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (items[0].equals("milk")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.CHEESE), 3);
                        unripeProduct.setHarvestHours(game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else if (items[0].equals("large_milk")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.CHEESE), 3);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else return "Invalid item";
                } else return "Invalid artisan";
            }
            case "goat_cheese" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (items[0].equals("goat_milk")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.GOAT_CHEESE), 3);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else if (items[0].equals("large_Goat_milk")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.GOAT_CHEESE), 3);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else return "Invalid item";
                } else return "Invalid artisan";
            }
            case "beer" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("wheat")) return "Invalid item name";
                    if (hasEnoughItem("wheat", 1)) return "You don't have enough wheat";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.BEER), 24);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "vinegar" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("rice")) return "Invalid item name";
                    if (hasEnoughItem("rice", 1)) return "You don't have enough rice";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.VINEGAR), 10);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "coffee" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("coffee_been")) return "Invalid item name";
                    if (hasEnoughItem("coffee been", 5)) return "You don't have enough coffee";
                    inventory.getSlot(items[0]).removeQuantity(5);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.COFFEE), 2);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "juice" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("potato")) return "Invalid item name";
                    if (hasEnoughItem("potato", 1)) return "You don't have enough potato";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.JUICE), 96);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "mead" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("honey")) return "Invalid item name";
                    if (hasEnoughItem("honey", 1)) return "You don't have enough honey";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.MEAD), 10);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "pale_ale" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("hops")) return "Invalid item name";
                    if (hasEnoughItem("hops", 1)) return "You don't have enough hops";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.PALE_ALE), 72);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "wine" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("apple")) return "Invalid item name";
                    if (hasEnoughItem("apple", 5)) return "You don't have enough apple";
                    inventory.getSlot(items[0]).removeQuantity(5);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.WINE), 168);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "dried_mushrooms" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("mushroom")) return "Invalid item name";
                    if (hasEnoughItem("mushroom", 5)) return "You don't have enough mushrooms";
                    inventory.getSlot(items[0]).removeQuantity(5);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.DRIED_MASHROOMS), -1);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "dried_fruit" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("apple")) return "Invalid item name";
                    if (hasEnoughItem("apple", 5)) return "You don't have enough apple";
                    inventory.getSlot(items[0]).removeQuantity(5);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.WINE), 168);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "raisins" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("grapes")) return "Invalid item name";
                    if (hasEnoughItem("grapes", 5)) return "You don't have enough grapes";
                    inventory.getSlot(items[0]).removeQuantity(5);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.RAISINS), -1);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);

                } else return "Invalid artisan";
            }
            case "coal" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("wood")) return "Invalid item name";
                    if (hasEnoughItem("apple", 10)) return "You don't have enough woods";
                    inventory.getSlot(items[0]).removeQuantity(10);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.COAL), 1);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "cloth" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("wool")) return "Invalid item name";
                    if (hasEnoughItem("wool", 1)) return "You don't have enough wool";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.CLOTH), 4);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "mayonnaise" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (items[0].equals("egg")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        unripeProducts.add(new UnripeProduct(new CraftedProducts(AllCraftedProductsType.MAYONNAISE), 3));
                    } else if (items[0].equals("large_egg")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.MAYONNAISE), 3);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else return "Invalid item";
                } else return "Invalid artisan";
            }
            case "duck_mayonnaise" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("duck_egg")) return "Invalid item name";
                    if (hasEnoughItem("duck egg", 1)) return "You don't have enough duck egg";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.DUCK_MAYONNAISE), 3);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "dinosaur_mayonnaise" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("dinosaur_egg")) return "Invalid item name";
                    if (hasEnoughItem("dinosaur egg", 1)) return "You don't have enough dinosaur egg";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.DINOSAUR_MAYONNAISE), 3);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "truffle_oil" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("truffle")) return "Invalid item name";
                    if (hasEnoughItem("truffle", 1)) return "You don't have enough truffle";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.TRUFFLE_OIL), 6);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "oil" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (items[0].equals("corn")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.OIL), 6);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else if (items[0].equals("sunflower_seeds")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.OIL), 48);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else if (items[0].equals("sunflower")) {
                        if (inventory.getSlot(items[0]) == null) return "Invalid item name";
                        inventory.getSlot(items[0]).removeQuantity(1);
                        isThereSuitableCraftingDevice(artisanName).setWorking(true);
                        UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.OIL), 1);
                        unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                        unripeProducts.add(unripeProduct);
                    } else return "Invalid item";
                } else return "Invalid artisan";
            }
            case "pickles" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("corn")) return "Invalid item name";
                    if (hasEnoughItem("corn", 1)) return "You don't have enough vegetables";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.PICKLES), 6);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "jelly" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("apple")) return "Invalid item name";
                    if (hasEnoughItem("apple", 1)) return "You don't have enough fruits";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.JELLY), 72);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "smoked_fish" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("salmon")) return "Invalid item name";
                    if (items.length != 2) return "Invalid item name";
                    if (!items[1].equals("coal")) return "Invalid item name";
                    if (hasEnoughItem("salmon", 1)) return "You don't have enough fish";
                    if (hasEnoughItem("coal", 1)) return "You don't have enough coal";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    inventory.getSlot(items[1]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.SMOKED_FISH), 1);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "iron_bar" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("iron_ore")) return "Invalid item name";
                    if (items.length != 2) return "Invalid item name";
                    if (!items[1].equals("coal")) return "Invalid item name";
                    if (hasEnoughItem("iron ore", 1)) return "You don't have enough iron ore";
                    if (hasEnoughItem("coal", 1)) return "You don't have enough coal";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    inventory.getSlot(items[1]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.IRON_BAR), 4);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            case "gold_bar" -> {
                if (isThereSuitableCraftingDevice(artisanName) != null) {
                    if (!items[0].equals("gold_ore")) return "Invalid item name";
                    if (items.length != 2) return "Invalid item name";
                    if (!items[1].equals("coal")) return "Invalid item name";
                    if (hasEnoughItem("gold ore", 1)) return "You don't have enough gold ore";
                    if (hasEnoughItem("coal", 1)) return "You don't have enough coal";
                    inventory.getSlot(items[0]).removeQuantity(1);
                    inventory.getSlot(items[1]).removeQuantity(1);
                    isThereSuitableCraftingDevice(artisanName).setWorking(true);
                    UnripeProduct unripeProduct = new UnripeProduct(new CraftedProducts(AllCraftedProductsType.GOLD_BAR), 4);
                    unripeProduct.setHarvestHours(this.game.getTimeManager().getNow().getHour(), unripeProduct.getHarvestTime());
                    unripeProducts.add(unripeProduct);
                } else return "Invalid artisan";
            }
            default -> {
                return "invalid command";
            }
        }
        return "Invalid command";
    }

    public void addCraftingDevices(CraftingDevice craftingDevice) {
        if (!craftingDevices.contains(craftingDevice)) craftingDevices.add(craftingDevice);
    }

    public CraftingDevice isThereSuitableCraftingDevice(String CraftingDeviceName) {
        CraftingDevice craftingDevice = (CraftingDevice) inventory.getSlot(CraftingDeviceName).getItem();
        if (craftingDevice == null || !craftingDevices.contains(craftingDevice) || isNearToDevice()) return craftingDevice;
        else return null;
    }

    public boolean isNearToDevice() {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            if (position.x() + direction[0] < 0 ||
                    position.x() + direction[0] > 74 ||
                    position.y() + direction[1] < 0 ||
                    position.y() + direction[1] > 74) continue;
            if (farm.getTiles().get(position.x() + direction[0]).get(position.y() + direction[1]).getObject() instanceof CraftingDevice) {
                return true;
            }
        }
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getArtisan(String artisanName) {
        Item item = null;
        for (UnripeProduct unripeProduct : unripeProducts) {
            if (unripeProduct.getInventoryItem().getName().equals(artisanName)) {
                if (!unripeProduct.isRipe()) return "item is not ripe";
                item = unripeProduct.getInventoryItem();
            };
        }

        if (item == null) return "there is no item with name: " + artisanName;
        inventory.addItem(artisanName.toLowerCase().replace("_", " "), 1);
        return "You got the item";
    }

    public List<UnripeProduct> getUnripeProducts() {
        return unripeProducts;
    }

    public void addBuffDaysCounter() {
        buffDaysCounter++;
        if (buffDaysCounter >= 5) {
            hasEnergyBuff = false;
            energy.changeShoudBeFull(false);
            buffDaysCounter = 0;
        }
    }

    public void isBuffActivated() {
        hasEnergyBuff = true;
        energy.changeShoudBeFull(true);
    }

    public boolean hasEnergyBuff() {
        return hasEnergyBuff;
    }

    public void updateStateTime(float delta) {
        stateTime += delta;
        globalDelta = delta;
    }

    public void updateFaintingStateTime() {
        faintingTime += globalDelta;
    }

    public void setX(float x) {
        setPosition(new Position((int) x, position.y()));
        this.x = x;
    }

    public void setY(float y) {
        setPosition(new Position(position.x(), (int) y));
        this.y = y;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean getMoving() {
        return isMoving;
    }

    public boolean isFainting() {
        return whileFainting;
    }

    public void setFainting(boolean fainting) {
        whileFainting = fainting;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Artisan> getArtisans() {
        return artisans;
    }

    public void addArtisan(Artisan artisan) {
        artisans.add(artisan);
    }

    public int getArtisanId() {
        return artisanId++;
    }

    private List<MarriageRequest> marriageRequests = new ArrayList<>();

    public void addMarriageRequest(MarriageRequest request) {
        marriageRequests.add(request);
    }

    public List<MarriageRequest> getMarriageRequests() {
        return marriageRequests;
    }

    public void removeMarriageRequest(MarriageRequest request) {
        marriageRequests.remove(request);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMapJson(String mapJson) {
        this.mapJson = mapJson;
    }

    public String getMapJson() {
        return mapJson;
    }

    public TextureRegion getTestTexture() {
        return AssetManager.getAssetManager().get_Alex_0_walking_down_animation().getKeyFrame(0);
    }

    public Map<String,Integer> getFoods() {
        return foods;
    }

    public void addFood(String item) {
        foods.put(item, foods.getOrDefault(item, 0) + 1);
    }

    public void removeFood(String item) {
        int count = foods.getOrDefault(item, 0) - 1;
        if (count > 0) {
            foods.put(item, count);
        } else {
            foods.remove(item);
        }
    }

    public static Direction numToDirection(int num) {
        switch (num) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            default:
                return Direction.RIGHT;
        }
    }

    public static int directionToNum(Direction direction) {
        switch (direction) {
            case UP:
                return 0;
            case DOWN:
                return 1;
            case LEFT:
                return 2;
            default:
                return 3;
        }
    }


    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setReaction(ReactionType react) {
        reaction.setStarted(react);
    }

    public ReactionUI getReactionUI() {
        return reaction;
    }

    private String reactionText;
    private float reactionTextTime = 0f;

    public void setReactionText(String text) {
        this.reactionText = text;
        this.reactionTextTime = 0f;
    }

    public String getReactionText() {
        return reactionText;
    }

    public boolean isReactionTextActive() {
        return reactionText != null && reactionTextTime < 5f;
    }

    public void updateReactionText(float delta) {
        if (reactionText != null) {
            reactionTextTime += delta;
            if (reactionTextTime >= 5f) {
                reactionText = null;
            }
        }
    }

    public int getTotalNumOfQuests() {
        final int[] count = {0};

        Repository.getRepo().getCurrentGame().getFarm().getNPCs().forEach(npc -> {
            npc.getQuests().forEach(quest -> {
                if (quest.getOwner() != null) {
                    if (quest.getOwner().getUser().getUsername().equals(this.getUser().getUsername())) {
                        count[0]++;
                    }
                }
            });
        });

        return count[0];
    }

    public int getTotalNumOfLevels() {
        return getAbilityService().getFarming().getLevel() +
            getAbilityService().getMining().getLevel() +
            getAbilityService().getFishing().getLevel() +
            getAbilityService().getForaging().getLevel();
    }


}
