package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import it.unicam.cs.pa.jbudget105333.JBLogger;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su una Transazione Programmata.
 */
public class ProgramTransaction extends TransactionBase {

    /**
     * Variabile utile alla gestione del log della ProgramTransaction.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Costruttore della Transazione Programmata.
     * @param localDate Data della Transazione Programmata.
     * @param ID ID della Transazione Programmata.
     */
    public ProgramTransaction(LocalDateTime localDate, int ID) {
        super(localDate, ID);
        this.logger.finest("ProgramTransaction created.");
    }
}
