package it.unicam.cs.pa.jbudget105333.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Budget.BudgetController;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerController;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.io.IOException;
import java.util.Map;

public interface BudgetReportController {
    LedgerController getLedgerC();
    BudgetController getBudgetC();
    void save() throws IOException;
    Map<Tag, Double> check();
}
