package it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BudgetReportWriterTxt implements Writer<BudgetReport> {

    private final ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public BudgetReportWriterTxt(String path) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path+".txt"));
    }

    //Metodo che viene usato per scrivere su un file un oggetto Ledger
    @Override
    public void write(BudgetReport object) throws IOException {
        out.writeObject(object);
        out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        out.close();
    }
}