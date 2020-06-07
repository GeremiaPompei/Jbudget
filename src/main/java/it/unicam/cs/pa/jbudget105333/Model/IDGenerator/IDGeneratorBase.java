package it.unicam.cs.pa.jbudget105333.Model.IDGenerator;

import it.unicam.cs.pa.jbudget105333.JBLogger;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di generare un ID univoco.
 */
public class IDGeneratorBase implements IDGenerator, Serializable {

    /**
     * Variabile utile alla gestione del log dell'IDGeneratorBase.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Variabile utilizzata incrementare l'ID.
     */
    private int nextID;

    /**
     * Costruttore dell'IDGeneratorBase senza parametri.
     */
    public IDGeneratorBase() {
        this(0);
    }

    /**
     * Costruttore dell'IDGeneratorBase con parametro ID iniziale.
     * @param nextID ID iniziale.
     */
    public IDGeneratorBase(int nextID) {
        this.nextID = nextID;
    }

    /**
     * Metodo responsabile di ritornare un ID.
     * @return intero corrispondente all'ID.
     */
    @Override
    public int generate() {
        this.logger.finest("ID generated.");
        return ++nextID;
    }

}
