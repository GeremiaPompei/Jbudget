package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface Store<B extends Bilancio> {

    GestoreMovimenti<B> read() throws IOException;
    void write(GestoreMovimenti<B> gestoreMovimenti) throws IOException;
    void close();
}
