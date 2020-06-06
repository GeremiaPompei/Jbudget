package it.unicam.cs.pa.jbudget105333.TransactionTest;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase;
import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementBase;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import it.unicam.cs.pa.jbudget105333.Transaction.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionBaseTest {

    private IDGenerator idGenerator;
    private Transaction transaction;
    private Transaction transaction2;
    private Account fondoCassa;
    private Tag sport;
    private Tag benzina;
    private Movement debito1;
    private Movement debito2;

    @BeforeEach
    void createTransactionBase(){
        this.idGenerator = new IDGeneratorBase();
        this.transaction = new InstantTransaction(idGenerator);
        this.transaction2 = new InstantTransaction(idGenerator);
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator);
        this.sport = new TagBase("Sport","tennis",idGenerator);
        this.benzina = new TagBase("Viaggio","macchina",idGenerator);
        this.debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        this.debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento",idGenerator);

    }

    @Test
    void addMovement() {
        assertFalse(transaction2.getMovements().contains(debito1));
        assertFalse(transaction2.getMovements().contains(debito2));
        transaction2.addMovement(debito1);
        transaction2.addMovement(debito2);
        assertTrue(transaction2.getMovements().contains(debito1));
        assertTrue(transaction2.getMovements().contains(debito2));
    }

    @Test
    void getMovements() {
        transaction2.addMovement(debito1);
        transaction2.addMovement(debito2);
        assertTrue(transaction2.getMovements().contains(debito1));
        assertTrue(transaction2.getMovements().contains(debito2));
    }

    @Test
    void getTags() {
        assertTrue(transaction.getTags().contains(this.sport));
        assertTrue(transaction.getTags().contains(this.benzina));
    }

    @Test
    void getDate() {
        assertTrue(transaction.getDate().equals(this.debito1.getDate()));
        assertTrue(transaction.getDate().isBefore(LocalDateTime.now()));
    }

    @Test
    void getTotalAmount() {
        assertNotEquals(this.transaction.getTotalAmount(),this.debito1.getAmount()
                +this.debito2.getAmount());
        assertNotEquals(this.transaction.getTotalAmount(),-this.debito1.getAmount());
        assertNotEquals(this.transaction.getTotalAmount(),-this.debito2.getAmount());
        assertEquals(this.transaction.getTotalAmount(),-this.debito1.getAmount()
                -this.debito2.getAmount());
    }

    @Test
    void getID() {
        assertNotEquals(this.transaction.getID(),this.debito1.getID());
        assertNotEquals(this.transaction.getID(),this.debito2.getID());
        assertEquals(this.transaction.getID(),1);
    }

    @Test
    void compareTo() {
        assertTrue(this.transaction.compareTo(transaction2)<0);
        assertTrue(transaction2.compareTo(transaction2)==0);
        assertTrue(this.transaction.compareTo(transaction)==0);
    }
}