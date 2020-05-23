package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetWriter<B extends Budget> implements Writer<B> {

    private final ObjectOutputStream out;

    public BudgetWriter(String path) throws IOException {
        this.out = new ObjectOutputStream(new FileOutputStream(path));
    }

    @Override
    public void write(B object) throws IOException {
        this.out.writeObject(object);
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}
