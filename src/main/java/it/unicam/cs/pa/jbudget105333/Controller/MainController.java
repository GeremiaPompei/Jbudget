package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface MainController {
    Set<Account> getAccounts();
    void addAccount(Account account);
    void removeAccount(Account account);
    Set<Tag> getTags();
    void addTag(Tag tag);
    void removeTag(Tag tag);
    Set<Transaction> getTransactions();
    boolean addTransaction(Transaction transaction);
    void removeTransaction(Transaction transaction);
    Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop);
    Set<Transaction> scheduleTransactionsTag(Tag tag);
    Map<Tag,Double> getBudgetRecords();
    boolean addBudgetRecord(Tag tag, Double amount);
    void update();
    void save() throws IOException;
    void removeBudgetRecord(Tag tag);
    Map<Tag, Double> check();
    IDGenerator idGenerator();
}
