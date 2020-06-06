package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;

import java.lang.reflect.Type;

public class AccountBaseSerializer implements JsonSerializer<Account> {

    @Override
    public JsonElement serialize(Account src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Name", context.serialize(src.getName()));
        jo.add("Description", context.serialize(src.getDescription()));
        jo.add("Type", context.serialize(src.getAccountType()));
        jo.add("OpeningBalance", context.serialize(src.getOpeningBalance()));
        return jo;
    }

}
