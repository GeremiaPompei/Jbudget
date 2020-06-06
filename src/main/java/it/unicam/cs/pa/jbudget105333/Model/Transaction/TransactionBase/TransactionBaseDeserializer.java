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

public class TransactionBaseDeserializer implements JsonDeserializer<Transaction> {

    private final Ledger ledger;

    public TransactionBaseDeserializer(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public Transaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        LocalDateTime date = context.deserialize(jo.get("Date"),LocalDateTime.class);
        Transaction t = (new ProgramTransaction(date,ID));
        t.addMovements(movementBaseDeserialize(jo.get("Movements"),context,t));
        return t;
    }

    private Set<Movement> movementBaseDeserialize(JsonElement json, JsonDeserializationContext context,Transaction t) {
        Set<Movement> movements = new TreeSet<>();
        for (JsonElement je : json.getAsJsonArray())
            movements.add(new MovementBaseDeserializer(this.ledger,t).deserialize(je, Movement.class, context));
        return movements;
    }
}
