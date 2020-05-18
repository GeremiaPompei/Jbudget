package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface Store<O extends Object>{
    O read() throws IOException;
    void write(O object) throws IOException;
    void close();
}
