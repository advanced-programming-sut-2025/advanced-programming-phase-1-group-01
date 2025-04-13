package models.character.player;

public class Energy {
    private double amount;

    private static final double FIRST_ENERGY_AMOUNT = 200;
    private static final double MAX_ENERGY = 200;

    public Energy() {
        this.amount = FIRST_ENERGY_AMOUNT;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void initEnergy() {
        this.amount = MAX_ENERGY;
    }

    public void passOut() {
    }
}
