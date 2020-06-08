package it.unicam.cs.pa.jbudget105333.View;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;

import java.io.IOException;

/**
 * Interfaccia responsabile di dichiarare i metodi di una vista.
 */
public interface View {

    /**
     * Metodo che ha la responsabilità di far partire una vista passandogli un MainController.
     * @param controller MainController utile alla vista per l'interazione con il model.
     * @throws IOException
     */
    void open(MainController controller) throws IOException;

    /**
     * Metodo che ha la responsabilità di chiudere le variabili della vista.
     */
    void close();
}
