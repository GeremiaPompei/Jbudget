package it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase.BudgetBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBaseSerializer;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un BudgetReportBase.
 */
public class BudgetReportBaseSerializer implements JsonSerializer<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del BudgetReportBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione dei un BudgetReportBase.
     * @param src BudgetReport da serializzare.
     * @param typeOfSrc
     * @param context
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(BudgetReport src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.finer("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("Ledger",new LedgerBaseSerializer().serialize(src.getLedger(), Ledger.class,context));
        jo.add("Budget",new BudgetBaseSerializer().serialize(src.getBudget(), Budget.class,context));
        this.logger.finer("Stop serializzation.");
        return jo;
    }
}
