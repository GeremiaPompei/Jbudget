package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerManager;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.BudgetReportWriterJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer.Writer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MainControllerBase implements MainController{

    private Ledger ledger;
    private Budget budget;
    private BudgetReport budgetReport;

    public MainControllerBase() {
        this.budgetReport = BudgetReportManager.generateReport(LedgerManager.generateLedger()
                ,BudgetManager.generateBudget());
        this.ledger = this.budgetReport.getLedger();
        this.budget = this.budgetReport.getBudget();
    }

    /**
     * Metodo che restituisce tutti gli account
     */
    @Override
    public Set<Account> getAccounts(){
        return this.ledger.getAccounts();
    }

    @Override
    public Account getAccount(int ID){
        return this.ledger.getAccount(ID);
    }

    /**
     * Metodo che permette di aggiungere un account
     * @param account
     */
    @Override
    public void addAccount(Account account){
        this.ledger.addAccount(account);
    }

    @Override
    public void removeAccount(Account account){
        this.ledger.removeAccount(account);
    }

    /**
     * Metodo che restituisce tutti i tag
     */

    @Override
    public Set<Tag> getTags(){
        return this.ledger.getTags();
    }

    @Override
    public Tag getTag(int ID){
        return this.ledger.getTag(ID);
    }

    /**
     * Metodo che permette di aggiungere un tag
     * @param tag
     */
    @Override
    public void addTag(Tag tag){
        this.ledger.addTag(tag);
    }

    @Override
    public void removeTag(Tag tag){
        this.ledger.removeTag(tag);
        removeBudgetRecord(tag);
    }

    /**
     * Metodo che restituisce tutte le transazioni
     */
    @Override
    public Set<Transaction> getTransactions(){
        return this.ledger.getTransactions();
    }

    @Override
    public Transaction getTransaction(int ID){
        return this.ledger.getTransaction(ID);
    }
    /**
     * Data una transazione e un set di metodi permette di creare una transazione
     * @param transaction
     * @return
     */
    @Override
    public boolean addTransaction(Transaction transaction){
        if(!transaction.getMovements().isEmpty()){
            this.ledger.addTransaction(transaction);
            return true;
        }
        return false;
    }

    @Override
    public void removeTransaction(Transaction transaction){
        this.ledger.removeTransaction(transaction);
    }

    @Override
    public Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop) {
        Set<Transaction> stransactions = new TreeSet();
        this.ledger.getTransactions()
                .stream()
                .filter(t -> t.getDate().isAfter(start))
                .filter(t -> t.getDate().isBefore(stop))
                .forEach(t->stransactions.add(t));
        return stransactions;
    }

    /*Metodo che permette di schedulare le transazioni restituendo quelle con un certo
    tag identificato dal tagID
     */
    @Override
    public Set<Transaction> scheduleTransactionsTag(Tag tag){
        if(tag != null) {
            Set<Transaction> stransactions = new TreeSet();
            this.ledger.getTransactions()
                    .stream()
                    .filter(t -> t.getTags().contains(tag))
                    .forEach(t -> stransactions.add(t));
            return stransactions;
        }
        return null;
    }

    @Override
    public Map<Tag,Double> getBudgetRecords(){
        return this.budget.getBudgetMap();
    }

    /*Permette di aggiungere un tag per ID grazie al metodo getTag del ledgerController e
    ritorna true se viene trovato il tag, false altrimenti
     */
    @Override
    public boolean addBudgetRecord(Tag tag, Double amount){
        if(tag!=null && this.ledger.getTags().contains(tag)) {
            this.budget.add(tag,amount);
            return true;
        }
        return false;
    }

    @Override
    public void removeBudgetRecord(Tag tag){
        this.budget.remove(tag);
    }

    @Override
    public Map<Tag, Double> check(){
        Map<Tag, Double> result = new HashMap<>();
        this.budgetReport.check().keySet().stream()
                .filter(t->this.budgetReport.check().get(t)<0)
                .forEach(t->result.put(t,this.budgetReport.check().get(t)));
        return result;
    }

    //Metodo per aggiornare il ledger
    @Override
    public void update(){
        this.ledger.update();
    }


    @Override
    public void save() {
        try {
            Writer<BudgetReport> writerL = new BudgetReportWriterJson("src/file/BudgetReport");
            writerL.write(this.budgetReport);
            writerL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IDGenerator idGenerator(){
        return this.ledger.getIDGenerator();
    }
}
