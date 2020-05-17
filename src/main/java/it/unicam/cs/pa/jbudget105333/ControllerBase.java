package it.unicam.cs.pa.jbudget105333;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ControllerBase implements Controller<BudgetReport>{

    private Map<String,Consumer<? extends Controller>> commands = null;
    private BudgetReport budgetReport = null;
    private boolean state = false;

    public ControllerBase(BudgetReport budgetReport) {
        this.commands = new HashMap<>();
        this.budgetReport = budgetReport;
        this.state = true;
    }

    @Override
    public void processCommand(String command) {
        try {
            if(command.toLowerCase().equals("newtransaction")){
                Transaction transaction = new InstantTransaction();
                ControllerInstantTransaction controller = new ControllerInstantTransaction(this.budgetReport,transaction);
                controller.addCommand("exit",t->t.shutdown());
                Consumer<ControllerInstantTransaction> c =
                        t->transaction.addMovement(new MovementBase(t.getMovementType(), t.getAmount()
                                , transaction, t.getAccount(), t.getLocalDate(), t.getTag(), t.getDescription()));
                controller.addCommand("create",c);
                View view = new ConsoleViewTransaction();
                view.open(controller);
                view.close();
            }
            else
            if(command.substring(0,6).toLowerCase().equals("newtag"))
                this.budgetReport.getLedger()
                        .addTag(new TagBaseScanner().scanOf(command.substring(6)));
            else
            if(command.substring(0,10).toLowerCase().equals("newaccount"))
                this.budgetReport.getLedger()
                        .addAccount(new AccountBaseScanner().scanOf(command.substring(10)));
            else
            if(command.substring(0,9).toLowerCase().equals("newbudget"))
                new BudgetBaseScanner(this.budgetReport).scanOf(command.substring(9));
        }catch (Exception e) {
            Consumer<Controller> action = (Consumer)this.commands.get(command);
            if(action == null)
                System.err.println("Command unknown: "+command);
            else
                action.accept( this);
        }finally {
            this.budgetReport.getLedger().update();
        }
    }

    @Override
    public Set getCommands() {
        return this.commands.keySet();
    }

    @Override
    public BudgetReport getBudgetReport() {
        return this.budgetReport;
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
}
