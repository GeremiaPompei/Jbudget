package it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.ProgramTransaction;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.TransactionBase;

import java.time.LocalDateTime;

public class ProgramTransaction extends TransactionBase {

    //Il costruttore prende un idGenerator e una data
    public ProgramTransaction(LocalDateTime localDate, IDGenerator idGenerator) {
        super(localDate, idGenerator);
    }
}
