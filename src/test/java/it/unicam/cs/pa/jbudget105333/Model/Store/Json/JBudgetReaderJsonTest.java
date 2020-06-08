package it.unicam.cs.pa.jbudget105333.Model.Store.Json;

import it.unicam.cs.pa.jbudget105333.Controller.MainControllerManager;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerManager;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JBudgetReaderJsonTest {

    private Reader<BudgetReport> reader;
    private final static String path = "src/file/jbudgetTest";

    @AfterAll
    static void restoreContext(){
        new File(path+".json").delete();
    }

    @BeforeEach
    void createJBudgetReaderJson(){
        try {
            Writer<BudgetReport> writer = new JBudgetWriterJson(path);
            writer.write(BudgetReportManager.generateReport(LedgerManager.generateLedger()
                    ,BudgetManager.generateBudget(),null));
            writer.close();
            MainControllerManager.generateMainController().save();
            this.reader = new JBudgetReaderJson(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Salvo il BudgetReport report1 su file dopodichè lo leggo e salvo nella variabile report2 e confronto
     i suoi parametri riguardanti il tag con quelli iniziali di report1
     */
    @Test
    void read() {
        try {
            Ledger ledger = LedgerManager.generateLedger();
            BudgetReport report1 = BudgetReportManager.generateReport(ledger, BudgetManager.generateBudget(),null);
            Tag sport = new TagBase("Sport","Tennis",ledger.getIDGenerator().generate());
            ledger.addTag(sport);
            report1.getLedger().addTag(sport);
            Writer<BudgetReport> writer = new JBudgetWriterJson(path);
            writer.write(report1);
            writer.close();
            BudgetReport report2 = reader.read();
            assertEquals(report1.getLedger().getTags().iterator().next().getName(),report2.getLedger().getTags()
                    .iterator().next().getName());
            assertEquals(report1.getLedger().getTags().iterator().next().getID(),report2.getLedger().getTags()
                    .iterator().next().getID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void close() {
        try {
            this.reader.close();
            assertThrows(com.google.gson.JsonSyntaxException.class,()->reader.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}