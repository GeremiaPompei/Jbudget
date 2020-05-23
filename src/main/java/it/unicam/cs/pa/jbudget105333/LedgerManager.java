package it.unicam.cs.pa.jbudget105333;

public interface LedgerManager {
    static Ledger generateLedger(){
       Ledger ledger;
       String path = "src/file/Ledger.txt";
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
