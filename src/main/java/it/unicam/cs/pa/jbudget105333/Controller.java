package it.unicam.cs.pa.jbudget105333;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface Controller <O extends Object>{
    void processCommand(String command);
    Set getCommands();
    O getBudgetReport();
    void addCommand(String string, Consumer<? extends Controller> command);
    void addCommands(Map<String,Consumer<? extends Controller>> commands);
    boolean isOn();
    void shutdown();
}
