package it.unicam.cs.pa.jbudget105333.Model.Account;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Utility;

import java.util.Set;

/**
 * Interfaccia che sarà implementata dalle classi che hanno la responsabilità di gestire un conto sul
 * quale possono essere fatti dei movimenti che ne alterano il saldo iniziale in un saldo effettivo.
 * Tali implementazioni permetteranno di accedere al proprio id, nome, descrizione, tipo, bilancio iniziale,
 * bilancio effettivo, ad una serie di movimenti eseguiti su di esso, di aggiungere o rimuovere un movimento
 * e di essere aggiornato.
 */
public interface Account extends Comparable<Account>, Utility {

    /**
     * Metodo responsabile di restituire il nome dell'Account.
     * @return nome dell'Account.
     */
    String getName();

    /**
     * Metodo responsabile di restituire il bilancio iniziale dell'Account.
     * @return bilancio iniziale dell'Account.
     */
    double getOpeningBalance();

    /**
     * Metodo responsabile di restituire il bilancio dell'Account.
     * @return bilancio dell'Account.
     */
    double getBalance();

    /**
     * Metodo responsabile di aggiungere un movimento all'Account.
     * @param movement Movimento da aggiungere all'Account.
     */
    void addMovement(Movement movement);

    /**
     * Metodo responsabile di rimuovere un movimento dall'Account.
     * @param movement Movimento da rimuovere dall'Account.
     */
    void removeMovement(Movement movement);

    /**
     * Metodo responsabile di restituire i movimenti dell'Account.
     * @return Movimenti dell'Account.
     */
    Set<Movement> getMovements();

    /**
     * Metodo responsabile di restituire il tipo dell'Account.
     * @return Tipo dell'Account.
     */
    AccountType getAccountType();

    /**
     * Metodo responsabile di aggiornare il bilancio dell'Account.
     */
    void update();
}
