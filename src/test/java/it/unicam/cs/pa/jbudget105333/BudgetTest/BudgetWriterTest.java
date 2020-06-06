package it.unicam.cs.pa.jbudget105333.BudgetTest;

import it.unicam.cs.pa.jbudget105333.Budget.*;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetBase;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Reader;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import it.unicam.cs.pa.jbudget105333.Writer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetWriterTest {

    private static BudgetReportController brcontroller;

    private Writer<Budget> writer;
    private final String path = "src/file/Budget";

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
    void createBudgetWriter(){
        try {
            this.writer = new BudgetWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Creo budget1 e lo scrivo su file dopodiche lo leggo e salvo in budget2 e confronto i valori
    contenuti nei due per stabilire che sono uguali
     */
    @Test
    void write() {
        try {
            Budget budget1 = new BudgetBase();
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase());
            budget1.add(sport,34.0);
            this.writer.write(budget1);
            Reader<Budget> reader = new BudgetReader(this.path);
            Budget budget2 = reader.read();
            reader.close();
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
            Budget budget = new BudgetBase();
            this.writer.close();
            assertThrows(IOException.class,() -> this.writer.write(budget));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}