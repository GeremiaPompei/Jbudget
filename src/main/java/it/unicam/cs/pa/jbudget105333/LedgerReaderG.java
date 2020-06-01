package it.unicam.cs.pa.jbudget105333;

import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;

public class LedgerReaderG implements Reader<Ledger>{

    private final FileReader in;

    public LedgerReaderG(String path) throws IOException {
        in = new FileReader(path+".json");
    }

    @Override
    public Ledger read() {
        return new GsonBuilder()
                .registerTypeAdapter(LedgerBase.class,new LedgerBaseAdapter()).create()
                .fromJson(in,LedgerBase.class);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
