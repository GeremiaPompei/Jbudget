package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface Ledger extends Serializable {
    Set<Account> getAccounts();
    void addAccount(Account account);
    void removeAccount(Account account);
    Set<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    void removeTransaction(Transaction transaction);
    Set<Tag> getTags();
    void addTag(Tag tag);
    void removeTag(Tag tag);
    IDGenerator getIDGenerator();
    Map<Tag,Double> getTagsAmount();
    void update();
}
