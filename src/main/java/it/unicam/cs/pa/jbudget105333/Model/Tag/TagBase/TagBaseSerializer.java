package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;

public class TagBaseSerializer implements JsonSerializer<Tag> {

    @Override
    public JsonElement serialize(Tag src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Name", context.serialize(src.getName()));
        jo.add("Description", context.serialize(src.getDescription()));
        return jo;
    }
}
