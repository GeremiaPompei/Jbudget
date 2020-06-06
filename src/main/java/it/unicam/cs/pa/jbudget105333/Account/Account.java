package it.unicam.cs.pa.jbudget105333.Account;

import it.unicam.cs.pa.jbudget105333.Movement.Movement;

import java.io.Serializable;
import java.util.Set;

public interface Account extends Serializable,Comparable<Account> {

    String getName();
    String getDescription();
    double getOpeningBalance();
    double getBalance();
    void addMovement(Movement movement);
    Set<Movement> getMovements();
    AccountType getAccountType();
    int getID();
    void update();
}
