package it.unicam.cs.pa.jbudget105333.Model.Budget;

import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase.BudgetBase;

/**
 * Interfaccia responsabile della creazione un Budget.
 */
public interface BudgetManager {

    /**
     * Metodo responsabile della generazione un Budget.
     * @return Budget generato.
     */
    static Budget generateBudget(){
        return new BudgetBase();
    }
}
