package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetReader<B extends Budget> implements Reader<B>{

    private final ObjectInputStream in;

    //Preso un path viene creato l'oggetto ObjectInputStream in
    public BudgetReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    //Metodo che viene usato per leggere da un file un oggetto B che estende Budget
    @Override
    public B read() throws IOException, ClassNotFoundException {
        return (B)in.readObject();
    }

    //Chiude l'oggetto ObjectInputStream in
    @Override
    public void close() throws IOException {
        in.close();
    }
}
