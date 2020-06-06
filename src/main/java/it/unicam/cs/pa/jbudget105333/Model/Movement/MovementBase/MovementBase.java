package it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.time.LocalDateTime;

public class MovementBase implements Movement {

    private final String descrizione;
    private final MovementType movementType;
    private final double amount;
    private final Transaction transaction;
    private final Account account;
    private final LocalDateTime date;
    private final Tag tag;
    private final int ID;

    /*Il costruttore prende i parametri per inizializzare le sue variabili di istanza oltre a collegare
    gli oggetti stessi passati come parametri a questo oggetto, in particolare transaction e account. Quindi
    appena istanziamo un movimento tramite tale costruttore esso punta alla propria transazione e questa
    punta a lui e succede lo stesso con l'account
     */
    public MovementBase(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String descrizione, int ID) {
        this.descrizione = descrizione;
        this.movementType = movementType;
        if(movementType.equals(MovementType.CREDITS))
            this.amount = amount;
        else
            this.amount = -amount;
        this.transaction = transaction;
        this.account = account;
        this.date = transaction.getDate();
        this.tag = tag;
        this.ID = ID;
        transaction.addMovement(this);
        account.addMovement(this);
        tag.addMovement(this);
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
    public Transaction getTransaction() {
        return this.transaction;
    }

    @Override
    public Account getAccount() {
        return this.account;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Tag getTag() {
        return this.tag;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    //I movimenti vengono ordinati per data e poi per id per garantirne l'univocità
    @Override
    public int compareTo(Movement o) {
        int comparator = this.date.compareTo(o.getDate());
        if(comparator==0)
            comparator = this.ID-o.getID();
        return comparator;
    }

    @Override
    public String toString(){
        return this.ID+": "+ this.movementType+" [" + this.amount+"]";
    }
}
