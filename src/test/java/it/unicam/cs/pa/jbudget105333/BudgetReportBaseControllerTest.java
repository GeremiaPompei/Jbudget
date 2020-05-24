package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class BudgetReportBaseControllerTest {

    private LedgerController ledgerC;
    private BudgetController budgetC;
    private BudgetReportController budRepC;

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
    void createBudgetReportBaseController(){
        try {
            new ObjectOutputStream(new FileOutputStream("src/file/Ledger.txt"));
            new ObjectOutputStream(new FileOutputStream("src/file/Budget.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ledgerC = new LedgerBaseController();
        this.budgetC = new BudgetBaseController(this.ledgerC);
        this.budRepC = new BudgetReportBaseController(this.ledgerC,this.budgetC);
    }

    @Test
    void getLedgerC() {
        assertEquals(this.budRepC.getLedgerC(),this.ledgerC);
        assertTrue(this.budRepC.getLedgerC() instanceof LedgerController);
        assertFalse(this.budRepC.getLedgerC() instanceof BudgetReportController);
    }

    @Test
    void getBudgetC() {
        assertEquals(this.budRepC.getBudgetC(),this.budgetC);
        assertTrue(this.budRepC.getBudgetC() instanceof BudgetController);
        assertFalse(this.budRepC.getBudgetC() instanceof BudgetReportController);
    }

    /*Salvo il ledger e il budget del controller budtRepC e controllo se il nome e l'id del tag salvato
    e letto dal file sono uguali a quelli del tag costruito in precedenza e controllo se la lunghezza
    del set della mappa del budget è uguale a quella salvata e poi letta come il valore relativo al primo
    tag nel budget
    */
    @Test
    void save() {
        try {
            Tag sport = new TagBase("Sport","tennis",new IDGeneratorBase());
            this.budRepC.getLedgerC().addTag(sport);
            this.budRepC.getBudgetC().addBudgetRecord(sport.getID(),60.0);
            this.budRepC.save();
            Reader<Ledger> reader1 = new LedgerReader("src/file/Ledger.txt");
            Ledger ledger = reader1.read();
            reader1.close();
            Reader<Budget> reader2 = new BudgetReader("src/file/Budget.txt");
            Budget budget = reader2.read();
            reader2.close();
            assertEquals(this.budRepC.getLedgerC().getTag(sport.getID()).getName(),sport.getName());
            assertEquals(this.budRepC.getLedgerC().getTag(sport.getID()).getID(),sport.getID());
            assertEquals(this.budRepC.getBudgetC().getBudgetMap().keySet().toArray().length
                    ,budget.getBudgetMap().keySet().toArray().length);
            assertEquals(this.budRepC.getBudgetC().getBudget().getBudgetMap().get(sport)
                    ,budget.getBudgetMap().get(budget.getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Compio una transazione di due movimenti debiti con due tag differenti e si puo notare che in tale
    tabella viene riportata la differenza solamente negativa di un budget e la somma dei rispettivi movimenti
     */
    @Test
    void check() {
        IDGenerator idGenerator = new IDGeneratorBase();
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Tag benzina = new TagBase("Viaggio","macchina",idGenerator);
        Transaction transaction = new InstantTransaction(idGenerator);
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento1",idGenerator);
        Movement debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento2",idGenerator);
        this.ledgerC.addTag(sport);
        this.ledgerC.addTag(benzina);
        this.ledgerC.addAccount(fondoCassa);
        this.ledgerC.addTransaction(transaction,transaction.getMovements());
        this.budgetC.addBudgetRecord(sport.getID(),100.0);
        this.budgetC.addBudgetRecord(benzina.getID(),100.0);
        assertEquals(this.budRepC.check().get(sport)
                ,this.budgetC.getBudgetMap().get(sport)-debito1.getAmount());
        assertNotEquals(this.budRepC.check().get(benzina)
                ,this.budgetC.getBudgetMap().get(benzina)-debito2.getAmount());
        assertEquals(this.budRepC.check().get(benzina),null);
    }
}