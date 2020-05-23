package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

public interface LedgerController {
    Ledger getLedger();
    Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop);
    Set<Transaction> scheduleTransactionsTag(int tagID);
    Set<Transaction> showTransactions();
    boolean newTransaction(Transaction transaction, Set<Movement> movements);
    Set<Account> showAccounts();
    void newAccount(Account account);
    Account getAccount(int accountID);
    Set<Tag> showTags();
    void newTag(Tag tag);
    Tag getTag(int tagID);
    void update();
    void save() throws IOException;
    IDGenerator getIDGenerator();
}
