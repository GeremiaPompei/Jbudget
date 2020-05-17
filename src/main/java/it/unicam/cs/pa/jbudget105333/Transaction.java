package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.List;

public interface Transaction {
    int getID();
    void addMovement(Movement movement);
    List<Movement> getMovements();
    List<Tag> getTags();
    LocalDate getDate();
}
