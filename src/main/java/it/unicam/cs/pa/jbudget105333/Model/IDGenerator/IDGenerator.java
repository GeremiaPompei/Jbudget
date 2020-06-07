package it.unicam.cs.pa.jbudget105333.Model.IDGenerator;

import java.io.Serializable;

/**
 * Interfaccia che ha la responsabilità di dichiarare i metodi di un IDGenerator.
 */
public interface IDGenerator extends Serializable {

    /**
     * Metodo responsabile di ritornare un ID.
     * @return intero corrispondente all'ID.
     */
    int generate();
}
