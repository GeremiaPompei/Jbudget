package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BudgetReportBaseController implements BudgetReportController{

    private final BudgetReport<Ledger,Budget> budgetReport;
    private final LedgerController ledgerC;
    private final BudgetController budgetC;

    //Il costruttore prende un ledgerController e un budgetController per creare un budgetReportController
    public BudgetReportBaseController(LedgerController ledgerC,BudgetController budgetC) {
        this.ledgerC = ledgerC;
        this.budgetC = budgetC;
        this.budgetReport = BudgetReportManager.generateReport(this.ledgerC.getLedger(),this.budgetC.getBudget());
    }

    @Override
    public LedgerController getLedgerC() {
        return ledgerC;
    }

    @Override
    public BudgetController getBudgetC() {
        return budgetC;
    }

    //Permette di salvare sia il ledger del ledgerController che il budget del budgetController
    @Override
    public void save() throws IOException {
        this.ledgerC.save();
        this.budgetC.save();
    }

    /*Permette di filtrare i valori negativi della mappa del check del budgetReport così da riportare quali
    tag del budget sono stati superati e di quanto
     */
    @Override
    public Map<Tag, Double> check(){
        Map<Tag, Double> result = new HashMap<>();
        this.budgetReport.check().keySet().stream()
                .filter(t->this.budgetReport.check().get(t)<0)
                .forEach(t->result.put(t,this.budgetReport.check().get(t)));
        return result;
    }

}
