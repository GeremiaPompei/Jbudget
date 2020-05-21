package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.Map;

public interface BudgetReport<L extends Ledger,B extends Budget> extends Serializable {
    L getLedger();
    B getBudget();
    Map<Tag,Double> check();
}
