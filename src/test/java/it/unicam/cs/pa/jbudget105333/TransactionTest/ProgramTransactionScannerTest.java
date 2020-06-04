package it.unicam.cs.pa.jbudget105333.TransactionTest;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Scanner;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.ProgramTransaction.ProgramTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.ProgramTransaction.ProgramTransactionScanner;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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