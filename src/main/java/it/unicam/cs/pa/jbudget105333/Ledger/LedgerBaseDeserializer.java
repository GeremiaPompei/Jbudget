package it.unicam.cs.pa.jbudget105333.Ledger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase;
import it.unicam.cs.pa.jbudget105333.Account.AccountBaseDeserialize;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBaseDeserialize;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
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
        this.ledger.setTagAmount(context.deserialize(json.getAsJsonObject().get("TagAmounts"), HashMap.class));
        this.ledger.addTags(Set.copyOf(Arrays.asList(context.deserialize(json.getAsJsonObject().get("Tags"), TagBase[].class))));
        this.ledger.addAccounts(accountBaseDeserialize(json.getAsJsonObject().get("Accounts"),context));
        this.ledger.addTransactions(transactionBaseDeserialize(json.getAsJsonObject().get("Transactions"),context));
        return this.ledger;
    }

    private Set<Account> accountBaseDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Account> accounts = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            accounts.add(new AccountBaseDeserialize().deserialize(je, AccountBase.class,context));
        return accounts;
    }

    private Set<Transaction> transactionBaseDeserialize(JsonElement json, JsonDeserializationContext context){
        Set<Transaction> transactions = new TreeSet<>();
        for(JsonElement je : json.getAsJsonArray())
            transactions.add(new TransactionBaseDeserialize(this.ledger).deserialize(je,Transaction.class,context));
        return transactions;
    }

}
