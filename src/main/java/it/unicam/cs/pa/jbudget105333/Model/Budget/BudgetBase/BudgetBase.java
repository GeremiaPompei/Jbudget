package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BudgetBase implements Budget {

    private final Map<Tag,Double> budget;

    public BudgetBase() {
        this.budget = new HashMap<>();
    }

    //Aggiunta di un record
    @Override
    public void add(Tag tag, double value) {
        if(this.budget.containsKey(tag))
            this.budget.remove(tag);
        this.budget.put(tag,value);
    }

    //Eliminazione di un record
    @Override
    public void remove(Tag tag) {
        this.budget.remove(tag);
    }

    //Getter di tutta la mappa
    @Override
    public Map<Tag, Double> getBudgetMap() {
        return this.budget;
    }

    //Getter del valore corrispondente al tag
    @Override
    public double getValue(Tag tag) {
        return this.budget.get(tag);
    }

    //Getter del set di tag
    @Override
    public Set<Tag> getTags() {
        return this.budget.keySet();
    }

}
