package io.github.some_example_name.model;

public class Tool {
    private final int id;
    private final String nome;
    private final int hierarquia;

    public Tool(int id, String nome, int hierarquia) {
        this.id = id;
        this.nome = nome;
        this.hierarquia = hierarquia;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getHierarquia() { return hierarquia; }
}