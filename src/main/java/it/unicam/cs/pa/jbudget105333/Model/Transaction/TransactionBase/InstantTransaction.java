package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su una Transazione Istantanea.
 */
public class InstantTransaction extends TransactionBase {

    /**
     * Variabile utile alla gestione del log della InstantTransaction.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Costruttore della Transazione Istantanea.
     * @param description Descrizione della transazione istantanea.
     * @param ID ID della Transazione Istantanea.
     */
    public InstantTransaction(String description, int ID) {
        super(description, ID);
        this.logger.finest("InstantTransaction created.");
    }

}
