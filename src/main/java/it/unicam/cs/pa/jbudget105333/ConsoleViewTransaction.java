package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleViewTransaction<C extends ControllerTransaction> implements View<C>{

    private BufferedReader reader = null;
    private Transaction transaction = null;

    public ConsoleViewTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void open(C controller) throws IOException {
        while(controller.isOn()){
            printState(controller);
            System.out.print(" Transaction > ");
            System.out.flush();
            String command = this.reader.readLine();
            controller.processCommand(command);
        }
    }

    @Override
    public void close() {}

    private void printState(C controller){
        System.out.println();
        if(this.transaction.getMovements().isEmpty())
            System.out.println("[NO MOVEMENTS]");
        else{
            transaction.getMovements().stream()
                    .forEach(m->System.out.println("["+new MovementBasePrinter().stringOf(m)+"]"));
        }
    }
}
