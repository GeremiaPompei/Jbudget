package it.unicam.cs.pa.jbudget105333.Model.Store.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBase;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBaseSerializer;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di scrivere su un file Json.
 */
public class JBudgetWriterJson implements Writer<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del JBudgetWriterJson.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Percorso su cui scrivere il BudgetReport.
     */
    private final String path;

    /**
     * Variabile utile per scrivere su un file.
     */
    private OutputStreamWriter out;

    /**
     * Variabile utile per convertire un BudgetReader in un JsonElement.
     */
    private final Gson gson;

    /**
     * Costruttore del JBudgetWriterJson.
     * @param path Percorso del file Json.
     */
    public JBudgetWriterJson(String path){
        if(!path.contains(".json"))
            path = path+".json";
        this.path = path;
        this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter
                (BudgetReportBase.class,new BudgetReportBaseSerializer()).create();
    }

    /**
     * Metodo che ha la responsabilità di scrivere un BudgetReport su un file Json.
     * @param object
     * @throws IOException
     */
    @Override
    public void write(BudgetReport object) throws IOException {
        this.out = new OutputStreamWriter(new FileOutputStream(path));
        this.out.write(this.gson.toJson(object));
        this.out.flush();
        this.logger.info("Writing.");
    }

    /**
     * Metodo che ha la responsabilità di chiudere le variabili utilizzate per scrivere.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if(this.out!=null)
            this.out.close();
    }
}
