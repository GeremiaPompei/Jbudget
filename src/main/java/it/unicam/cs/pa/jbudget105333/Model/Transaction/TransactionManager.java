package it.unicam.cs.pa.jbudget105333.Model.Transaction;

import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.ProgramTransaction;

import java.time.LocalDateTime;

/**
 * Interfaccia responsabile della creazione di una Transazione.
 */
public interface TransactionManager {

    /**
     * Metodo responsabile della generazione di una Transazione.
     * @param description Descrizione dela transazione.
     * @param ID ID della transazione.
     * @return Transazione generata.
     */
    static Transaction generateTransaction(String description, int ID){
        return new InstantTransaction(description, ID);
    }

    /**
     * Metodo responsabile della generazione di una Transazione con una certa data.
     * @param date Data della transazione.
     * @param description Descrizione dela transazione.
     * @param ID ID della transazione.
     * @return Transazione generata.
     */
    static Transaction generateTransaction(LocalDateTime date, String description, int ID){
        return new ProgramTransaction(date, description, ID);
    }
}
