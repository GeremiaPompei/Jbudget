package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetManager;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportManager;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerManager;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Store.Reader;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Utility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di dare un'implementazione del controller dell'MVC per coordinare
 * le attività eseguite sull'applicazione. Tale implementazione permette di gestire accounts, tags,
 * transazioni e budgets di un budget report oltre a dare la possibilità di aggiornare lo stato dei
 * diversi componenti, salvare il budget report, ritornare il generatore degli id e tener traccia
 * del valore di superamento di un budget con relativo tag.
 */
public class MainControllerBase implements MainController{

    /**
     * Variabile utile alla gestione del log del MainController.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * BudgetReport del MainControllerBase.
     */
    private BudgetReport budgetReport;

    /**
     * Costruttore del mainControllerBase.
     */
    public MainControllerBase() {
        resetBudgetReport();
        this.logger.fine("Controller created.");
    }

    /**
     * Metodo responsabile di resettare il BudgetReport pulendo i suoi campi.
     */
    @Override
    public void resetBudgetReport(){
        this.budgetReport = BudgetReportManager.generateReport(LedgerManager.generateLedger()
                ,BudgetManager.generateBudget());
    }

    /**
     * Metodo responsabile di restituire gli account.
     * @return Accounts restituiti.
     */
    @Override
    public Set<Account> getAccounts(){
        this.logger.fine("Accounts getter.");
        return this.budgetReport.getLedger().getAccounts();
    }

    /**
     * Metodo responsabile di restituire l'account avente l'ID dato.
     * @param ID ID dell'Account ricercato.
     * @return Account ricercato.
     */
    @Override
    public Account getAccount(int ID){
        this.logger.fine("Account getter with ID:["+ID+"]");
        return this.budgetReport.getLedger().getAccount(ID);
    }

    /**
     * Metodo responsabile di aggiungere un Account.
     * @param account Account da aggiungere.
     */
    @Override
    public void addAccount(Account account){
        this.budgetReport.getLedger().addAccount(account);
        this.logger.fine("Addition of Account: ["+account.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un Account.
     * @param account Account da rimuovere.
     */
    @Override
    public void removeAccount(Account account){
        this.budgetReport.getLedger().removeAccount(account);
        this.logger.fine("Removal of Account: ["+account.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire i tag.
     * @return Tags restituiti.
     */
    @Override
    public Set<Tag> getTags(){
        this.logger.fine("Tags getter.");
        return this.budgetReport.getLedger().getTags();
    }

    /**
     * Metodo responsabile di restituire il tag avente l'ID dato.
     * @param ID ID del tag ricercato.
     * @return Tag ricercato.
     */
    @Override
    public Tag getTag(int ID){
        this.logger.fine("Tag getter with ID: ["+ID+"]");
        return this.budgetReport.getLedger().getTag(ID);
    }

    /**
     * Metodo responsabile di aggiungere un Tag.
     * @param tag Tag da aggiungere.
     */
    @Override
    public void addTag(Tag tag){
        this.budgetReport.getLedger().addTag(tag);
        this.logger.fine("Addition of Tag: ["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un tag.
     * @param tag Tag da rimuovere.
     */
    @Override
    public void removeTag(Tag tag){
        this.budgetReport.getLedger().removeTag(tag);
        removeBudgetRecord(tag);
        this.logger.fine("Removal of Tag: ["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire le transazioni.
     * @return Trasazioni restituite.
     */
    @Override
    public Set<Transaction> getTransactions(){
        this.logger.fine("Transactions getter.");
        return this.budgetReport.getLedger().getTransactions();
    }

    /**
     * Metodo responsabile di restituire la transazione avente l'ID dato.
     * @param ID ID della transazione ricercata.
     * @return Transazione ricercata.
     */
    @Override
    public Transaction getTransaction(int ID){
        this.logger.fine("Transaction getter with ID: ["+ID+"]");
        return this.budgetReport.getLedger().getTransaction(ID);
    }

    /**
     * Metodo responsabile di aggiungere una Transazione e di aggiungere a questa una serie di movimenti.
     * @param transaction Transazione da aggiungere.
     * @param movements Movimenti da aggiungere alla transazione.
     */
    @Override
    public void addTransaction(Transaction transaction, Collection<Movement> movements){
        if(!movements.isEmpty()&&transaction.getDate().toLocalDate().compareTo(LocalDate.now())>=0){
            transaction.addMovements(movements);
            this.budgetReport.getLedger().addTransaction(transaction);
            update();
            this.logger.fine("Addition of transaction: ["+transaction.toString()+"]");
        }else
            this.logger.fine("Failed in addition of transaction: ["+transaction.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere una Transazione.
     * @param transaction Transazione da rimuovere.
     */
    @Override
    public void removeTransaction(Transaction transaction){
        this.budgetReport.getLedger().removeTransaction(transaction);
        this.logger.fine("Removal of transaction: ["+transaction.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un Movimento.
     * @param movement Movimento da rimuovere.
     */
    @Override
    public void removeMovement(Movement movement) {
        movement.getTransaction().removeMovement(movement);
        movement.getTag().removeMovement(movement);
        movement.getAccount().removeMovement(movement);
        this.logger.fine("Removal of movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo che ha la responsabilità di modificare la descrizione di un oggetto istanziato da una classe
     * che implementa l'interfaccia Utility.
     * @param u Oggetto istanziato da una classe che implementa l'interfaccia Utility.
     * @param description Descrizione da sostituire.
     */
    @Override
    public void setDescription(Utility u, String description) {
        u.setDescription(description);
        this.logger.fine("Description of utility: ["+u.toString()+"] changed with: ["+description+"]");
    }

    /**
     * Metodo che ha la responsabilità di schedulare le transazioni in un certo range temporale.
     * @param start Inizio range.
     * @param stop Fine rande.
     * @return Serie di transazioni date dalla schedulazione.
     */
    @Override
    public Set<Transaction> scheduleTransactionsDate(LocalDateTime start, LocalDateTime stop) {
        if(start.isBefore(stop)) {
            Set<Transaction> stransactions = new TreeSet();
            this.budgetReport.getLedger().getTransactions()
                    .stream()
                    .filter(t -> t.getDate().isAfter(start))
                    .filter(t -> t.getDate().isBefore(stop))
                    .forEach(t -> stransactions.add(t));
            this.logger.fine("Transactions scheduled from :["+start+"] to ["+stop+"]");
            return stransactions;
        }
        this.logger.fine("Failed in scheduling transactions from :["+start+"] to ["+stop+"]");
        return null;
    }

    /**
     * Metodo che ha la responsabilità di schedulare le transazioni con un certo tag.
     * @param tag Tag appartenente alle transazioni schedulate.
     * @return Serie di transazioni date dalla schedulazione.
     */
    @Override
    public Set<Transaction> scheduleTransactionsTag(Tag tag){
        if(tag != null) {
            Set<Transaction> stransactions = new TreeSet();
            this.budgetReport.getLedger().getTransactions()
                    .stream()
                    .filter(t -> t.getTags().contains(tag))
                    .forEach(t -> stransactions.add(t));
            this.logger.fine("Transactions scheduled by tag :["+tag.toString()+"]");
            return stransactions;
        }
        this.logger.fine("Failed in scheduling transactions by tag :["+tag.toString()+"]");
        return null;
    }

    /**
     * Metodo responsabile di restituire una mappa contenente tag e valori associati di un Budget.
     * @return Mappa di tag e double.
     */
    @Override
    public Map<Tag,Double> getBudgetRecords(){
        this.logger.fine("BudgetRecords getter.");
        return this.budgetReport.getBudget().getBudgetMap();
    }

    /**
     * Metodo responsabile di aggiungere un tag e un valore al Budget.
     * @param tag Tag chiave.
     * @param value Value relativo al tag.
     */
    @Override
    public void addBudgetRecord(Tag tag, Double value){
        if(tag!=null && this.budgetReport.getLedger().getTags().contains(tag)) {
            this.budgetReport.getBudget().add(tag,value);
            this.logger.fine("Addition of budget record with key: ["
                    +tag.toString()+"] and value :["+value+"]");
        }else
            this.logger.fine("Failed in addition of budget record with key: ["
                +tag.toString()+"] and value :["+value+"]");
    }

    /**
     * Metodo responsabile di eliminare un tag dal Budget.
     * @param tag Tag da rimuovere.
     */
    @Override
    public void removeBudgetRecord(Tag tag){
        this.budgetReport.getBudget().remove(tag);
        this.logger.fine("Removal of budget record with key :["+tag.toString()+"]");
    }

    /**
     * Metodo responsabile di fare un controllo e restituire una mappa che associa, se presenti, ad ogni tag
     * per cui è stato fissato un budget la differenza tra saldo totale e il budget se questa è negativa
     * ; ovvero se il valore del budget fissato per quel tag viene superato.
     * @return Mappa con tag e differenze in negativo.
     */
    @Override
    public Map<Tag, Double> check(){
        Map<Tag, Double> result = new HashMap<>();
        this.budgetReport.check().keySet().stream()
                .filter(t->this.budgetReport.check().get(t)<0)
                .forEach(t->result.put(t,this.budgetReport.check().get(t)));
        this.logger.fine("Check getter.");
        return result;
    }

    /**
     * Metodo che ha la responsabilità di aggiornare il MainController.
     */
    @Override
    public void update(){
        this.budgetReport.getLedger().update();
        this.logger.fine("MainController updated.");
    }

    /**
     * Metodo che ha la responsabilità di leggere un budget report e sostituirlo.
     * @param reader Permette la lettura del budget report.
     * @throws IOException Eccezione dovuta ad un errore dovuto alla lettura.
     */
    @Override
    public void read(Reader<BudgetReport> reader) throws IOException {
        this.budgetReport = reader.read();
        reader.close();
        logger.info("File read correctly.");
    }

    /**
     * Metodo che ha la responsabilità di salvare un BudgetReport.
     * @param writer Writer con cui salvare il BudgetReport.
     * @throws IOException Eccezione dovuta ad un errore dovuto alla scrittura.
     */
    @Override
    public void save(Writer<BudgetReport> writer) throws IOException {
        if(writer!= null) {
            writer.write(this.budgetReport);
            writer.close();
            this.logger.fine("MainController saved.");
        }
    }

    /**
     * Metodo responsabile di ritornare l'IDGenerator.
     * @return IDGenerator restituito.
     */
    @Override
    public IDGenerator idGenerator(){
        this.logger.fine("IDGenerator getter.");
        return this.budgetReport.getLedger().getIDGenerator();
    }
}
