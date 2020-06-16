package it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBaseSerializer;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un MovementBase.
 */
public class MovementBaseSerializer implements JsonSerializer<Movement> {

    /**
     * Variabile utile alla gestione del log del MovementBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione di un MovementBase.
     * @param src Movement da serializzare.
     * @param typeOfSrc Tipo di src.
     * @param context Contesto di serializzazione.
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(Movement src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.finer("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Type",context.serialize(src.getType(), MovementType.class));
        jo.add("Amount", context.serialize(src.getAmount()));
        jo.add("Account", new AccountBaseSerializer().serialize(src.getAccount(), Account.class,context));
        jo.add("Tag", new TagBaseSerializer().serialize(src.getTag(), Tag.class,context));
        jo.add("Description", context.serialize(src.getDescription()));
        this.logger.finer("Stop serializzation.");
        return jo;
    }
}
