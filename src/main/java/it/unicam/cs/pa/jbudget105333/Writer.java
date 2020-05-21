package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface Writer <O extends Object> {
    void write(O object) throws IOException;
    void close() throws IOException;
}
