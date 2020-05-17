package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ControllerInstantTransaction implements Controller<Transaction>{

    private Transaction transaction = null;
    private Map<String,Consumer<? extends Controller>> commands = null;
    private BudgetReport budgetReport = null;
    private boolean state = false;

    private MovementType movementType = null;
    private double amount = 0.0;
    private Account account = null;
    private LocalDate localDate = null;
    private Tag tag = null;
    private String description = "";


    public ControllerInstantTransaction(BudgetReport budgetReport,Transaction transaction) {
        this.commands = new HashMap<>();
        this.budgetReport = budgetReport;
        this.transaction = transaction;
        this.state = true;
    }

    @Override
    public void processCommand(String command) {
        try{
            Consumer<Controller> action = (Consumer<Controller>) this.commands.get(command);
            if(action != null)
                action.accept(this);
            else
                this.movementType = MovementType.valueOf(command.toUpperCase().trim());
        }catch(Exception e) {
            try {
                this.amount = Double.parseDouble(command.trim());
            } catch (Exception e1) {
                try {
                    this.budgetReport.getLedger().getAccounts().stream()
                            .filter(a -> a.getName().toLowerCase().equals(command.toLowerCase().trim()))
                            .forEach(a -> this.account = a);
                } catch (Exception e2) {
                    try {
                        this.localDate = LocalDate.parse(command.trim());
                    }catch (Exception e3){
                        try{
                            this.budgetReport.getLedger().getTags().stream()
                                    .filter(t->t.getID()==Integer.parseInt(command.trim()))
                                    .forEach(t->this.tag = t);
                        }catch (Exception e4){
                            this.description = command.trim();
                        }
                    }
                }
            }
        }finally {
            this.budgetReport.getLedger().update();
        }
    }

    @Override
    public Set getCommands() {
        return this.commands.keySet();
    }

    @Override
    public Transaction getBudgetReport() {
        return this.transaction;
    }

    @Override
    public void addCommand(String string, Consumer<? extends Controller> command) {
        this.commands.put(string,command);
    }

    @Override
    public void addCommands(Map<String, Consumer<? extends Controller>> commands) {
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

    public MovementType getMovementType() {
        return movementType;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Tag getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }
}
