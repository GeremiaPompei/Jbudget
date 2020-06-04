package it.unicam.cs.pa.jbudget105333.AccountTest;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBaseScanner;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase.IDGeneratorBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountBaseScannerTest {

    @Test
    void scanOf() {
        IDGenerator idGenerator = new IDGeneratorBase();
        String stringFondoCassa = "FondoCassa,personale,400,ASSETS";
        assertTrue(stringFondoCassa instanceof String);
        AccountBaseScanner accountBaseScanner = new AccountBaseScanner();
        assertTrue(accountBaseScanner.scanOf(stringFondoCassa,idGenerator) instanceof Account);
        assertTrue(accountBaseScanner.scanOf(stringFondoCassa,idGenerator) instanceof AccountBase);
        assertEquals(accountBaseScanner.scanOf(stringFondoCassa,idGenerator).getName()
                ,stringFondoCassa.substring(0,stringFondoCassa.indexOf(',')));
    }
}