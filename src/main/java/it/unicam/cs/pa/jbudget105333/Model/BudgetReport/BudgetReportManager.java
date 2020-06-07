package it.unicam.cs.pa.jbudget105333.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader.Reader;

/**
 * Interfaccia responsabile della creazione un BudgetReport.
 */
public interface BudgetReportManager {

    /**
     * Metodo responsabile della generazione un BudgetReport.
     * @return BudgetReport generato.
     */
    static BudgetReport generateReport(Ledger ledger, Budget budget,Reader<BudgetReport> reader){
        BudgetReport budgetReport;
        try{
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
