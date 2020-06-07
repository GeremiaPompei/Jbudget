package it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.ProgramTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MovementBaseTest {

    private IDGenerator idGenerator;
    private Transaction transaction1;
    private Transaction transaction2;
    private Account fondoCassa;
    private AccountBase prepagata;
    private Tag sport;
    private Tag benzina;
    private Movement debito1;
    private Movement debito2;
    private Movement credito1;

    @BeforeEach
    void createMovementBase(){
        this.idGenerator = new IDGeneratorBase();
        this.transaction1 = new InstantTransaction(idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        this.sport = new TagBase("Sport","tennis",idGenerator.generate());
        this.benzina = new TagBase("Viaggio","macchina",idGenerator.generate());
        this.debito1 = new MovementBase(MovementType.DEBIT,800,this.transaction1
                , fondoCassa,sport,"movimento",idGenerator.generate());
        this.debito2 = new MovementBase(MovementType.DEBIT,80,this.transaction1
                , fondoCassa,benzina,"movimento",idGenerator.generate());
        this.transaction2 = new ProgramTransaction(LocalDateTime.of(2020,9
                ,9,00,00,00),idGenerator.generate());
        this.credito1 = new MovementBase(MovementType.CREDITS,870,this.transaction2
                , fondoCassa,sport,"movimento",idGenerator.generate());
    }

    @Test
    void getDescription() {
        assertTrue(this.credito1.getDescription() instanceof String);
        assertEquals(this.credito1.getDescription(),"movimento");
    }

    @Test
    void getType() {
        assertEquals(this.credito1.getType(),MovementType.CREDITS);
        assertNotEquals(this.credito1.getType(),MovementType.DEBIT);
        assertTrue(this.credito1.getType() instanceof MovementType);
    }

    @Test
    void getAmount() {
        assertEquals(this.credito1.getAmount(),870);
        assertNotEquals(this.credito1.getAmount(),this.debito2.getAmount());
    }

    @Test
    void getTransaction() {
        assertTrue(this.credito1.getTransaction() instanceof Transaction);
        assertEquals(this.credito1.getTransaction(),this.transaction2);
        assertNotEquals(this.credito1.getTransaction(),this.transaction1);
    }

    @Test
    void getAccount() {
        assertTrue(this.credito1.getAccount() instanceof Account);
        assertEquals(this.credito1.getAccount(),this.fondoCassa);
        assertNotEquals(this.credito1.getAccount(),this.prepagata);
    }

    @Test
    void getDate() {
        assertTrue(this.credito1.getDate() instanceof LocalDateTime);
        assertNotEquals(this.credito1.getDate(),this.transaction1.getDate());
        assertEquals(this.credito1.getDate(),this.transaction2.getDate());
    }

    @Test
    void getTag() {
        assertTrue(this.credito1.getTag() instanceof Tag);
        assertEquals(this.credito1.getTag(),this.sport);
        assertNotEquals(this.credito1.getTag(),this.benzina);
    }

    @Test
    void getID() {
        assertEquals(this.credito1.getID(),9);
        assertNotEquals(this.credito1.getID(),this.debito2.getID());
    }

    @Test
    void compareTo() {
        assertTrue(this.credito1.compareTo(this.debito1)>0);
        assertFalse(this.credito1.compareTo(this.debito2)<0);
        assertTrue(this.credito1.compareTo(this.credito1)==0);
    }
}