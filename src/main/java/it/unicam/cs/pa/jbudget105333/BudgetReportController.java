package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class BudgetReportController {

    private final BudgetReport budgetReport;
    private final Writer<BudgetReport> writerB;

    public BudgetReportController() throws IOException {
        this.budgetReport = BudgetManager.generateReport();
        this.writerB = new BudgetReportWriter("src/file/BudgetReport.txt");
    }

    public Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop) {
        Set<Transaction> stransactions = new TreeSet();
        this.budgetReport.getLedger().getTransactions()
                .stream()
                .filter(t -> t.getDate().isAfter(start))
                .filter(t -> t.getDate().isBefore(stop))
                .forEach(t->stransactions.add(t));
        return stransactions;
    }

    public Set<Transaction> scheduleTransactionsTag(int tagID){
        Set<Transaction> stransactions = new TreeSet();
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.budgetReport.getLedger().getTags().stream()
                .filter(t -> t.getID() == tagID)
                .forEach(t -> tag.set(t));
        this.budgetReport.getLedger().getTransactions()
                .stream()
                .filter(t -> t.getTags().contains(tag.get()))
                .forEach(t->stransactions.add(t));
        return stransactions;
    }

    public Set<Transaction> showTransactions(){
        Set<Transaction> stransactions = new TreeSet();
        this.budgetReport.getLedger().getTransactions().stream().forEach(t->stransactions.add(t));
        return stransactions;
    }

    public void newTransaction(Transaction transaction){
        if(!transaction.getMovements().isEmpty())
            this.budgetReport.getLedger().addTransaction(transaction);
    }

    public Set<Account> showAccounts(){
        Set<Account> accounts = new TreeSet();
        this.budgetReport.getLedger().getAccounts().stream().forEach(a->accounts.add(a));
        return accounts;
    }

    public void newAccount(Account account){
        this.budgetReport.getLedger().addAccount(account);
    }

    public Set<Tag> showTags(){
        Set<Tag> tags = new TreeSet();
        this.budgetReport.getLedger().getTags().stream().forEach(t->tags.add(t));
        return tags;
    }

    public void newTag(Tag tag){
        this.budgetReport.getLedger().addTag(tag);
    }

    public Map<Tag, Double> showBudgetRecords(){
        return this.budgetReport.getBudget().getBudget();
    }

    public void newBudgetRecord(int tagID,Double amount){
        AtomicReference<Tag> tag = new AtomicReference<>();
        this.budgetReport.getLedger().getTags().stream()
                .filter(t -> t.getID() == tagID)
                .forEach(t -> tag.set(t));
        this.budgetReport.getBudget().add(tag.get(),amount);
    }

    public void update(){
        this.getLedger().update();
    }

    public void save() throws IOException {
        if (this.writerB != null) {
            this.writerB.write(this.budgetReport);
            try {
                this.writerB.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Ledger getLedger(){
        return this.budgetReport.getLedger();
    }

    public Budget getBudget(){
        return this.budgetReport.getBudget();
    }
}
