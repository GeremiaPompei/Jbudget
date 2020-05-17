package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface View <C extends Controller>{
    void open(C controller) throws IOException;
    void close();
}
