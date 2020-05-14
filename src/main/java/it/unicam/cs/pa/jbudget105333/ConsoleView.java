package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView<B extends Bilancio,BP extends BilancioPrinter<B>> implements View<B> {

    private final BP bilancioPrinter;
    private final BufferedReader reader;

    public ConsoleView(BP bilancioPrinter) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.bilancioPrinter = bilancioPrinter;
    }

    @Override
    public void open(Controller<B> controller) throws IOException {
        hello();
        while(controller.isOn()){
            printState(controller);
            System.out.print(" > ");
            System.out.flush();
            String command = reader.readLine();
            controller.processCommand(command);
        }
        goodbye();
    }

    @Override
    public void close() {
        try {
            this.reader.close();
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

    private void printState(Controller<B> controller){
        System.out.println("["+ bilancioPrinter.stringOf(controller.getStato())+"]");
    }

}
