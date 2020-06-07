package it.unicam.cs.pa.jbudget105333.Model.Store.Reader;

import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di leggere un file Txt.
 */
public class JBudgetReaderTxt implements Reader<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del JBudgetReaderTxt.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Variabile utile per leggere da un file un oggetto.
     */
    private final ObjectInputStream in;

    /**
     * Costruttore del JBudgetReaderTxt.
     * @param path Percorso del file txt.
     * @throws IOException
     */
    public JBudgetReaderTxt(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path+".txt"));
    }

    /**
     * Metodo che ha la responsabilità di leggere da file txt e ritornare un BudgetReport.
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public BudgetReport read() throws IOException, ClassNotFoundException {
        this.logger.info("Reading.");
        return (BudgetReport) in.readObject();
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
