package io.github.some_example_name.registry;

import io.github.some_example_name.model.Plant;

public class PlantRegistry{
    private final ToolRegistry toolRegistry = new ToolRegistry();

    public final Plant trigo = new Plant(1, "TRIGO", 5, toolRegistry.trigoTools);
    public final Plant cana = new Plant(2, "CANA", 5, toolRegistry.canaTools);
    public final Plant morango = new Plant(3, "MORANGO", 5, toolRegistry.morangoTools);
    public final Plant abobora = new Plant(4, "ABOBORA", 5, toolRegistry.aboboraTools);
    public final Plant tomate = new Plant(5, "TOMATE", 5, toolRegistry.tomateTools);
    public final Plant alface = new Plant(6, "ALFACE", 5, toolRegistry.alfaceTools);
    public final Plant amendoim = new Plant(7, "AMENDOIM", 5, toolRegistry.amendoimTools);
    public final Plant maca = new Plant(8, "MACA", 5, toolRegistry.macaTools);
    public final Plant laranja = new Plant(9, "LARANJA", 5, toolRegistry.laranjaTools);
    public final Plant uva = new Plant(10, "UVA", 5, toolRegistry.uvaTools);
}
