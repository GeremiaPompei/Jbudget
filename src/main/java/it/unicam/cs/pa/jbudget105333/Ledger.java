package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Ledger extends Serializable {
    List<Account> getAccounts();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    List<Tag> getTags();
    Map<Tag,Double> getTagsAmount();
    void addAccount(Account account);
    void update();
    void addTag(Tag tag);
    List<Transaction> scheduleDate(LocalDate start,LocalDate stop);
    List<Transaction> scheduleTag(Tag t);
}
