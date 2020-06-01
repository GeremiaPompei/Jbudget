package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.Map;

public class BudgetBaseController implements BudgetController{

    private Budget budget;
    private final LedgerController ledgerC;

    /*Il costruttore del budgetReportController prende un ledgerController e genera
    sia un Budget che un budgetWriter
     */
    public BudgetBaseController(LedgerController ledgerC) {
        this.ledgerC = ledgerC;
        this.budget = BudgetManager.generateBudget();
    }

    @Override
    public Budget getBudget(){
        return this.budget;
    }

    //Ritorna la mappa del budget con un valore associato ad ogni tag
    @Override
    public Map<Tag, Double> getBudgetMap(){
        return this.budget.getBudgetMap();
    }

    /*Permette di aggiungere un tag per ID grazie al metodo getTag del ledgerController e
    ritorna true se viene trovato il tag, false altrimenti
     */
    @Override
    public boolean addBudgetRecord(int tagID, Double amount){
        Tag tag = this.ledgerC.getTag(tagID);
        if(tag!=null) {
            this.budget.add(tag,amount);
            return true;
        }
        return false;
    }

    //Permette di salvare il budget
    @Override
    public void save() throws IOException {
        try {
            Writer<Budget> writerB = new BudgetWriter("src/file/Budget");
            writerB.write(this.budget);
            writerB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
