package io.github.some_example_name.model;

import java.util.List;

public class Plant extends Ingredient {
    private final int tempoDeCrescimento;
    private final List<Tool> ferramentasNecessarias;

    public Plant(int id, String nome, int tempoDeCrescimento, List<Tool> ferramentasNecessarias) {
        super(id, nome);
        this.tempoDeCrescimento = tempoDeCrescimento;
        this.ferramentasNecessarias = ferramentasNecessarias;
    }

    public int getTempoDeCrescimento() { 
        return tempoDeCrescimento; 
    }

    public List<Tool> getFerramentasNecessarias() { 
        return ferramentasNecessarias; 
    }
}