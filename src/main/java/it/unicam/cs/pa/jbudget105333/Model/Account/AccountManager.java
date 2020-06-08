package it.unicam.cs.pa.jbudget105333.Model.Account;

import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;

/**
 * Interfaccia responsabile della creazione di un Account.
 */
public interface AccountManager {

    /**
     * Metodo responsabile della generazione di un Account.
     * @param name Nome dell'Account.
     * @param description Descrizione dell'Account.
     * @param openingBalance Bilancio iniziale dell'Account.
     * @param type Tipo dell'Account.
     * @param ID ID dell'Account.
     * @return Account generato.
     */
    static Account generateAccount(String name,String description,double openingBalance,AccountType type, int ID){
        return new AccountBase(name,description,openingBalance,type,ID);
    }
}
