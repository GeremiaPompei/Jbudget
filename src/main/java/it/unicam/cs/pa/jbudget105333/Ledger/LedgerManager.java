package it.unicam.cs.pa.jbudget105333.Ledger;

public interface LedgerManager {
    static Ledger generateLedger(){
       return new LedgerBase();
    }
}
