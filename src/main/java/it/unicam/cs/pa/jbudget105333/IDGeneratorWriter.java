package it.unicam.cs.pa.jbudget105333;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class IDGeneratorWriter<I extends IDGenerator> implements Writer<I> {

    private ObjectOutputStream out = null;
    private final String path;

    public IDGeneratorWriter(String path) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        this.path = path;
    }

    @Override
    public void write(I object) throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(object);
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
