package it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.TransactionBaseSerializer;

import java.lang.reflect.Type;
import java.util.Set;

public class LedgerBaseSerializer implements JsonSerializer<Ledger> {

    @Override
    public JsonElement serialize(Ledger src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jo = new JsonObject();
        jo.add("IDGenerator",context.serialize(src.getIDGenerator(), IDGeneratorBase.class));
        jo.add("Tags",tagsSerializer(src.getTags(), context));
        jo.add("Accounts", accountsSerializer(src.getAccounts(),context));
        jo.add("Transactions", transactionsSerializer(src.getTransactions(),context));
        return jo;
    }

    private JsonElement tagsSerializer(Set<Tag> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        for(Tag t : src)
            ja.add(new TagBaseSerializer().serialize(t,Tag.class,context));
        return ja;
    }

    private JsonElement transactionsSerializer(Set<Transaction> src, JsonSerializationContext context){
        JsonArray ja = new JsonArray();
        for(Transaction t : src)
            ja.add(new TransactionBaseSerializer().serialize(t,Transaction.class,context));
        return ja;
    }

    private JsonElement accountsSerializer(Set<Account> src, JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (Account a : src) {
            ja.add(new AccountBaseSerializer().serialize(a,Account.class,context));
        }
        return ja;
    }

}
