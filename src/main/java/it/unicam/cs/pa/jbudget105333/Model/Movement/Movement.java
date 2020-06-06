package it.unicam.cs.pa.jbudget105333.Model.Movement;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Movement extends Serializable,Comparable<Movement> {
    String getDescription();
    MovementType getType();
    double getAmount();
    Transaction getTransaction();
    Account getAccount();
    LocalDateTime getDate();
    Tag getTag();
    int getID();
}
