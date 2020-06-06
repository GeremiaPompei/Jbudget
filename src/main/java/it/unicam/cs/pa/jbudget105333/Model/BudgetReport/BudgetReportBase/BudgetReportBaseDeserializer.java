package it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase.BudgetBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBaseDeserializer;

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
