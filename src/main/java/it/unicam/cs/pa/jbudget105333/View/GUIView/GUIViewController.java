package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Controller.MainControllerManager;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountManager;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetReaderJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Json.JBudgetWriterJson;
import it.unicam.cs.pa.jbudget105333.Model.Store.Writer;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagManager;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionManager;
import it.unicam.cs.pa.jbudget105333.Model.Utility;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di fare da controller alla view principale
 * permettendo di renderla dinamica e gestire l'interazione con il controller
 * dell'MVC così da consentire l'utente di interfacciarsi in maniera dinamica
 * con il resto dell'applicazione.
 */
public class GUIViewController implements Initializable {

    /**
     * Variabile utile alla gestione del log del MainController.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Controller dell'MVC usato per avviare le diverse funzionalità
     * richieste dall'utente.
     */
    private MainController controller;

    /**
     * Tabella che presenta le informazioni relative agli account presenti.
     */
    @FXML TableView<Account> tableAccount;

    /**
     * Colonna della tabella degli Account che memorizza gli ID degli account.
     */
    @FXML TableColumn<Account,Integer> accountID;

    /**
     * Colonna della tabella degli Account che memorizza i nomi degli account.
     */
    @FXML TableColumn<Account,String> accountName;

    /**
     * Colonna della tabella degli Account che memorizza i bilanci degli account.
     */
    @FXML TableColumn<Account,Double> accountBalance;

    /**
     * Colonna della tabella degli Account che memorizza i bilanci iniziali
     * degli account.
     */
    @FXML TableColumn<Account,Double> accountOpeningBalance;

    /**
     * Colonna della tabella degli Account che memorizza i tipi degli account.
     */
    @FXML TableColumn<Account, AccountType> accountType;

    /**
     * Colonna della tabella degli Account che memorizza la descrizione degli account.
     */
    @FXML TableColumn<Account,String> accountDescription;

    /**
     * ObservableList contenente gli account presenti nel ledger.
     */
    private ObservableList<Account> olAccount;

    /**
     * Campo da completare con il nome del nuovo account per inserirne uno nuovo.
     */
    @FXML TextField accountNewName;

    /**
     * Campo da completare con la descrizione del nuovo account per inserirne uno nuovo.
     */
    @FXML TextField accountNewDescription;

    /**
     * Campo da completare con il bilancio iniziale del nuovo account per inserirne uno nuovo.
     */
    @FXML TextField accountNewOpeningBalance;

    /**
     * Campo da scegliere per selezionare il tipo del nuovo account per inserirne uno nuovo.
     */
    @FXML ChoiceBox<AccountType> accountNewType;

    /**
     * ObservableList contenente i tipi di account da selezionare..
     */
    private ObservableList<AccountType> olAccountType;

    /**
     * Tabella che presenta le informazioni relative ai budget presenti.
     */
    @FXML TableView<Map.Entry<Tag,Double>> tableBudget;

    /**
     * Colonna della tabella dei Budget che memorizza il tag del budget.
     */
    @FXML TableColumn<Map.Entry<Tag,Double>,Tag> budgetTagID;

    /**
     * Colonna della tabella dei Budget che memorizza il valore per un certo tag del budget.
     */
    @FXML TableColumn<Map.Entry<Tag,Double>,Double> budgetValue;

    /**
     * ObservableList contenente i diversi record rappresentanti attraverso la coppia
     * tag-valore un budget.
     */
    private ObservableList<Map.Entry<Tag,Double>> olBudget;

    /**
     * Campo da scegliere per selezionare il tag su cui impostare il budget.
     */
    @FXML ChoiceBox<Tag> budgetNewTagId;

    /**
     * Campo da completare con il valore del budget per un certo tag.
     */
    @FXML TextField budgetNewValue;

    /**
     * Tabella che presenta le informazioni relative ai tag presenti.
     */
    @FXML TableView<Tag> tableTag;

    /**
     * Colonna della tabella dei Tag che memorizza gli ID dei Tag.
     */
    @FXML TableColumn<Tag,Integer> tagID;

    /**
     * Colonna della tabella dei Tag che memorizza i nomi dei Tag.
     */
    @FXML TableColumn<Tag,String> tagName;

    /**
     * Colonna della tabella deei Tag che memorizza il saldo totale dei Tag.
     */
    @FXML TableColumn<Tag,Double> tagTAmount;

    /**
     * Colonna della tabella dei Tag che memorizza le descrizioni dei Tag.
     */
    @FXML TableColumn<Tag,String> tagDescription;

    /**
     * ObservableList contenente i tag presenti nel ledger.
     */
    private ObservableList<Tag> olTag;

    /**
     * Campo da completare con il nome di un nuovo tag.
     */
    @FXML TextField tagNewName;

    /**
     * Campo da completare con la descrizione di un nuovo tag.
     */
    @FXML TextField tagNewDescription;

    /**
     * Tabella che presenta le informazioni relative alle transazioni presenti.
     */
    @FXML TableView<Transaction> tableTransaction;

    /**
     * Colonna della tabella delle Transazioni che memorizza gli ID delle Transazioni.
     */
    @FXML TableColumn<Transaction,Integer> transactionID;

    /**
     * Colonna della tabella delle Transazioni che memorizza le date delle Transazioni.
     */
    @FXML TableColumn<Transaction, LocalDate> transactionDate;

    /**
     * Colonna della tabella delle Transazioni che memorizza il saldo totale delle Transazioni.
     */
    @FXML TableColumn<Transaction,Double> transactionTAmount;

    /**
     * Colonna della tabella delle Transazioni che memorizza le descrizioni delle Transazioni.
     */
    @FXML TableColumn<Transaction,String> transactionDescription;

    /**
     * ObservableList che contiene tutte le transazioni del ledger..
     */
    private ObservableList<Transaction> olTransaction;

    /**
     * RadioButton utile per selezionare una transazione programmata.
     */
    @FXML RadioButton programTransaction;

    /**
     * RadioButton utile per selezionare una transazione istantanea.
     */
    @FXML RadioButton instantTransaction;

    /**
     * Meccanismo utile per visualizzare e scegliere una data da assegnare alla transazione programmata.
     */
    @FXML DatePicker transactionNewDate;

    /**
     * Campo responsabile di gestire l'aggiunta della descrizione della transazione.
     */
    @FXML TextField transactionNewDescription;

    /**
     * Campo da scegliere per selezionare il tag da usare per schedulare le transazioni.
     */
    @FXML ChoiceBox<Tag> tagSchedChoice;

    /**
     * RadioButton utile per selezionare se schedulare le transazioni per tag.
     */
    @FXML RadioButton scheduleTag;

    /**
     * RadioButton utile per selezionare se schedulare le transazioni per data.
     */
    @FXML RadioButton scheduleDate;

    /**
     * Meccanismo utile per visualizzare e scegliere una data di inizio per la schedulazione
     * delle transazioni.
     */
    @FXML DatePicker dateStart;

    /**
     * Meccanismo utile per visualizzare e scegliere una data di fine per la schedulazione
     * delle transazioni.
     */
    @FXML DatePicker dateStop;

    /**
     * Tabella che presenta le informazioni relative ai movimenti derivati da un oggetto come
     * account, transazione, tag o budget.
     */
    @FXML TableView<Movement> tableMovement;

    /**
     * Colonna della tabella deli Movimenti che memorizza gli ID dei Movimenti.
     */
    @FXML TableColumn<Movement,Integer> movementID;

    /**
     * Colonna della tabella deli Movimenti che memorizza i saldi dei Movimenti.
     */
    @FXML TableColumn<Movement,Double> movementAmount;

    /**
     * Colonna della tabella deli Movimenti che memorizza i tipi dei Movimenti.
     */
    @FXML TableColumn<Movement, MovementType> movementType;

    /**
     * Colonna della tabella deli Movimenti che memorizza le date dei Movimenti.
     */
    @FXML TableColumn<Movement, LocalDate> movementDate;

    /**
     * Colonna della tabella deli Movimenti che memorizza i tag dei Movimenti.
     */
    @FXML TableColumn<Movement,Tag> movementTagID;

    /**
     * Colonna della tabella deli Movimenti che memorizza le transazioni dei Movimenti.
     */
    @FXML TableColumn<Movement,Integer> movementTransactionID;

    /**
     * Colonna della tabella deli Movimenti che memorizza gli account dei Movimenti.
     */
    @FXML TableColumn<Movement,Account> movementAccountID;

    /**
     * Colonna della tabella deli Movimenti che memorizza le descrizioni dei Movimenti.
     */
    @FXML TableColumn<Movement,String> movementDescription;

    /**
     * ObservableList contenente i movimenti appartenuti ad un oggetto come tag, account
     * , transazione o budget.
     */
    private ObservableList<Movement> olMovement;

    /**
     * Variabile che fa riferimento all'Utility selezionata.
     */
    private Utility selectedUtility;

    /**
     * Area di testo che contiene la descrizione dell'Utility.
     */
    @FXML TextArea descriptionArea;

    /**
     * Variabile utile per salvare le operazioni all'interno dello stesso path.
     */
    private Writer writer;

    /**
     * Campo di avviso di successo o fallimento riguardo un'operazione e notifiche.
     */
    @FXML Label notificationLabel;

    /**
     * Metodo responsabile della rimozione di un Account.
     */
    @FXML
    public void removeAccount() {
        Account a = tableAccount.getSelectionModel().getSelectedItem();
        if(!tableAccount.getItems().isEmpty()&&a!=null) {
            this.controller.removeAccount(a);
            updateTables();
            save();
            logger.info("Removal of account: ["+a.toString()+"]");
        }
    }

    /**
     * Metodo responsabile dell'aggiunta di un Account.
     */
    @FXML
    public void addAccount() {
        try {
            Account a = AccountManager.generateAccount(accountNewName.getText(), accountNewDescription.getText()
                    , Double.parseDouble(accountNewOpeningBalance.getText()), accountNewType.getValue()
                    ,this.controller.idGenerator().generate());
            this.controller.addAccount(a);
            save();
            updateTables();
            notificationLabel.setText("Success!");
            logger.info("Addition of account: ["+a.toString()+"]");
        }catch (Exception e){
            notificationLabel.setText("Add Account Failed");
            logger.warning("Failed in Account Addition.");
        }finally {
            accountNewName.clear();
            accountNewDescription.clear();
            accountNewOpeningBalance.clear();
        }
    }

    /**
     * Metodo responsabile della rimozione di un tag.
     */
    @FXML
    public void removeTag(){
        Tag t = tableTag.getSelectionModel().getSelectedItem();
        if(!tableTag.getItems().isEmpty()&&t!=null) {
            this.controller.removeTag(t);
            updateTables();
            save();
            logger.info("Removal of tag: ["+t.toString()+"]");
        }
    }

    /**
     * Metodo responsabile dell'aggiunta di un tag.
     */
    @FXML
    public void addTag(){
        try{
            Tag t = TagManager.generateTag(tagNewName.getText(),tagNewDescription.getText()
                    ,this.controller.idGenerator().generate());
            this.controller.addTag(t);
            updateTables();
            notificationLabel.setText("Success!");
            logger.info("Addition of tag: ["+t.toString()+"]");
        }catch (Exception e){
            notificationLabel.setText("Add Tag Failed");
            logger.warning("Failed in Tag Addition.");
        }finally {
            tagNewName.clear();
            tagNewDescription.clear();
            save();
        }
    }

    /**
     * Metodo responsabile della rimozione di una transazione.
     */
    @FXML
    public void removeTransaction(){
        Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
        if(!tableTransaction.getItems().isEmpty()&&t!=null) {
            this.controller.removeTransaction(t);
            updateTables();
            save();
            logger.info("Removal of transaction: ["+t.toString()+"]");
        }
    }

    /**
     * Metodo responsabile dell'aggiunta di una transazione.
     */
    @FXML
    public void addTransaction(){
        Transaction transaction = null;
        if(instantTransaction.isSelected())
            transaction = addInstantTransaction();
        else if (programTransaction.isSelected())
            transaction = addProgramTransaction();
        if(transaction!=null && !controller.getTags().isEmpty() && !controller.getAccounts().isEmpty()) {
            transactionStage(transaction);
            notificationLabel.setText("Success!");
        }else {
            notificationLabel.setText("Add Transaction Failed");
            logger.warning("Failed in Transaction Addition.");
        }
        transactionNewDescription.clear();
    }

    /**
     * Metodo responsabile dell'aggiunta di una transazione istantanea.
     */
    private Transaction addInstantTransaction(){
        Transaction transaction = TransactionManager
                .generateTransaction(this.transactionNewDescription.getText(), this.controller.idGenerator().generate());
        logger.info("Addition of instant transaction: ["+transaction.toString()+"]");
        return transaction;
    }

    /**
     * Metodo responsabile dell'aggiunta di una transazione programmata.
     */
    private Transaction addProgramTransaction(){
        Transaction transaction = null;
        if(transactionNewDate.getValue()!=null && transactionNewDate.getValue().compareTo(LocalDate.now())>=0) {
            transaction = TransactionManager.generateTransaction(LocalDateTime.of(transactionNewDate.getValue()
                    , LocalTime.MIN),this.transactionNewDescription.getText(),  this.controller.idGenerator().generate());
            logger.info("Addition of program transaction: ["+transaction.toString()+"]");
        }
        return transaction;
    }

    /**
     * Metodo responsabile di far partire un nuovo stage per l'inserimento dei movimenti
     * nella transazione data.
     * @param transaction Transazione dove inserire i movimenti.
     */
    private void transactionStage(Transaction transaction){
        try {
            logger.info("Start New Movements stage.");
            Stage stage = createStage();
            stage.setTitle("New Movements");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JBudgetNewMovements.fxml"));
            loader.setController(new GUIViewMovementController(this.controller, transaction,stage,this));
            stage.setScene(new Scene(loader.load(), 610, 400));
            stage.show();
            logger.info("Stop New Movements stage.");
        } catch (IOException e) {
            notificationLabel.setText("Movements View Failed");
            logger.warning("Failed New Movements stage.");
        }

    }

    /**
     * Metodo responsabile della rimozione di un budget.
     */
    @FXML
    public void removeBudget(){
        Map.Entry<Tag,Double> b = tableBudget.getSelectionModel().getSelectedItem();
        if(!tableBudget.getItems().isEmpty()&&b!=null) {
            this.controller.removeBudgetRecord(b.getKey());
            updateTables();
            save();
            logger.info("Removal of budget: ["+b.toString()+"]");
        }
    }

    /**
     * Metodo responsabile dell'aggiunta di un budget.
     */
    @FXML
    public void addBudget() {
        try {
            Tag tag = budgetNewTagId.getValue();
            double value = Double.parseDouble(budgetNewValue.getText());
            this.controller.addBudgetRecord(tag,value);
            save();
            updateTables();
            logger.info("Addition of budget: ["+tag.toString()+":"+value+"]");
        }catch (Exception e){
            attention();
            notificationLabel.setText(notificationLabel.getText()+" - Add Budget Failed");
            logger.warning("Failed in Budget Addition.");
        }finally {
            budgetNewValue.clear();
        }
    }

    /**
     * Metodo responsabile della schedulazione delle transazioni per tag o data.
     */
    @FXML
    public void schedule() {
        if(scheduleTag.isSelected() && tagSchedChoice.getValue()!=null) {
            olTransaction.setAll(this.controller.scheduleTransactionsTag(tagSchedChoice.getValue()));
            logger.info("Transactions scheduled by tag: ["+tagSchedChoice.getValue().toString()+"]");
        }
        if(scheduleDate.isSelected()&&dateStart.getValue()!=null&&dateStop.getValue()!=null) {
            olTransaction.setAll(this.controller.scheduleTransactionsDate(LocalDateTime.of(dateStart.getValue()
                    , LocalTime.MIN),LocalDateTime.of(dateStop.getValue(), LocalTime.MIN)));
            logger.info("Transactions scheduled by date: ["+dateStart.getValue()+","+dateStop.getValue()+"]");
        }
        tableTransaction.refresh();
    }

    /**
     * Metodo che ha la responsabilità di visualizzare tutte le transazioni in memoria.
     */
    @FXML
    public void showAll() {
        updateTransactions();
        logger.info("Shown all transactions.");
    }

    /**
     * Metodo che ha la responsabilità di mostrare tutti i movimenti appartenenti ad un
     * certo account selezionato.
     */
    @FXML
    public void tableAccountClicked() {
        Account a = tableAccount.getSelectionModel().getSelectedItem();
        if(!tableAccount.getItems().isEmpty()&&a!=null) {
            selectedUtility = a;
            descriptionArea.setText(a.getDescription());
            updateMovements(a.getMovements());
            tableMovement.refresh();
            logger.info("Account selected: ["+a.toString()+"]");
        }
    }

    /**
     * Metodo che ha la responsabilità di mostrare tutti i movimenti appartenenti ad una
     * certa transazione selezionata.
     */
    @FXML
    public void tableTransactionClicked() {
        Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
        if(!tableTransaction.getItems().isEmpty()&&t!=null) {
            selectedUtility = t;
            descriptionArea.setText(t.getDescription());
            updateMovements(t.getMovements());
            tableMovement.refresh();
            logger.info("Transaction selected: ["+t.toString()+"]");
        }
    }

    /**
     * Metodo che ha la responsabilità di mostrare tutti i movimenti appartenenti ad un
     * certo tag selezionato.
     */
    @FXML
    public void tableTagClicked() {
        Tag t = tableTag.getSelectionModel().getSelectedItem();
        if(!tableTag.getItems().isEmpty()&&t!=null) {
            selectedUtility = t;
            descriptionArea.setText(t.getDescription());
            updateMovements(t.getMovements());
            tableMovement.refresh();
            logger.info("Transaction selected: ["+t.toString()+"]");
        }
    }

    /**
     * Metodo che ha la responsabilità di mostrare tutti i movimenti appartenenti ad un
     * certo tag di un budget selezionato selezionato.
     */
    @FXML
    public void budgetTableClicked() {
        Map.Entry<Tag, Double> b = tableBudget.getSelectionModel().getSelectedItem();
        if(!tableBudget.getItems().isEmpty()&&b!=null) {
            selectedUtility = b.getKey();
            descriptionArea.setText(b.getKey().getDescription());
            updateMovements(b.getKey().getMovements());
            tableMovement.refresh();
            logger.info("Budget selected: ["+b.toString()+"]");
        }
    }

    /**
     * Metodo che ha la responsabilità di gestire il movimento selezionato.
     */
    @FXML
    public void tableMovementClicked() {
        Movement m = tableMovement.getSelectionModel().getSelectedItem();
        if(!tableMovement.getItems().isEmpty()&&m!=null) {
            selectedUtility = m;
            descriptionArea.setText(m.getDescription());
        }
    }

    /**
     * Metodo che ha la responsabilità di notificare se un certo budget è stato superato.
     */
    @FXML
    private void attention(){
        Map<Tag,Double> check = this.controller.check();
        if(!check.isEmpty()) {
            notificationLabel.setText("ATTENTION: " + check.toString());
            logger.info("Budget exceeded.");
        }
        else
            notificationLabel.setText("");
    }

    /**
     * Metodo che ha la responsabilità di cambiare la descrizione dell'Utility selezionata.
     */
    @FXML
    public void changeDescription() {
        if (selectedUtility!=null){
            this.controller.setDescription(selectedUtility,descriptionArea.getText());
            save();
            notificationLabel.setText("Success!");
            logger.info("Description changed.");
        }
        updateTables();
    }

    /**
     * Metodo che ha la responsabilità di aprire un file per gestire i dati del JBudget al suo interno.
     */
    @FXML
    public void openFile() {
        try {
            String path = createFileChooser().showOpenDialog(createStage()).getAbsolutePath();
            this.controller.read(new JBudgetReaderJson(path));
            this.writer = new JBudgetWriterJson(path);
            updateTables();
            logger.info("File Opened.");
        } catch (Exception e) {
            notificationLabel.setText("File not read.");
            logger.warning("File not read.");
        }
    }

    /**
     * Metodo che ha la responsabilità di creare un nuovo file su cui salvare JBudget.
     */
    @FXML
    public void newFile() {
        try {
            String path = createFileChooser().showSaveDialog(createStage()).getAbsolutePath();
            this.writer = new JBudgetWriterJson(path);
            this.controller.resetBudgetReport();
            olMovement.removeAll(olMovement);
            updateTables();
            save();
            notificationLabel.setText("File created: " + path);
            logger.info("File Created.");
        } catch (Exception e) {
            notificationLabel.setText("File Not Created.");
            logger.warning("File Not Created.");
        }
    }

    /**
     * Metodo che ha la responsabilità di salvare il JBudget su un file.
     */
    @FXML
    public void saveFile(){
        try {
            String path = createFileChooser().showSaveDialog(createStage()).getAbsolutePath();
            this.writer = new JBudgetWriterJson(path);
            save();
            notificationLabel.setText("File Saved: " + path);
            logger.info("File saved.");
        } catch (Exception e) {
            notificationLabel.setText("File Not Saved.");
            logger.warning("File Not Saved.");
        }
    }

    /**
     * Metodo che ha la responsabilità di esportare il JBudget su un file.
     */
    @FXML
    public void exportFile(){
        try {
            String path = createFileChooser().showSaveDialog(createStage()).getAbsolutePath();
            this.controller.save(new JBudgetWriterJson(path));
            notificationLabel.setText("File Exported: " + path);
            logger.info("File exported.");
        } catch (Exception e) {
            notificationLabel.setText("File Not Exported.");
            logger.warning("File Not Exported.");
        }
    }

    /**
     * Metodo che ha la responsabilità di inizializzare le variabili di istanza.
     * @param location Locazione utilizzata per risolvere il path relativo dell'oggetto.
     * @param resources Risorse utilizzate per localizzare l'oggetto root.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = MainControllerManager.generateMainController();
        olAccount = FXCollections.observableArrayList();
        olTag = FXCollections.observableArrayList();
        olBudget = FXCollections.observableArrayList();
        olTransaction = FXCollections.observableArrayList();
        olMovement = FXCollections.observableArrayList();
        initializeAccountType();
        updateTables();
        tagSchedChoice.setItems(olTag);
        budgetNewTagId.setItems(olTag);
        logger.info("GUIViewController initialized.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare tutte le tabelle.
     */
    public void updateTables(){
        updateAccounts();
        updateTags();
        updateBudget();
        updateTransactions();
        tableMovement.refresh();
    }

    /**
     * Metodo che ha la responsabilità di inizializzare le variabili riguardanti il tipo di account.
     */
    private void initializeAccountType(){
        olAccountType = FXCollections.observableArrayList();
        olAccountType.add(AccountType.ASSETS);
        olAccountType.add(AccountType.LIABILITIES);
        accountNewType.setItems(olAccountType);
        logger.finest("AccountType initialized.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti gli account.
     */
    private void updateAccounts(){
        olAccount.removeAll(olAccount);
        olAccount.addAll(this.controller.getAccounts());
        tableAccount.setItems(olAccount);
        accountID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        accountName.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        accountBalance.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getBalance()));
        accountOpeningBalance.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getOpeningBalance()));
        accountType.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccountType()));
        accountDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableAccount.refresh();
        logger.finest("Accounts updated.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti i tag di account.
     */
    private void updateTags(){
        olTag.removeAll(olTag);
        olTag.addAll(this.controller.getTags());
        tableTag.setItems(olTag);
        tagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        tagName.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        tagTAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().totalAmount()));
        tagDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableTag.refresh();
        logger.finest("Tags updated.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti i budget.
     */
    private void updateBudget() {
        olBudget.removeAll(olBudget);
        olBudget.addAll(this.controller.getBudgetRecords().entrySet());
        tableBudget.setItems(olBudget);
        budgetTagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));
        budgetValue.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));
        tableBudget.refresh();
        attention();
        logger.finest("Budgets updated.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti le transazioni.
     */
    private void updateTransactions() {
        olTransaction.removeAll(olTransaction);
        olTransaction.addAll(this.controller.getTransactions());
        tableTransaction.setItems(olTransaction);
        transactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        transactionDate.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().toLocalDate()));
        transactionTAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
        transactionDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableTransaction.refresh();
        attention();
        logger.finest("Transactions updated.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti i movimenti.
     */
    private void updateMovements(Collection<Movement> movements) {
        olMovement.removeAll(olMovement);
        olMovement.addAll(movements);
        tableMovement.setItems(olMovement);
        movementID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        movementAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        movementType.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));
        movementDate.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().toLocalDate()));
        movementTagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTag()));
        movementTransactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getID()));
        movementAccountID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccount()));
        movementDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableMovement.refresh();
        logger.finest("Movements updated.");
    }

    /**
     * Metodo che ha la responsabilità di ritornare un file chooser.
     * @return File chooser creato.
     */
    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        fileChooser.setInitialFileName("JBudget");
        return fileChooser;
    }

    /**
     * Metodo che ha la responsabilità di creare un nuovo stage.
     * @return Stage creato.
     */
    private Stage createStage(){
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    /**
     * Metodo che ha la responsabilità di salvare le operazioni con la variabile writer.
     */
    public void save(){
        this.controller.save(this.writer);
    }
}
