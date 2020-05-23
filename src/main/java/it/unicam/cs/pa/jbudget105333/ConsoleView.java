package it.unicam.cs.pa.jbudget105333;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

public class ConsoleView implements View<ViewController>{

    private final BufferedReader reader;
    private final Map<String,String> commands;

    //Il costruttore inizializza il bufferReader e la mappa dei comandi
    public ConsoleView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.commands = new HashMap<>();
    }

    /*Metodo utilizzato per richiedere all'utente l'inserimento di un comando e stampare il risultato
    a console
     */
    @Override
    public void open(ViewController controller) throws IOException {
        String command = "";
        addCommands();
        System.out.print("\n\n * JBUDGET * \n\n");
        do{
            printState(controller);
            System.out.print(" > ");
            System.out.flush();
            command = this.reader.readLine();
            System.out.println(processCommand(controller,command));
            controller.update();
            controller.save();
        }while(!command.equals("exit"));
    }

    //Metodo utilizzato per la chiusura del bufferedReader
    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Metodo utilizzato per stampare la data dell'ultimo aggiornamento e i tag dei budget superati
    se presenti
     */
    private void printState(ViewController controller){
        String check = controller.check();
        if(!check.isEmpty())
            System.out.print("\nATTENTION:"+check);
        System.out.println("["+ LocalDateTime.now().format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) +"]");
    }

    //Aggiunta di comandi alla mappa
    private void addCommands(){
        this.commands.put("showtransactionsd","showtransactionsd AAAA-MM-DD(startDate),AAAA-MM-DD(stopDate)");
        this.commands.put("showtransactionst","showtransactionst tagID");
        this.commands.put("newitransaction","newitransaction movementType1,amount1,accountID1,tagID1,description1;"+
                "movementType2,amount2,accountID2,tagID2,description2;...");
        this.commands.put("newptransaction","newptransaction AAAA-MM-DD(date)/movementType1,amount1,accountID1" +
                ",tagID1,description1;movementType2,amount2,accountID2,tagID2,description2;...");
        this.commands.put("newaccount","newaccount name,description,initialBalance,accountType");
        this.commands.put("newbudget","newbudget tagID,amount");
        this.commands.put("newtag","newtag name,description");
        this.commands.put("exit","\nGoodBye!!\n");
        this.commands.put("help",this.commands.keySet().toString());
    }

    //Processore dei comandi che usa i metodi del viewController per processarli uno ad uno
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
            default:
                command = this.commands.get(command);
                if(command == null)
                    command = "Command unknown: " + string;
            }
        return command;
    }

}
