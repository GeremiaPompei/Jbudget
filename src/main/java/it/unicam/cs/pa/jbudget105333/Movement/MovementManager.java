package it.unicam.cs.pa.jbudget105333.Movement;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;

public interface MovementManager {
    static Movement generateMovement(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String description, int ID){
        return new MovementBase(movementType,amount,transaction,account,tag,description,ID);
    }
}
