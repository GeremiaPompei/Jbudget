package it.unicam.cs.pa.jbudget105333.Model.Budget;

import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Set;
import java.util.Map;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un Budget.
 */
public interface Budget extends Serializable {

    /**
     * Metodo responsabile di aggiungere un tag e un valore al Budget.
     * @param tag
     * @param value
     */
    void add(Tag tag,double value);

    /**
     * Metodo responsabile di eliminare un tag dal Budget.
     * @param tag Tag da rimuovere.
     */
    void remove(Tag tag);

    /**
     * Metodo responsabile di restituire una mappa contenente tag e valori associati.
     * @return Mappa di tag e double.
     */
    Map<Tag,Double> getBudgetMap();

    /**
     * Metodo responsabile di restituire un valore dato il rispettivo tag.
     * @param tag Tag associato al valore.
     * @return Valore associato al tag.
     */
    double getValue(Tag tag);

    /**
     * Metodo responsabile di restituire i tag del Budget.
     * @return Set di tag.
     */
    Set<Tag> getTags();
}
