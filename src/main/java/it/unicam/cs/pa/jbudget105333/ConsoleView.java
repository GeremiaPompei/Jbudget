package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View<ViewController>{

    private final BufferedReader reader;

    public ConsoleView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void open(ViewController controller) throws IOException {
        String command = "";
        System.out.print("\n\n * JBUDGET * \n\n");
        do{
            System.out.print(" > ");
            System.out.flush();
            command = this.reader.readLine();
            System.out.println(processCommand(controller,command));
            controller.update();
            controller.save();
        }while(!command.equals("exit"));
    }

    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processCommand(ViewController controller,String string) throws IOException {
        String command = "";
        String argument = "";
        try {
            command = string.substring(0, string.indexOf(' ')).trim();
            argument = string.substring(string.indexOf(' ')).trim();
        }catch (Exception e){
            command = string;
        }
        switch (command) {
            case "showtransactionsd":
                command = controller.scheduleTransactionsDate(argument);
                break;
            case "showtransactionst":
                command = controller.scheduleTransactionsTag(argument);
                break;
            case "showtransactions":
                command = controller.showTransactions();
                break;
            case "newitransaction":
                command = controller.newITransaction(argument);
                break;
            case "newptransaction":
                command = controller.newPTransaction(argument);
                break;
            case "showaccounts":
                command = controller.showAccounts();
                break;
            case "newaccount":
                command = controller.newAccount(argument);
                break;
            case "showbudgets":
                command = controller.showBudgets();
                break;
            case "newbudget":
                command = controller.newBudgetRecord(argument);
                break;
            case "showtags":
                command = controller.showTags();
                break;
            case "newtag":
                command = controller.newTag(argument);
                break;
            case "exit" :
                command = "\nGoodBye!!\n";
                break;
            default:
                command = "Command unknown: " + string;
            }
        return command;
    }

}
