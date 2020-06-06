package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import java.time.LocalDateTime;

public class ProgramTransaction extends TransactionBase {

    //Il costruttore prende un idGenerator e una data
    public ProgramTransaction(LocalDateTime localDate, int ID) {
        super(localDate, ID);
    }
}
