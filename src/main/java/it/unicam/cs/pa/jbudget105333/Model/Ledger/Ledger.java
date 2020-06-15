package it.unicam.cs.pa.jbudget105333.Model.Ledger;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.util.Collection;
import java.util.Set;

/**
 * Questa interfaccia è implementata dalle classi che hanno la responsabilità di gestire un ledger,
 * ovvero un libro mastro che permette di aggiungere, leggere e rimuovere oggetti come account, tag e
 * transazioni. Tale interfaccia permette alle classi che la implementano di accedere ad una serie di
 * tag, account o transazioni, di aggiungerli o rimuoverli, di accedere al generatore di id o impostarlo
 * ad un certo id di partenza. Inoltre tali classi potranno aggiornare ogni account, rimuovere un movimento
 * e accedere ad un account, tag o transazione tramite il loro id.
 */
public interface Ledger {

    /**
     * Metodo responsabile di restituire gli account del Ledger.
     * @return Accounts del Ledger.
     */
    Set<Account> getAccounts();

    /**
     * Metodo responsabile di restituire l'account del Ledger avente l'ID dato.
     * @param ID ID dell'Account ricercato.
     * @return Account ricercato.
     */
    Account getAccount(int ID);

    /**
     * Metodo responsabile di aggiungere un Account al Ledger.
     * @param account Account da aggiungere.
     */
    void addAccount(Account account);

    /**
     * Metodo responsabile di aggiungere una serie di Account al Ledger.
     * @param accounts Accounts da aggiungere.
     */
    void addAccounts(Collection<Account> accounts);

    /**
     * Metodo responsabile di rimuovere un Account dal Ledger.
     * @param account Account da rimuovere.
     */
    void removeAccount(Account account);

    /**
     * Metodo responsabile di restituire le transazioni del Ledger.
     * @return Trasazioni del Ledger.
     */
    Set<Transaction> getTransactions();

    /**
     * Metodo responsabile di restituire la transazione del Ledger avente l'ID dato.
     * @param ID ID della transazione ricercata.
     * @return Transazione ricercata.
     */
    Transaction getTransaction(int ID);

    /**
     * Metodo responsabile di aggiungere una Transazione al Ledger.
     * @param transaction Transazione da aggiungere.
     */
    void addTransaction(Transaction transaction);

    /**
     * Metodo responsabile di aggiungere una serie di Transazioni al Ledger.
     * @param transactions Transazioni da aggiungere.
     */
    void addTransactions(Collection<Transaction> transactions);

    /**
     * Metodo responsabile di rimuovere una Transazione dal Ledger.
     * @param transaction Transazione da rimuovere.
     */
    void removeTransaction(Transaction transaction);

    /**
     * Metodo responsabile di restituire i tag del Ledger.
     * @return Tag del Ledger.
     */
    Set<Tag> getTags();

    /**
     * Metodo responsabile di restituire il tag del Ledger avente l'ID dato.
     * @param ID ID del tag ricercato.
     * @return Tag ricercato.
     */
    Tag getTag(int ID);

    /**
     * Metodo responsabile di aggiungere un Tag al Ledger.
     * @param tag Tag da aggiungere.
     */
    void addTag(Tag tag);

    /**
     * Metodo responsabile di aggiungere una serie di Tag al Ledger.
     * @param tags Tag da aggiungere.
     */
    void addTags(Collection<Tag> tags);

    /**
     * Metodo responsabile di rimuovere un tag dal Ledger.
     * @param tag Tag da rimuovere.
     */
    void removeTag(Tag tag);

    /**
     * Metodo responsabile di ritornare l'IDGenerator.
     * @return IDGenerator del Ledger.
     */
    IDGenerator getIDGenerator();

    /**
     * Metodo responsabile di modificare l'IDGenerator del Ledger.
     * @param idGenerator IDGenerator del Ledger.
     */
    void setIdGenerator(IDGenerator idGenerator);

    /**
     * Metodo che ha la responsabilità di aggiornare il Ledger.
     */
    void update();
}
