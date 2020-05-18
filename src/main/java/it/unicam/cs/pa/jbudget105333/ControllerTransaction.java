package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ControllerTransaction<L extends Ledger,T extends Transaction> implements Controller<T>{

    private Map<String,Consumer<Controller<T>>> commands = null;
    private L ledger = null;
    private T transaction = null;
    private boolean state = false;

    public ControllerTransaction(L ledger,T transaction) {
        this.commands = new HashMap<>();
        this.ledger = ledger;
        this.transaction = transaction;
        this.state = true;
    }

    @Override
    public void processCommand(String command) {
        try{
            new MovementBaseScanner(this.transaction,this.ledger).scanOf(command);
        }catch (Exception e){
            Consumer<Controller<T>> action = this.commands.get(command);
            if(action == null)
                System.err.println("Command unknown: "+command);
            else
                action.accept( this);
        }
    }

    @Override
    public Set getCommands() {
        return this.commands.keySet();
    }

    @Override
    public T getBudgetReport() {
        return this.transaction;
    }

    @Override
    public void addCommand(String string, Consumer<Controller<T>> command) {
        this.commands.put(string,command);
    }

    @Override
    public void addCommands(Map<String, Consumer<Controller<T>>> commands) {
        this.commands.putAll(commands);
    }

    @Override
    public boolean isOn() {
        return this.state;
    }

    @Override
    public void shutdown() {
        this.state = false;
    }
}
