package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Utility;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Interfaccia sarà estesa dalle classi che hanno la responsabilità di dare un'implementazione
 * del controller dell'MVC per coordinare le attività eseguite sull'applicazione. Le implementazioni
 * permetteranno di gestire accounts, tags, transazioni e budgets di un budget report oltre a dare
 * la possibilità di aggiornare lo stato dei diversi componenti, salvare il budget report, ritornare
 * il generatore degli id e tener traccia del valore di superamento di un budget con relativo tag.
 */
public interface MainController {

    /**
     * Metodo responsabile di restituire gli account.
     * @return Accounts restituiti.
     */
    Set<Account> getAccounts();

    /**
     * Metodo responsabile di restituire l'account avente l'ID dato.
     * @param ID ID dell'Account ricercato.
     * @return Account ricercato.
     */
    Account getAccount(int ID);

    /**
     * Metodo responsabile di aggiungere un Account.
     * @param account Account da aggiungere.
     */
    void addAccount(Account account);

    /**
     * Metodo responsabile di rimuovere un Account.
     * @param account Account da rimuovere.
     */
    void removeAccount(Account account);

    /**
     * Metodo responsabile di restituire i tag.
     * @return Tags restituiti.
     */
    Set<Tag> getTags();

    /**
     * Metodo responsabile di restituire il tag avente l'ID dato.
     * @param ID ID del tag ricercato.
     * @return Tag ricercato.
     */
    Tag getTag(int ID);

    /**
     * Metodo responsabile di aggiungere un Tag.
     * @param tag Tag da aggiungere.
     */
    void addTag(Tag tag);

    /**
     * Metodo responsabile di rimuovere un tag.
     * @param tag Tag da rimuovere.
     */
    void removeTag(Tag tag);

    /**
     * Metodo responsabile di restituire le transazioni.
     * @return Trasazioni restituite.
     */
    Set<Transaction> getTransactions();

    /**
     * Metodo responsabile di restituire la transazione avente l'ID dato.
     * @param ID ID della transazione ricercata.
     * @return Transazione ricercata.
     */
    Transaction getTransaction(int ID);

    /**
     * Metodo responsabile di aggiungere una Transazione e di aggiungere a questa una serie di movimenti..
     * @param transaction Transazione da aggiungere.
     * @param movements Movimenti da aggiungere alla transazione.
     * @return
     */
    boolean addTransaction(Transaction transaction, Collection<Movement> movements);

    /**
     * Metodo responsabile di rimuovere una Transazione.
     * @param transaction Transazione da rimuovere.
     */
    void removeTransaction(Transaction transaction);

    /**
     * Metodo responsabile di rimuovere un Movimento.
     * @param movement Movimento da rimuovere.
     */
    void removeMovement(Movement movement);

    /**
     * Metodo che ha la responsabilità di modificare la descrizione di un oggetto istanziato da una classe
     * che implementa l'interfaccia Utility.
     * @param u Oggetto istanziato da una classe che implementa l'interfaccia Utility.
     * @param description Descrizione da sostituire.
     */
    void setDescription(Utility u, String description);

    /**
     * Metodo che ha la responsabilità di schedulare le transazioni in un certo range temporale.
     * @param start Inizio range.
     * @param stop Fine rande.
     * @return Serie di transazioni date dalla schedulazione.
     */
    Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop);

    /**
     * Metodo che ha la responsabilità di schedulare le transazioni con un certo tag.
     * @param tag Tag appartenente alle transazioni schedulate.
     * @return Serie di transazioni date dalla schedulazione.
     */
    Set<Transaction> scheduleTransactionsTag(Tag tag);

    /**
     * Metodo responsabile di restituire una mappa contenente tag e valori associati di un Budget.
     * @return Mappa di tag e double.
     */
    Map<Tag,Double> getBudgetRecords();

    /**
     * Metodo responsabile di aggiungere un tag e un valore al Budget.
     * @param tag Tag chiave.
     * @param value Value relativo al tag.
     */
    boolean addBudgetRecord(Tag tag, Double value);

    /**
     * Metodo che ha la responsabilità di aggiornare il MainController.
     */
    void update();

    /**
     * Metodo che ha la responsabilità di salvare un BudgetReport da qualche parte.
     */
    void save();

    /**
     * Metodo responsabile di eliminare un tag dal Budget.
     * @param tag Tag da rimuovere.
     */
    void removeBudgetRecord(Tag tag);

    /**
     * Metodo responsabile di fare un controllo e restituire una mappa che associa se presenti ad ogni tag
     * per cui è stato fissato un budget la differenza tra saldo totale e budget se questa è negativa
     * , ovvero se il valore del budget fissato per quel tag viene superato.
     * @return
     */
    Map<Tag, Double> check();

    /**
     * Metodo responsabile di ritornare l'IDGenerator.
     * @return IDGenerator restituito.
     */
    IDGenerator idGenerator();
}
