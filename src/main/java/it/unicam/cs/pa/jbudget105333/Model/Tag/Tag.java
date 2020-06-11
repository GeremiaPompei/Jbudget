package it.unicam.cs.pa.jbudget105333.Model.Tag;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;

import java.util.Set;

/**
 * Questa interfaccia è implementata dalle classi che hanno la responsabilità di definire una categoria di
 * un movimento. Tale interfaccia permette alle classi che la implementano di accedere al proprio id,
 * nome, descrizione, ad una serie di movimenti etichettati con essa, alla somma del saldo di questi e
 * di aggiungere o rimuovere un movimento.
 */
public interface Tag extends Comparable<Tag> {

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

    /**
     * Metodo responsabile di rimuovere un movimento dal Tag.
     * @param movement Movimento da rimuovere.
     */
    void removeMovement(Movement movement);
}
