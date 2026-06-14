package io.github.some_example_name.model;

import java.util.List;

public class Recipe extends Ingredient {
    private final int xpReward;
    private final List<Ingredient> ingredientes;

    public Recipe(int id, String nome, int xpReward, List<Ingredient> ingredientes) {
        super(id, nome);
        this.xpReward = xpReward;
        this.ingredientes = ingredientes;
    }

    public int getXpReward() { 
        return xpReward; 
    }

    public List<Ingredient> getIngredientes() { 
        return ingredientes; 
    }
}