package it.unicam.cs.pa.jbudget105333.Model.BudgetReport;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.Map;

/**
 * Interfaccia implementata dalle classi che hanno la responsabilità di gestire un budget report,
 * ovvero un aggeggio capace di stilare il resoconto del valore dato dalla differenza tra un budget
 * e il saldo totale speso per un certo tag. Con tale resoconto si puo monitorare i budget superati
 * o no, o quanto mancherebbe per superarli. Tale interfaccia permette alle classi che la implementano
 * di accedere al ledger, al budget e alla tabella del resoconto.
 */
public interface BudgetReport {

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
