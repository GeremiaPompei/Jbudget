package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface View <B extends Bilancio>{

    void open(Comandi<B> comandi) throws IOException;
    void close();
}
