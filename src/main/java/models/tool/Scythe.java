package models.tool;

import models.Item;
import models.Position;
import models.building.Tile;
import models.character.player.Inventory;
import models.character.player.Player;
import models.enums.Direction;
import models.farming.Crop;
import models.farming.Fruit;
import models.farming.Plant;
import models.foraging.ForagingCrop;

public class Scythe extends Tool {
    private static final int INITIAL_SCYTHE_USE_ENERGY = 2;

    public Scythe(Inventory inventory) {
        super(inventory);
        name = "scythe";
    }

    @Override
    public int getBaseEnergyCost() {
        return INITIAL_SCYTHE_USE_ENERGY;
    }

    @Override
    public void use(Direction direction) {
        Player player = inventory.getPlayer();
        Position appliedPosition = player.getPosition().applyDirection(direction);
        Tile tile = player.getFarm().getTile(appliedPosition);

        Item product = null;
        if (tile.getObject() instanceof Plant plant && plant.hasProduct()) {
            product = plant.getProduct();
            if (product instanceof Crop crop && crop.getInfo().isOneTime()) {
                tile.setObject(null);
            }
            player.getInventory().addItem(product.getName(), 1);
        } else if (tile.getObject() instanceof ForagingCrop foragingCrop) {

        }

        if (product instanceof Fruit || (product instanceof Crop crop && !crop.getInfo().isOneTime())) {
            player.getAbilityService().getFarming().increaseXp(5);
        } else if (product instanceof Crop crop && crop.getInfo().isOneTime()) {
            player.getAbilityService().getFarming().increaseXp(5);
            player.getAbilityService().getHiking().increaseXp(10);
        }


        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }

    @Override
    public void upgrade() {

    }
}
