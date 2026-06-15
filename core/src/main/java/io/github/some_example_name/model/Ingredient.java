package io.github.some_example_name.model;

public class Ingredient {
    private final int id;
    private final String nome;

    public Ingredient(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return nome;
    }

}
