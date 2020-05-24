package it.unicam.cs.pa.jbudget105333;

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
        this.transaction1 = new InstantTransaction(idGenerator);
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator);
        this.sport = new TagBase("Sport","tennis",idGenerator);
        this.benzina = new TagBase("Viaggio","macchina",idGenerator);
        this.debito1 = new MovementBase(MovementType.DEBIT,800,this.transaction1
                , fondoCassa,sport,"movimento",idGenerator);
        this.debito2 = new MovementBase(MovementType.DEBIT,80,this.transaction1
                , fondoCassa,benzina,"movimento",idGenerator);
        this.transaction2 = new ProgramTransaction(LocalDateTime.of(2020,9
                ,9,00,00,00),idGenerator);
        this.credito1 = new MovementBase(MovementType.CREDITS,870,this.transaction2
                , fondoCassa,sport,"movimento",idGenerator);
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