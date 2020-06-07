package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su un Budget.
 */
public class BudgetBase implements Budget {

    /**
     * Variabile utile alla gestione del log dell'Account.
     */
    private Logger logger = new JBLogger(this.getClass()).getLogger();

    /**
     * Mappa avente un tag come chiave e un double come valore associato.
     */
    private final Map<Tag,Double> budget;

    /**
     * Costruttore del budget.
     */
    public BudgetBase() {
        this.budget = new HashMap<>();
        this.logger.finest("Budget created.");
    }

    /**
     * Metodo responsabile di aggiungere un tag e un valore al Budget. Se il tag è gia presente
     * il valore viene  aggiornato.
     * @param tag
     * @param value
     */
    @Override
    public void add(Tag tag, double value) {
        if(this.budget.containsKey(tag))
            this.budget.remove(tag);
        this.budget.put(tag,value);
        this.logger.finest("Addition of Key: ["+tag.toString()+"] and Value: ["+value+"]");
    }

    /**
     * Metodo responsabile di eliminare un tag dal Budget.
     * @param tag Tag da rimuovere.
     */
    @Override
    public void remove(Tag tag) {
        this.budget.remove(tag);
        this.logger.finest("Remotion of Key: ["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire una mappa contenente tag e valori associati.
     * @return Mappa di tag e double.
     */
    @Override
    public Map<Tag, Double> getBudgetMap() {
        this.logger.finest("");
        return this.budget;
    }

    /**
     * Metodo responsabile di restituire un valore dato il rispettivo tag.
     * @param tag Tag associato al valore.
     * @return Valore associato al tag.
     */
    @Override
    public double getValue(Tag tag) {
        return this.budget.get(tag);
    }

    /**
     * Metodo responsabile di restituire i tag del Budget.
     * @return Set di tag.
     */
    @Override
    public Set<Tag> getTags() {
        return this.budget.keySet();
    }

}
