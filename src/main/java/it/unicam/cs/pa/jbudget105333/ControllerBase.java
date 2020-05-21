package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ControllerBase<B extends BudgetReport> implements Controller<B>{

    private final Map<String,Consumer<Controller<B>>> commands;
    private final B budgetReport;
    private boolean state = false;
    private final Processor processor;
    private final Writer<B> writerB;
    private final Writer<IDGenerator> writerI;
    private final IDGenerator idGenerator;

    public ControllerBase(B budgetReport, Processor processor, IDGenerator idGenerator
            , Writer<B> writerB, Writer<IDGenerator> writerI) {
        this.budgetReport = budgetReport;
        this.processor = processor;
        this.idGenerator = idGenerator;
        this.writerB = writerB;
        this.writerI = writerI;
        this.state = true;this.commands = new HashMap<>();
    }

    @Override
    public String processCommand(String command) throws IOException {
        String result = "";
        if(command.contains(" ")){
            String intro = command.substring(0, command.indexOf(' ')).trim();
            String coda = command.substring(command.indexOf(' ')).trim();
            switch (intro) {
                case "showtransactionsd":
                    result = this.processor.scheduleTransactionsDate(coda);
                    break;
                case "showtransactionst":
                    result = this.processor.scheduleTransactionsTag(coda);
                    break;
                case "newitransaction":
                    result = this.processor.newITransaction(coda);
                    break;
                case "newptransaction":
                    result = this.processor.newPTransaction(coda);
                    break;
                case "newaccount":
                    result = this.processor.newAccount(coda);
                    break;
                case "newbudget":
                    result = this.processor.newBudgetRecord(coda);
                    break;
                case "newtag":
                    result = this.processor.newTag(coda);
                    break;
                default:
            }
        }
        Consumer<Controller<B>> action = this.commands.get(command);
        if(result == "")
            if (action == null)
                result = "Command unknown: " + command;
            else
                action.accept(this);
        this.budgetReport.getLedger().update();
        save();
        return result;
    }

    @Override
    public Set getCommands() {
        return this.commands.keySet();
    }

    @Override
    public B getObject() {
        return this.budgetReport;
    }

    @Override
    public void addCommand(String string, Consumer<Controller<B>> command) {
        this.commands.put(string,command);
    }

    @Override
    public void addCommands(Map<String, Consumer<Controller<B>>> commands) {
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

    private void save() throws IOException {
        if (this.writerB != null) {
            this.writerB.write(this.budgetReport);
            if(this.state==false)
                try {
                    this.writerB.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        if (this.writerI != null) {
            this.writerI.write(this.idGenerator);
            if(this.state==false)
                try {
                    this.writerI.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
