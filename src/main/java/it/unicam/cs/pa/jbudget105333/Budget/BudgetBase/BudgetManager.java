package it.unicam.cs.pa.jbudget105333.Budget.BudgetBase;

import it.unicam.cs.pa.jbudget105333.Budget.Budget;

public interface BudgetManager {
    //Factory method che ripristina un budget da un file o in caso di errore lo crea nuovo
    static Budget generateBudget(){
        Budget budget;
        /*String path = "src/file/Budget";
        try{
            Reader<Budget> reader = new BudgetReader(path);
            budget = reader.read();
            reader.close();
        } catch (Exception e) {*/
            budget = new BudgetBase();
        //}
        return budget;
    }
}
