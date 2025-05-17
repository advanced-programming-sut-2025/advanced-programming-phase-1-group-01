package models.character.player;

public class Energy {
    private double amount;
    private double maxEnergy;
    public static final double MAX_ENERGY = 200;
    public static final double INITIAL_ENERGY_AMOUNT = MAX_ENERGY;
    private boolean hasPassedOut = false;

    public Energy() {
        this.amount = INITIAL_ENERGY_AMOUNT;
        maxEnergy = MAX_ENERGY;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void consume(double amount) {

        if (amount > 0) this.amount -= amount;

        if (this.amount <= 0) {
            passOut();
        }
    }

    public void increase(double amount) {
        if (amount > 0) this.amount += amount;
        if (this.amount >= MAX_ENERGY) {
            this.amount = MAX_ENERGY;
        }
    }

    public void fillEnergy() {
        this.amount = maxEnergy;
    }

    public void fillEnergyPassedOut() {
        this.amount = maxEnergy * 0.75;
    }

    public void passOut() {
        hasPassedOut = true;
        this.amount = 0;
    }

    public boolean hasPassedOut() {
        return hasPassedOut;
    }

    public void setHasPassedOut(boolean hasPassedOut) {
        this.hasPassedOut = hasPassedOut;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}