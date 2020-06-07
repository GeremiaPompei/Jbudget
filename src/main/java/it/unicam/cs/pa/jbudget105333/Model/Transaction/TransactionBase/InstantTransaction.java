package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import it.unicam.cs.pa.jbudget105333.JBLogger;

import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su una Transazione Istantanea.
 */
public class InstantTransaction extends TransactionBase {

    /**
     * Variabile utile alla gestione del log della InstantTransaction.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Costruttore della Transazione Istantanea.
     * @param ID ID della Transazione Istantanea.
     */
    public InstantTransaction(int ID) {
        super(ID);
        this.logger.finest("InstantTransaction created.");
    }

}
