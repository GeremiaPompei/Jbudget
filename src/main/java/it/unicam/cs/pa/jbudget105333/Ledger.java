package it.unicam.cs.pa.jbudget105333;

import java.util.List;

public interface Ledger {
    List<Account> getAccounts();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    List<Tag> getTags();
    void addAccount(Account account);
    void update();
    void addTag(Tag tag);
}
