package it.unicam.cs.pa.jbudget105333.LedgerTest;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Ledger.*;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase;
import it.unicam.cs.pa.jbudget105333.Reader;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import it.unicam.cs.pa.jbudget105333.Writer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LedgerReaderTxtTest {

    private static BudgetReportController brcontroller;

    private Reader<Ledger> reader;
    private final String path = "src/file/Ledger";

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
            this.reader = new LedgerReaderTxt(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Salvo il ledger del ledgerC su file dopodichè lo leggo e salvo nella variabile ledger2 e confronto
     i suoi parametri riguardanti il tag con quelli iniziali di ledger1
     */
    @Test
    void read() {
        try {
            Ledger ledger1 = new LedgerBase();
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase());
            ledger1.addTag(sport);
            Writer<Ledger> writer = new LedgerWriterTxt(path);
            writer.write(ledger1);
            writer.close();
            Ledger ledger2 = reader.read();
            assertEquals(ledger1.getTags().iterator().next().getName(),ledger2.getTags()
                    .iterator().next().getName());
            assertEquals(ledger1.getTags().iterator().next().getID(),ledger2.getTags()
                    .iterator().next().getID());
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