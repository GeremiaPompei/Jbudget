package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MovementBase implements Movement{

    private String descrizione = null;
    private MovementType movementType = null;
    private double amount = 0.0;
    private Transaction transaction = null;
    private Account account = null;
    private LocalDateTime localDate = null;
    private Tag tag = null;

    public MovementBase(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String descrizione) {
        this.descrizione = descrizione;
        this.movementType = movementType;
        this.amount = amount;
        this.transaction = transaction;
        this.account = account;
        this.localDate = transaction.getDate();
        this.tag = tag;
        transaction.addMovement(this);
        account.addMovement(this);
    }

    @Override
    public String getDescription() {
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
    public LocalDateTime getDate() {
        return this.localDate;
    }

    @Override
    public Tag getTag() {
        return this.tag;
    }

}
