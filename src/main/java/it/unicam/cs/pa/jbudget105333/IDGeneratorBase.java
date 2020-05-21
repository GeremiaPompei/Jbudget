package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class IDGeneratorBase implements IDGenerator, Serializable {

    private int nextID;
    private final Map<Integer,Object> history;

    public IDGeneratorBase() {
        this.history = new HashMap<>();
    }

    @Override
    public int generate() {
        return ++nextID;
    }

    @Override
    public void store(Object o){
        this.history.put(nextID,o);
    }

    @Override
    public Map<Integer, Object> getMap() {
        return this.history;
    }
}
