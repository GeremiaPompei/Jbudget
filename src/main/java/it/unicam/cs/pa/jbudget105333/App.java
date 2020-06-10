/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.cs.pa.jbudget105333;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Controller.MainControllerManager;
import it.unicam.cs.pa.jbudget105333.View.GUIView.GUIView;
import it.unicam.cs.pa.jbudget105333.View.View;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire la JBudget App.
 */
public class App {

    /**
     * Variabile utile alla gestione del log dell'App.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * Controller dell'App.
     */
    private final MainController controller;

    /**
     * View dell'App.
     */
    private final View view;

    /**
     * Costruttore di App che prende un Controller e una View.
     * @param controller controller dell'App.
     * @param view view dell'App.
     */
    public App(MainController controller, View view) {
        this.controller = controller;
        this.view = view;
        this.logger.info("App created.");
    }

    /**
     * Metodo main.
     * @param args
     */
    public static void main(String[] args) {
        createApp().start();
    }

    /**
     * Metodo responsabile di eseguire la view passandole il controller come parametro
     * dopodiché chiudere la View.
     */
    private void start() {
        try {
            this.logger.info("App started.");
            this.view.open(this.controller);
        } catch (IOException e) {
            e.printStackTrace();
            this.logger.info("App failed.");
        }finally {
            this.view.close();
            this.logger.info("App closed.");
        }
    }

    /**
     * Factory Mathod responsabile di creare un'App.
     * @return nuova App.
     */
    private static App createApp() {
        String path = "src/file/jbudget";
        MainController controller = MainControllerManager.generateMainController(path);
        return new App(controller,new GUIView());
    }
}
