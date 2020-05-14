package it.unicam.cs.pa.jbudget105333;

import java.util.List;

public interface Account {

    String getName();
    String getDescription();
    double getOpeningBalance();
    double getBalance();
    void incrementBalance(double value);
    void decrementBalance(double value);
    void addMovement(Movement movement);
    List<Movement> getMovements();
}
