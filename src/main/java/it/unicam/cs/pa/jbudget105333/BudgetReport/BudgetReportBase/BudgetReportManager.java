package it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportBase;

import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;

public interface BudgetReportManager {
    static BudgetReport generateReport(Ledger ledger, Budget budget){
        return new BudgetReportBase(ledger,budget);
    }
}
