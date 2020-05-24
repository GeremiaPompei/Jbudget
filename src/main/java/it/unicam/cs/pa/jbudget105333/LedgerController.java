package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

public interface LedgerController {
    Ledger getLedger();
    Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop);
    Set<Transaction> scheduleTransactionsTag(int tagID);
    Set<Transaction> getTransactions();
    boolean addTransaction(Transaction transaction, Set<Movement> movements);
    Set<Account> getAccounts();
    void addAccount(Account account);
    Account getAccount(int accountID);
    Set<Tag> getTags();
    void addTag(Tag tag);
    Tag getTag(int tagID);
    void update();
    void save() throws IOException;
    IDGenerator getIDGenerator();
}
