package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LedgerWriterTest {

    private static BudgetReportController brcontroller;

    private Writer<Ledger> writer;
    private final String path = "src/file/Ledger.txt";

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
            this.writer = new LedgerWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Aggiungo a ledger1 un tag e scrivo esso su un file dopodichè lo leggo e lo metto nella variabile
    ledger2 e confronto i campi dei due ledger
     */
    @Test
    void write() {
        try {
            Ledger ledger1 = new LedgerBase();
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase());
            ledger1.addTag(sport);
            this.writer.write(ledger1);
            Reader<Ledger> reader = new LedgerReader<>(this.path);
            Ledger ledger2 = reader.read();
            reader.close();
            assertEquals(ledger1.getTags().iterator().next().getName()
                    ,ledger2.getTags().iterator().next().getName());
            assertEquals(ledger1.getTags().iterator().next().getID()
                    ,ledger2.getTags().iterator().next().getID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void close() {
        try {
            Ledger ledger = new LedgerBase();
            this.writer.close();
            assertThrows(IOException.class,() -> this.writer.write(ledger));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}