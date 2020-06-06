package it.unicam.cs.pa.jbudget105333.Ledger;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class LedgerBase implements Ledger {

    private final Set<Account> account;
    private final Set<Transaction> transactions;
    private final Set<Tag> tags;
    private Map<Tag,Double> tagAmount;
    private IDGenerator idGenerator;

    //Il costruttore inizializza ogni variabile di instanza
    public LedgerBase() {
        this.account = new TreeSet<>();
        this.transactions = new TreeSet<>();
        this.tags = new TreeSet<>();
        this.tagAmount = new HashMap<>();
        this.idGenerator = new IDGeneratorBase();
    }

    @Override
    public Set<Account> getAccounts() {
        return this.account;
    }

    @Override
    public Account getAccount(int ID) {
        AtomicReference<Account> account = new AtomicReference<>();
        this.account.stream().filter(a->a.getID()==ID).forEach(a->account.set(a));
        return account.get();
    }

    @Override
    public void addAccount(Account account) {
        this.account.add(account);
    }

    @Override
    public void addAccounts(Set<Account> account) {
        this.account.addAll(account);
    }

    @Override
    public void removeAccount(Account account) {
        this.account.remove(account);
    }

    @Override
    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public Transaction getTransaction(int ID) {
        AtomicReference<Transaction> transaction = new AtomicReference<>();
        this.transactions.stream().filter(t->t.getID()==ID).forEach(t->transaction.set(t));
        return transaction.get();
    }

    /*Quando viene aggiunta una transazione viene aggiurnata la mappa tagsAmount con la somma dei
    valori dei movimenti per ogni tag
     */
    @Override
    public void addTransaction(Transaction transaction) {
        for (Movement m : transaction.getMovements()) {
            double amount = 0.0;
            if (this.tagAmount.containsKey(m.getTag())) {
                amount = this.tagAmount.get(m.getTag());
                this.tagAmount.remove(m.getTag());
            }
            this.tagAmount.put(m.getTag(), amount + m.getAmount());
        }
        this.transactions.add(transaction);
    }

    @Override
    public void addTransactions(Set<Transaction> transactions) {
        transactions.stream().forEach(t->addTransaction(t));
    }

    @Override
    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
    }

    @Override
    public Set<Tag> getTags() {
        return this.tags;
    }

    @Override
    public Tag getTag(int ID) {
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.tags.stream().filter(t->t.getID()==ID).forEach(t->tag.set(t));
        return tag.get();
    }

    @Override
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    @Override
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    @Override
    public Map<Tag,Double> getTagsAmount() {
        return tagAmount;
    }

    @Override
    public void setTagAmount(Map<Tag,Double> tagAmount){
        this.tagAmount = tagAmount;
    }

    @Override
    public IDGenerator getIDGenerator() {
        return idGenerator;
    }

    @Override
    public void setIdGenerator(IDGenerator idGenerator){
        this.idGenerator = idGenerator;
    }

    //Aggiorna il bilancio degli account se vi sono
    @Override
    public void update() {
        if(!this.account.isEmpty())
            this.account.stream().forEach(a->a.update());
    }

}
