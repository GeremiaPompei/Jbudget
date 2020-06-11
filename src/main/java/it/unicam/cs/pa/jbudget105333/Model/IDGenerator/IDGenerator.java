package it.unicam.cs.pa.jbudget105333.Model.IDGenerator;

/**
 * Questa interfaccia è implementata dalle classi che hanno la responsabilità di gestire un generatore
 * di ID. Essa permette di generare un id univoco.
 */
public interface IDGenerator {

    /**
     * Metodo responsabile di ritornare un ID.
     * @return intero corrispondente all'ID.
     */
    int generate();
}
