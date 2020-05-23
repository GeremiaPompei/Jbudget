package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class LedgerBaseController implements LedgerController {

    private Ledger ledger;

    public LedgerBaseController() {
        String path = "src/file/Ledger.txt";
        this.ledger = LedgerManager.generateLedger();
    }

    @Override
    public Ledger getLedger() {
        return ledger;
    }

    @Override
    public Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop) {
        Set<Transaction> stransactions = new TreeSet();
        this.ledger.getTransactions()
                .stream()
                .filter(t -> t.getDate().isAfter(start))
                .filter(t -> t.getDate().isBefore(stop))
                .forEach(t->stransactions.add(t));
        return stransactions;
    }

    @Override
    public Set<Transaction> scheduleTransactionsTag(int tagID){
        Set<Transaction> stransactions = new TreeSet();
        Tag tag = getTag(tagID);
        this.ledger.getTransactions()
                .stream()
                .filter(t -> t.getTags().contains(tag))
                .forEach(t->stransactions.add(t));
        return stransactions;
    }

    @Override
    public Set<Transaction> showTransactions(){
        Set<Transaction> stransactions = new TreeSet();
        this.ledger.getTransactions().stream().forEach(t->stransactions.add(t));
        return stransactions;
    }

    @Override
    public boolean newTransaction(Transaction transaction, Set<Movement> movements){
        if(movements!= null && !movements.isEmpty()){
            movements.stream().forEach(m->transaction.addMovement(m));
            this.ledger.addTransaction(transaction);
            return true;
        }
        return false;
    }

    @Override
    public Set<Account> showAccounts(){
        Set<Account> accounts = new TreeSet();
        this.ledger.getAccounts().stream().forEach(a->accounts.add(a));
        return accounts;
    }

    @Override
    public void newAccount(Account account){
        this.ledger.addAccount(account);
    }

    @Override
    public Account getAccount(int accountID){
        AtomicReference<Account> account = new AtomicReference<>();
        this.ledger.getAccounts().stream()
                .filter(a -> a.getID() == accountID)
                .forEach(a -> account.set(a));
        return account.get();
    }

    @Override
    public Set<Tag> showTags(){
        Set<Tag> tags = new TreeSet();
        this.ledger.getTags().stream().forEach(t->tags.add(t));
        return tags;
    }

    @Override
    public void newTag(Tag tag){
        this.ledger.addTag(tag);
    }

    @Override
    public Tag getTag(int tagID){
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.ledger.getTags().stream()
                .filter(t -> t.getID() == tagID)
                .forEach(t -> tag.set(t));
        return tag.get();
    }

    @Override
    public void update(){
        this.ledger.update();
    }

    @Override
    public IDGenerator getIDGenerator(){
        return this.ledger.getIDGenerator();
    }

    @Override
    public void save() {
        try {
            Writer<Ledger> writerL = new LedgerWriter("src/file/Ledger.txt");
            writerL.write(this.ledger);
            writerL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
