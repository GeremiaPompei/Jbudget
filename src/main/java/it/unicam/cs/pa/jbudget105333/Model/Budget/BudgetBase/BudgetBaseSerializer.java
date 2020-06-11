package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un BudgetBase.
 */
public class BudgetBaseSerializer implements JsonSerializer<Budget> {

    /**
     * Variabile utile alla gestione del log del BudgetBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione del BudgetBase.
     * @param src Budget da serializzare.
     * @param typeOfSrc
     * @param context
     * @return JasonElement serializzato.
     */
    @Override
    public JsonElement serialize(Budget src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.info("Start serializzation.");
        JsonArray ja = new JsonArray();
        for(Tag t : src.getBudgetMap().keySet()) {
            JsonObject jo = new JsonObject();
            jo.add("Tag", context.serialize(t.getID()));
            jo.add("Value",context.serialize(src.getValue(t)));
            ja.add(jo);
        }
        this.logger.info("Stop serializzation.");
        return ja;
    }
}
