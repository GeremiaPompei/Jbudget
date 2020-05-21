package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface View <O extends Object>{
    void open(O controller) throws IOException;
    void close();
}
