package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un TagBase.
 */
public class TagBaseSerializer implements JsonSerializer<Tag> {

    /**
     * Variabile utile alla gestione del log dell'TagBaseSerializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della serializzazione dell'TagBase.
     * @param src Tag da serializzare.
     * @param typeOfSrc Tipo di src.
     * @param context Contesto della serializzazione.
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(Tag src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.finer("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("ID", context.serialize(src.getID()));
        jo.add("Name", context.serialize(src.getName()));
        jo.add("Description", context.serialize(src.getDescription()));
        this.logger.finer("Stop serializzation.");
        return jo;
    }
}
