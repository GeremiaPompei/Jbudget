package it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.InstantTransaction;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.TransactionBase;

public class InstantTransaction extends TransactionBase {

    /*Il costruttore prende solo l'idGenerator perchè la data viene fissata direttamente quella
    del momento
     */
    public InstantTransaction(IDGenerator idGenerator) {
        super(idGenerator);
    }

}
