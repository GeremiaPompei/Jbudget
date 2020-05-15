package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleTransaction {
    String getDescription();
    List<Transaction> getTransactions(LocalDate localDate);
    boolean isCompleted();
}
