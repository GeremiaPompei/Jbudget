package it.unicam.cs.pa.jbudget105333;

public class TagBase implements Tag{

    private String name = "";
    private String description = "";
    private int ID = 0;
    private static int IDStatic = 0;

    public TagBase(String name, String description) {
        this.name = name;
        this.description = description;
        this.ID = this.IDStatic++;
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
    public int getID() {
        return this.ID;
    }
}
