package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountBaseTest {
    private IDGenerator idGenerator;
    private AccountBase fondoCassa;
    private AccountBase prepagata;
    private TagBase sport;
    private MovementBase debito1;
    private MovementBase credito1;
    private MovementBase credito2;

    @BeforeEach
    void createAccountBase(){
        this.idGenerator = new IDGeneratorBase();
        this.fondoCassa = new AccountBase("FondoCassa", "personale"
                , 500, AccountType.ASSETS, idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata", "personale"
                , 200, AccountType.LIABILITIES, idGenerator.generate());
        this.sport = new TagBase("Sport", "tennis", idGenerator.generate());
        this.debito1 = new MovementBase(MovementType.DEBIT, 40, new InstantTransaction(
                this.idGenerator.generate()), fondoCassa, sport, "primo movimento debito"
                , idGenerator.generate());
        this.credito1 = new MovementBase(MovementType.CREDITS, 89, new InstantTransaction(
                this.idGenerator.generate()), fondoCassa, sport, "primo movimento credito"
                , idGenerator.generate());
        this.credito2 = new MovementBase(MovementType.CREDITS, 123, new InstantTransaction(
                this.idGenerator.generate()), fondoCassa, sport, "secondo movimento credito"
                , idGenerator.generate());
    }

    @Test
    void AccountBaseTest(){
        assertThrows(IllegalArgumentException.class,()->new AccountBase("","personale"
                ,500, AccountType.ASSETS,idGenerator.generate()));
    }

    @Test
    void getName() {
        assertEquals(this.fondoCassa.getName(),"FondoCassa");
        assertEquals(this.prepagata.getName(),"Prepagata");
    }

    @Test
    void getDescription() {
        assertEquals(this.fondoCassa.getDescription(),"personale");
        assertEquals(this.prepagata.getDescription(),"personale");
    }

    @Test
    void getOpeningBalance() {
        assertEquals(this.fondoCassa.getOpeningBalance(),500);
        assertNotEquals(this.fondoCassa.getOpeningBalance(),-500);
        assertNotEquals(this.fondoCassa.getOpeningBalance(),200);
        assertEquals(this.prepagata.getOpeningBalance(),-200);
        assertNotEquals(this.prepagata.getOpeningBalance(),200);
        assertNotEquals(this.prepagata.getOpeningBalance(),500);
    }

    @Test
    void getBalance() {
        assertEquals(this.fondoCassa.getBalance(),this.fondoCassa.getOpeningBalance());
        this.fondoCassa.update();
        assertEquals(this.fondoCassa.getBalance(),this.fondoCassa.getOpeningBalance()+this.credito1.getAmount()
                +this.credito2.getAmount()+this.debito1.getAmount());
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance());
        assertEquals(this.prepagata.getBalance(),-200);
    }

    @Test
    void addMovement() {
        assertFalse(this.prepagata.getMovements().contains(debito1));
        this.prepagata.addMovement(debito1);
        assertTrue(this.prepagata.getMovements().contains(debito1));
    }

    @Test
    void update() {
        //Creo e aggiungo il primo movimento credito aggiornando
        Movement credito1 = new MovementBase(MovementType.CREDITS,88,new InstantTransaction(
                this.idGenerator.generate()), fondoCassa,sport,"movimento credito aggiornando"
                , idGenerator.generate());
        this.prepagata.addMovement(credito1);
        this.prepagata.update();
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                +credito1.getAmount());
        //Creo e aggiungo il secondo movimento credito non aggiornando
        Movement credito2 = new MovementBase(MovementType.CREDITS,30,new InstantTransaction(
                this.idGenerator.generate()), fondoCassa,sport,"movimento credito non aggiornando"
                ,idGenerator.generate());
        this.prepagata.addMovement(credito2);
        //Senza update
        assertNotEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                +credito1.getAmount()+credito2.getAmount());
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                +credito1.getAmount());
    }

    @Test
    void getMovements() {
        assertTrue(this.fondoCassa.getMovements().contains(this.credito1));
        assertFalse(this.prepagata.getMovements().contains(this.credito2));
        assertTrue(this.fondoCassa.getMovements().contains(this.debito1));
    }

    @Test
    void getAccountType() {
        assertEquals(this.fondoCassa.getAccountType(),AccountType.ASSETS);
        assertNotEquals(this.fondoCassa.getAccountType(),AccountType.LIABILITIES);
        assertEquals(this.prepagata.getAccountType(),AccountType.LIABILITIES);
        assertNotEquals(this.prepagata.getAccountType(),AccountType.ASSETS);
    }

    @Test
    void getID() {
        assertEquals(this.fondoCassa.getID(),1);
        assertNotEquals(this.fondoCassa.getID(),0);
        assertEquals(this.prepagata.getID(),2);
        assertNotEquals(this.prepagata.getID(),1);
    }

    @Test
    void compareTo() {
        Account fondocassa2 = null;
        fondocassa2 = new AccountBase("FondoCassa","personale"
                    ,500, AccountType.ASSETS,idGenerator.generate());
        assertTrue(this.fondoCassa.compareTo(this.prepagata)<0);
        assertTrue(this.fondoCassa.compareTo(fondocassa2)<0);
        assertFalse(this.prepagata.compareTo(fondocassa2)<0);
    }
}