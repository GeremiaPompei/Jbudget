package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface Store<B extends Bilancio> {

    void read(GestoreMovimenti<B> gestoreMovimenti) throws IOException;
    void write(GestoreMovimenti<B> gestoreMovimenti) throws IOException;
    void close();
}
