package it.unicam.cs.pa.jbudget105333.Model.Account;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;

import java.util.Set;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un Account.
 */
public interface Account extends Comparable<Account> {

    /**
     * Metodo responsabile di restituire il nome dell'Account.
     * @return nome dell'Account.
     */
    String getName();

    /**
     * Metodo responsabile di restituire la descrizione dell'Account.
     * @return descrizione dell'Account.
     */
    String getDescription();

    /**
     * Metodo responsabile di restituire il bilancio iniziale dell'Account.
     * @return bilancio iniziale dell'Account.
     */
    double getOpeningBalance();

    /**
     * Metodo responsabile di restituire il bilancio dell'Account.
     * @return bilancio dell'Account.
     */
    double getBalance();

    /**
     * Metodo responsabile di aggiungere un movimento all'Account.
     * @param movement Movimento da aggiungere all'Account.
     */
    void addMovement(Movement movement);

    /**
     * Metodo responsabile di restituire i movimenti dell'Account.
     * @return Movimenti dell'Account.
     */
    Set<Movement> getMovements();

    /**
     * Metodo responsabile di restituire il tipo dell'Account.
     * @return Tipo dell'Account.
     */
    AccountType getAccountType();

    /**
     * Metodo responsabile di restituire l'ID dell'Account.
     * @return ID dell'Account.
     */
    int getID();

    /**
     * Metodo responsabile di aggiornare il bilancio dell'Account.
     */
    void update();
}
