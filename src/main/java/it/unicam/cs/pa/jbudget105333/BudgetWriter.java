package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetWriter<B extends Budget> implements Writer<B> {

    private final ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public BudgetWriter(String path) throws IOException {
        this.out = new ObjectOutputStream(new FileOutputStream(path));
    }

    //Metodo che viene usato per scrivere su un file un oggetto B che estende Budget
    @Override
    public void write(B object) throws IOException {
        this.out.writeObject(object);
        this.out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        this.out.close();
    }
}