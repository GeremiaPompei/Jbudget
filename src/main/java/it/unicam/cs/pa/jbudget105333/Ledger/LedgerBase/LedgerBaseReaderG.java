package it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase;

import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.jbudget105333.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Reader;

import java.io.FileReader;
import java.io.IOException;

public class LedgerBaseReaderG implements Reader<Ledger> {

    private final FileReader in;

    public LedgerBaseReaderG(String path) throws IOException {
        in = new FileReader(path+".json");
    }

    @Override
    public Ledger read() {
        return new GsonBuilder()
                .registerTypeAdapter(LedgerBase.class,new LedgerBaseAdapter()).create()
                .fromJson(in, LedgerBase.class);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
