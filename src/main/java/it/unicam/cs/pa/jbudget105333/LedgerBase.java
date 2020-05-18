package it.unicam.cs.pa.jbudget105333;

import java.util.ArrayList;
import java.util.List;

public class LedgerBase implements Ledger{

    private List<Account> account = null;
    private  List<Transaction> transactions = null;
    private List<Tag> tags = null;

    public LedgerBase() {
        this.account = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    @Override
    public List<Account> getAccounts() {
        return this.account;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public List<Tag> getTags() {
        return this.tags;
    }

    @Override
    public void addAccount(Account account) {
        this.account.add(account);
    }

    @Override
    public void update() {
        this.account.stream().forEach(a->a.update());
    }

    @Override
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

}
