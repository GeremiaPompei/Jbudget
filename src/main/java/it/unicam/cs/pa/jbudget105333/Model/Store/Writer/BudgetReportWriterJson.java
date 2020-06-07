package it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBase;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase.BudgetReportBaseSerializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BudgetReportWriterJson implements Writer<BudgetReport> {

    private final String path;
    private OutputStreamWriter out;
    private final Gson gson;

    public BudgetReportWriterJson(String path) throws IOException {
        this.path = path+".json";
        this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter
                (BudgetReportBase.class,new BudgetReportBaseSerializer()).create();
    }

    @Override
    public void write(BudgetReport object) throws IOException {
        this.out = new OutputStreamWriter(new FileOutputStream(path));
        this.out.write(this.gson.toJson(object));
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        if(this.out!=null)
            this.out.close();
    }
}
