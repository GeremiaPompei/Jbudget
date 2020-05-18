package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ControllerBase<B extends BudgetReport> implements Controller<B>{

    private Map<String,Consumer<Controller<B>>> commands = null;
    private B budgetReport = null;
    private boolean state = false;
    private Store store = null;

    public ControllerBase(B budgetReport,Store store) {
        this.commands = new HashMap<>();
        this.budgetReport = budgetReport;
        this.state = true;
        this.store = store;
    }

    @Override
    public void processCommand(String command) throws IOException {
        try {
            if(command.equalsIgnoreCase("newitransaction")) {
                InstantTransaction transaction = new InstantTransaction();
                createTransaction(transaction);
            }else
            if(command.substring(0,15).equalsIgnoreCase("newptransaction")) {
                ProgramTransaction transaction =
                        new ProgramTransaction(LocalDateTime.of(
                                LocalDate.parse(command.substring(15).trim()), LocalTime.MIN));
                if(transaction.getDate().isAfter(LocalDateTime.now()))
                    createTransaction(transaction);
            }else
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
            else
                throw new Exception();
        }catch (Exception e) {
            Consumer<Controller<B>> action = this.commands.get(command);
            if(action == null)
                System.err.println("Command unknown: "+command);
            else
                action.accept( this);
        }finally {
            this.budgetReport.getLedger().update();
            this.store.write(this.budgetReport);
        }
    }

    @Override
    public Set getCommands() {
        return this.commands.keySet();
    }

    @Override
    public B getBudgetReport() {
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

    private void createTransaction(Transaction transaction) throws IOException {
        ControllerTransaction<Ledger,Transaction> controller =
                new ControllerTransaction<>(this.budgetReport.getLedger(),transaction);
        controller.addCommand("exit",c->c.shutdown());
        controller.addCommand("help",c->System.out.println(c.getCommands().toString()));
        controller.addCommand("newmovement"
                ,c->System.out.println("movementType,amount,accountName,tagName,description"));
        controller.addCommand("showmovtype",m-> Arrays.stream(MovementType.values())
                .forEach(n->System.out.println(n.toString())));
        controller.addCommand("showtags"
                ,c->this.budgetReport.getLedger().getTags().stream()
                        .forEach(t->System.out.println(new TagBasePrinter<>().stringOf(t))));
        controller.addCommand("showaccounts"
                ,c->this.budgetReport.getLedger().getAccounts().stream()
                        .forEach(a->System.out.println(new AccountBasePrinter<>().stringOf(a))));
        ConsoleViewTransaction<ControllerTransaction> view =
                new ConsoleViewTransaction<>(transaction);
        view.open(controller);
        if(!transaction.getMovements().isEmpty())
            this.budgetReport.getLedger().addTransaction(transaction);
    }

}
