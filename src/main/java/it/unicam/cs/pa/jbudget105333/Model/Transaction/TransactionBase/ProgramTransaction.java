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
     * Costruttore della Transazione Programmata nel futuro.
     * @param date Data della Transazione Programmata nel futuro.
     * @param ID ID della Transazione Programmata nel futuro.
     */
    public ProgramTransaction(LocalDateTime date, int ID) {
        super(date, ID);
        this.logger.finest("ProgramTransaction created.");
    }
}
