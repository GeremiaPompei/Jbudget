package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetReader implements Reader<Budget>{

    private final ObjectInputStream in;

    //Preso un path viene creato l'oggetto ObjectInputStream in
    public BudgetReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path+".txt"));
    }

    //Metodo che viene usato per leggere da un file un oggetto Budget
    @Override
    public Budget read() throws IOException, ClassNotFoundException {
        return (Budget) in.readObject();
    }

    //Chiude l'oggetto ObjectInputStream in
    @Override
    public void close() throws IOException {
        in.close();
    }
}
