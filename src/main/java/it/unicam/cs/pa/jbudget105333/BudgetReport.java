package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;

public interface BudgetReport extends Serializable {
    Ledger getLedger();
    Budget getBudget();
}
