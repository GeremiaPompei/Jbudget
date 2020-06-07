package it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.TransactionBaseSerializer;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Classe responsabile della serializzazione di un LedgerBase.
 */
public class LedgerBaseSerializer implements JsonSerializer<Ledger> {

    /**
     * Variabile utile alla gestione del log di un LedgerBaseSerializer.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Metodo responsabile della serializzazione di un LedgerBase.
     * @param src Ledger da serializzare.
     * @param typeOfSrc
     * @param context
     * @return JsonElement serializzato.
     */
    @Override
    public JsonElement serialize(Ledger src, Type typeOfSrc, JsonSerializationContext context) {
        this.logger.info("Start serializzation.");
        JsonObject jo = new JsonObject();
        jo.add("IDGenerator",context.serialize(src.getIDGenerator().generate()));
        jo.add("Tags",tagsSerializer(src.getTags(), context));
        jo.add("Accounts", accountsSerializer(src.getAccounts(),context));
        jo.add("Transactions", transactionsSerializer(src.getTransactions(),context));
        this.logger.info("Stop serializzation.");
        return jo;
    }

    /**
     * Metodo responsabile della serializzazione di una serie di Tag.
     * @param src Tags da serializzare.
     * @param context
     * @return JsonElement serializzato.
     */
    private JsonElement tagsSerializer(Set<Tag> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        src.parallelStream().forEach(t->ja.add(new TagBaseSerializer().serialize(t,Tag.class,context)));
        return ja;
    }

    /**
     * Metodo responsabile della serializzazione di una serie di Transazioni.
     * @param src Transazioni da serializzare.
     * @param context
     * @return JsonElement serializzato.
     */
    private JsonElement transactionsSerializer(Set<Transaction> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        src.parallelStream().forEach(t->ja.add(new TransactionBaseSerializer().serialize(t,Transaction.class,context)));
        return ja;
    }

    /**
     * Metodo responsabile della serializzazione di una serie di Account.
     * @param src Accounts da serializzare.
     * @param context
     * @return JsonElement serializzato.
     */
    private JsonElement accountsSerializer(Set<Account> src, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        src.parallelStream().forEach(a->ja.add(new AccountBaseSerializer().serialize(a,Account.class,context)));
        return ja;
    }

}
