package it.unicam.cs.pa.jbudget105333.TransactionTest;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Transaction.ProgramTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.ProgramTransaction.ProgramTransactionScanner;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProgramTransactionScannerTest {

    @Test
    void scanOf() {
        IDGenerator idGenerator = new IDGeneratorBase();
        String programTransactionS = "2020-09-09";
        Scanner<ProgramTransaction> scannerT = new ProgramTransactionScanner();
        LocalDateTime dateTime = LocalDateTime.of(2020,9,9,0,0,0);
        ProgramTransaction programTransaction = new ProgramTransaction(dateTime,idGenerator);
        assertEquals(scannerT.scanOf(programTransactionS,idGenerator).getDate(),programTransaction.getDate());

    }
}