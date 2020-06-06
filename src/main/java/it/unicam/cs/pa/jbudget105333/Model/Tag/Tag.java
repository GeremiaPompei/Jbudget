package it.unicam.cs.pa.jbudget105333.Model.Tag;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;

import java.io.Serializable;
import java.util.Set;

public interface Tag extends Serializable,Comparable<Tag> {
    String getName();
    String getDescription();
    int getID();
    Set<Movement> getMovements();
    void addMovement(Movement movement);
}
