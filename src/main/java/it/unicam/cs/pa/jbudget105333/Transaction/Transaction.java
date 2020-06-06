package it.unicam.cs.pa.jbudget105333.Transaction;

import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public interface Transaction extends Serializable,Comparable<Transaction> {
    void addMovement(Movement movement);
    void addMovements(Set<Movement> movement);
    Set<Movement> getMovements();
    Set<Tag> getTags();
    LocalDateTime getDate();
    double getTotalAmount();
    int getID();
}
