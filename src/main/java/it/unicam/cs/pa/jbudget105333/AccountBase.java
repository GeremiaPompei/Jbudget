package it.unicam.cs.pa.jbudget105333;

import java.util.ArrayList;
import java.util.List;

public class AccountBase implements Account{

    private String name = "";
    private String description = "";
    private final double openingBalance;
    private double balance = 0.0;
    private List<Movement> movements = null;
    private AccountType accountType = null;

    public AccountBase(String name, String description, double openingBalance,AccountType accountType) {
        this.name = name;
        this.description = description;
        this.openingBalance = openingBalance;
        this.balance = openingBalance;
        this.accountType = accountType;
        this.movements = new ArrayList<>();
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
    public double getOpeningBalance() {
        return this.openingBalance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void incrementBalance(double value) {
        this.balance += value;
    }

    @Override
    public void decrementBalance(double value) {
        this.balance -= value;
    }

    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public AccountType getAccountTyepe() {
        return this.accountType;
    }
}
