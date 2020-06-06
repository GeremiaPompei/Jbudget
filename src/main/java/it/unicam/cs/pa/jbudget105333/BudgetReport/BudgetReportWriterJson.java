package it.unicam.cs.pa.jbudget105333.BudgetReport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BudgetReportWriterJson implements Writer<BudgetReport> {

    private final OutputStreamWriter out;
    private final Gson gson;

    public BudgetReportWriterJson(String path) throws IOException {
        this.out = new OutputStreamWriter(new FileOutputStream(path+".json"));
        this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter
                (BudgetReportBase.class,new BudgetReportBaseSerializer()).create();
    }

    @Override
    public void write(BudgetReport object) throws IOException {
        this.out.write(this.gson.toJson(object));
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
