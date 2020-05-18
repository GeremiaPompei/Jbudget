package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class MovementBaseScanner implements Scanner<MovementBase>{

    private Transaction transaction = null;
    private Ledger ledger = null;

    public MovementBaseScanner(Transaction transaction,Ledger ledger) {
        this.transaction = transaction;
        this.ledger = ledger;
    }

    @Override
    public MovementBase scanOf(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string,",");
        MovementBase movementBase = new MovementBase(
                MovementType.valueOf(stringTokenizer.nextToken().trim().toUpperCase())
                ,Double.parseDouble(stringTokenizer.nextToken().trim())
                ,this.transaction
                ,checkAccount(stringTokenizer.nextToken().trim())
                ,checkTag(stringTokenizer.nextToken().trim())
                ,stringTokenizer.nextToken().trim());
        return movementBase;
    }

    private Account checkAccount(String string){
        AtomicReference<Account> account = new AtomicReference<>();
        this.ledger.getAccounts().stream()
                .filter(a->a.getName().equalsIgnoreCase(string))
                .forEach(a-> account.set(a));
        return account.get();
    }

    private Tag checkTag(String string){
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.ledger.getTags().stream()
                .filter(t->t.getID()==Integer.parseInt(string))
                .forEach(t-> tag.set(t));
        return tag.get();
    }
}
