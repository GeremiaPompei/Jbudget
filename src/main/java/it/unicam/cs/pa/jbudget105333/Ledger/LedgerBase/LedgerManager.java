package it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase;

import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerReader;
import it.unicam.cs.pa.jbudget105333.Reader;

public interface LedgerManager {
    static Ledger generateLedger(){
       Ledger ledger;
       String path = "src/file/Ledger";
       try{
           Reader<Ledger> reader = new LedgerReader(path);
           ledger = reader.read();
           reader.close();
       } catch (Exception e) {
           ledger = new LedgerBase();
       }
       return ledger;
    }
}
