package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;

public interface Tag extends Serializable {
    String getName();
    String getDescription();
    int getID();
}
