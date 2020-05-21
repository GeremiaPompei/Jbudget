package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BudgetBase implements Budget{

    private final Map<Tag,Double> budget;
    private final int ID;

    public BudgetBase(IDGenerator idGenerator) {
        this.ID = idGenerator.generate();
        this.budget = new HashMap<>();
        idGenerator.store(this);
    }

    public BudgetBase(int ID) {
        this.ID = ID;
        this.budget = new HashMap<>();
    }

    @Override
    public void add(Tag tag, double value) {
        if(this.budget.containsKey(tag))
            this.budget.remove(tag);
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

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public int compareTo(Budget o) {
        return this.ID-o.getID();
    }
}
