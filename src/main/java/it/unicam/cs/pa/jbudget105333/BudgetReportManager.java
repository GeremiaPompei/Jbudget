package it.unicam.cs.pa.jbudget105333;

public interface BudgetReportManager {
    static BudgetReport generateReport(Ledger ledger,Budget budget){
        return new BudgetReportBase(ledger,budget);
    }
}
