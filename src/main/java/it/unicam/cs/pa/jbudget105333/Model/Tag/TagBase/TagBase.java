package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.Set;
import java.util.TreeSet;

public class TagBase implements Tag {

    private final String name;
    private final String description;
    private final int ID;
    private final Set<Movement> movements;

    public TagBase(String name, String description, int ID) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        movements = new TreeSet<>();
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

    @Override
    public Set<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    //I tag vengono riordinati per nome e per descrizione per garantirne l'univocità
    @Override
    public int compareTo(Tag o) {
        int comparator = this.name.compareTo(o.getName());
        if(comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }

    @Override
    public String toString(){
        return this.ID+": "+this.name;
    }
}
