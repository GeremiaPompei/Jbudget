package it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportBase;

import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class BudgetReportBase<L extends Ledger,B extends Budget> implements BudgetReport<L,B> {

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
