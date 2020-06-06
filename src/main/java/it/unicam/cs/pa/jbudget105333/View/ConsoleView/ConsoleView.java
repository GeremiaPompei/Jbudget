package it.unicam.cs.pa.jbudget105333.View.ConsoleView;

import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class ConsoleView implements View {

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
    public void open(MainController controller) throws IOException {
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
    private void printState(MainController controller){
        Map<Tag,Double> check = controller.check();
        if(!check.isEmpty())
            System.out.print("\n\nATTENTION:"+check.toString());
        System.out.println("\n["+ LocalDateTime.now().format(
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
        this.commands.put("accountType", AccountType.ASSETS.toString()+" , "+AccountType.LIABILITIES.toString());
        this.commands.put("movementType", MovementType.CREDITS.toString()+" , "+MovementType.DEBIT.toString());
        this.commands.put("exit","\nGoodBye!!\n");
        Set<String> com = new TreeSet<>();
        com.addAll(this.commands.keySet());
        com.add("showtags");
        com.add("showaccounts");
        com.add("showtransactions");
        com.add("showbudgets");
        this.commands.put("help",com.toString());
    }

    //Processore dei comandi che usa i metodi del viewController per processarli uno ad uno
    private String processCommand(MainController controller,String string) throws IOException {
        String command = "";
        String argument = "";
        command = this.commands.get(string);
        if(command != null)
            return command;
        try {
            command = string.substring(0, string.indexOf(' ')).trim();
            argument = string.substring(string.indexOf(' ')).trim();
        }catch (Exception e){
            command = string;
        }
        switch (command) {
            case "showtransactionsd":
                try{
                    LocalDateTime start = LocalDateTime.of(LocalDate.parse(argument.substring
                            (0,argument.indexOf(',')).trim()), LocalTime.MIN);
                    LocalDateTime stop = LocalDateTime.of(LocalDate.parse(argument.substring
                            (argument.indexOf(',')).trim()), LocalTime.MIN);
                    command = controller.scheduleTransactionsDate(start,stop).toString();
                }catch (Exception e){
                    command = "Error in "+command;
                }
                break;
            case "showtransactionst":
                try{
                    AtomicReference<Tag> tag = new AtomicReference<>();
                    int tagId = Integer.parseInt(argument);
                    controller.getTags().stream().filter(t->t.getID()==tagId).forEach(t->tag.set(t));
                    command = controller.scheduleTransactionsTag(tag.get()).toString();
                }catch (Exception e){
                    command = "Error in "+command;
                }
                break;
            case "showtransactions":
                command = controller.getTransactions().toString();
                break;
            case "newitransaction":
                //command = controller.addTransaction(argument);
                break;
            case "newptransaction":
                //command = controller.addTransaction(argument);
                break;
            case "showaccounts":
                command = controller.getAccounts().toString();
                break;
            case "newaccount":
                //command = controller.addAccount(argument);
                break;
            case "showbudgets":
                command = controller.getBudgetRecords().toString();
                break;
            case "newbudget":
                //command = controller.addBudgetRecord(argument);
                break;
            case "showtags":
                command = controller.getTags().toString();
                break;
            case "newtag":
                //command = controller.addTag(argument);
                break;
            default:
                command = "Command unknown: " + string;
            }
        return command;
    }

}
