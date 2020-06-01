package it.unicam.cs.pa.jbudget105333;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class LedgerWriterG implements Writer<Ledger>{

    private final FileWriter out;

    public LedgerWriterG(String path) throws IOException {
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
