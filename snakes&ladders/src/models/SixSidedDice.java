package models;

import java.util.Random;

public class SixSidedDice implements Dice {
    private Random random;

    public SixSidedDice() {
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
