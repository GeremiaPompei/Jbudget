package it.unicam.cs.pa.jbudget105333;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class IDGeneratorReader<I extends IDGenerator> implements Reader<I>{

    private final ObjectInputStream in;

    public IDGeneratorReader(String path) throws IOException {
        in = new ObjectInputStream(new FileInputStream(path));
    }

    @Override
    public I read() throws IOException, ClassNotFoundException {
        return (I)in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
