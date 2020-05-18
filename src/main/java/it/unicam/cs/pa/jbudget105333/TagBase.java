package it.unicam.cs.pa.jbudget105333;

public class TagBase implements Tag{

    private String name = "";
    private String description = "";

    public TagBase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
