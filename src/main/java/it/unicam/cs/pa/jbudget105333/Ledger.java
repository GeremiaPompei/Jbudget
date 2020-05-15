package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.List;

public interface Ledger {
    List<Account> getAccounts();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    List<Tag> getTags();
    void addAccount(Account account);
    void addTag(Tag tag);
    void addScheduleTransaction(ScheduleTransaction scheduleTransaction);
    void schedule(LocalDate localDate);
}
