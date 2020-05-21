package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class MovementBaseScanner implements Scanner<MovementBase>{

    private final Transaction transaction;
    private final Ledger ledger;

    public MovementBaseScanner(Transaction transaction,Ledger ledger) {
        this.transaction = transaction;
        this.ledger = ledger;
    }

    @Override
    public MovementBase scanOf(String string) {
        StringTokenizer st = new StringTokenizer(string,",");
        MovementBase movementBase = null;
        try {
            movementBase = new MovementBase(
                    MovementType.valueOf(st.nextToken().trim().toUpperCase())
                    , Double.parseDouble(st.nextToken().trim())
                    , this.transaction
                    , parseAccount(st.nextToken().trim())
                    , parseTag(st.nextToken().trim())
                    , st.nextToken().trim()
                    , Integer.parseInt(st.nextToken().trim()));
        }catch (Exception e){
            movementBase = null;
        }
        return movementBase;
    }

    @Override
    public MovementBase scanOf(String string,IDGenerator idGenerator) {
        StringTokenizer st = new StringTokenizer(string,",");
        MovementBase movementBase;
        try {
            movementBase = new MovementBase(
                    MovementType.valueOf(st.nextToken().trim().toUpperCase())
                    , Double.parseDouble(st.nextToken().trim())
                    , this.transaction
                    , parseAccount(st.nextToken().trim())
                    , parseTag(st.nextToken().trim())
                    , st.nextToken().trim()
                    , idGenerator);
        }catch (Exception e){
            movementBase = null;
        }
        return movementBase;
    }

    private Account parseAccount(String string) throws Exception {
        AtomicReference<Account> account = new AtomicReference<>();
        this.ledger.getAccounts().stream()
                .filter(a->a.getID()==Integer.parseInt(string))
                .forEach(a-> account.set(a));
        return account.get();
    }

    private Tag parseTag(String string) throws Exception {
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.ledger.getTags().stream()
                .filter(t->t.getID()==Integer.parseInt(string))
                .forEach(t-> tag.set(t));
        return tag.get();
    }
}
