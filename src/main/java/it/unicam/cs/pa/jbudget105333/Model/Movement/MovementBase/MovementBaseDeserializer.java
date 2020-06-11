package it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un MovementBase.
 */
public class MovementBaseDeserializer implements JsonDeserializer<Movement> {

    /**
     * Ledger utile alla gestione del log del MovementBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Ledger utile alla gestione del log del MovementBaseDeserializer.
     */
    private Ledger ledger;

    /**
     * Transazione utile alla gestione del log del MovementBaseDeserializer.
     */
    private Transaction transaction;

    /**
     * Costruttore del MovementBaseDeserializer.
     * @param ledger Ledger utile alla gestione del log del MovementBaseDeserializer.
     * @param transaction Transazione utile alla gestione del log del MovementBaseDeserializer.
     */
    public MovementBaseDeserializer(Ledger ledger, Transaction transaction) {
        this.ledger = ledger;
        this.transaction = transaction;
    }

    /**
     * Metodo responsabile della deserializzazione deli un MovementBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT
     * @param context
     * @return MovementBase deserializzato.
     * @throws JsonParseException
     */
    @Override
    public Movement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.logger.info("Start deserialization.");
        JsonObject jo =json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        MovementType type = MovementType.valueOf(jo.get("Type").getAsString());
        double amount;
        if(type.equals(MovementType.CREDITS))
            amount = jo.get("Amount").getAsDouble();
        else
            amount = -jo.get("Amount").getAsDouble();
        String description = jo.get("Description").getAsString();
        Tag tag = deserializeTag(jo,context);
        Account account = deserializeAccount(jo,context);
        this.logger.info("Stop deserialization.");
        return new MovementBase(type,amount,this.transaction,account,tag,description,ID);
    }

    /**
     * Metodo che ha la responsabilità di deserializzare un JsonObject in un tag.
     * @param jo JsonObject da deserializzare.
     * @param context
     * @return Tag deserializzato.
     */
    private Tag deserializeTag(JsonObject jo, JsonDeserializationContext context){
        String stringT = "Tag";
        Tag tag = this.ledger.getTag(jo.get(stringT).getAsJsonObject().get("ID").getAsInt());
        if(tag == null)
            tag = new TagBaseDeserializer().deserialize(jo.get(stringT),Tag.class,context);;
        return tag;
    }

    /**
     * Metodo che ha la responsabilità di deserializzare un JsonObject in un account.
     * @param jo JsonObject da deserializzare.
     * @param context
     * @return Account deserializzato.
     */
    private Account deserializeAccount(JsonObject jo, JsonDeserializationContext context){
        String stringA = "Account";
        Account account = this.ledger.getAccount(jo.get(stringA).getAsJsonObject().get("ID").getAsInt());
        if(account == null)
            account = new AccountBaseDeserializer().deserialize(jo.get(stringA),Account.class,context);;
        return account;
    }
}
