package it.unicam.cs.pa.jbudget105333.Ledger;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBaseSerialize;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBaseSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;

public class LedgerBaseSerializer implements JsonSerializer<Ledger> {

    @Override
    public JsonElement serialize(Ledger src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("IDGenerator",context.serialize(src.getIDGenerator(), IDGeneratorBase.class));
        jo.add("TagAmounts",context.serialize(src.getTagsAmount(), HashMap.class));
        jo.add("Tags",context.serialize(src.getTags().toArray(), TagBase[].class));
        jo.add("Accounts", accountBaseSerializer(src.getAccounts(),context));
        jo.add("Transactions", transactionBaseSerializer(src.getTransactions(),context));
        return jo;
    }

    private JsonElement transactionBaseSerializer(Set<Transaction> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        for(Transaction t : src)
            ja.add(new TransactionBaseSerializer().serialize(t,Transaction.class,context));
        return ja;
    }

    private JsonElement accountBaseSerializer(Set<Account> src, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (Account a : src) {
            ja.add(new AccountBaseSerialize().serialize(a,Account.class,context));
        }
        return ja;
    }

}
