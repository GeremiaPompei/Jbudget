package it.unicam.cs.pa.jbudget105333.Model.Movement;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

public interface MovementManager {
    static Movement generateMovement(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String description, int ID){
        return new MovementBase(movementType,amount,transaction,account,tag,description,ID);
    }
}
