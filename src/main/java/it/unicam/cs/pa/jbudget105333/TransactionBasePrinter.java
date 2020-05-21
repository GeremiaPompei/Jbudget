package it.unicam.cs.pa.jbudget105333;

import java.util.concurrent.atomic.AtomicReference;

public class TransactionBasePrinter implements Printer<Transaction>{

    private final Printer<Movement> movement;

    public TransactionBasePrinter(Printer<Movement> movement) {
        this.movement = movement;
    }

    @Override
    public String stringOf(Transaction transaction) {
        AtomicReference<String> ar = new AtomicReference<>();
        ar.set("\n"+transaction.getDate().toLocalDate().toString()+" , [ID:"+transaction.getID()
                +"] , TotalAmount: "+transaction.getTotalAmount());
        transaction.getMovements().stream()
                        .forEach(m->ar.set(ar.get()+this.movement.stringOf(m)));
        return ar.get();
    }
}
