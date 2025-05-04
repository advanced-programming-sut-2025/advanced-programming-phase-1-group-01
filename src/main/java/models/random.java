package models;

import java.util.concurrent.ThreadLocalRandom;

public class random {
    private random() {
    }

    public static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max + Double.MIN_VALUE);
    }

    public static long rand(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}