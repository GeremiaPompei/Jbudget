package it.unicam.cs.pa.jbudget105333.View;

import java.io.IOException;

public interface View <O>{
    void open(O controller) throws IOException;
    void close();
}
