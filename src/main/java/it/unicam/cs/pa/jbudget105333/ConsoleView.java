package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ConsoleView<B extends  BudgetReport,C extends Controller<B>> implements View<C>{

    private BufferedReader reader = null;

    public ConsoleView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void open(C controller) throws IOException {
        hello();
        while(controller.isOn()){
            printState(controller);
            System.out.print(" > ");
            System.out.flush();
            String command = this.reader.readLine();
            controller.processCommand(command);
        }
        goodbye();
    }

    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hello() {
        System.out.println("*****************************");
        System.out.println("         JBUDGET 1.0         ");
        System.out.println("*****************************");
    }

    private void goodbye() {
        System.out.println("\nThank you for having used JBudget");
        System.out.println("See you next time!\n");
    }

    private void printState(C controller){
        System.out.println();
        if(controller.getBudgetReport().getLedger().getAccounts().isEmpty())
            System.out.println("NO ACCOUNTS");
        else{
            System.out.println("ACCOUNTS:");
            controller.getBudgetReport().getLedger().getAccounts().stream()
                    .forEach(a -> System.out.println
                            ("   "+new AccountBasePrinter<>().stringOf(a)));
            controller.getBudgetReport().check().keySet().stream()
                    .filter(t->controller.getBudgetReport().check().get(t)<0)
                    .forEach(t->System.out.println("ATTENTION: "+t.getName()
                            +": "+controller.getBudgetReport().check().get(t)));
        }
        System.out.println(LocalDateTime.now());
    }
}
