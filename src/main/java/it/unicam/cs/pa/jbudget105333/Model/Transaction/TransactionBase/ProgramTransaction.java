package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su una Transazione Programmata.
 */
public class ProgramTransaction extends TransactionBase {

    /**
     * Variabile utile alla gestione del log della ProgramTransaction.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Costruttore della Transazione Programmata nel futuro.
     * @param date Data della Transazione Programmata nel futuro.
     * @param description Descrizione della Transazione Programmata nel futuro.
     * @param ID ID della Transazione Programmata nel futuro.
     */
    public ProgramTransaction(LocalDateTime date, String description, int ID) {
        super(date, description, ID);
        this.logger.finest("ProgramTransaction created.");
    }
}
