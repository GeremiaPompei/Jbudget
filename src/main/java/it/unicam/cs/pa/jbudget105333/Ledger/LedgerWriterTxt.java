package it.unicam.cs.pa.jbudget105333.Ledger;

import it.unicam.cs.pa.jbudget105333.Writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LedgerWriterTxt implements Writer<Ledger> {

    private final ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public LedgerWriterTxt(String path) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path+".txt"));
    }

    //Metodo che viene usato per scrivere su un file un oggetto Ledger
    @Override
    public void write(Ledger object) throws IOException {
        out.writeObject(object);
        out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        out.close();
    }
}