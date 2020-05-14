package it.unicam.cs.pa.jbudget105333;

public class TagBase implements Tag{

    private String name = "";
    private String description = "";
    private static int ID = 0;

    public TagBase(String nome) {
        this.name = nome;
        this.ID++;
    }

    public TagBase(String name, String description) {
        this.name = name;
        this.description = description;
        this.ID++;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int ID() {
        return this.ID;
    }
}
