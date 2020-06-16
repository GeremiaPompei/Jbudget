package it.unicam.cs.pa.jbudget105333.Model.Store;

import java.io.IOException;

/**
 * Interfaccia che ha la responsabilità di leggere da qualche parte.
 * @param <O> Tipo di ritorno dell'oggetto letto.
 */
public interface Reader<O> {

    /**
     * Metodo che ha la responsabilità di leggere e ritornare un O.
     * @return Oggetto letto.
     */
    O read();

    /**
     * Metodo che ha la responsabilità di chiudere le variabili istanziate per leggere.
     * @throws IOException Eccezione dovuta alla chiusura di uno stream di lettura.
     */
    void close() throws IOException;
}
