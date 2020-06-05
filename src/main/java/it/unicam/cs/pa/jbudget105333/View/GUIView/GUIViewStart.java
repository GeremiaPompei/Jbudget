package it.unicam.cs.pa.jbudget105333.View.GUIView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIViewStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/JBudgetHome.fxml"));
        stage.setTitle("JBudget");
        stage.setScene(new Scene(root, 1050, 500));
        stage.show();
    }
}
