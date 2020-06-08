package it.unicam.cs.pa.jbudget105333.Model.Store;

import java.io.IOException;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi per leggere da qualche parte.
 * @param <O> Tipo di ritorno dell'oggetto letto.
 */
public interface Reader<O> {

    /**
     * Metodo che ha la responsabilità di leggere e ritornare un O.
     * @return Oggetto letto.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    O read() throws IOException, ClassNotFoundException;

    /**
     * Metodo che ha la responsabilità di chiudere le variabili istanziate per leggere.
     * @throws IOException
     */
    void close() throws IOException;
}
