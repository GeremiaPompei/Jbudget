package it.unicam.cs.pa.jbudget105333.Model.Ledger;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public interface Ledger extends Serializable {
    Set<Account> getAccounts();
    Account getAccount(int ID);
    void addAccount(Account account);
    void addAccounts(Set<Account> account);
    void removeAccount(Account account);
    Set<Transaction> getTransactions();
    Transaction getTransaction(int ID);
    void addTransaction(Transaction transaction);
    void addTransactions(Set<Transaction> transactions);
    void removeTransaction(Transaction transaction);
    Set<Tag> getTags();
    Tag getTag(int ID);
    void addTag(Tag tag);
    void addTags(Set<Tag> tags);
    void removeTag(Tag tag);
    void setTagAmount(Map<Tag, Double> tagAmount);
    IDGenerator getIDGenerator();
    Map<Tag,Double> getTagsAmount();
    void setIdGenerator(IDGenerator idGenerator);
    void update();
}
