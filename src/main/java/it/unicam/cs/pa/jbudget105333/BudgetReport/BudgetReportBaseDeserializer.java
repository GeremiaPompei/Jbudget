package it.unicam.cs.pa.jbudget105333.BudgetReport;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerBaseDeserializer;

import java.lang.reflect.Type;

public class BudgetReportBaseDeserializer implements JsonDeserializer<BudgetReport> {

    @Override
    public BudgetReport deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        Ledger ledger = new LedgerBaseDeserializer().deserialize(jo.get("Ledger"),Ledger.class,context);
        Budget budget = new BudgetBaseDeserializer(ledger).deserialize(jo.get("Budget"),Budget.class,context);
        return new BudgetReportBase(ledger,budget);
    }
}
