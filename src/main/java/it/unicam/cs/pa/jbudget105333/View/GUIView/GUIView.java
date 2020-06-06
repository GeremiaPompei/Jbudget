package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.View.View;
import javafx.application.Application;

import java.io.IOException;

public class GUIView implements View {
    @Override
    public void open(MainController controller) throws IOException {
        Application.launch(GUIViewStart.class);
    }

    @Override
    public void close() {
    }
}
