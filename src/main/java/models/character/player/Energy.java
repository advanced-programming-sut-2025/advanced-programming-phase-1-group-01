package models.character.player;

public class Energy {
    private double amount;
    private double MAX_ENERGY = 200;
    private double INITIAL_ENERGY_AMOUNT = MAX_ENERGY;
    private boolean hasPassedOut = false;

    public Energy() {
        this.amount = INITIAL_ENERGY_AMOUNT;
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
    }

    public void fillEnergy() {
        this.amount = INITIAL_ENERGY_AMOUNT;
    }

    public void fillEnergyPassedOut() {
        this.amount = INITIAL_ENERGY_AMOUNT * 0.75;
    }

    public void passOut() {
        hasPassedOut = true;
        this.amount = 0;
    }

    public boolean isHasPassedOut() {
        return hasPassedOut;
    }

    public void setHasPassedOut(boolean hasPassedOut) {
        this.hasPassedOut = hasPassedOut;
    }
}