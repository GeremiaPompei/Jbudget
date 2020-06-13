package it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.TransactionBaseDeserializer;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un LedgerBase.
 */
public class LedgerBaseDeserializer implements JsonDeserializer<Ledger> {

    /**
     * Variabile utile alla gestione del log di un LedgerBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Variabile che conterrà il JsonElement da deserializzare.
     */
    private final Ledger ledger;

    /**
     * Costruttore del LedgerBaseDeserializer.
     */
    public LedgerBaseDeserializer() {
        this.ledger = new LedgerBase();
    }

    /**
     * Metodo responsabile della deserializzazione di un LedgerBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT
     * @param context
     * @return LedgerBase deserializzato.
     * @throws JsonParseException
     */
    @Override
    public Ledger deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.logger.info("Start deserialization.");
        this.ledger.setIdGenerator(new IDGeneratorBase(context.deserialize(json.getAsJsonObject().get("IDGenerator")
                ,Integer.class)));
        this.ledger.addTags(tagsDeserialize(json.getAsJsonObject().get("Tags"), context));
        this.ledger.addAccounts(accountsDeserialize(json.getAsJsonObject().get("Accounts"),context));
        this.ledger.addTransactions(transactionsDeserialize(json.getAsJsonObject().get("Transactions"),context));
        this.ledger.update();
        this.logger.info("Stop deserialization.");
        return this.ledger;
    }

    /**
     * Metodo responsabile della deserializzazione di un JsonElemant in una serie di Tag.
     * @param json JsonElement da deserializzare.
     * @param context
     * @return Serie di Tag deserializzati.
     */
    private Set<Tag> tagsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Tag> tags = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            tags.add(new TagBaseDeserializer().deserialize(je,Transaction.class,context));
        return tags;
    }

    /**
     * Metodo responsabile della deserializzazione di un JsonElemant in una serie di Transazioni.
     * @param json JsonElement da deserializzare.
     * @param context
     * @return Serie di Transazioni deserializzati.
     */
    private Set<Transaction> transactionsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Transaction> transactions = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            transactions.add(new TransactionBaseDeserializer(this.ledger).deserialize(je,Transaction.class,context));
        return transactions;
    }

    /**
     * Metodo responsabile della deserializzazione di un JsonElemant in una serie di Account.
     * @param json JsonElement da deserializzare.
     * @param context
     * @return Serie di Accounts deserializzati.
     */
    private Set<Account> accountsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Account> accounts = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            accounts.add(new AccountBaseDeserializer().deserialize(je, AccountBase.class,context));
        return accounts;
    }

}
