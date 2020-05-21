package it.unicam.cs.pa.jbudget105333;

import java.io.*;

public class BudgetReportWriter<BR extends BudgetReport> implements Writer<BR> {

    private ObjectOutputStream out = null;
    private final String path;

    public BudgetReportWriter(String path) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        this.path = path;
    }

    @Override
    public void write(BR object) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(object);
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
