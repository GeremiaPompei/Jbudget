package it.unicam.cs.pa.jbudget105333;

import java.util.concurrent.atomic.AtomicReference;

public class TransactionBasePrinter implements Printer<Transaction>{

    private final Printer<Movement> movementP;

    public TransactionBasePrinter(Printer<Movement> movementP) {
        this.movementP = movementP;
    }

    //Converte una transazione in stringa
    @Override
    public String stringOf(Transaction transaction) {
        AtomicReference<String> ar = new AtomicReference<>();
        ar.set("\n"+transaction.getDate().toLocalDate().toString()+" , [ID:"+transaction.getID()
                +"] , TotalAmount: "+transaction.getTotalAmount());
        transaction.getMovements().stream()
                        .forEach(m->ar.set(ar.get()+this.movementP.stringOf(m)));
        return ar.get();
    }
}
