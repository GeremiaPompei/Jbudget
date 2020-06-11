package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un AccountBase.
 */
public class AccountBaseSerializer implements JsonSerializer<Account> {

    /**
     * Variabile utile alla gestione del log dell'AccountBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione dell'AccountBase.
     * @param src Account da serializzare.
     * @param typeOfSrc
     * @param context
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(Account src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.info("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Name", context.serialize(src.getName()));
        jo.add("Description", context.serialize(src.getDescription()));
        jo.add("Type", context.serialize(src.getAccountType()));
        jo.add("OpeningBalance", context.serialize(src.getOpeningBalance()));
        this.logger.info("Stop serializzation.");
        return jo;
    }

}
