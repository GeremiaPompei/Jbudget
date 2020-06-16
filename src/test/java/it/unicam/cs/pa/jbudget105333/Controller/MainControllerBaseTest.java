package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetReaderJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetWriterJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.ProgramTransaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerBaseTest {

    private MainController controller;
    private IDGenerator idGenerator;
    private Transaction transaction1;
    private Transaction transaction2;
    private Account fondoCassa;
    private Account prepagata;
    private Tag sport;
    private Tag utenza;
    private Movement debito1;
    private Movement credito1;

    private static final String path = "jbudgetTest.json";

    @AfterAll
    static void restoreContext(){
        new File(path).delete();
    }

    @BeforeEach
    void createMainControllerBase(){
        this.controller = MainControllerManager.generateMainController();
        this.idGenerator =  this.controller.idGenerator();
        this.transaction1 = new ProgramTransaction(LocalDateTime.of(LocalDate.of(2021,2,5)
                , LocalTime.MIN),"transaction 1",idGenerator.generate());
        this.transaction2 = new ProgramTransaction(LocalDateTime.of(LocalDate.of(2020,9,9)
                , LocalTime.MIN),"transaction 2",idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        this.sport = new TagBase("Sport","tennis",idGenerator.generate());
        this.utenza = new TagBase("Utenza","luce",idGenerator.generate());
        this.debito1 = new MovementBase(MovementType.DEBIT,80,this.transaction1
                , fondoCassa,utenza,"movimento",idGenerator.generate());
        this.credito1 = new MovementBase(MovementType.CREDITS,870,this.transaction2
                , fondoCassa,sport,"movimento",idGenerator.generate());
    }

    @Test
    void resetBudgetReport(){
        this.controller.addTag(this.utenza);
        assertTrue(this.controller.getTags().contains(this.utenza));
        this.controller.resetBudgetReport();
        assertFalse(this.controller.getTags().contains(this.utenza));
        this.controller.addAccount(this.fondoCassa);
        assertTrue(this.controller.getAccounts().contains(this.fondoCassa));
        this.controller.resetBudgetReport();
        assertFalse(this.controller.getAccounts().contains(this.fondoCassa));
    }

    @Test
    void getAccounts() {
        this.controller.addAccount(this.fondoCassa);
        this.controller.addAccount(this.prepagata);
        assertTrue(this.controller.getAccounts().contains(this.fondoCassa));
        assertTrue(this.controller.getAccounts().contains(this.prepagata));
        assertFalse(this.controller.getAccounts() instanceof Account);
        assertFalse(this.controller.getAccounts() instanceof Account);
        assertTrue(this.controller.getAccounts() instanceof Set);
        assertTrue(this.controller.getAccounts() instanceof Set);
    }

    @Test
    void getAccount() {
        this.controller.addAccount(this.fondoCassa);
        this.controller.addAccount(this.prepagata);
        assertEquals(this.controller.getAccount(this.fondoCassa.getID()),this.fondoCassa);
        assertEquals(this.controller.getAccount(this.prepagata.getID()),this.prepagata);
        assertTrue(this.controller.getAccount(this.fondoCassa.getID()) instanceof Account);
        assertTrue(this.controller.getAccount(this.prepagata.getID()) instanceof Account);
    }

    @Test
    void addAccount() {
        assertFalse(this.controller.getAccounts().contains(this.fondoCassa));
        assertFalse(this.controller.getAccounts().contains(this.prepagata));
        this.controller.addAccount(this.fondoCassa);
        this.controller.addAccount(this.prepagata);
        assertTrue(this.controller.getAccounts().contains(this.fondoCassa));
        assertTrue(this.controller.getAccounts().contains(this.prepagata));
    }

    @Test
    void removeAccount() {
        this.controller.addAccount(this.fondoCassa);
        this.controller.addAccount(this.prepagata);
        assertTrue(this.controller.getAccounts().contains(this.fondoCassa));
        assertTrue(this.controller.getAccounts().contains(this.prepagata));
        this.controller.removeAccount(this.fondoCassa);
        this.controller.removeAccount(this.prepagata);
        assertFalse(this.controller.getAccounts().contains(this.fondoCassa));
        assertFalse(this.controller.getAccounts().contains(this.prepagata));
    }

    @Test
    void getTags() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        assertTrue(this.controller.getTags().contains(this.sport));
        assertTrue(this.controller.getTags().contains(this.utenza));
        assertFalse(this.controller.getTags() instanceof Tag);
        assertFalse(this.controller.getTags() instanceof Tag);
        assertTrue(this.controller.getTags() instanceof Set);
        assertTrue(this.controller.getTags() instanceof Set);
    }

    @Test
    void getTag() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        assertEquals(this.controller.getTag(this.sport.getID()),this.sport);
        assertEquals(this.controller.getTag(this.utenza.getID()),this.utenza);
        assertTrue(this.controller.getTag(this.sport.getID()) instanceof Tag);
        assertTrue(this.controller.getTag(this.utenza.getID()) instanceof Tag);
    }

    @Test
    void addTag() {
        assertFalse(this.controller.getTags().contains(this.sport));
        assertFalse(this.controller.getTags().contains(this.utenza));
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        assertTrue(this.controller.getTags().contains(this.sport));
        assertTrue(this.controller.getTags().contains(this.utenza));
    }

    @Test
    void removeTag() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        assertTrue(this.controller.getTags().contains(this.sport));
        assertTrue(this.controller.getTags().contains(this.utenza));
        this.controller.removeTag(this.sport);
        this.controller.removeTag(this.utenza);
        assertFalse(this.controller.getTags().contains(this.sport));
        assertFalse(this.controller.getTags().contains(this.utenza));
    }

    @Test
    void getTransactions() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        assertTrue(this.controller.getTransactions().contains(this.transaction1));
        assertTrue(this.controller.getTransactions().contains(this.transaction2));
        assertFalse(this.controller.getTransactions() instanceof Transaction);
        assertFalse(this.controller.getTransactions() instanceof Transaction);
        assertTrue(this.controller.getTransactions() instanceof Set);
        assertTrue(this.controller.getTransactions() instanceof Set);
    }

    @Test
    void getTransaction() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        assertEquals(this.controller.getTransaction(this.transaction1.getID()),this.transaction1);
        assertEquals(this.controller.getTransaction(this.transaction2.getID()),this.transaction2);
        assertTrue(this.controller.getTransaction(this.transaction1.getID()) instanceof Transaction);
        assertTrue(this.controller.getTransaction(this.transaction2.getID()) instanceof Transaction);
    }

    @Test
    void addTransaction() {
        assertFalse(this.controller.getTransactions().contains(this.transaction1));
        assertFalse(this.controller.getTransactions().contains(this.transaction2));
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        assertTrue(this.controller.getTransactions().contains(this.transaction1));
        assertTrue(this.controller.getTransactions().contains(this.transaction2));
    }

    @Test
    void removeTransaction() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        assertTrue(this.controller.getTransactions().contains(this.transaction1));
        assertTrue(this.controller.getTransactions().contains(this.transaction2));
        this.controller.removeTransaction(this.transaction1);
        this.controller.removeTransaction(this.transaction2);
        assertFalse(this.controller.getTransactions().contains(this.transaction1));
        assertFalse(this.controller.getTransactions().contains(this.transaction2));
    }

    @Test
    void removeMovement() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTag(this.utenza);
        this.controller.addAccount(this.fondoCassa);
        assertTrue(this.transaction1.getMovements().contains(this.debito1));
        assertTrue(this.utenza.getMovements().contains(this.debito1));
        assertTrue(this.fondoCassa.getMovements().contains(this.debito1));
        this.controller.removeMovement(this.debito1);
        assertFalse(this.transaction1.getMovements().contains(this.debito1));
        assertFalse(this.utenza.getMovements().contains(this.debito1));
        assertFalse(this.fondoCassa.getMovements().contains(this.debito1));
    }

    @Test
    void setDescription(){
        assertEquals(this.credito1.getDescription(),"movimento");
        this.controller.setDescription(this.credito1,"credito 1");
        assertEquals(this.credito1.getDescription(),"credito 1");
        assertEquals(this.sport.getDescription(),"tennis");
        this.controller.setDescription(this.sport,"calcio");
        assertEquals(this.sport.getDescription(),"calcio");
        assertEquals(this.fondoCassa.getDescription(),"personale");
        this.controller.setDescription(this.fondoCassa,"cassa");
        assertEquals(this.fondoCassa.getDescription(),"cassa");
        assertEquals(this.transaction1.getDescription(),"transaction 1");
        this.controller.setDescription(this.transaction1,"transaction");
        assertEquals(this.transaction1.getDescription(),"transaction");
    }

    @Test
    void scheduleTransactionsDate() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2020,8,8),LocalTime.MIN);
        LocalDateTime stop = LocalDateTime.of(LocalDate.of(2020,10,10),LocalTime.MIN);
        assertTrue(this.controller.scheduleTransactionsDate(start,stop).contains(this.transaction2));
        assertFalse(this.controller.scheduleTransactionsDate(start,stop).contains(this.transaction1));
    }

    @Test
    void scheduleTransactionsTag() {
        this.controller.addTransaction(this.transaction1,this.transaction1.getMovements());
        this.controller.addTransaction(this.transaction2,this.transaction2.getMovements());
        assertTrue(this.controller.scheduleTransactionsTag(this.sport).contains(this.transaction2));
        assertFalse(this.controller.scheduleTransactionsTag(this.sport).contains(this.transaction1));
    }

    @Test
    void getBudgetRecords() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        this.controller.addBudgetRecord(this.sport,200.0);
        this.controller.addBudgetRecord(this.utenza,300.0);
        assertTrue(this.controller.getBudgetRecords().containsKey(this.sport));
        assertTrue(this.controller.getBudgetRecords().containsKey(this.utenza));
        assertEquals(this.controller.getBudgetRecords().get(this.sport),200);
        assertEquals(this.controller.getBudgetRecords().get(this.utenza),300);
    }

    @Test
    void addBudgetRecord() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        assertFalse(this.controller.getBudgetRecords().containsKey(this.sport));
        assertFalse(this.controller.getBudgetRecords().containsKey(this.utenza));
        this.controller.addBudgetRecord(this.sport,200.0);
        this.controller.addBudgetRecord(this.utenza,300.0);
        assertTrue(this.controller.getBudgetRecords().containsKey(this.sport));
        assertTrue(this.controller.getBudgetRecords().containsKey(this.utenza));
    }

    @Test
    void removeBudgetRecord() {
        this.controller.addTag(this.sport);
        this.controller.addTag(this.utenza);
        this.controller.addBudgetRecord(this.sport,200.0);
        this.controller.addBudgetRecord(this.utenza,300.0);
        assertTrue(this.controller.getBudgetRecords().containsKey(this.sport));
        assertTrue(this.controller.getBudgetRecords().containsKey(this.utenza));
        this.controller.removeBudgetRecord(this.sport);
        this.controller.removeBudgetRecord(this.utenza);
        assertFalse(this.controller.getBudgetRecords().containsKey(this.sport));
        assertFalse(this.controller.getBudgetRecords().containsKey(this.utenza));
    }

    /*Compio una transazione di due movimenti debiti con due tag differenti e si puo notare che in tale
    tabella viene riportata la differenza solamente negativa di un budget e la somma dei rispettivi movimenti
     */
    @Test
    void check() {
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        Account prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        Tag sport = new TagBase("Sport","tennis",idGenerator.generate());
        Tag utenza = new TagBase("Utenza","luce",idGenerator.generate());
        Transaction transaction = new InstantTransaction(null, idGenerator.generate());
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento1",idGenerator.generate());
        Movement debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,utenza,"movimento2",idGenerator.generate());
        this.controller.addTag(sport);
        this.controller.addTag(utenza);
        this.controller.addAccount(fondoCassa);
        this.controller.addTransaction(transaction,transaction.getMovements());
        this.controller.addBudgetRecord(sport,100.0);
        this.controller.addBudgetRecord(utenza,100.0);
        assertEquals(this.controller.check().get(sport)
                ,this.controller.getBudgetRecords().get(sport)+debito1.getAmount());
        assertNotEquals(this.controller.check().get(utenza)
                ,this.controller.getBudgetRecords().get(utenza)+debito2.getAmount());
        assertEquals(this.controller.check().get(utenza),null);
    }

    @Test
    void update() {
        //Creo e aggiungo il primo movimento credito aggiornando
        Transaction transaction1 = new InstantTransaction(null, this.idGenerator.generate());
        Movement credito1 = new MovementBase(MovementType.CREDITS,88,transaction1
                , this.prepagata,sport,"movimento credito aggiornando",idGenerator.generate());
        this.controller.addAccount(this.prepagata);
        this.controller.addTag(this.sport);
        this.controller.addTransaction(transaction1,transaction1.getMovements());
        this.controller.update();
        assertEquals(this.controller.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount());
        //Creo e aggiungo il secondo movimento credito non aggiornando
        Transaction transaction2 = new InstantTransaction(null, this.idGenerator.generate());
        Movement credito2 = new MovementBase(MovementType.CREDITS,30,transaction2
                , this.prepagata,sport,"movimento credito non aggiornando",idGenerator.generate());
        this.controller.addTransaction(transaction2,transaction2.getMovements());
        ;
        assertNotEquals(this.controller.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount()-credito2.getAmount());
        assertNotEquals(this.controller.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount());
        this.controller.update();
        assertEquals(this.controller.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount()+credito2.getAmount());
    }

    /*Salvo il budgetReport e controllo se il nome e l'id del tag salvato e letto dal file sono uguali
    a quelli del tag costruito in precedenza e controllo se la lunghezza del set della mappa del
    budget è uguale a quella salvata e poi letta come il valore relativo al primo tag nel budget
    */
    @Test
    void read() {
        try {
            MainController controller = new MainControllerBase();
            Tag sport = new TagBase("Sport","tennis",this.controller.idGenerator().generate());
            controller.addTag(sport);
            controller.addBudgetRecord(sport,60.0);
            controller.save(new JBudgetWriterJson(path));
            MainController controller2 = new MainControllerBase();
            controller2.read(new JBudgetReaderJson(path));
            assertEquals(controller2.getTag(sport.getID()).getName(),sport.getName());
            assertEquals(controller2.getTag(sport.getID()).getID(),sport.getID());
            assertEquals(controller2.getBudgetRecords().keySet().toArray().length
                    ,controller.getBudgetRecords().keySet().toArray().length);
            assertEquals(controller.getBudgetRecords().get(sport)
                    ,controller2.getBudgetRecords().get(controller2.getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Salvo il budgetReport e controllo se il nome e l'id del tag salvato e letto dal file sono uguali
    a quelli del tag costruito in precedenza e controllo se la lunghezza del set della mappa del
    budget è uguale a quella salvata e poi letta come il valore relativo al primo tag nel budget
    */
    @Test
    void save() {
        try {
            MainController controller = new MainControllerBase();
            Tag sport = new TagBase("Sport","tennis",this.controller.idGenerator().generate());
            controller.addTag(sport);
            controller.addBudgetRecord(sport,60.0);
            controller.save(new JBudgetWriterJson(path));
            Reader<BudgetReport> reader1 = new JBudgetReaderJson(path);
            BudgetReport report = reader1.read();
            reader1.close();
            assertEquals(report.getLedger().getTag(sport.getID()).getName(),sport.getName());
            assertEquals(report.getLedger().getTag(sport.getID()).getID(),sport.getID());
            assertEquals(report.getBudget().getBudgetMap().keySet().toArray().length
                    ,controller.getBudgetRecords().keySet().toArray().length);
            assertEquals(controller.getBudgetRecords().get(sport)
                    ,report.getBudget().getBudgetMap().get(report.getBudget().getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void idGenerator() {
        assertTrue(this.controller.idGenerator() instanceof IDGenerator);
        assertNotEquals(this.controller.idGenerator().generate(),this.controller.idGenerator().generate());
    }
}