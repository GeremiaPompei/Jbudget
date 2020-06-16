package it.unicam.cs.pa.jbudget105333.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;

/**
 * Interfaccia responsabile della creazione di un BudgetReport.
 */
public interface BudgetReportManager {

    /**
     * Metodo responsabile della generazione di un BudgetReport.
     * @param ledger Ledger del BudgetReport.
     * @param budget Budget del BudgetReport.
     * @return BudgetReport generato o letto.
     */
    static BudgetReport generateReport(Ledger ledger, Budget budget){
        return new BudgetReportBase(ledger,budget);
    }
}
