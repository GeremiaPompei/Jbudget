package it.unicam.cs.pa.jbudget105333;

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
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator);
        this.sport = new TagBase("Sport","tennis",idGenerator);
        this.debito1 = new MovementBase(MovementType.DEBIT,40,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"primo movimento debito",idGenerator);
        this.credito1 = new MovementBase(MovementType.CREDITS,89,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"primo movimento credito",idGenerator);
        this.credito2 = new MovementBase(MovementType.CREDITS,123,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"secondo movimento credito",idGenerator);
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
        //Verifico che il saldo sia uguale al saldo iniziale in positivo se credito e negativo se debito
        assertEquals(this.fondoCassa.getOpeningBalance(),500);
        assertNotEquals(this.fondoCassa.getOpeningBalance(),-500);
        assertEquals(this.prepagata.getOpeningBalance(),-200);
        assertNotEquals(this.prepagata.getOpeningBalance(),200);
        //Creo e aggiungo il primo movimento debito di 80
        Movement debito1 = new MovementBase(MovementType.DEBIT,80,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"movimento debito",idGenerator);
        this.prepagata.addMovement(debito1);
        this.prepagata.update();
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                -debito1.getAmount());
        //Creo e aggiungo il primo movimento credito di 65
        Movement credito1 = new MovementBase(MovementType.CREDITS,65,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"movimento credito",idGenerator);
        this.prepagata.addMovement(credito1);
        this.prepagata.update();
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                -debito1.getAmount()+credito1.getAmount());
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
        Movement credito1 = new MovementBase(MovementType.CREDITS,88,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"movimento credito aggiornando",idGenerator);
        this.prepagata.addMovement(credito1);
        this.prepagata.update();
        assertEquals(this.prepagata.getBalance(),this.prepagata.getOpeningBalance()
                +credito1.getAmount());
        //Creo e aggiungo il secondo movimento credito non aggiornando
        Movement credito2 = new MovementBase(MovementType.CREDITS,30,new InstantTransaction(this.idGenerator)
                , fondoCassa,sport,"movimento credito non aggiornando",idGenerator);
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
        Account fondocassa2 = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        assertTrue(this.fondoCassa.compareTo(this.prepagata)<0);
        assertTrue(this.fondoCassa.compareTo(fondocassa2)<0);
        assertFalse(this.prepagata.compareTo(fondocassa2)<0);
    }
}