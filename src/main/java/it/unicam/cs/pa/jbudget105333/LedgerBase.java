package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LedgerBase implements Ledger{

    private final Set<Account> account;
    private  final Set<Transaction> transactions;
    private final Set<Tag> tags;
    private final Map<Tag,Double> tagAmount;
    private final IDGenerator idGenerator;

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
    public void addAccount(Account account) {
        this.account.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        this.account.remove(account);
    }

    @Override
    public Set<Transaction> getTransactions() {
        return this.transactions;
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
    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
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
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    @Override
    public Map<Tag,Double> getTagsAmount() {
        return tagAmount;
    }

    @Override
    public IDGenerator getIDGenerator() {
        return idGenerator;
    }

    //Aggiorna il bilancio degli account se vi sono
    @Override
    public void update() {
        if(!this.account.isEmpty())
            this.account.stream().forEach(a->a.update());
    }

}
