package it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class BudgetReportBase implements BudgetReport {

    private final Ledger ledger;
    private final Budget budget;

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

    /*Metodo che salva in una tabella per ogni tag comuni a ledger e budget la differenza tra i
    la somma del saldo dei movimenti e il valore associato ad ogni tag nel budget. Tale tabella è
    utile per segnalare quando è stato spesa una somma oltre al budget impostato per un certo tag
     */
    @Override
    public Map<Tag,Double> check() {
        Map<Tag,Double> result = new HashMap<>();
        Map<Tag,Double> check = this.ledger.getTagsAmount();
        this.budget.getTags().stream()
                .filter(t->check.containsKey(t))
                .forEach(t->result.put(t,this.budget.getValue(t)+check.get(t)));
        return result;
    }
}
