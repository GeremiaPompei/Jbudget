package it.unicam.cs.pa.jbudget105333;

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

}
