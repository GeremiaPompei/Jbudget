package it.unicam.cs.pa.jbudget105333.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Map;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un BudgetReport.
 */
public interface BudgetReport extends Serializable {

    /**
     * Metodo responsabile di restituire il Ledger del BudgetReport.
     * @return Ledger del BudgetReport.
     */
    Ledger getLedger();

    /**
     * Metodo responsabile di restituire il Budget del BudgetReport.
     * @return Budget del BudgetReport.
     */
    Budget getBudget();

    /**
     * Metodo responsabile di restituire per ogni tag comuni a ledger e budget la differenza tra
     * la differenza tra il saldo dei movimenti e il valore associato ad ogni tag nel budget.
     * @return Ledger del BudgetReport.
     */
    Map<Tag,Double> check();
}
