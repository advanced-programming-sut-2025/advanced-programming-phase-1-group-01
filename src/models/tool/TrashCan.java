package models.tool;

public class TrashCan extends Tool {

    @Override
    public void use() {
        // what tool does

        double energyCost = getEffectiveEnergyCost();
        inventory.getPlayer().getEnergy().consume(energyCost);
    }
}
