package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public interface Transaction extends Serializable,Comparable<Transaction> {
    void addMovement(Movement movement);
    Set<Movement> getMovements();
    Set<Tag> getTags();
    LocalDateTime getDate();
    int getID();
}
