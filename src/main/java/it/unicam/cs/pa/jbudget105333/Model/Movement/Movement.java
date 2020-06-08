package it.unicam.cs.pa.jbudget105333.Model.Movement;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.time.LocalDateTime;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un Movimento.
 */
public interface Movement extends Comparable<Movement> {

    /**
     * Metodo che ha la responsabilità di ritornare la descrizione del Movimento.
     * @return Descrizione del Movimento.
     */
    String getDescription();

    /**
     * Metodo che ha la responsabilità di ritornare il tipo del Movimento.
     * @return Tipo del Movimento.
     */
    MovementType getType();

    /**
     * Metodo che ha la responsabilità di ritornare il saldo del Movimento.
     * @return Saldo del Movimento.
     */
    double getAmount();

    /**
     * Metodo che ha la responsabilità di ritornare la Transazione del Movimento.
     * @return Transazione del Movimento.
     */
    Transaction getTransaction();

    /**
     * Metodo che ha la responsabilità di ritornare l'Account' del Movimento.
     * @return Account del Movimento.
     */
    Account getAccount();

    /**
     * Metodo che ha la responsabilità di ritornare la data del Movimento.
     * @return Data del Movimento.
     */
    LocalDateTime getDate();

    /**
     * Metodo che ha la responsabilità di ritornare il Tag del Movimento.
     * @return Tag del Movimento.
     */
    Tag getTag();

    /**
     * Metodo che ha la responsabilità di ritornare l'ID del Movimento.
     * @return ID del Movimento.
     */
    int getID();
}
