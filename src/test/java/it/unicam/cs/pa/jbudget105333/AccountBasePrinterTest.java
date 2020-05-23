package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountBasePrinterTest {

    @Test
    void stringOf() {
        IDGenerator idGenerator = new IDGeneratorBase();
        AccountBasePrinter accountBasePrinter = new AccountBasePrinter();
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Account prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator);
        String fondocassaString = fondoCassa.getName()+"("+ fondoCassa.getAccountType()+")[ID:"
                + fondoCassa.getID()+"]: "+ fondoCassa.getBalance();
        String prepagataString = prepagata.getName()+"("+ prepagata.getAccountType()+")[ID:"
                + prepagata.getID()+"]: "+ prepagata.getBalance();
        assertEquals(accountBasePrinter.stringOf(fondoCassa),fondocassaString);
        assertEquals(accountBasePrinter.stringOf(prepagata),prepagataString);
    }
}