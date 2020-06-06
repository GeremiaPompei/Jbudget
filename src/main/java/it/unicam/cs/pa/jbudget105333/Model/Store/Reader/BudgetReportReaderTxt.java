package it.unicam.cs.pa.jbudget105333.Model.Store.Reader;

import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BudgetReportReaderTxt implements Reader<BudgetReport> {

    private final ObjectInputStream in;

    public BudgetReportReaderTxt(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path+".txt"));
    }

    @Override
    public BudgetReport read() throws IOException, ClassNotFoundException {
        return (BudgetReport) in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
