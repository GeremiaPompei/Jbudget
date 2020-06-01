package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetWriter implements Writer<Budget> {

    private final ObjectOutputStream out;

    //Preso un path viene creato l'oggetto ObjectOutputStream out
    public BudgetWriter(String path) throws IOException {
        this.out = new ObjectOutputStream(new FileOutputStream(path+".txt"));
    }

    //Metodo che viene usato per scrivere su un file un oggetto Budget
    @Override
    public void write(Budget object) throws IOException {
        this.out.writeObject(object);
        this.out.flush();
    }

    //Chiude l'oggetto ObjectOutputStream out
    @Override
    public void close() throws IOException {
        this.out.close();
    }
}
