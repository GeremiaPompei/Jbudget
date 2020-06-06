package it.unicam.cs.pa.jbudget105333.IDGenerator;

import java.io.Serializable;

public class IDGeneratorBase implements IDGenerator, Serializable {

    private int nextID;

    //Metodo invocato per generare un id
    @Override
    public int generate() {
        return ++nextID;
    }

}
