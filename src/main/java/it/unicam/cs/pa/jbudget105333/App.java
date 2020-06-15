/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.jbudget105333;

import it.unicam.cs.pa.jbudget105333.View.GUIView.GUIViewStart;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.*;

/**
 * Classe che ha la responsabilità di gestire la JBudget App.
 */
public class App {

    /**
     * Variabile utile alla gestione del log dell'App.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo main responsabile di lanciare la GUI View.
     * @param args
     */
    public static void main(String[] args) {
        setLogger();
        logger.info("Start app.");
        Application.launch(GUIViewStart.class);
        logger.info("Stop app.");
    }

    /**
     * Metodo responsabile della personalizzazione del logger per permettere di
     * essere scritto su file, inizializzare le directory dove i file di logging
     * vengono salvati e non essere stampato a console.
     */
    private static void setLogger() {
        String dirPath = "src/file/logging";
        new File((dirPath)).mkdirs();
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        Handler handler = null;
        try {
            handler = new FileHandler(dirPath+"/log_"
                    + LocalDateTime.now()+".txt");
            handler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {}
        logger.addHandler(handler);
    }
}
