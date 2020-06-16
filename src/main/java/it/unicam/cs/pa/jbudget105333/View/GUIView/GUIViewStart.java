package it.unicam.cs.pa.jbudget105333.View.GUIView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di far partire lo stage della GUIView.
 */
public class GUIViewStart extends Application {

    /**
     * Variabile utile alla gestione del log della GUIViewStart.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Mwtodo che ha la responsabilità di impostare i parametri e far partire lo stage della GUIView.
     * @param stage Stage della GUIView.
     * @throws Exception Eccezione dovuta al caricamento eseguito male del file FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/JBudgetHome.fxml"));
        stage.setTitle("JBudget");
        stage.setScene(new Scene(root, 1050, 500));
        stage.show();
        this.logger.fine("GuiView started.");
    }
}
