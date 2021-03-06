package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un TransactionBase.
 */
public class TransactionBaseDeserializer implements JsonDeserializer<Transaction> {

    /**
     * Ledger utile alla gestione del log dell'TransactionBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();


    /**
     * Ledger utile alla gestione del log dell'TransactionBaseDeserializer.
     */
    private final Ledger ledger;

    /**
     * Costruttore della TransactionBaseDeserializer.
     * @param ledger Ledger della TransactionBaseDeserializer.
     */
    public TransactionBaseDeserializer(Ledger ledger) {
        this.ledger = ledger;
    }

    /**
     * Metodo responsabile della deserializzazione dell'TransactionBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT Tipo in cui deserializzare.
     * @param context Contesto della deserializzazione.
     * @return TransactionBase deserializzato.
     * @throws JsonParseException Eccezione dovuta al formato.
     */
    @Override
    public Transaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.logger.finer("Start deserialization.");
        JsonObject jo = json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        String description = jo.get("Description").getAsString();
        LocalDateTime date = context.deserialize(jo.get("Date"),LocalDateTime.class);
        Transaction t = (new ProgramTransaction(date,description,ID));
        t.addMovements(movementsDeserialize(jo.get("Movements"),context,t));
        this.logger.finer("Stop deserialization.");
        return t;
    }

    /**
     * Metodo che ha la responsabilità di deserializzare un JsonElement in una serie di movimenti.
     * @param json JsonElement da deserializzare.
     * @param context
     * @param t Transazione utile alla deserializzazione dei movimenti.
     * @return Serie di movimenti deserializzati.
     */
    private Set<Movement> movementsDeserialize(JsonElement json, JsonDeserializationContext context, Transaction t) {
        Set<Movement> movements = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            movements.add(new MovementBaseDeserializer(this.ledger,t).deserialize(je, Movement.class, context));
        return movements;
    }
}
