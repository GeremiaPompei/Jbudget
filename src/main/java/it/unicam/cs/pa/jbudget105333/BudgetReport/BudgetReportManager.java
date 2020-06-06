package it.unicam.cs.pa.jbudget105333.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Reader;

public interface BudgetReportManager {
    static BudgetReport generateReport(Ledger ledger, Budget budget){
        BudgetReport budgetReport;
        String path = "src/file/BudgetReport";
        try{
            Reader<BudgetReport> reader = new BudgetReportReaderJson(path);
            budgetReport = reader.read();
            reader.close();
            if(budgetReport==null)
                throw new NullPointerException();
        } catch (Exception e) {
            budgetReport = new BudgetReportBase(ledger,budget);
        }
        return budgetReport;
    }
}
