package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class LedgerBase implements Ledger{

    private final Set<Account> account;
    private  final Set<Transaction> transactions;
    private final Set<Tag> tags;
    private final Map<Tag,Double> tagAmount;
    private final IDGenerator idGenerator;

    public LedgerBase(IDGenerator idGenerator) {
        this.account = new TreeSet<>();
        this.transactions = new TreeSet<>();
        this.tags = new TreeSet<>();
        this.tagAmount = new HashMap<>();
        this.idGenerator = idGenerator;
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
    public void removeAccount(int ID) {
        AtomicReference<Account> account = new AtomicReference<>();
        this.account.stream()
                .filter(a->a.getID()==ID)
                .forEach(a->account.set(a));
        this.transactions.stream()
                .filter(a->a.getTags().contains(account.get()))
                .forEach(a->this.transactions.remove(a));
        this.account.remove(account.get());
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
    public void removeTransaction(int ID) {
        this.transactions.stream()
                .filter(t->t.getID()==ID)
                .forEach(t->this.transactions.remove(t));
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
    public void removeTag(int ID) {
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.tags.stream()
                .filter(t->t.getID()==ID)
                .forEach(t->tag.set(t));
        this.transactions.stream()
                .filter(t->t.getTags().contains(tag.get()))
                .forEach(t->this.transactions.remove(t));
        this.tags.remove(tag.get());
    }

    @Override
    public Map<Tag,Double> getTagsAmount() {
        return tagAmount;
    }


    @Override
    public IDGenerator getIDGenerator() {
        return idGenerator;
    }

    @Override
    public void update() {
        if(!this.account.isEmpty())
            this.account.stream().forEach(a->a.update());
    }

}
