package it.unicam.cs.pa.jbudget105333.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.io.Serializable;
import java.util.Map;

public interface BudgetReport extends Serializable {
    Ledger getLedger();
    Budget getBudget();
    Map<Tag,Double> check();
}
