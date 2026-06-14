package io.github.some_example_name.registry;


import java.util.List;

import io.github.some_example_name.model.Ingredient;
import io.github.some_example_name.model.Recipe;

public class RecipeRegistry{
    private final PlantRegistry plantRegistry = new PlantRegistry();

    public final List<Ingredient> farinhaIngredients = List.of(plantRegistry.trigo);
    public final List<Ingredient> paoIngredients = List.of(this.farinha);
    public final List<Ingredient> acucarIngredients = List.of(plantRegistry.cana);
    public final List<Ingredient> geleiaMorangoIngredients = List.of(this.acucar, plantRegistry.morango);
    public final List<Ingredient> geleiaAboboraIngredients = List.of(this.acucar, plantRegistry.abobora);
    public final List<Ingredient> tortaAboboraIngredients = List.of(this.farinha, this.acucar, plantRegistry.abobora);
    public final List<Ingredient> molhoTomateIngredients = List.of(this.acucar, plantRegistry.tomate);
    public final List<Ingredient> sanduicheIngredients = List.of(this.pao, this.molhoTomate, plantRegistry.alface, plantRegistry.tomate);
    public final List<Ingredient> cremeAmendoimIngredients = List.of(this.acucar, plantRegistry.amendoim);
    public final List<Ingredient> americanoIngredients = List.of(this.pao, this.cremeAmendoim, this.geleiaMorango);
    public final List<Ingredient> sucoLaranjaIngredients = List.of(plantRegistry.laranja);
    public final List<Ingredient> tortaMacaIngredients = List.of(this.farinha, this.acucar, plantRegistry.maca);
    public final List<Ingredient> saladaFrutasIngredients = List.of(plantRegistry.maca, plantRegistry.laranja, plantRegistry.morango, plantRegistry.uva);
    public final List<Ingredient> alcoolIngredients = List.of(plantRegistry.cana);
    public final List<Ingredient> vinhoIngredients = List.of(plantRegistry.uva, this.alcool);
    

    public final Recipe farinha = new Recipe(1, "FARINHA", 3, this.farinhaIngredients);
    public final Recipe pao = new Recipe(2, "PAO", 5, this.paoIngredients);
    public final Recipe acucar = new Recipe(3, "ACUCAR", 5, this.acucarIngredients);
    public final Recipe geleiaMorango = new Recipe(4, "GELEIA-DE-MORANGO", 10, this.geleiaAboboraIngredients);
    public final Recipe geleiaAbobora = new Recipe(5, "GELEIA-DE-ABOBORA", 20, this.geleiaAboboraIngredients);
    public final Recipe tortaAbobora = new Recipe(6, "TORTA-DE-ABOBORA", 20, this.tortaAboboraIngredients);
    public final Recipe molhoTomate = new Recipe(7, "MOLHO-DE-TOMATE", 40, this.molhoTomateIngredients);
    public final Recipe sanduiche = new Recipe(8, "SANDUICHE", 80, this.sanduicheIngredients);
    public final Recipe cremeAmendoim = new Recipe(9, "CREME-DE-AMENDOIM", 80, this.cremeAmendoimIngredients);
    public final Recipe americano = new Recipe(10, "SANDUICHE-AMERICANO", 160, this.americanoIngredients);
    public final Recipe sucoLaranja = new Recipe(11, "SUCO-DE-LARANJA", 200, this.sucoLaranjaIngredients);
    public final Recipe tortaMaca = new Recipe(12, "TORTA-DE-MACA", 250, this.tortaAboboraIngredients);
    public final Recipe saladaFrutas = new Recipe(13, "SALADA-DE-FRUTAS", 500, this.saladaFrutasIngredients);
    public final Recipe alcool = new Recipe(14, "ALCOOL", 250, this.alcoolIngredients);
    public final Recipe vinho = new Recipe(15, "VINHO", 500, this.vinhoIngredients);

}
