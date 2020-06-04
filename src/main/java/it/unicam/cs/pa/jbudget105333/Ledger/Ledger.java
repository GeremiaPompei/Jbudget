package it.unicam.cs.pa.jbudget105333.Ledger;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;

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
