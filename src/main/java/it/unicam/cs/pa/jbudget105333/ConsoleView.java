package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View<ControllerBase>{

    private BufferedReader reader = null;

    public ConsoleView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void open(ControllerBase controller) throws IOException {
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

    private void printState(ControllerBase controller){
        System.out.println();
        if(controller.getBudgetReport().getLedger().getAccounts().isEmpty())
            System.out.println("NO ACCOUNTS");
        else
            controller.getBudgetReport().getLedger().getAccounts().stream()
                    .forEach(a -> System.out.println
                            (new AccountBasePrinter<>().stringOf(a)));
    }
}
