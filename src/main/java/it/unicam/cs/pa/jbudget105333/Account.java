package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.SortedSet;

public interface Account extends Serializable,Comparable<Account> {

    String getName();
    String getDescription();
    double getOpeningBalance();
    double getBalance();
    void addMovement(Movement movement);
    void update();
    SortedSet<Movement> getMovements();
    AccountType getAccountType();
    int getID();
}
