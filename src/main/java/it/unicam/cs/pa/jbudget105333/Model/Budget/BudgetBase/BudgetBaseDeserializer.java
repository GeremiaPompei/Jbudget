package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un BudgetBase.
 */
public class BudgetBaseDeserializer implements JsonDeserializer<Budget> {

    /**
     * Variabile utile alla gestione del log del BudgetBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Variabile utile alla deserializzazione di un BudgetBase.
     */
    private Ledger ledger;

    /**
     * Costruttore del BudgetBaseDeserializzation.
     * @param ledger Ledger utile alla deserializzazione di un BudgetBase.
     */
    public BudgetBaseDeserializer(Ledger ledger) {
        this.ledger = ledger;
    }

    /**
     * Metodo responsabile della deserializzazione del BudgetBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT Tipo dell'oggetto in cui deserializzare.
     * @param context Contesto di deserializzazione.
     * @return BudgetBase deserializzato.
     * @throws JsonParseException Errore nel formato del file json da deserializzare.
     */
    @Override
    public Budget deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        logger.finer("Start deserialization.");
        Budget budget = new BudgetBase();
        for(JsonElement je : json.getAsJsonArray()) {
            int tagId = context.deserialize(je.getAsJsonObject().get("Tag"),Integer.class);
            double value = context.deserialize(je.getAsJsonObject().get("Value"),Double.class);
            Tag tag = this.ledger.getTag(tagId);
            budget.add(tag,value);
        }
        logger.finer("Stop deserialization.");
        return budget;
    }
}
