package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;

public class BudgetReportBase<L extends Ledger,B extends Budget> implements BudgetReport<L,B>{

    private final L ledger;
    private final B budget;

    public BudgetReportBase(L ledger, B budget) {
        this.ledger = ledger;
        this.budget = budget;
    }

    @Override
    public L getLedger() {
        return this.ledger;
    }

    @Override
    public B getBudget() {
        return this.budget;
    }

    @Override
    public Map<Tag,Double> check() {
        Map<Tag,Double> result = new HashMap<>();
        this.budget.getTags().stream()
                .filter(t->this.ledger.getTagsAmount().containsKey(t))
                .forEach(t->result.put(t,this.budget.getValue(t)+this.ledger.getTagsAmount().get(t)));
        return result;
    }
}
