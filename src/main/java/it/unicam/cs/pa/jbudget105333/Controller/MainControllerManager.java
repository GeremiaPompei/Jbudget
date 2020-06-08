package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetReaderJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetWriterJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import java.io.IOException;

/**
 * Interfaccia responsabile della creazione di un MainController..
 */
public interface MainControllerManager {

    /**
     * Metodo responsabile della generazione di un MainController.
     * @return MainController generato.
     */
    static MainController generateMainController(){
        String path = "src/file/jbudget";
        Reader<BudgetReport> reader;
        Writer<BudgetReport> writer = null;
        try {
            reader = new JBudgetReaderJson(path);
        } catch (IOException e) {
            reader = null;
        }
        try {
            writer = new JBudgetWriterJson(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MainControllerBase(reader,writer);
    }
}
