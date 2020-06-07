package it.unicam.cs.pa.jbudget105333.Model.Transaction;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Interfaccia responsabile della creazione un Transazione.
 */
public interface Transaction extends Serializable,Comparable<Transaction> {

    /**
     * Metodo responsabile di aggiungere il movimento alla Transazione.
     * @param movement Movimento aggiunto alla Transazione.
     */
    void addMovement(Movement movement);

    /**
     * Metodo responsabile di aggiungere una serie di movimenti alla Transazione.
     * @param movements Serie di movimenti aggiunti alla Transazione.
     */
    void addMovements(Set<Movement> movements);

    /**
     * Metodo che ha la responsabilità di ritornare i movimenti della Transazione.
     * @return Movimenti dela transazione.
     */
    Set<Movement> getMovements();

    /**
     * Metodo che ha la responsabilità di ritornare i tag della Transazione.
     * @return Tag della transazione.
     */
    Set<Tag> getTags();

    /**
     * Metodo che ha la responsabilità di ritornare la data della Transazione.
     * @return Data della Transazione.
     */
    LocalDateTime getDate();

    /**
     * Metodo che ha la responsabilità di ritornare il saldo totale della Transazione.
     * @return Saldo totale della transazione.
     */
    double getTotalAmount();

    /**
     * Metodo che ha la responsabilità di ritornare l'ID della Transazione.
     * @return ID della transazione.
     */
    int getID();
}
