package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BudgetReaderTest {

    private static BudgetReportController brcontroller;

    private Reader<Budget> reader;
    private final String path = "src/file/Budget.txt";

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
    void createBudgetReader(){
        try {
            this.reader = new BudgetReader(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Creo budget1 e lo scrivo su file dopodiche lo leggo e salvo in budget2 e confronto i valori
    contenuti nei due per stabilire che sono uguali
     */
    @Test
    void read() {
        try {
            Budget budget1 = new BudgetBase();
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase());
            budget1.add(sport,34.0);
            Writer<Budget> writer = new BudgetWriter(path);
            writer.write(budget1);
            writer.close();
            Budget budget2 = reader.read();
            assertEquals(budget1.getBudgetMap().keySet().toArray().length
                    ,budget2.getBudgetMap().keySet().toArray().length);
            assertEquals(budget1.getBudgetMap().get(sport)
                    ,budget2.getBudgetMap().get(budget2.getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void close() {
        try {
            this.reader.close();
            assertThrows(IOException.class,()->reader.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}