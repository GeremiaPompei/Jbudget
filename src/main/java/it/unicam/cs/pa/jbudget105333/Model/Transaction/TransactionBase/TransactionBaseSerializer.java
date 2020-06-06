package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Set;

public class TransactionBaseSerializer implements JsonSerializer<Transaction> {

    @Override
    public JsonElement serialize(Transaction src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Date", context.serialize(src.getDate(), LocalDateTime.class));
        jo.add("TotalAmounts", context.serialize(src.getTotalAmount()));
        jo.add("Movements", movementBaseSerializer(src.getMovements(),context));
        return jo;
    }


    private JsonElement movementBaseSerializer(Set<Movement> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        for(Movement m : src)
            ja.add(new MovementBaseSerializer().serialize(m,Movement.class,context));
        return ja;
    }
}
