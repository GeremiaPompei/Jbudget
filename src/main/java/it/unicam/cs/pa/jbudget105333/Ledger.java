package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface Ledger extends Serializable {
    Set<Account> getAccounts();
    void addAccount(Account account);
    void removeAccount(int ID);
    Set<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    void removeTransaction(int ID);
    Set<Tag> getTags();
    void addTag(Tag tag);
    void removeTag(int ID);
    Set<Transaction> scheduleDate(LocalDate start,LocalDate stop);
    Set<Transaction> scheduleTag(Tag t);
    IDGenerator getIDGenerator();
    Map<Tag,Double> getTagsAmount();
    void update();
}
