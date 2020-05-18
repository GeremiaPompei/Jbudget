package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountBase implements Account{

    private String name = "";
    private String description = "";
    private final double openingBalance;
    private double balance = 0.0;
    private List<Movement> movements = null;
    private AccountType accountType = null;
    private LocalDateTime lastUpdate = null;

    public AccountBase(String name, String description, double openingBalance, AccountType accountType) {
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
    public void update(){
        this.movements.parallelStream()
                .filter(m->m.getDate().compareTo(LocalDateTime.now())<=0
                        &&m.getDate().compareTo(this.lastUpdate)>=0
                        &&m.type().equals(MovementType.DEBIT))
                .forEach(m->this.balance-=m.amount());
        this.movements.parallelStream()
                .filter(m->m.getDate().compareTo(LocalDateTime.now())<=0
                        &&m.getDate().compareTo(this.lastUpdate)>=0
                        &&m.type().equals(MovementType.CREDITS))
                .forEach(m->this.balance+=m.amount());
        this.lastUpdate = LocalDateTime.now();
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public AccountType getAccountType() {
        return this.accountType;
    }
}
