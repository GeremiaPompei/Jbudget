package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.Map;

public interface BudgetReportController {
    LedgerController getLedgerC();
    BudgetController getBudgetC();
    void save() throws IOException;
    Map<Tag, Double> check();
}
