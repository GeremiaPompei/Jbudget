package it.unicam.cs.pa.jbudget105333.Transaction;

public class InstantTransaction extends TransactionBase {

    /*Il costruttore prende solo l'idGenerator perchè la data viene fissata direttamente quella
    del momento
     */
    public InstantTransaction(int ID) {
        super(ID);
    }

}
