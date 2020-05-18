package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface Controller <O extends Object>{
    void processCommand(String command) throws IOException;
    Set getCommands();
    O getBudgetReport();
    void addCommand(String string, Consumer<Controller<O>> command);
    void addCommands(Map<String, Consumer<Controller<O>>> commands);
    boolean isOn();
    void shutdown();
}
