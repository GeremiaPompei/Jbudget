package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;

public interface IDGenerator extends Serializable {
    int generate();
    void store(Object o);
}
