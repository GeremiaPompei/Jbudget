package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LedgerBase implements Ledger{

    private List<Account> account = null;
    private  List<Transaction> transactions = null;
    private List<Tag> tags = null;
    private List<ScheduleTransaction> sheduleTransactions = null;

    public LedgerBase(List<Account> account, List<Transaction> transactions, List<Tag> tags
            , List<ScheduleTransaction> sheduleTransactions) {
        this.account = account;
        this.transactions = transactions;
        this.tags = tags;
        this.sheduleTransactions = sheduleTransactions;
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
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public void addScheduleTransaction(ScheduleTransaction scheduleTransaction) {
        this.sheduleTransactions.add(scheduleTransaction);
    }

    @Override
    public void schedule(LocalDate localDate) {

    }
}
