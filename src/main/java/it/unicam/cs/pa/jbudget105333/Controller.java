package it.unicam.cs.pa.jbudget105333;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface Controller {
    void processCommand(String command);
    Set getCommands();
    BudgetReport getBudgetReport();
    void addCommand(String string, Consumer<Controller> command);
    void addCommands(Map<String,Consumer<Controller>> commands);
    boolean isOn();
    void shutdown();
}
