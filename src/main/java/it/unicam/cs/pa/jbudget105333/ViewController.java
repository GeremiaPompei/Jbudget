package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class ViewController {

    private final BudgetReportController budgetReportC;
    private final LedgerController ledgerC;
    private final BudgetController budgetC;
    private final IDGenerator idGenerator;
    private final Printer<Account> accountP;
    private final Printer<Tag> tagP;
    private final Printer<Transaction> transactionP;
    private final Scanner<? extends Account> accountS;
    private final Scanner<? extends ProgramTransaction> pTransactionS;
    private final Scanner<? extends TagBase> tagS;

    public ViewController(BudgetReportController budgetReportC) {
        this.budgetReportC = budgetReportC;
        this.ledgerC = budgetReportC.getLedgerC();
        this.budgetC = budgetReportC.getBudgetC();
        this.idGenerator = ledgerC.getIDGenerator();
        this.tagP = new TagBasePrinter();
        this.accountP = new AccountBasePrinter();
        this.transactionP = new TransactionBasePrinter(new MovementBasePrinter(this.accountP,this.tagP));
        this.accountS = new AccountBaseScanner();
        this.pTransactionS = new ProgramTransactionScanner();
        this.tagS = new TagBaseScanner();
    }

    public String scheduleTransactionsDate(String string){
        StringTokenizer st = new StringTokenizer(string,",");
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        try {
            Set<Transaction> transactionSet = this.ledgerC.scheduleTransactionsDate(
                    LocalDateTime.of(LocalDate.parse(st.nextToken().trim()), LocalTime.MIN),
                    LocalDateTime.of(LocalDate.parse(st.nextToken().trim()), LocalTime.MIN));
            transactionSet.stream()
                    .forEach(t -> result.set(result.get()+"\n"+this.transactionP.stringOf(t)));
        }catch (Exception e){
            result.set("Incorret Date Format");
        }
        return result.get();
    }

    public String scheduleTransactionsTag(String string){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        try {
            Set<Transaction> transactionSet = this.ledgerC.scheduleTransactionsTag(
                    Integer.parseInt(string));
            transactionSet.stream()
                    .forEach(t -> result.set(result.get()+"\n"+this.transactionP.stringOf(t)));
        }catch (Exception e){
            return "Incorret TagID Format";
        }
        return result.get();
    }

    public String  showTransactions(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.ledgerC.showTransactions().stream()
                .forEach(t -> result.set(result.get()+"\n"+this.transactionP.stringOf(t)));
        return result.get();
    }

    public String newITransaction(String string){
        String result = "Success!";
        Transaction it = new InstantTransaction(this.idGenerator);
        if (it == null || !this.ledgerC.newTransaction(it,newMovements(string,it)))
            return  "Incorrect ITransaction Format";
        return result;
    }

    public String newPTransaction(String string){
        String result = "Success!";
        StringTokenizer st = new StringTokenizer(string, "/");
        Transaction pt = this.pTransactionS.scanOf(st.nextToken().trim(),this.idGenerator);
        if (pt == null || !this.ledgerC.newTransaction(pt,newMovements(st.nextToken().trim(),pt)))
            return  "Incorrect PTransaction Format";
        return result;
    }

    private Set<Movement> newMovements(String string,Transaction transaction) {
        StringTokenizer st2 = new StringTokenizer(string, ";");
        Set<Movement> movements = new TreeSet<>();
        try {
            while (st2.hasMoreTokens()) {
                Account account = null;Tag tag = null;
                StringTokenizer st1 = new StringTokenizer(st2.nextToken().trim(), ",");
                Movement movement = new MovementBase(
                        MovementType.valueOf(st1.nextToken().trim().toUpperCase())
                        , Double.parseDouble(st1.nextToken().trim())
                        , transaction
                        , account = this.ledgerC.getAccount(Integer.parseInt(st1.nextToken()))
                        , tag = this.ledgerC.getTag(Integer.parseInt(st1.nextToken()))
                        , st1.nextToken().trim()
                        , idGenerator);
                movements.add(movement);
                if(account == null || tag == null)
                    throw new Exception();
            }
        } catch (Exception e) {
            movements = null;
        }
        return movements;
    }

    public String showAccounts(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.ledgerC.showAccounts().stream()
                .forEach(a -> result.set(result.get()+"\n"+this.accountP.stringOf(a)));
        return result.get();
    }

    public String newAccount(String string){
        String result = "Success!";
        Account a = this.accountS.scanOf(string,this.idGenerator);
        if(a==null)
            return "Incorret Account Format";
        this.ledgerC.newAccount(a);
        return result;
    }

    public String showTags(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.ledgerC.showTags().stream()
                .forEach(t -> result.set(result.get()+"\n"+this.tagP.stringOf(t)));
        return result.get();
    }

    public String newTag(String string){
        String result = "Success!";
        Tag t = this.tagS.scanOf(string,idGenerator);
        if(t==null)
            return "Incorret Tag Format";
        this.ledgerC.newTag(t);
        return result;
    }

    public String showBudgets(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        this.budgetC.getBudgetMap().keySet().stream()
                .forEach(t -> result.set(result.get()+"\n"+tagP.stringOf(t)
                        +": "+this.budgetC.getBudgetMap().get(t)));
        return result.get();
    }

    public String newBudgetRecord(String string){
        String result = "Success!";
        StringTokenizer st = new StringTokenizer(string,",");
        try {
            int tagID = Integer.parseInt(st.nextToken());
            double amount = Double.parseDouble(st.nextToken());
            if(!this.budgetC.addBudgetRecord(tagID,amount))
                result = "Tag not found";
        }catch (Exception e){
            return "Incorret Budget Format";
        }
        return result;
    }

    public String check(){
        AtomicReference<String> result = new AtomicReference<>();
        result.set("");
        Map<Tag,Double> mapCheck = this.budgetReportC.check();
        mapCheck.keySet().stream()
                .forEach(t -> result.set(result.get()+"\n"+tagP.stringOf(t)
                        +": "+mapCheck.get(t)+"\n"));
        return result.get();
    }

    public void update(){
        this.ledgerC.update();
    }

    public void save() throws IOException {
        this.budgetReportC.save();
    }

}
