package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class AccountBaseScanner implements Scanner<AccountBase>{

    @Override
    public AccountBase scanOf(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string,",");
        AccountBase account = new AccountBase(
                stringTokenizer.nextToken().trim()
                ,stringTokenizer.nextToken().trim()
                ,Double.parseDouble(stringTokenizer.nextToken().trim())
                ,AccountType.valueOf(stringTokenizer.nextToken().trim().toUpperCase()));
        return account;
    }
}
