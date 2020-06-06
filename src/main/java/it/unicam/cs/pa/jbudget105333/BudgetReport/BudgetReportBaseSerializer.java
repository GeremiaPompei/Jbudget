package it.unicam.cs.pa.jbudget105333.BudgetReport;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerBaseSerializer;

import java.lang.reflect.Type;

public class BudgetReportBaseSerializer implements JsonSerializer<BudgetReport> {
    @Override
    public JsonElement serialize(BudgetReport src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("Ledger",new LedgerBaseSerializer().serialize(src.getLedger(), Ledger.class,context));
        jo.add("Budget",new BudgetBaseSerializer().serialize(src.getBudget(), Budget.class,context));
        return jo;
    }
}
