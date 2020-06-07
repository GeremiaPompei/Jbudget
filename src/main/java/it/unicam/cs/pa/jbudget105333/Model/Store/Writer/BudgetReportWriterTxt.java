package it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BudgetReportWriterTxt implements Writer<BudgetReport> {

    private final String path;
    private ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public BudgetReportWriterTxt(String path) throws IOException {
        this.path = path+".txt";
    }

    //Metodo che viene usato per scrivere su un file un oggetto Ledger
    @Override
    public void write(BudgetReport object) throws IOException {
        this.out = new ObjectOutputStream(new FileOutputStream(this.path));
        this.out.writeObject(object);
        this.out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        if(this.out!=null)
            this.out.close();
    }
}