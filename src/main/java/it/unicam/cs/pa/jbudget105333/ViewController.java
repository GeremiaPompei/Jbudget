package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class ViewController {

    private final BudgetReportController controller;
    private final IDGenerator idGenerator;
    private final Printer<Account> accountP;
    private final Printer<Tag> tagP;
    private final Printer<Transaction> transactionP;
    private final Scanner<? extends Account> accountS;
    private final Scanner<? extends ProgramTransaction> pTransactionS;
    private final Scanner<? extends InstantTransaction> iTransactionS;
    private final Scanner<? extends TagBase> tagS;

    public ViewController(BudgetReportController controller) {
        this.controller = controller;
        this.idGenerator = this.controller.getLedger().getIDGenerator();
        this.tagP = new TagBasePrinter();
        this.accountP = new AccountBasePrinter();
        this.transactionP = new TransactionBasePrinter(new MovementBasePrinter(this.accountP,this.tagP));
        this.accountS = new AccountBaseScanner();
        this.pTransactionS = new ProgramTransactionScanner(this.controller.getLedger());
        this.iTransactionS = new InstantTransactionScanner(this.controller.getLedger());
        this.tagS = new TagBaseScanner();
    }

    public String scheduleTransactionsDate(String string){
        StringTokenizer st = new StringTokenizer(string,",");
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        try {
            Set<Transaction> transactionSet = this.controller.scheduleTransactionsDate(
                    LocalDateTime.of(LocalDate.parse(st.nextToken().trim()), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.parse(st.nextToken().trim()), LocalTime.MIN));
            transactionSet.stream()
                    .forEach(t -> result.set(result.get()+this.transactionP.stringOf(t)));
        }catch (Exception e){
            result.set("Incorret Date Format");
        }
        return result.get();
    }

    public String scheduleTransactionsTag(String string){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        try {
            Set<Transaction> transactionSet = this.controller.scheduleTransactionsTag(
                    Integer.parseInt(string));
            transactionSet.stream()
                    .forEach(t -> result.set(result.get()+this.transactionP.stringOf(t)));
        }catch (Exception e){
            return "Incorret TagID Format";
        }
        return result.get();
    }

    public String  showTransactions(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.controller.showTransactions().stream()
                .forEach(t -> result.set(result.get()+this.transactionP.stringOf(t)));
        return result.get();
    }

    public String newITransaction(String string){
        String result = "Success!";
        Transaction it = this.iTransactionS.scanOf(string,this.idGenerator);
        if(it == null)
            return "Incorrect ITransaction Format";
        this.controller.newTransaction(it);
        return result;
    }

    public String newPTransaction(String string){
        String result = "Success!";
        Transaction pt = this.pTransactionS.scanOf(string,this.idGenerator);
        if(pt == null)
            return "Incorrect PTransaction Format";
        this.controller.newTransaction(pt);
        return result;
    }

    public String showAccounts(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.controller.showAccounts().stream()
                .forEach(a -> result.set(result.get()+this.accountP.stringOf(a)));
        return result.get();
    }

    public String newAccount(String string){
        String result = "Success!";
        Account a = this.accountS.scanOf(string,this.idGenerator);
        if(a==null)
            return "Incorret Account Format";
        this.controller.newAccount(a);
        return result;
    }

    public String showTags(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.controller.showTags().stream()
                .forEach(t -> result.set(result.get()+this.tagP.stringOf(t)));
        return result.get();
    }

    public String newTag(String string){
        String result = "Success!";
        Tag t = this.tagS.scanOf(string,idGenerator);
        if(t==null)
            return "Incorret Tag Format";
        this.controller.newTag(t);
        return result;
    }

    public String showBudgets(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.controller.showBudgetRecords().keySet().stream()
                .forEach(t -> result.set(result.get()+tagP.stringOf(t)
                        +": "+this.controller.getBudget().getValue(t)+"\n"));
        return result.get();
    }

    public String newBudgetRecord(String string){
        String result = "Success!";
        StringTokenizer st = new StringTokenizer(string,",");
        try {
            int tagID = Integer.parseInt(st.nextToken());
            double amount = Double.parseDouble(st.nextToken());
            this.controller.newBudgetRecord(tagID,amount);
        }catch (Exception e){
            return "Incorret Budget Format";
        }
        return result;
    }

    public void update(){
        this.controller.update();
    }

    public void save() throws IOException {
        this.controller.save();
    }

}
