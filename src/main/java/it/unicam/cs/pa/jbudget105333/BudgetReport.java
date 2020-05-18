package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.Map;

public interface BudgetReport extends Serializable {
    Ledger getLedger();
    Budget getBudget();
    Map<Tag,Double> check();
}
