package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountBase implements Account{

    private final int ID;
    private final String name;
    private final String description;
    private final double openingBalance;
    private final AccountType accountType;
    private double balance = 0.0;
    private SortedSet<Movement> movements = null;
    private LocalDateTime lastUpdate = null;

    public AccountBase(String name, String description, double openingBalance, AccountType accountType
            ,int ID) {
        this.name = name;
        this.description = description;
        this.openingBalance = openingBalance;
        this.balance = openingBalance;
        this.accountType = accountType;
        this.movements = new TreeSet<>();
        this.ID = ID;
    }

    public AccountBase(String name, String description, double openingBalance, AccountType accountType
            ,IDGenerator idGenerator) {
        this.name = name;
        this.description = description;
        this.accountType = accountType;
        if(accountType.equals(AccountType.ASSETS))
            this.openingBalance = openingBalance;
        else
            this.openingBalance = -openingBalance;
        this.balance = this.openingBalance;
        this.movements = new TreeSet<>();
        this.ID = idGenerator.generate();
        idGenerator.store(this);
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
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    @Override
    public void update(){
        for(Movement m : this.movements)
            if(m.getDate().isBefore(LocalDateTime.now())&&m.getDate().isAfter(this.lastUpdate))
                if(m.getType().equals(MovementType.CREDITS)
                        && m.getAccount().getAccountType().equals(AccountType.ASSETS)
                        ||m.getType().equals(MovementType.DEBIT)
                        && m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                    this.balance+=m.getAmount();
                else
                    this.balance-=m.getAmount();
        this.lastUpdate = LocalDateTime.now();
    }

    @Override
    public SortedSet<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public int compareTo(Account o) {
        int comparator = this.name.compareTo(o.getName());
        if (comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }
}
