package it.unicam.cs.pa.jbudget105333.Model.Tag;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;

import java.io.Serializable;
import java.util.Set;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un Tagt.
 */
public interface Tag extends Serializable,Comparable<Tag> {

    /**
     * Metodo responsabile di restituire il nome del Tag.
     * @return Nome del tag.
     */
    String getName();

    /**
     * Metodo responsabile di restituire la descrizione del Tag.
     * @return Descrizione del tag.
     */
    String getDescription();

    /**
     * Metodo responsabile di restituire l'ID del Tag.
     * @return ID del tag.
     */
    int getID();

    /**
     * Metodo responsabile di restituire i movimenti che usano il Tag.
     * @return Movimenti del Tag.
     */
    Set<Movement> getMovements();

    /**
     * Metodo responsabile di restituire il saldo totale del Tag dato dalla somma dei saldi dei suoi movimenti.
     * @return Saldo totale del tag.
     */
    double totalAmount();

    /**
     * Metodo responsabile di aggiungere un movimento al Tag.
     * @param movement Movimento da aggiungere.
     */
    void addMovement(Movement movement);
}
