package it.unicam.cs.pa.jbudget105333.View;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;

import java.io.IOException;

public interface View {
    void open(MainController controller) throws IOException;
    void close();
}
