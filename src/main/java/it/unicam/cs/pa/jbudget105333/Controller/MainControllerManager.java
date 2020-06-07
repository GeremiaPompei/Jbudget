package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader.BudgetReportReaderJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader.Reader;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.BudgetReportWriterJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.Writer;

import java.io.IOException;

public interface MainControllerManager {
    static MainController generateMainController(){
        String path = "src/file/jbudget";
        Reader<BudgetReport> reader;
        Writer<BudgetReport> writer = null;
        try {
            reader = new BudgetReportReaderJson(path);
        } catch (IOException e) {
            reader = null;
        }
        try {
            writer = new BudgetReportWriterJson(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MainControllerBase(reader,writer);
    }
}
