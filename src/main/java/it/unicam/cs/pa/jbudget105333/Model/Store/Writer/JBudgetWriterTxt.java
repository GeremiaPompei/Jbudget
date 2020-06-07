package it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di scrivere su un file Txt.
 */
public class JBudgetWriterTxt implements Writer<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del JBudgetWriterTxt.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Percorso su cui scrivere il BudgetReport.
     */
    private final String path;

    /**
     * Variabile utile per scrivere un oggetto su un file.
     */
    private ObjectOutputStream out;

    /**
     * Costruttore del JBudgetWriterTxt.
     * @param path Percorso del file txt.
     * @throws IOException
     */
    public JBudgetWriterTxt(String path) throws IOException {
        this.path = path+".txt";
    }

    /**
     * Metodo che ha la responsabilità di scrivere un BudgetReport su un file txt.
     * @param object
     * @throws IOException
     */
    @Override
    public void write(BudgetReport object) throws IOException {
        this.out = new ObjectOutputStream(new FileOutputStream(this.path));
        this.out.writeObject(object);
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