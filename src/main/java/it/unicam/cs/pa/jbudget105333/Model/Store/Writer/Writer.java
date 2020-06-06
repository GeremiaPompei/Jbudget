package it.unicam.cs.pa.jbudget105333.Model.Store.Writer;

import java.io.IOException;

public interface Writer <O> {
    void write(O object) throws IOException;
    void close() throws IOException;
}
