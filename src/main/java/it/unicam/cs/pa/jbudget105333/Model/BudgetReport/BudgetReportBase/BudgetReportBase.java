package it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase;

import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire un budget report, ovvero un aggeggio capace di stilare
 * il resoconto del valore dato dalla differenza tra un budget e il saldo totale speso per un certo tag.
 * Con tale resoconto si puo monitorare i budget superati o no, o quanto mancherebbe per superarli.
 * Tale implementazione permette di accedere al ledger, al budget e alla tabella del resoconto.
 */
public class BudgetReportBase implements BudgetReport {

    /**
     * Variabile utile alla gestione del log del BudgetReportBase.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Ledger del BudgetReportBase.
     */
    private final Ledger ledger;

    /**
     * Budget del BudgetReportBase.
     */
    private final Budget budget;

    /**
     * Costruttore del BudgetReportBase.
     * @param ledger Ledger del BudgetReportBase.
     * @param budget Budget del BudgetReportBase.
     */
    public BudgetReportBase(Ledger ledger, Budget budget) {
        this.ledger = ledger;
        this.budget = budget;
        this.logger.finest("BudgetReportBase created.");
    }

    /**
     * Metodo responsabile di restituire il Ledger del BudgetReportBase.
     * @return Ledger del BudgetReportBase.
     */
    @Override
    public Ledger getLedger() {
        this.logger.finest("Ledger getter.");
        return this.ledger;
    }

    /**
     * Metodo responsabile di restituire il Budget del BudgetReportBase.
     * @return Budget del BudgetReportBase.
     */
    @Override
    public Budget getBudget() {
        this.logger.finest("Budget getter.");
        return this.budget;
    }

    /**
     * Metodo responsabile di restituire per ogni tag comuni a ledger e budget la differenza tra
     * la differenza tra il saldo dei movimenti e il valore associato ad ogni tag nel budget.
     * @return Ledger del BudgetReportBase.
     */
    @Override
    public Map<Tag,Double> check() {
        Map<Tag,Double> result = new HashMap<>();
        this.budget.getTags().stream()
                .filter(t->this.ledger.getTags().contains(t))
                .forEach(t->result.put(t,this.budget.getValue(t)+this.ledger.getTag(t.getID()).totalAmount()));
        this.logger.finer("Check completed.");
        return result;
    }
}
