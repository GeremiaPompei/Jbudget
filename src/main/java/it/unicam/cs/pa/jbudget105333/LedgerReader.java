package it.unicam.cs.pa.jbudget105333;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LedgerReader implements Reader<Ledger> {

    private final ObjectInputStream in;

    //Preso un path viene creato l'oggetto ObjectInputStream in
    public LedgerReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path+".txt"));
    }

    //Metodo che viene usato per leggere da un file un oggetto Ledger
    @Override
    public Ledger read() throws IOException, ClassNotFoundException {
        return (Ledger) in.readObject();
    }

    //Chiude l'oggetto ObjectInputStream in
    @Override
    public void close() throws IOException {
        in.close();
    }
}
