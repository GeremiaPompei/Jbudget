package it.unicam.cs.pa.jbudget105333;

import java.util.List;

public interface ScheduleTransaction {
    String getDescription();
    List<Transaction> getTransactions();
    boolean isCompleted();
}
