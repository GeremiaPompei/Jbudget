package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetReportReader<BR extends BudgetReport> implements Reader<BR>{

    private final ObjectInputStream in;

    public BudgetReportReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

        @Override
    public BR read() throws IOException, ClassNotFoundException {
        return (BR)in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
