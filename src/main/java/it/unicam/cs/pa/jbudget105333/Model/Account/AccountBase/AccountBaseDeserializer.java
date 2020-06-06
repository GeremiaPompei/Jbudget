package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;

import java.lang.reflect.Type;

public class AccountBaseDeserializer implements JsonDeserializer<Account>{

    @Override
    public Account deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        String name = jo.get("Name").getAsString();
        String description = jo.get("Description").getAsString();
        AccountType type = AccountType.valueOf(jo.get("Type").getAsString());
        double openingBalance;
        if(type== AccountType.ASSETS)
            openingBalance = jo.get("OpeningBalance").getAsDouble();
        else
            openingBalance = -jo.get("OpeningBalance").getAsDouble();
        return new AccountBase(name,description,openingBalance,type,ID);
    }
}
