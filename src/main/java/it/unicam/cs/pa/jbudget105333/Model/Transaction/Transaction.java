package it.unicam.cs.pa.jbudget105333.Model.Transaction;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Utility;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Questa interfaccia sarà implementata dalle classi che hanno la responsabilità di gestire una singola
 * transazione. Essa non è altro che un gruppo di movimenti eseguiti in una certa data. Tale interfaccia
 * permette alle classi che la implementano di accedere al proprio id, data, saldo totale (dato dalla
 * somma dei saldi dei movimenti che la compongono), la serie di movimenti che la popolano, di aggiungere
 * o rimuovere un movimento o aggiungere una serie di movimenti.
 */
public interface Transaction extends Comparable<Transaction>, Utility {

    /**
     * Metodo responsabile di aggiungere il movimento alla Transazione.
     * @param movement Movimento aggiunto alla Transazione.
     */
    void addMovement(Movement movement);

    /**
     * Metodo responsabile di rimuovere il movimento alla Transazione.
     * @param movement Movimento rimosso alla Transazione.
     */
    void removeMovement(Movement movement);

    /**
     * Metodo responsabile di aggiungere una serie di movimenti alla Transazione.
     * @param movements Serie di movimenti aggiunti alla Transazione.
     */
    void addMovements(Collection<Movement> movements);

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
}
