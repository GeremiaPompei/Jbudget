package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.List;

public interface Account extends Serializable {

    String getName();
    String getDescription();
    double getOpeningBalance();
    double getBalance();
    void incrementBalance(double value);
    void decrementBalance(double value);
    void addMovement(Movement movement);
    void update();
    List<Movement> getMovements();
    AccountType getAccountType();
}
