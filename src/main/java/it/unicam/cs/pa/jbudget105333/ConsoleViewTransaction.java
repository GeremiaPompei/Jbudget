package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleViewTransaction implements View<ControllerInstantTransaction>{

    private BufferedReader reader = null;

    public ConsoleViewTransaction() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void open(ControllerInstantTransaction controller) throws IOException {
        while(controller.isOn()){
            printState(controller);
            System.out.print(" IstantTransaction > ");
            System.out.flush();
            String command = this.reader.readLine();
            controller.processCommand(command);
        }
    }

    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void printState(ControllerInstantTransaction controller){
        try{
            System.out.println("["+controller.getMovementType().toString()+"] ["+controller.getAmount()
                    +"] ["+controller.getAccount().getName()+"] ["+controller.getLocalDate().toString()+"] ["+
                    controller.getTag().getName()+"] ["+controller.getDescription()+"]");
        }catch (Exception e){
            System.out.println("[EMPTY]");
        }
    }
}
