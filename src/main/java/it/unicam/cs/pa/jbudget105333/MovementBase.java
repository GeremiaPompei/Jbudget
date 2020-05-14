package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.List;

public class MovementBase implements Movement{

    private String descrizione = null;
    private MovementType movementType = null;
    private double amount = 0.0;
    private Transaction transaction = null;
    private Account account = null;
    private static int ID = 0;
    private LocalDate localDate = null;
    private List<Tag> tags = null;

    public MovementBase(MovementType movementType, double amount
            , Account account, LocalDate localDate) {
        this.movementType = movementType;
        this.amount = amount;
        this.account = account;
        this.localDate = localDate;
        this.tags = tags;
        this.ID++;
        account.addMovement(this);
    }

    public MovementBase(String descrizione, MovementType movementType, double amount
            , Account account, LocalDate localDate, List<Tag> tags) {
        this.descrizione = descrizione;
        this.movementType = movementType;
        this.amount = amount;
        this.transaction = transaction;
        this.account = account;
        this.localDate = localDate;
        this.tags = tags;
        this.ID++;
        account.addMovement(this);
    }

    @Override
    public String getDescrizione() {
        return this.descrizione;
    }

    @Override
    public MovementType type() {
        return this.movementType;
    }

    @Override
    public double amount() {
        return this.amount;
    }

    @Override
    public Transaction getTransaction() {
        return this.transaction;
    }

    @Override
    public Account getAccount() {
        return this.account;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public LocalDate getDate() {
        return this.localDate;
    }

    @Override
    public List<Tag> tags() {
        return this.tags;
    }

    @Override
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
