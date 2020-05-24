package it.unicam.cs.pa.jbudget105333;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LedgerReader<L extends Ledger> implements Reader<L> {

    private final ObjectInputStream in;

    //Preso un path viene creato l'oggetto ObjectInputStream in
    public LedgerReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    //Metodo che viene usato per leggere da un file un oggetto L che estende Ledger
    @Override
    public L read() throws IOException, ClassNotFoundException {
        return (L)in.readObject();
    }

    //Chiude l'oggetto ObjectInputStream in
    @Override
    public void close() throws IOException {
        in.close();
    }
}
