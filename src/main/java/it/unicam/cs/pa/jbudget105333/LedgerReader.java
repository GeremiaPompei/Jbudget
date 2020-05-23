package it.unicam.cs.pa.jbudget105333;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LedgerReader<L extends Ledger> implements Reader<L> {

    private final ObjectInputStream in;

    public LedgerReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    @Override
    public L read() throws IOException, ClassNotFoundException {
        return (L)in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
