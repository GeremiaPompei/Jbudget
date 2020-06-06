package it.unicam.cs.pa.jbudget105333.Model.Account;

import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;

public interface AccountManager {
    static Account generateAccount(String name,String description,double openingBalance,AccountType type, int ID){
        return new AccountBase(name,description,openingBalance,type,ID);
    }
}
