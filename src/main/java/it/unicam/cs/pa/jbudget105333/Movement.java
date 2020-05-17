package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.List;

public interface Movement {
    String getDescrizione();
    MovementType type();
    double amount();
    Transaction getTransaction();
    Account getAccount();
    int getID();
    LocalDate getDate();
    Tag getTag();
}
