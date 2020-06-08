package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

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
        this.transaction = new InstantTransaction(idGenerator.generate());
        this.transaction2 = new InstantTransaction(idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.sport = new TagBase("Sport","tennis",idGenerator.generate());
        this.benzina = new TagBase("Viaggio","macchina",idGenerator.generate());
        this.debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento",idGenerator.generate());
        this.debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento",idGenerator.generate());

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
    void removeMovement() {
        transaction2.addMovement(debito1);
        transaction2.addMovement(debito2);
        assertTrue(transaction2.getMovements().contains(debito1));
        assertTrue(transaction2.getMovements().contains(debito2));
        transaction2.removeMovement(debito1);
        transaction2.removeMovement(debito2);
        assertFalse(transaction2.getMovements().contains(debito1));
        assertFalse(transaction2.getMovements().contains(debito2));
    }

    @Test
    void addMovements() {
        Movement debito3 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento",idGenerator.generate());
        Movement debito4 = new MovementBase(MovementType.DEBIT,34,transaction
                , fondoCassa,benzina,"movimento",idGenerator.generate());
        Set<Movement> movements = new TreeSet<>();
        movements.add(debito3);
        movements.add(debito4);
        Transaction transaction = new InstantTransaction(idGenerator.generate());
        assertFalse(transaction.getMovements().contains(debito3));
        assertFalse(transaction.getMovements().contains(debito4));
        transaction.addMovements(movements);
        assertTrue(transaction.getMovements().contains(debito3));
        assertTrue(transaction.getMovements().contains(debito4));
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
        assertEquals(this.transaction.getTotalAmount(),this.debito1.getAmount()
                +this.debito2.getAmount());
        assertNotEquals(this.transaction.getTotalAmount(),-this.debito1.getAmount());
        assertNotEquals(this.transaction.getTotalAmount(),-this.debito2.getAmount());
        assertEquals(this.transaction.getTotalAmount(),this.debito1.getAmount()
                +this.debito2.getAmount());
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