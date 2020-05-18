package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Movement extends Serializable {
    String getDescription();
    MovementType type();
    double amount();
    Transaction getTransaction();
    Account getAccount();
    LocalDateTime getDate();
    Tag getTag();
}
