package it.unicam.cs.pa.jbudget105333.Model.Store.Json;

import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
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

class JBudgetWriterJsonTest {

    private Writer<BudgetReport> writer;
    private static final String path = "jbudgetTest.json";

    @AfterAll
    static void restoreContext(){
        new File(path).delete();
    }

    @BeforeEach
    void createJBudgetWriterJson(){
        try {
            Writer<BudgetReport> writer = new JBudgetWriterJson(path);
            writer.write(BudgetReportManager.generateReport(LedgerManager.generateLedger()
                    ,BudgetManager.generateBudget()));
            writer.close();
            this.writer = new JBudgetWriterJson(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Aggiungo a report1 un tag e scrivo esso su un file dopodichè lo leggo e lo metto nella variabile
    report2 e confronto i campi dei due BudgetReport
     */
    @Test
    void write() {
        try {
            BudgetReport report1 = BudgetReportManager.generateReport(LedgerManager.generateLedger()
                    , BudgetManager.generateBudget());
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase().generate());
            report1.getLedger().addTag(sport);
            this.writer.write(report1);
            Reader<BudgetReport> reader = new JBudgetReaderJson(this.path);
            BudgetReport report2 = reader.read();
            reader.close();
            assertEquals(report1.getLedger().getTags().iterator().next().getName()
                    ,report2.getLedger().getTags().iterator().next().getName());
            assertEquals(report1.getLedger().getTags().iterator().next().getID()
                    ,report2.getLedger().getTags().iterator().next().getID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void close() {

    }
}