package it.unicam.cs.pa.jbudget105333.Model.Ledger;

import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBase;

public interface LedgerManager {
    static Ledger generateLedger(){
       return new LedgerBase();
    }
}
