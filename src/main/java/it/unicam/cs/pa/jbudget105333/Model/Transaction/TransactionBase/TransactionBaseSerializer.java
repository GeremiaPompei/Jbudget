package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un TransactionBase.
 */
public class TransactionBaseSerializer implements JsonSerializer<Transaction> {

    /**
     * Variabile utile alla gestione del log dell'TransactionBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione della TransactionBase.
     * @param src Transazione da serializzare.
     * @param typeOfSrc
     * @param context
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(Transaction src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.info("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Date", context.serialize(src.getDate(), LocalDateTime.class));
        jo.add("TotalAmounts", context.serialize(src.getTotalAmount()));
        jo.add("Movements", movementsSerializer(src.getMovements(),context));
        jo.add("Description", context.serialize(src.getDescription()));
        this.logger.info("Stop serializzation.");
        return jo;
    }

    /**
     * Metodo che ha la responsabilità di serializzare una serie di movimenti.
     * @param src Serie di movimenti da serializzare.
     * @param context
     * @return JsonElement serializzato.
     */
    private JsonElement movementsSerializer(Set<Movement> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        src.parallelStream().forEach(m->ja.add(new MovementBaseSerializer().serialize(m,Movement.class,context)));
        return ja;
    }
}
