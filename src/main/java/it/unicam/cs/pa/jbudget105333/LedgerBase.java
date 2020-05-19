package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LedgerBase implements Ledger{

    private List<Account> account = null;
    private  List<Transaction> transactions = null;
    private List<Tag> tags = null;
    private Map<Tag,Double> tagAmount = null;

    public LedgerBase() {
        this.account = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.tagAmount = new HashMap<>();
    }

    @Override
    public List<Account> getAccounts() {
        return this.account;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        for (Movement m : transaction.getMovements()) {
            double amount = 0.0;
            if (this.tagAmount.containsKey(m.getTag())) {
                amount = this.tagAmount.get(m.getTag());
                this.tagAmount.remove(m.getTag());
            }
            if (m.type().equals(MovementType.CREDITS)
                    &&m.getAccount().getAccountType().equals(AccountType.ASSETS)
                    ||m.type().equals(MovementType.DEBIT)
                    &&m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                this.tagAmount.put(m.getTag(), amount + m.amount());
            else
                this.tagAmount.put(m.getTag(), amount - m.amount());
        }
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
    public Map<Tag,Double> getTagsAmount() {
        return tagAmount;
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

    @Override
    public List<Transaction> scheduleDate(LocalDate start, LocalDate stop) {
        List<Transaction> transactions = new ArrayList<>();
        this.transactions.stream()
                .filter(t->t.getDate().isAfter(LocalDateTime.of(start, LocalTime.MIN)))
                .filter(t->t.getDate().isBefore(LocalDateTime.of(stop, LocalTime.MIN)))
                .forEach(t->transactions.add(t));
        return transactions;
    }

    @Override
    public List<Transaction> scheduleTag(Tag tag) {
        List<Transaction> transaction = new ArrayList<>();
        this.transactions.stream()
                .filter(t->t.getTags().contains(tag))
                .forEach(t->transaction.add(t));
        return transactions;
    }

}
