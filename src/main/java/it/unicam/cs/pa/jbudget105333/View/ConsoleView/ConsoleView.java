package it.unicam.cs.pa.jbudget105333.View.ConsoleView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountManager;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementManager;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagManager;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.ProgramTransaction;
import it.unicam.cs.pa.jbudget105333.View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
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
        this.commands.put("scheduletransdate","showtransactionsd AAAA-MM-DD(startDate),AAAA-MM-DD(stopDate)");
        this.commands.put("scheduletranstag","showtransactionst tagID");
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
    private String processCommand(MainController controller, String string) throws IOException {
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
            case "scheduletransdate":
                command = scheduleTransactionDate(controller,command,argument);
                break;
            case "scheduletranstag":
                command = scheduleTransactionTag(controller,command,argument);
                break;
            case "showtransactions":
                command = controller.getTransactions().toString();
                break;
            case "newitransaction":
                command = newITransaction(controller,command,argument);
                break;
            case "newptransaction":
                command = newPTransaction(controller,command,argument);
                break;
            case "showaccounts":
                command = controller.getAccounts().toString();
                break;
            case "newaccount":
                command = newAccount(controller,command,argument);
                break;
            case "showbudgets":
                command = controller.getBudgetRecords().toString();
                break;
            case "newbudget":
                command = newBudget(controller,command,argument);
                break;
            case "showtags":
                command = controller.getTags().toString();
                break;
            case "newtag":
                command = newTag(controller,command,argument);
                break;
            default:
                command = "Command unknown: " + string;
            }
        return command;
    }

    private String scheduleTransactionDate(MainController controller, String command, String argument){
        try{
            LocalDateTime start = LocalDateTime.of(LocalDate.parse(argument.substring
                    (0,argument.indexOf(',')).trim()), LocalTime.MIN);
            LocalDateTime stop = LocalDateTime.of(LocalDate.parse(argument.substring
                    (argument.indexOf(',')+1).trim()), LocalTime.MIN);
            command = controller.scheduleTransactionsDate(start,stop).toString();
        }catch (Exception e){
            command = "Error in "+command;
        }
        return command;
    }

    private String scheduleTransactionTag(MainController controller, String command, String argument){
        try{
            AtomicReference<Tag> tag = new AtomicReference<>();
            int tagId = Integer.parseInt(argument);
            controller.getTags().stream().filter(t->t.getID()==tagId).forEach(t->tag.set(t));
            command = controller.scheduleTransactionsTag(tag.get()).toString();
        }catch (Exception e){
            command = "Error in "+command;
        }
        return command;
    }

    private String newAccount(MainController controller, String command, String argument){
        try {
            StringTokenizer st = new StringTokenizer(argument,",");
            Account account= AccountManager.generateAccount(st.nextToken().trim(), st.nextToken().trim(),
                    Double.parseDouble(st.nextToken().trim()),AccountType.valueOf(st.nextToken().toUpperCase().trim())
                    , controller.idGenerator().generate());
            controller.addAccount(account);
            command = "Success!";
        }catch (Exception e){
            command = "Error in "+command;
        }
        return command;
    }

    private String newTag(MainController controller, String command, String argument){
        try {
            StringTokenizer st = new StringTokenizer(argument,",");
            controller.addTag(TagManager.generateTag(st.nextToken().trim(), st.nextToken().trim()
                    , controller.idGenerator().generate()));
            command = "Success!";
        }catch (Exception e){
            command = "Error in "+command;
        }
        return command;
    }

    private String newBudget(MainController controller, String command, String argument){
        try {
            StringTokenizer st = new StringTokenizer(argument,",");
            Tag tag = controller.getTag(Integer.parseInt(st.nextToken().trim()));
            controller.addBudgetRecord(tag, Double.parseDouble(st.nextToken().trim()));
            command = "Success!";
        }catch (Exception e){
            command = "Error in "+command;
        }
        return command;
    }

    private String newITransaction(MainController controller, String command, String argument){
        try {
            Transaction it = new InstantTransaction(controller.idGenerator().generate());
            it.addMovements(newMovements(argument, controller, it));
            command = "Success!";
            if (it == null || !controller.addTransaction(it))
                throw new NullPointerException();
        }catch (Exception e){
            command = "Error in" + command;
        }
        return command;
    }

    private String newPTransaction(MainController controller, String command, String argument){
        try {
            StringTokenizer st = new StringTokenizer(argument, "/");
            Transaction pt = new ProgramTransaction(LocalDateTime.of(LocalDate.parse(st.nextToken().trim())
                    , LocalTime.MIN), controller.idGenerator().generate());
            pt.addMovements(newMovements(st.nextToken().trim(), controller, pt));
            command = "Success!";
            if (pt == null || !controller.addTransaction(pt))
                throw new IllegalArgumentException();
        }catch (Exception e){
            command = "Error in" + command;
        }
        return command;
    }

    private Set<Movement> newMovements(String string,MainController controller, Transaction transaction) {
        StringTokenizer st2 = new StringTokenizer(string, ";");
        Set<Movement> movements = new TreeSet<>();
        try {
            while (st2.hasMoreTokens()) {
                Account account = null;Tag tag = null;
                StringTokenizer st1 = new StringTokenizer(st2.nextToken().trim(), ",");
                Movement movement = MovementManager.generateMovement(
                        MovementType.valueOf(st1.nextToken().trim().toUpperCase())
                        , Double.parseDouble(st1.nextToken().trim())
                        , transaction
                        , account = controller.getAccount(Integer.parseInt(st1.nextToken()))
                        , tag = controller.getTag(Integer.parseInt(st1.nextToken()))
                        , st1.nextToken().trim()
                        , controller.idGenerator().generate());
                movements.add(movement);
                if(account == null || tag == null)
                    throw new Exception();
            }
        } catch (Exception e) {
            movements = null;
        }
        return movements;
    }

}
