package it.unicam.cs.pa.jbudget105333.Movement;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;

import java.lang.reflect.Type;

public class MovementBaseDeserializer implements JsonDeserializer<Movement> {

    private Ledger ledger;
    private Transaction transaction;

    public MovementBaseDeserializer(Ledger ledger, Transaction transaction) {
        this.ledger = ledger;
        this.transaction = transaction;
    }

     @Override
    public Movement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo =json.getAsJsonObject();
        int ID = jo.get("ID").getAsInt();
        MovementType type = MovementType.valueOf(jo.get("Type").getAsString());
        double amount;
        if(type.equals(MovementType.CREDITS))
            amount = jo.get("Amount").getAsDouble();
        else
            amount = -jo.get("Amount").getAsDouble();
        String description = jo.get("Description").getAsString();
        Tag tag = this.ledger.getTag(jo.get("Tag").getAsInt());
        Account account = this.ledger.getAccount(jo.get("Account").getAsInt());
        return new MovementBase(type,amount,this.transaction,account,tag,description,ID);
    }
}
