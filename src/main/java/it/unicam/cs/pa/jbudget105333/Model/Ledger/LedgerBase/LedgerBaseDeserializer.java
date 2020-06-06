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

public class LedgerBaseDeserializer implements JsonDeserializer<Ledger> {

    private final Ledger ledger;

    public LedgerBaseDeserializer() {
        this.ledger = new LedgerBase();
    }

    @Override
    public Ledger deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.ledger.setIdGenerator(context.deserialize(json.getAsJsonObject().get("IDGenerator"), IDGeneratorBase.class));
        this.ledger.addTags(tagsDeserialize(json.getAsJsonObject().get("Tags"), context));
        this.ledger.addAccounts(accountsDeserialize(json.getAsJsonObject().get("Accounts"),context));
        this.ledger.addTransactions(transactionsDeserialize(json.getAsJsonObject().get("Transactions"),context));
        return this.ledger;
    }

    private Set<Tag> tagsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Tag> tags = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            tags.add(new TagBaseDeserializer().deserialize(je,Transaction.class,context));
        return tags;
    }

    private Set<Transaction> transactionsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Transaction> transactions = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            transactions.add(new TransactionBaseDeserializer(this.ledger).deserialize(je,Transaction.class,context));
        return transactions;
    }

    private Set<Account> accountsDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Account> accounts = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            accounts.add(new AccountBaseDeserializer().deserialize(je, AccountBase.class,context));
        return accounts;
    }

}
