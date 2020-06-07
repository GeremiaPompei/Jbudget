package it.unicam.cs.pa.jbudget105333.Model.Store.Reader;

import it.unicam.cs.pa.jbudget105333.Controller.MainControllerManager;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerManager;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.JBudgetWriterTxt;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.Writer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JBudgetReaderTxtTest {

    private Reader<BudgetReport> reader;
    private static final String path = "src/file/jbudgetTest";

    @AfterAll
    static void restoreContext(){
        new File(path+".txt").delete();
    }

    @BeforeEach
    void createBudgetReader(){
        try {
            Writer<BudgetReport> writer = new JBudgetWriterTxt(path);
            writer.write(BudgetReportManager.generateReport(LedgerManager.generateLedger()
                    ,BudgetManager.generateBudget(),null));
            writer.close();
            MainControllerManager.generateMainController().save();
            new FileOutputStream(new File(path+".txt"));
            this.reader = new JBudgetReaderTxt(path);
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
            BudgetReport report1 = BudgetReportManager.generateReport(LedgerManager.generateLedger(), BudgetManager.generateBudget(),null);
            Tag sport = new TagBase("Sport","Tennis",new IDGeneratorBase().generate());
            report1.getLedger().addTag(sport);
            Writer<BudgetReport> writer = new JBudgetWriterTxt(path);
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
            assertThrows(IOException.class,()->reader.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}