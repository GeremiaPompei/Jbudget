package it.unicam.cs.pa.jbudget105333.Movement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MovementBaseSerializer implements JsonSerializer<Movement> {

    @Override
    public JsonElement serialize(Movement src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Type",context.serialize(src.getType(),MovementType.class));
        jo.add("Amount", context.serialize(src.getAmount()));
        jo.add("Account", context.serialize(src.getAccount().getID()));
        jo.add("Tag", context.serialize(src.getTag().getID()));
        jo.add("Description", context.serialize(src.getDescription()));
        return jo;
    }

}
