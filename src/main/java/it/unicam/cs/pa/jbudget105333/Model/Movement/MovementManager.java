package it.unicam.cs.pa.jbudget105333.Model.Movement;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

/**
 * Interfaccia responsabile della creazione un Movimento.
 */
public interface MovementManager {

    /**
     * Metodo responsabile della generazione un Movimento.
     * @param movementType Tipo del Movimento.
     * @param amount Saldo del Movimento.
     * @param transaction Transazione del Movimento.
     * @param account Account del Movimento.
     * @param tag Tag del Movimento.
     * @param description Descrizione del Movimento.
     * @param ID ID del Movimento.
     * @return Movimento generato.
     */
    static Movement generateMovement(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String description, int ID){
        return new MovementBase(movementType,amount,transaction,account,tag,description,ID);
    }
}
