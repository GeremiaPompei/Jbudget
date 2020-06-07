package it.unicam.cs.pa.jbudget105333.Model.Ledger;

import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBase;

/**
 * Interfaccia responsabile della creazione un Ledger.
 */
public interface LedgerManager {

    /**
     * Metodo responsabile della generazione un Ledger.
     * @return Ledger generato.
     */
    static Ledger generateLedger(){
       return new LedgerBase();
    }
}
