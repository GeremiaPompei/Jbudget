package it.unicam.cs.pa.jbudget105333.Budget;

public interface BudgetManager {
    //Factory method che ripristina un budget da un file o in caso di errore lo crea nuovo
    static Budget generateBudget(){
        return new BudgetBase();
    }
}
