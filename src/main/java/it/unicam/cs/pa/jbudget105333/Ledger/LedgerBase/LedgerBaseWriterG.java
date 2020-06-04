package it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class LedgerBaseWriterG implements Writer<Ledger> {

    private final FileWriter out;

    public LedgerBaseWriterG(String path) throws IOException {
        out = new FileWriter(path+".json");
    }

    @Override
    public void write(Ledger object) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LedgerBase.class,new LedgerBaseAdapter())
                .setPrettyPrinting()
                .create();
        out.write(gson.toJson(object,LedgerBase.class));
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
