package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BudgetBase implements Budget{

    private Map<Tag,Double> budget = null;

    public BudgetBase() {
        this.budget = new HashMap<>();
    }

    @Override
    public void add(Tag tag, double value) {
        this.budget.put(tag,value);
    }

    @Override
    public Map<Tag, Double> getBudget() {
        return this.budget;
    }

    @Override
    public double getValue(Tag tag) {
        return this.budget.get(tag);
    }

    @Override
    public Set<Tag> getTags() {
        return this.budget.keySet();
    }
}
