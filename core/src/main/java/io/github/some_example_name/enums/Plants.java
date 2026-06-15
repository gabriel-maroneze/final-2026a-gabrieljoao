package io.github.some_example_name.enums;

public enum Plants {
    TRIGO,
    CANA,
    MORANGO,
    ABOBORA,
    TOMATE,
    ALFACE,
    AMENDOIM,
    MACA,
    LARANJA,
    UVA;

    public String id(){
        return this.name().toLowerCase();
    }
}