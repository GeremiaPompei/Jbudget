package it.unicam.cs.pa.jbudget105333;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LedgerBaseAdapter implements JsonSerializer<LedgerBase>, JsonDeserializer<LedgerBase> {

    @Override
    public JsonElement serialize(LedgerBase jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Accounts", new AccountBaseAdapter().serialize(jsonElement.getAccounts().iterator().next()
                ,AccountBase.class,jsonSerializationContext));
        /*jsonObject.add("Transactions", jsonSerializationContext.serialize(TransactionBase[].class));
        jsonObject.add("Tags", jsonSerializationContext.serialize(TagBase[].class));
        jsonObject.add("IdGenerator", jsonSerializationContext.serialize(IDGeneratorBase.class));*/
        return jsonObject;
    }

    public LedgerBase deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        LedgerBase lb = new LedgerBase();
        lb.addAccount(new AccountBaseAdapter().deserialize(jo.get("Accounts"),AccountBase.class,jsonDeserializationContext));
        return lb;
    }


}

