package it.unicam.cs.pa.jbudget105333.Budget;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.lang.reflect.Type;

public class BudgetBaseDeserializer implements JsonDeserializer<Budget> {

    private Ledger ledger;

    public BudgetBaseDeserializer(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public Budget deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Budget budget = new BudgetBase();
        for(JsonElement je : json.getAsJsonArray()) {
            int tagId = context.deserialize(je.getAsJsonObject().get("Tag"),Integer.class);
            double value = context.deserialize(je.getAsJsonObject().get("Value"),Double.class);
            Tag tag = this.ledger.getTag(tagId);
            budget.add(tag,value);
        }
        return budget;
    }
}
