package it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Utility;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire un ledger, ovvero un libro mastro che permette di
 * aggiungere, leggere e rimuovere oggetti come account, tag e transazioni. Tale implementazione
 * permette di accedere ad una serie di tag, account o transazioni, di aggiungerli o rimuoverli,
 * di accedere al generatore di id o impostarlo ad un certo id di partenza. Inoltre questa classe
 * permette di aggiornare ogni account, rimuovere un movimento e accedere ad un account, tag o
 * transazione tramite il loro id.
 */
public class LedgerBase implements Ledger {

    /**
     * Variabile utile alla gestione del log del LedgerBase.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Accounts del LedgerBase.
     */
    private final Set<Account> accounts;

    /**
     * Transazioni del LedgerBase.
     */
    private final Set<Transaction> transactions;

    /**
     * Tags del LedgerBase.
     */
    private final Set<Tag> tags;

    /**
     * IDGenerator del LedgerBase.
     */
    private IDGenerator idGenerator;

    /**
     * Costruttore del LedgerBase.
     */
    public LedgerBase() {
        this.accounts = new TreeSet<>();
        this.transactions = new TreeSet<>();
        this.tags = new TreeSet<>();
        this.idGenerator = new IDGeneratorBase();
        this.logger.finest("LedgerBase created.");
    }

    /**
     * Metodo responsabile di restituire gli account del LedgerBase.
     * @return Accounts del LedgerBase.
     */
    @Override
    public Set<Account> getAccounts() {
        this.logger.finest("Accounts getter.");
        return this.accounts;
    }

    /**
     * Metodo responsabile di restituire l'account del LedgerBase avente l'ID dato.
     * @param ID ID dell'Account ricercato.
     * @return Account ricercato.
     */
    @Override
    public Account getAccount(int ID) {
        this.logger.finest("Account getter with ID:["+ID+"]");
        return this.get(this.accounts,ID);
    }

    /**
     * Metodo responsabile di aggiungere un Account al LedgerBase.
     * @param account Account da aggiungere.
     */
    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
        this.logger.finest("Addition of Account: ["+account.toString()+"]");
    }

    /**
     * Metodo responsabile di aggiungere una serie di Account al LedgerBase.
     * @param accounts Accounts da aggiungere.
     */
    @Override
    public void addAccounts(Collection<Account> accounts) {
        this.accounts.addAll(accounts);
        this.logger.finest("Addition of Accounts: ["+accounts.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un Account dal LedgerBase.
     * @param account Account da rimuovere.
     */
    @Override
    public void removeAccount(Account account) {
        this.accounts.remove(account);
        this.logger.finest("Removal of Account: ["+account.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire le transazioni del LedgerBase.
     * @return Trasazioni del Ledger.
     */
    @Override
    public Set<Transaction> getTransactions() {
        this.logger.finest("Transactions getter.");
        return this.transactions;
    }

    /**
     * Metodo responsabile di restituire la transazione del LedgerBase avente l'ID dato.
     * @param ID ID della transazione ricercata.
     * @return Transazione ricercata.
     */
    @Override
    public Transaction getTransaction(int ID) {
        this.logger.finest("Transaction getter with ID:["+ID+"]");
        return this.get(this.transactions,ID);
    }

    /**
     * Metodo responsabile di aggiungere una Transazione al LedgerBase.
     * @param transaction Transazione da aggiungere.
     */
    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.logger.finest("Addition of Transaction:["+transaction.toString()+"]");
    }

    /**
     * Metodo responsabile di aggiungere una serie di Transazioni al LedgerBase.
     * @param transactions Transazioni da aggiungere.
     */
    @Override
    public void addTransactions(Collection<Transaction> transactions) {
        this.transactions.addAll(transactions);
        this.logger.finest("Addition of Transactions:["+transactions.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere una Transazione dal LedgerBase.
     * @param transaction Transazione da rimuovere.
     */
    @Override
    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        this.logger.finest("Removal of Transaction:["+transaction.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire i tag del LedgerBase.
     * @return Tag del LedgerBase.
     */
    @Override
    public Set<Tag> getTags() {
        this.logger.finest("Tags getter.");
        return this.tags;
    }

    /**
     * Metodo responsabile di restituire il tag del LedgerBase avente l'ID dato.
     * @param ID ID del tag ricercato.
     * @return Tag ricercato.
     */
    @Override
    public Tag getTag(int ID) {
        this.logger.finest("Tag getter with ID:["+ID+"]");
        return this.get(this.tags,ID);
    }

    /**
     * Metodo responsabile di aggiungere un Tag al LedgerBase.
     * @param tag Tag da aggiungere.
     */
    @Override
    public void addTag(Tag tag) {
        this.tags.add(tag);
        this.logger.finest("Addition of Tag:["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di aggiungere una serie di Tag al LedgerBase.
     * @param tags Tag da aggiungere.
     */
    @Override
    public void addTags(Collection<Tag> tags) {
        this.tags.addAll(tags);
        this.logger.finest("Addition of Tags:["+tags.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un tag dal LedgerBase.
     * @param tag Tag da rimuovere.
     */
    @Override
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        this.logger.finest("Removal of Tags:["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di ritornare l'IDGenerator.
     * @return IDGenerator del LedgerBase.
     */
    @Override
    public IDGenerator getIDGenerator() {
        this.logger.finest("IDGenerator getter.");
        return idGenerator;
    }

    /**
     * Metodo responsabile di modificare l'IDGenerator del LedgerBase.
     * @param idGenerator IDGenerator del LedgerBase.
     */
    @Override
    public void setIdGenerator(IDGenerator idGenerator){
        this.idGenerator = idGenerator;
        this.logger.finest("IDGenerator modified");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare il LedgerBase. In particolare aggiorna ogni
     * account del LedgerBase.
     */
    @Override
    public void update() {
        if(!this.accounts.isEmpty())
            this.accounts.parallelStream().forEach(a->a.update());
        this.logger.finer("LedgerBase updated.");
    }

    /**
     * Metodo che ha la responsabilità di ritornare un certo elemento T ricercato per id in una
     * Collenction parametrizzata rispetto a lui.
     * @param collection Collection nella quale ricercare il determinato oggetto.
     * @param ID Identificativo dell'oggetto ricercato.
     * @param <T> Tipo dell'oggetto ricercato.
     * @return Oggetto ricercato.
     */
    private <T extends Utility> T get(Collection<T> collection, int ID){
        AtomicReference<T> v = new AtomicReference<>();
        collection.stream().filter(t->t.getID()==ID).forEach(t->v.set(t));
        return v.get();
    }

}
