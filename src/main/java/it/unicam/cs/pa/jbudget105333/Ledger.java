package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.List;

public interface Ledger extends Serializable {
    List<Account> getAccounts();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    List<Tag> getTags();
    void addAccount(Account account);
    void update();
    void addTag(Tag tag);
}
