package com.example;

import lombok.Value;

@Value
public class Supply {
    private final int count;
    private final int totalWeight;
    private final boolean canceled;
}
