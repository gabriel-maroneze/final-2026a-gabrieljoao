package io.github.some_example_name.registry;

import java.util.List;

import io.github.some_example_name.model.Tool;

public class ToolRegistry {

    public final Tool enxada   = new Tool(1, "ENXADA",   2);
    public final Tool rasteira = new Tool(2, "RASTEIRA",  4);
    public final Tool suporte  = new Tool(3, "SUPORTE",   5);
    public final Tool adubo    = new Tool(4, "ADUBO",     3);
    public final Tool pa       = new Tool(5, "PA",        1);

    public final List<Tool> trigoTools   = List.of(enxada);
    public final List<Tool> canaTools    = List.of(enxada);
    public final List<Tool> morangoTools = List.of(rasteira);
    public final List<Tool> aboboraTools = List.of(enxada, rasteira);
    public final List<Tool> tomateTools  = List.of(suporte);
    public final List<Tool> alfaceTools = List.of(adubo, rasteira);
    public final List<Tool> amendoimTools = List.of(pa, enxada);
    public final List<Tool> macaTools = List.of(pa, adubo);
    public final List<Tool> laranjaTools = List.of(adubo, suporte);
    public final List<Tool> uvaTools = List.of(pa, suporte);
}

