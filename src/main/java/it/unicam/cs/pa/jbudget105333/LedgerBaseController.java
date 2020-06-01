package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class LedgerBaseController implements LedgerController {

    private Ledger ledger;

    //Il costruttore crea un ledger grazie al factory method dell'interfaccia ledgerManager
    public LedgerBaseController() {
        this.ledger = LedgerManager.generateLedger();
    }

    @Override
    public Ledger getLedger() {
        return this.ledger;
    }

    /*Metodo che permette di schedulare le transazioni restituendo quelle comprese tra le
        date start e stop
         */
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

    /*Metodo che permette di schedulare le transazioni restituendo quelle con un certo
    tag identificato dal tagID
     */
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

    //Metodo che restituisce tutte le transazioni
    @Override
    public Set<Transaction> getTransactions(){
        return this.ledger.getTransactions();
    }

    //Data una transazione e un set di metodi permette di creare una transazione
    @Override
    public boolean addTransaction(Transaction transaction, Set<Movement> movements){
        if(movements!= null && !movements.isEmpty()){
            movements.stream().forEach(m->transaction.addMovement(m));
            this.ledger.addTransaction(transaction);
            return true;
        }
        return false;
    }

    //Metodo che restituisce tutti gli account
    @Override
    public Set<Account> getAccounts(){
        return this.ledger.getAccounts();
    }

    //Metodo che permette di aggiungere un account
    @Override
    public void addAccount(Account account){
        this.ledger.addAccount(account);
    }

    //Metodo che restituisce un account dato il suo ID
    @Override
    public Account getAccount(int accountID){
        AtomicReference<Account> account = new AtomicReference<>();
        this.ledger.getAccounts().stream()
                .filter(a -> a.getID() == accountID)
                .forEach(a -> account.set(a));
        return account.get();
    }

    //Metodo che restituisce tutti i tag
    @Override
    public Set<Tag> getTags(){
        return this.ledger.getTags();
    }

    //Metodo che permette di aggiungere un tag
    @Override
    public void addTag(Tag tag){
        this.ledger.addTag(tag);
    }

    //Metodo che restituisce un tag dato il suo id
    @Override
    public Tag getTag(int tagID){
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.ledger.getTags().stream()
                .filter(t -> t.getID() == tagID)
                .forEach(t -> tag.set(t));
        return tag.get();
    }

    //Metodo per aggiornare il ledger
    @Override
    public void update(){
        this.ledger.update();
    }

    @Override
    public IDGenerator getIDGenerator(){
        return this.ledger.getIDGenerator();
    }

    //Metodo che permette di salvare il ledger su file
    @Override
    public void save() {
        try {
            Writer<Ledger> writerL = new LedgerWriterG("src/file/Ledger");
            writerL.write(this.ledger);
            writerL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
