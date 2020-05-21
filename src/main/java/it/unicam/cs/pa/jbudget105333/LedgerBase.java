package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class LedgerBase implements Ledger{

    private final Set<Account> account;
    private  final Set<Transaction> transactions;
    private final Set<Tag> tags;
    private final Map<Tag,Double> tagAmount;

    public LedgerBase() {
        this.account = new TreeSet<>();
        this.transactions = new TreeSet<>();
        this.tags = new TreeSet<>();
        this.tagAmount = new HashMap<>();
    }

    @Override
    public Set<Account> getAccounts() {
        return this.account;
    }

    @Override
    public void addAccount(Account account) {
        this.account.add(account);
    }

    @Override
    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        for (Movement m : transaction.getMovements()) {
            double amount = 0.0;
            if (this.tagAmount.containsKey(m.getTag())) {
                amount = this.tagAmount.get(m.getTag());
                this.tagAmount.remove(m.getTag());
            }
            if (m.getType().equals(MovementType.CREDITS)
                    &&m.getAccount().getAccountType().equals(AccountType.ASSETS)
                    ||m.getType().equals(MovementType.DEBIT)
                    &&m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                this.tagAmount.put(m.getTag(), amount + m.getAmount());
            else
                this.tagAmount.put(m.getTag(), amount - m.getAmount());
        }
        this.transactions.add(transaction);
    }

    @Override
    public Set<Tag> getTags() {
        return this.tags;
    }

    @Override
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public Set<Transaction> scheduleDate(LocalDate start, LocalDate stop) {
        Set<Transaction> transactions = new TreeSet<>();
        this.transactions.stream()
                .filter(t->t.getDate().isAfter(LocalDateTime.of(start, LocalTime.MIN)))
                .filter(t->t.getDate().isBefore(LocalDateTime.of(stop, LocalTime.MIN)))
                .forEach(t->transactions.add(t));
        return transactions;
    }

    @Override
    public Set<Transaction> scheduleTag(Tag tag) {
        Set<Transaction> transaction = new TreeSet<>();
        this.transactions.stream()
                .filter(t->t.getTags().contains(tag))
                .forEach(t->transaction.add(t));
        return transactions;
    }

    @Override
    public Map<Tag,Double> getTagsAmount() {
        return tagAmount;
    }

    @Override
    public void update() {
        this.account.stream().forEach(a->a.update());
    }

}
