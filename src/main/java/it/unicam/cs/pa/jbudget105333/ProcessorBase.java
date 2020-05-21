package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class ProcessorBase <A extends Account,I extends Transaction
        ,P extends Transaction,T extends Tag>implements Processor<A,I,P,T>{

    private final BudgetReport budgetReport;
    private final IDGenerator idGenerator;
    private final Printer<Transaction> transactionP;
    private final Scanner<A> accountS;
    private final Scanner<P> pTransactionS;
    private final Scanner<I> iTransactionS;
    private final Scanner<T> tagS;

    public ProcessorBase(BudgetReport budgetReport, IDGenerator idGenerator, Printer<Transaction> transactionP
            , Scanner<A> accountS, Scanner<P> pTransactionS
            , Scanner<I> iTransactionS, Scanner<T> tagS) {
        this.budgetReport = budgetReport;
        this.idGenerator = idGenerator;
        this.transactionP = transactionP;
        this.accountS = accountS;
        this.pTransactionS = pTransactionS;
        this.iTransactionS = iTransactionS;
        this.tagS = tagS;
    }

    @Override
    public String scheduleTransactionsDate(String string){
        StringTokenizer st = new StringTokenizer(string,",");
        AtomicReference<String> result = new AtomicReference<>();
        try {
            this.budgetReport.getLedger().scheduleDate(
                    LocalDate.parse(st.nextToken().trim()),
                    LocalDate.parse(st.nextToken().trim()))
                    .stream()
                    .forEach(t -> result.set(result.get()+ this.transactionP.stringOf(t)));
        }catch (Exception e){
            result.set("Incorret Date Format");
        }
        return result.get();
    }

    @Override
    public String scheduleTransactionsTag(String string){
        AtomicReference<String> result = new AtomicReference<>();
        AtomicReference<Tag> tag = new AtomicReference<>();
        try {
            this.budgetReport.getLedger().getTags().stream()
                    .filter(t -> t.getID() == (Integer.parseInt(string)))
                    .forEach(t -> tag.set(t));
        }catch (Exception e){
            return "Incorret TagID Format";
        }
        result.set("");
        this.budgetReport.getLedger()
                .scheduleTag(tag.get())
                .stream()
                .filter(t->t.getTags().contains(tag.get()))
                .forEach(t-> result.set(result.get()+this.transactionP.stringOf(t)));
        if(result.get() == null)
            return "Tag Not Found";
        return result.get();
    }

    @Override
    public String newITransaction(String string){
        String result = "Success!";
        Transaction it = this.iTransactionS.scanOf(string,this.idGenerator);
        if(it == null)
            return "Incorrect ITransaction Format";
        if(!it.getMovements().isEmpty())
            this.budgetReport.getLedger().addTransaction(it);
        return result;
    }

    @Override
    public String newPTransaction(String string){
        String result = "Success!";
        Transaction pt = this.pTransactionS.scanOf(string,this.idGenerator);
        if(pt == null)
            return "Incorrect PTransaction Format";
        if(!pt.getMovements().isEmpty())
            this.budgetReport.getLedger().addTransaction(pt);
        return result;
    }

    @Override
    public String newAccount(String string){
        String result = "Success!";
        Account a = this.accountS.scanOf(string,this.idGenerator);
        if(a==null)
            return "Incorret Account Format";
        this.budgetReport.getLedger().addAccount(a);
        return result;
    }

    @Override
    public String newBudgetRecord(String string){
        String result = "Success!";
        StringTokenizer st = new StringTokenizer(string,",");
        AtomicReference<Tag> tag = new AtomicReference<>();
        double value = 0.0;
        try {
            this.budgetReport.getLedger().getTags().stream()
                    .filter(t -> t.getID() == (Integer.parseInt(st.nextToken())))
                    .forEach(t -> tag.set(t));
            value = Double.parseDouble(st.nextToken());
        }catch (Exception e){
            return "Incorret Budget Format";
        }
        this.budgetReport.getBudget().add(tag.get(),value);
        return result;
    }

    @Override
    public String newTag(String string){
        String result = "Success!";
        Tag t = this.tagS.scanOf(string,idGenerator);
        if(t==null)
            return "Incorret Tag Format";
        this.budgetReport.getLedger().addTag(t);
        return result;
    }
}
