package io.github.some_example_name.enums;

public enum Ingredients{
    FARINHA,
    PAO,
    ACUCAR,
    ALCOOL,
    GELEIA_DE_MORANGO,
    GELEIA_DE_ABOBORA,
    MOLHO_DE_TOMATE,
    CREME_DE_AMENDOIM;

    public String id(){
        return this.name().toLowerCase();
    }
}