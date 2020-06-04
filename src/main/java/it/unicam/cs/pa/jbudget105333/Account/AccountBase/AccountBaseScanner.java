package it.unicam.cs.pa.jbudget105333.Account.AccountBase;

import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Scanner;

import java.util.StringTokenizer;

public class AccountBaseScanner implements Scanner<AccountBase> {

    //Converte stringa in accountBase
    @Override
    public AccountBase scanOf(String string, IDGenerator idGenerator) {
        StringTokenizer st = new StringTokenizer(string, ",");
        AccountBase account = null;
        try {
            account = new AccountBase(
                    st.nextToken().trim()
                    , st.nextToken().trim()
                    , Double.parseDouble(st.nextToken().trim())
                    , AccountType.valueOf(st.nextToken().trim().toUpperCase())
                    , idGenerator);
        }catch (Exception e){
            account = null;
        }
        return account;
    }
}