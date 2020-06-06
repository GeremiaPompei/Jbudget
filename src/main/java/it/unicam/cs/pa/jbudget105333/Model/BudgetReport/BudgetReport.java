package it.unicam.cs.pa.jbudget105333.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Map;

public interface BudgetReport extends Serializable {
    Ledger getLedger();
    Budget getBudget();
    Map<Tag,Double> check();
}
