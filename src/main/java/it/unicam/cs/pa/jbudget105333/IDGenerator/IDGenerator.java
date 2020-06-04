package it.unicam.cs.pa.jbudget105333.IDGenerator;

import java.io.Serializable;
import java.util.Map;

public interface IDGenerator extends Serializable {
    int generate();
    void store(Object o);
    Map<Integer,Object> getMap();
    Object IDSearch(int id);
}
