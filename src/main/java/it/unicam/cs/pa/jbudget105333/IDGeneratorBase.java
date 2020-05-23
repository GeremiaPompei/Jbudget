package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class IDGeneratorBase implements IDGenerator, Serializable {

    private int nextID;
    private final Map<Integer,Object> history;

    //Il costruttore inizializza la mappa history
    public IDGeneratorBase() {
        this.history = new HashMap<>();
    }

    //Metodo invocato per generare un id
    @Override
    public int generate() {
        return ++nextID;
    }

    //Metodo utilizzato per salvare un oggetto all'interno della mappa con il relativo id come chiave
    @Override
    public void store(Object o){
        this.history.put(nextID,o);
    }

    @Override
    public Map<Integer, Object> getMap() {
        return this.history;
    }

    //Metodo che permette di cercare un oggetto dato il suo ID
    @Override
    public Object IDSearch(int id) {
        return this.history.get(id);
    }
}
