package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire una serie di budget ognuno fissato per un certo tag.
 * Tale implementazione permette di accedere alla tabella contenente i budget, al valore di un budget
 * dato un tag, ad una serie di tag per i quali è stato fissato un budget e di aggiungere o
 * rimuovere un tag.
 */
public class BudgetBase implements Budget {

    /**
     * Variabile utile alla gestione del log del BudgetBase.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Mappa avente un tag come chiave e un double come valore associato.
     */
    private final Map<Tag,Double> budget;

    /**
     * Costruttore del budgetBase.
     */
    public BudgetBase() {
        this.budget = new HashMap<>();
        this.logger.finest("Budget created.");
    }

    /**
     * Metodo responsabile di aggiungere un tag e un valore al BudgetBase. Se il tag è gia presente
     * il valore viene  aggiornato.
     * @param tag Tag chiave.
     * @param value Value relativo al tag.
     */
    @Override
    public void add(Tag tag, double value) {
        if(this.budget.containsKey(tag))
            this.budget.remove(tag);
        this.budget.put(tag,value);
        this.logger.finest("Addition of Key: ["+tag.toString()+"] and Value: ["+value+"]");
    }

    /**
     * Metodo responsabile di eliminare un tag dal BudgetBase.
     * @param tag Tag da rimuovere.
     */
    @Override
    public void remove(Tag tag) {
        this.budget.remove(tag);
        this.logger.finest("Removal of Key: ["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire una mappa contenente tag e valori associati.
     * @return Mappa di tag e double.
     */
    @Override
    public Map<Tag, Double> getBudgetMap() {
        this.logger.finest("Map getter.");
        return this.budget;
    }

    /**
     * Metodo responsabile di restituire un valore dato il rispettivo tag.
     * @param tag Tag associato al valore.
     * @return Valore associato al tag.
     */
    @Override
    public double getValue(Tag tag) {
        this.logger.finest("Value getter related to Tag: ["+tag.toString()+"]");
        return this.budget.get(tag);
    }

    /**
     * Metodo responsabile di restituire i tag del BudgetBase.
     * @return Set di tag.
     */
    @Override
    public Set<Tag> getTags() {
        this.logger.finest("Set of TAgs getter.");
        return this.budget.keySet();
    }

}
