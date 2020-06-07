package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile di deserializzare un AccountBase.
 */
public class AccountBaseDeserializer implements JsonDeserializer<Account>{

    /**
     * Variabile utile alla gestione del log dell'App.
     */
    private Logger logger = new JBLogger(this.getClass()).getLogger();

    /**
     * Metodo responsabile della deserializzazione dell'AccountBase.
     * @param json JsonElement da deserializzare
     * @param typeOfT
     * @param context
     * @return AccountBase deserializzato.
     * @throws JsonParseException
     */
    @Override
    public Account deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        logger.info("Start deserialization.");
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
        try {
            logger.info("Stop deserialization.");
            return new AccountBase(name,description,openingBalance,type,ID);
        } catch (IllegalAccessException e) {
            logger.info("Error in deserialization.");
            return null;
        }
    }
}
