package io.github.some_example_name.enums;

public enum PlantGrowth {
    
    GRASS(5f),

    CREEPING(10f),

    SHRUB(15f),

    SUPPORT(30f),

    TREE(60f);
    
    public final float growthTimeSeconds;

    PlantGrowth(float growthTimeSeconds) {
        this.growthTimeSeconds = growthTimeSeconds;
    }
}