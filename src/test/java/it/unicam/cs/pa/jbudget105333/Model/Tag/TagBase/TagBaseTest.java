package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagBaseTest {

    private Tag sport;
    private Tag macchina;
    private Transaction transaction;
    private Account fondoCassa;
    private AccountBase prepagata;
    private Movement credito1;

    @BeforeEach
    void createTagBase(){
        IDGenerator idGenerator = new IDGeneratorBase();
        this.sport = new TagBase("Sport","Tennis",idGenerator.generate());
        this.macchina = new TagBase("Macchina","Benzina",idGenerator.generate());
        this.transaction = new InstantTransaction(idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        this.credito1 = new MovementBase(MovementType.CREDITS,88,this.transaction
                , this.prepagata,macchina,"movimento credito aggiornando",idGenerator.generate());
    }

    @Test
    void getName() {
        assertTrue(this.sport.getName() instanceof String);
        assertEquals(this.sport.getName(),"Sport");
        assertEquals(this.macchina.getName(),"Macchina");
    }

    @Test
    void getDescription() {
        assertTrue(this.sport.getDescription() instanceof String);
        assertEquals(this.sport.getDescription(),"Tennis");
        assertEquals(this.macchina.getDescription(),"Benzina");
    }

    @Test
    void getID() {
        assertEquals(this.sport.getID(),1);
        assertNotEquals(this.sport.getID(),this.macchina.getID());
    }

    @Test
    void getMovements(){
        assertFalse(this.sport.getMovements().contains(this.credito1));
        this.sport.addMovement(this.credito1);
        assertTrue(this.sport.getMovements().contains(this.credito1));
    }

    @Test
    void totalAmount(){
        this.sport.addMovement(this.credito1);
        assertEquals(this.sport.totalAmount(),this.credito1.getAmount());
    }

    @Test
    void addMovement(){
        assertFalse(this.sport.getMovements().contains(this.credito1));
        this.sport.addMovement(this.credito1);
        assertTrue(this.sport.getMovements().contains(this.credito1));
    }

    @Test
    void removeMovement(){
        this.sport.addMovement(this.credito1);
        assertTrue(this.sport.getMovements().contains(this.credito1));
        this.sport.removeMovement(this.credito1);
        assertFalse(this.sport.getMovements().contains(this.credito1));
    }

    @Test
    void compareTo() {
        assertTrue(this.sport.compareTo(this.macchina)>0);
        assertTrue(this.sport.compareTo(this.sport)==0);
    }
}