package it.unicam.cs.pa.jbudget105333.Model.Account;

import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;

/**
 * Interfaccia responsabile della creazione un Account.
 */
public interface AccountManager {

    /**
     * Metodo responsabile della generazione un Account.
     * @param name Nome dell'Account.
     * @param description Descrizione dell'Account.
     * @param openingBalance Bilancio iniziale dell'Account.
     * @param type Tipo dell'Account.
     * @param ID ID dell'Account.
     * @return Account generato.
     * @throws IllegalAccessException Eccezione dovuta all'aggiunta di una stringa vuota al parametro Nome.
     */
    static Account generateAccount(String name,String description,double openingBalance,AccountType type, int ID)
            throws IllegalAccessException {
        return new AccountBase(name,description,openingBalance,type,ID);
    }
}
