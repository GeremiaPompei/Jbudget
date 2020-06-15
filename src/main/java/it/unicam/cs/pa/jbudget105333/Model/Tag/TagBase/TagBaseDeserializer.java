package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un TagBase.
 */
public class TagBaseDeserializer implements JsonDeserializer<Tag> {

    /**
     * Ledger utile alla gestione del log del TagBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della deserializzazione del TagBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT
     * @param context
     * @return TagBase deserializzato.
     * @throws JsonParseException
     */
    @Override
    public Tag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.logger.finer("Start deserialization.");
        JsonObject jo = json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        String name = jo.get("Name").getAsString();
        String description = jo.get("Description").getAsString();
        this.logger.finer("Stop deserialization.");
        return new TagBase(name,description,ID);
    }
}
