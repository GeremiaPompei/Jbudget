package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;

public class BudgetBaseSerializer implements JsonSerializer<Budget> {
    @Override
    public JsonElement serialize(Budget src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for(Tag t : src.getBudgetMap().keySet()) {
            JsonObject jo = new JsonObject();
            jo.add("Tag", context.serialize(t.getID()));
            jo.add("Value",context.serialize(src.getValue(t)));
            ja.add(jo);
        }
        return ja;
    }
}
