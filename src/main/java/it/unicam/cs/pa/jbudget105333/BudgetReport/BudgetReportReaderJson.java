package it.unicam.cs.pa.jbudget105333.BudgetReport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BudgetReportReaderJson implements Reader<BudgetReport> {

    private final InputStreamReader in;
    private final Gson gson;

    public BudgetReportReaderJson(String path) throws IOException {
        this.in = new InputStreamReader(new FileInputStream(path+".json"));
        this.gson = new GsonBuilder().registerTypeAdapter
                (BudgetReportBase.class,new BudgetReportBaseDeserializer()).create();
    }

    @Override
    public BudgetReport read() throws IOException, ClassNotFoundException {
        return this.gson.fromJson(in,BudgetReportBase.class);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
