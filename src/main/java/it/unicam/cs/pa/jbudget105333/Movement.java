package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Movement<TA extends Tag,TR extends Transaction,A extends Account>
        extends Serializable,Comparable<Movement> {
    String getDescription();
    MovementType getType();
    double getAmount();
    TR getTransaction();
    A getAccount();
    LocalDateTime getDate();
    TA getTag();
    int getID();
}
