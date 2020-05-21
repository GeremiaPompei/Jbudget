package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface Ledger extends Serializable {
    Set<Account> getAccounts();
    void addAccount(Account account);
    Set<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    Set<Tag> getTags();
    void addTag(Tag tag);
    Set<Transaction> scheduleDate(LocalDate start,LocalDate stop);
    Set<Transaction> scheduleTag(Tag t);
    Map<Tag,Double> getTagsAmount();
    void update();
}
