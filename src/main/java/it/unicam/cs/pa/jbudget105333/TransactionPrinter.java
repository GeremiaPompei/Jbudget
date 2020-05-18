package it.unicam.cs.pa.jbudget105333;

import java.util.concurrent.atomic.AtomicReference;

public class TransactionPrinter<T extends Transaction> implements Printer<T>{
    @Override
    public String stringOf(T transaction) {
        MovementBasePrinter mbp = new MovementBasePrinter();
        AtomicReference<String> ar = new AtomicReference<>();
                ar.set(transaction.getDate()+"");
        transaction.getMovements().stream()
                .forEach(m->ar.set(ar.get()+"\n"+mbp.stringOf(m)));
        return ar.get();
    }
}
