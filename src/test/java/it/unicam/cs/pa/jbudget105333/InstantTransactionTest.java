package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantTransactionTest {

    @Test
    void addMovement() {
        IDGenerator idGenerator = new IDGeneratorBase();
        InstantTransaction transaction = new InstantTransaction(idGenerator);
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Tag benzina = new TagBase("Viaggio","macchina",idGenerator);
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,new InstantTransaction(idGenerator)
                , fondoCassa,sport,"movimento",idGenerator);
        Movement debito2 = new MovementBase(MovementType.DEBIT,80,new InstantTransaction(idGenerator)
                , fondoCassa,benzina,"movimento",idGenerator);
        assertFalse(transaction.getMovements().contains(debito1));
        assertFalse(transaction.getMovements().contains(debito2));
        transaction.addMovement(debito1);
        transaction.addMovement(debito2);
        assertTrue(transaction.getMovements().contains(debito1));
        assertTrue(transaction.getMovements().contains(debito2));
    }
}