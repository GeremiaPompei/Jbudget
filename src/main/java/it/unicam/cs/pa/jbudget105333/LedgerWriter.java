package it.unicam.cs.pa.jbudget105333;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LedgerWriter<L extends Ledger> implements Writer<L> {

    private final ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public LedgerWriter(String path) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
    }

    //Metodo che viene usato per scrivere su un file un oggetto L che estende Ledger
    @Override
    public void write(L object) throws IOException {
        out.writeObject(object);
        out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        out.close();
    }
}