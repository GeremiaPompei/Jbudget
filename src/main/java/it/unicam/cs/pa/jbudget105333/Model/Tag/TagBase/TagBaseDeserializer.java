package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;

public class TagBaseDeserializer implements JsonDeserializer<Tag> {
    @Override
    public Tag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        String name = jo.get("Name").getAsString();
        String description = jo.get("Description").getAsString();
        return new TagBase(name,description,ID);
    }
}
