package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;

public class BudgetReportBase implements BudgetReport{

    private Ledger ledger = null;
    private Budget budget = null;

    public BudgetReportBase(Ledger ledger, Budget budget) {
        this.ledger = ledger;
        this.budget = budget;
    }

    @Override
    public Ledger getLedger() {
        return this.ledger;
    }

    @Override
    public Budget getBudget() {
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
