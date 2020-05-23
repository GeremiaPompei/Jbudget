package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BudgetBaseControllerTest {

    private static BudgetReportController brcontroller;

    private Tag sport;
    private BudgetController budgetC;

    @BeforeAll
    static void saveContext(){
        LedgerController lcontroller = new LedgerBaseController();
        BudgetController bcontroller = new BudgetBaseController(lcontroller);
        brcontroller = new BudgetReportBaseController(lcontroller,bcontroller);
    }

    @AfterAll
    static void restoreContext(){
        try {
            brcontroller.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void createBudgetBaseController(){
        LedgerController ledgerC = new LedgerBaseController();
        this.sport = new TagBase("Sport","Tennis",new IDGeneratorBase());
        ledgerC.getLedger().addTag(this.sport);
        this.budgetC = new BudgetBaseController(ledgerC);
    }

    @Test
    void getBudget() {
        assertTrue(this.budgetC.getBudget() instanceof Budget);
        assertFalse(this.budgetC.getBudget() instanceof BudgetController);
    }

    @Test
    void getBudgetMap() {
        assertTrue(this.budgetC.getBudgetMap() instanceof Map);
        assertFalse(this.budgetC.getBudgetMap() instanceof Budget);
        assertFalse(this.budgetC.getBudgetMap() instanceof BudgetController);
    }

    @Test
    void addBudgetRecord() {
        this.budgetC.addBudgetRecord(this.sport.getID(),800.0);
        assertTrue(this.budgetC.getBudgetMap().containsKey(this.sport));
        assertEquals(this.budgetC.getBudgetMap().get(this.sport),800);
    }

    /*Salvo il budget del controller budgetC e controllo se è uguale alla lettura
    dell'oggetto dal file. In particolare confronto la lunghezza del set delle chiavi
    della map del budget e il valore del primo elemento
    */
    @Test
    void save() {
        try {
            this.budgetC.addBudgetRecord(this.sport.getID(),89.0);
            this.budgetC.save();
            Reader<Budget> reader2 = new BudgetReader("src/file/Budget.txt");
            Budget budget2 = reader2.read();
            reader2.close();
            assertEquals(this.budgetC.getBudget().getBudgetMap().keySet().toArray().length
                    ,budget2.getBudgetMap().keySet().toArray().length);
            assertEquals(this.budgetC.getBudget().getBudgetMap().get(this.sport)
                    ,budget2.getBudgetMap().get(budget2.getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}