package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.View.View;
import javafx.application.Application;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire una GUIView.
 */
public class GUIView implements View{

    /**
     * Variabile utile alla gestione del log della GUIView.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Metodo che ha la responsabilità di far partire una GUIView passandogli un MainController.
     * @param controller MainController utile alla GUIView per l'interazione con il model.
     * @throws IOException
     */
    @Override
    public void open(MainController controller) throws IOException {
        Application.launch(GUIViewStart.class);
        this.logger.fine("GUIView opened.");
    }

    /**
     * Metodo che ha la responsabilità di chiudere le variabili della GUIView.
     */
    @Override
    public void close() {
        this.logger.fine("GUIView closed.");
    }
}
