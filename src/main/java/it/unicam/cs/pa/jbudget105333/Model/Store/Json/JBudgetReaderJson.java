package it.unicam.cs.pa.jbudget105333.Model.Store.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBase;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di leggere un file Json.
 */
public class JBudgetReaderJson implements Reader<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del JBudgetReaderJson.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Variabile utile per leggere da un file.
     */
    private final InputStreamReader in;

    /**
     * Variabile utile per convertire un JsonElement in un BudgetReader.
     */
    private final Gson gson;

    /**
     * Costruttore del JBudgetReaderJson.
     * @param path Percorso del file Json.
     * @throws IOException
     */
    public JBudgetReaderJson(String path) throws IOException {
        this.in = new InputStreamReader(new FileInputStream(path+".json"));
        this.gson = new GsonBuilder().registerTypeAdapter
                (BudgetReportBase.class,new BudgetReportBaseDeserializer()).create();
    }

    /**
     * Metodo che ha la responsabilità di leggere da file Json e ritornare un BudgetReport.
     * @return BudgetReport del file Json.
     */
    @Override
    public BudgetReport read(){
        this.logger.info("Reading.");
        BudgetReport report = this.gson.fromJson(in,BudgetReportBase.class);
        if(report == null)
            throw new NullPointerException();
        return report;
    }

    /**
     * Metodo che ha la responsabilità di chiudere le variabili utilizzate per leggere.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        in.close();
    }
}
