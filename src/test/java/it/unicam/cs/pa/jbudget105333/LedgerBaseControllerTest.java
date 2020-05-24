package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class LedgerBaseControllerTest {

    private LedgerController ledgerC;
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

    private BudgetReportController brcontroller;

    @BeforeEach
    void saveContext(){
        LedgerController lcontroller = new LedgerBaseController();
        BudgetController bcontroller = new BudgetBaseController(lcontroller);
        brcontroller = new BudgetReportBaseController(lcontroller,bcontroller);
    }

    @AfterEach
    void restoreContext(){
        try {
            brcontroller.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void createLedgerBaseController(){
        try {
            new ObjectOutputStream(new FileOutputStream("src/file/Ledger.txt"));
            new ObjectOutputStream(new FileOutputStream("src/file/Budget.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ledgerC = new LedgerBaseController();
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
    void getLedger() {
        assertTrue(this.ledgerC.getLedger() instanceof Ledger);
        assertFalse(this.ledgerC.getLedger() instanceof LedgerController);
    }

    @Test
    void scheduleTransactionsDate() {
        Set<Movement> movements1 = new TreeSet<>();
        movements1.add(this.debito1);
        movements1.add(this.debito2);
        Set<Movement> movements2 = new TreeSet<>();
        movements2.add(this.credito1);
        this.ledgerC.addTransaction(this.transaction1,movements1);
        this.ledgerC.addTransaction(this.transaction2,movements2);
        LocalDateTime start = LocalDateTime.of(2020,8,8,00,00,00);
        LocalDateTime stop = LocalDateTime.of(2020,10,10,00,00,00);
        assertTrue(this.ledgerC.scheduleTransactionsDate(start,stop).contains(this.transaction2));
        assertFalse(this.ledgerC.scheduleTransactionsDate(start,stop).contains(this.transaction1));
    }

    @Test
    void scheduleTransactionsTag() {
        Set<Movement> movements1 = new TreeSet<>();
        movements1.add(this.debito1);
        movements1.add(this.debito2);
        Set<Movement> movements2 = new TreeSet<>();
        movements2.add(this.credito1);
        this.ledgerC.addTransaction(this.transaction1, movements1);
        this.ledgerC.addTransaction(this.transaction2, movements2);
        this.ledgerC.addTag(this.sport);
        this.ledgerC.addTag(this.benzina);
        assertTrue(this.ledgerC.scheduleTransactionsTag(this.benzina.getID()).contains(this.transaction1));
        assertFalse(this.ledgerC.scheduleTransactionsTag(this.benzina.getID()).contains(this.transaction2));
    }

    @Test
    void getTransactions() {
        Set<Movement> movements1 = new TreeSet<>();
        movements1.add(this.debito1);
        movements1.add(this.debito2);
        Set<Movement> movements2 = new TreeSet<>();
        movements2.add(this.credito1);
        this.ledgerC.addTransaction(this.transaction1,movements1);
        this.ledgerC.addTransaction(this.transaction2,movements2);
        assertTrue(this.ledgerC.getTransactions().contains(this.transaction2));
        assertTrue(this.ledgerC.getTransactions().contains(this.transaction1));
    }

    @Test
    void addTransaction() {
        Set<Movement> movements1 = new TreeSet<>();
        movements1.add(this.debito1);
        movements1.add(this.debito2);
        Set<Movement> movements2 = new TreeSet<>();
        movements2.add(this.credito1);
        assertTrue(this.ledgerC.getTransactions().isEmpty());
        this.ledgerC.addTransaction(this.transaction1,movements1);
        this.ledgerC.addTransaction(this.transaction2,movements2);
        assertFalse(this.ledgerC.getTransactions().isEmpty());
        assertTrue(this.ledgerC.getTransactions().contains(this.transaction2));
        assertTrue(this.ledgerC.getTransactions().contains(this.transaction1));
    }

    @Test
    void getAccounts() {
        assertTrue(this.ledgerC.getAccounts().isEmpty());
        this.ledgerC.addAccount(this.fondoCassa);
        this.ledgerC.addAccount(this.prepagata);
        assertFalse(this.ledgerC.getAccounts().isEmpty());
        assertTrue(this.ledgerC.getAccounts().contains(this.fondoCassa));
        assertTrue(this.ledgerC.getAccounts().contains(this.prepagata));
    }

    @Test
    void addAccount() {
        assertTrue(this.ledgerC.getAccounts().isEmpty());
        assertFalse(this.ledgerC.getAccounts().contains(this.fondoCassa));
        this.ledgerC.addAccount(this.fondoCassa);
        assertFalse(this.ledgerC.getAccounts().isEmpty());
        assertTrue(this.ledgerC.getAccounts().contains(this.fondoCassa));
        assertFalse(this.ledgerC.getAccounts().contains(this.prepagata));
        this.ledgerC.addAccount(this.prepagata);
        assertTrue(this.ledgerC.getAccounts().contains(this.prepagata));
    }

    @Test
    void getAccount() {
        assertNotEquals(this.ledgerC.getAccount(this.fondoCassa.getID()),this.fondoCassa);
        this.ledgerC.addAccount(this.fondoCassa);
        assertEquals(this.ledgerC.getAccount(this.fondoCassa.getID()),this.fondoCassa);
    }

    @Test
    void getTags() {
        assertTrue(this.ledgerC.getTags().isEmpty());
        this.ledgerC.addTag(this.sport);
        this.ledgerC.addTag(this.benzina);
        assertFalse(this.ledgerC.getTags().isEmpty());
        assertTrue(this.ledgerC.getTags().contains(this.sport));
        assertTrue(this.ledgerC.getTags().contains(this.benzina));
    }

    @Test
    void addTag() {
        assertTrue(this.ledgerC.getTags().isEmpty());
        assertFalse(this.ledgerC.getTags().contains(this.sport));
        this.ledgerC.addTag(this.sport);
        assertTrue(this.ledgerC.getTags().contains(this.sport));
        assertFalse(this.ledgerC.getTags().contains(this.benzina));
        this.ledgerC.addTag(this.benzina);
        assertFalse(this.ledgerC.getTags().isEmpty());
        assertTrue(this.ledgerC.getTags().contains(this.benzina));
    }

    @Test
    void getTag() {
        assertNotEquals(this.ledgerC.getTag(this.sport.getID()),this.sport);
        this.ledgerC.addTag(this.sport);
        assertEquals(this.ledgerC.getTag(this.sport.getID()),this.sport);
    }

    @Test
    void update() {
        //Aggiungo una serie di transazioni al ledgerC e controllo il bilancio prima e dopo update
        Account account1 = new AccountBase("Cassa","Personale",78
                ,AccountType.ASSETS,idGenerator);
        Transaction transaction3 = new InstantTransaction(idGenerator);
        Movement movement1 = new MovementBase(MovementType.CREDITS,98,transaction3
                ,account1,this.sport,"movement3",this.idGenerator);
        Set<Movement> movements1 = new TreeSet<>();
        movements1.add(movement1);
        this.ledgerC.addAccount(account1);
        this.ledgerC.addTag(this.sport);
        this.ledgerC.addTransaction(transaction3,movements1);
        assertEquals(this.ledgerC.getAccount(account1.getID()).getBalance(),account1.getOpeningBalance());
        assertNotEquals(this.ledgerC.getAccount(account1.getID()).getBalance(),account1.getOpeningBalance()
                +movement1.getAmount());
        this.ledgerC.update();
        assertEquals(this.ledgerC.getAccount(account1.getID()).getBalance(),account1.getOpeningBalance()
                +movement1.getAmount());
    }

    @Test
    void getIDGenerator() {
        assertTrue(this.idGenerator instanceof IDGenerator);
        assertFalse(this.idGenerator instanceof LedgerBaseController);
    }

    @Test
    void save() {
        /*Salvo il ledger del ledgerC e lo leggo salvandolo nella variabile ledger dopodichè
        confronto i parametri sia del tag che dell'account aggiunto in precedenza
         */
        try {
            this.ledgerC.addTag(this.sport);
            this.ledgerC.addAccount(this.fondoCassa);
            this.ledgerC.save();
            Reader<Ledger> reader1 = new LedgerReader("src/file/Ledger.txt");
            Ledger ledger = reader1.read();
            reader1.close();
            assertEquals(this.ledgerC.getTag(sport.getID()).getName(),sport.getName());
            assertEquals(this.ledgerC.getTag(sport.getID()).getID(),sport.getID());
            assertEquals(this.ledgerC.getAccount(this.fondoCassa.getID()).getName(),
                    ledger.getAccounts().iterator().next().getName());
            assertEquals(this.ledgerC.getAccount(this.fondoCassa.getID()).getBalance(),
                    ledger.getAccounts().iterator().next().getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}