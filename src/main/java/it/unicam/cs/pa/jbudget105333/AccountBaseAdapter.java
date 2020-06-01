package it.unicam.cs.pa.jbudget105333;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AccountBaseAdapter implements JsonSerializer<Account>, JsonDeserializer<Account> {

    @Override
    public JsonElement serialize(Account src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("name", new JsonPrimitive(src.getName()));
        jsonObject.add("description", new JsonPrimitive(src.getDescription()));
        jsonObject.add("openingBalance", new JsonPrimitive(src.getOpeningBalance()));
        jsonObject.add("type", new JsonPrimitive(src.getAccountType().toString()));
        return jsonObject;
    }

    @Override
    public AccountBase deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonElement balance = jo.get("openingBalance");
        return new AccountBase(jo.get("name").getAsString(),jo.get("description").getAsString()
                ,balance.getAsDouble(),AccountType.valueOf(jo.get("type").getAsString()),new IDGeneratorBase());
    }

}
