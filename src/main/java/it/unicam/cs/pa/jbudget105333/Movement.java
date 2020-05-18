package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface Movement extends Serializable {
    String getDescrizione();
    MovementType type();
    double amount();
    Transaction getTransaction();
    Account getAccount();
    int getID();
    LocalDate getDate();
    Tag getTag();
}
