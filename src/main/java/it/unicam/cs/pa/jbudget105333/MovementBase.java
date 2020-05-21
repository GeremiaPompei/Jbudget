package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;

public class MovementBase<TA extends Tag,TR extends Transaction,A extends Account>
        implements Movement<TA,TR,A>{

    private final String descrizione;
    private final MovementType movementType;
    private final double amount;
    private final TR transaction;
    private final A account;
    private final LocalDateTime localDate;
    private final TA tag;
    private final int ID;

    public MovementBase(MovementType movementType, double amount, TR transaction
            , A account, TA tag, String descrizione, int ID) {
        this.descrizione = descrizione;
        this.movementType = movementType;
        this.amount = amount;
        this.transaction = transaction;
        this.account = account;
        this.localDate = transaction.getDate();
        this.tag = tag;
        this.ID = ID;
        transaction.addMovement(this);
        account.addMovement(this);
    }

    public MovementBase(MovementType movementType, double amount, TR transaction
            , A account, TA tag, String descrizione, IDGenerator idGenerator) {
        this.descrizione = descrizione;
        this.movementType = movementType;
        this.amount = amount;
        this.transaction = transaction;
        this.account = account;
        this.localDate = transaction.getDate();
        this.tag = tag;
        this.ID = idGenerator.generate();
        idGenerator.store(this);
        transaction.addMovement(this);
        account.addMovement(this);
    }

    @Override
    public String getDescription() {
        return this.descrizione;
    }

    @Override
    public MovementType getType() {
        return this.movementType;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public TR getTransaction() {
        return this.transaction;
    }

    @Override
    public A getAccount() {
        return this.account;
    }

    @Override
    public LocalDateTime getDate() {
        return this.localDate;
    }

    @Override
    public TA getTag() {
        return this.tag;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public int compareTo(Movement o) {
        int comparator = this.localDate.compareTo(o.getDate());
        if(comparator==0)
            comparator = this.ID-o.getID();
        return comparator;
    }
}
