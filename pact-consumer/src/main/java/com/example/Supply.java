package com.example;

public class Supply {
    private final int count;
    private final int totalWeight;
    private final boolean canceled;

    public Supply(int count, int totalWeight, boolean canceled) {
        this.count = count;
        this.totalWeight = totalWeight;
        this.canceled = canceled;
    }

    public int getCount() {
        return count;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
