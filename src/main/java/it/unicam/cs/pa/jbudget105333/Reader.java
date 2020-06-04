package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface Reader<O> {
    O read() throws IOException, ClassNotFoundException;
    void close() throws IOException;
}
