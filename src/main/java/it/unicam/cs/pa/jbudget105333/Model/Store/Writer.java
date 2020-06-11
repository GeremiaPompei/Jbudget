package it.unicam.cs.pa.jbudget105333.Model.Store;

import java.io.IOException;

/**
 * Interfaccia che ha la responsabilità di scrivere da qualche parte.
 * @param <O> Tipo dell'oggetto scritto.
 */
public interface Writer <O> {

    /**
     * Metodo che ha la responsabilità di scrivere un O.
     * @param object
     * @throws IOException
     */
    void write(O object) throws IOException;

    /**
     * Metodo che ha la responsabilità di chiudere le variabili istanziate per scrivere.
     * @throws IOException
     */
    void close() throws IOException;
}
