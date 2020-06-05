package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetBase.BudgetBaseController;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetController;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportBase.BudgetReportBaseController;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportController;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase.LedgerBaseController;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerController;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.InstantTransaction.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.ProgramTransaction.ProgramTransaction;
import it.unicam.cs.pa.jbudget105333.View.ViewBaseController;
import it.unicam.cs.pa.jbudget105333.View.ViewController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class GUIViewController implements Initializable {

    private LedgerController ledgerController;
    private BudgetController budgetController;
    private BudgetReportController budgetReportController;
    private ViewController viewController;

    @FXML TableView<Account> tableAccount;
    @FXML TableColumn<Account,Integer> accountID;
    @FXML TableColumn<Account,String> accountName;
    @FXML TableColumn<Account,Double> accountBalance;
    @FXML TableColumn<Account,Double> accountOpeningBalance;
    @FXML TableColumn<Account, AccountType> accountType;
    @FXML TableColumn<Account,String> accountDescription;
    private ObservableList<Account> olAccount;
    @FXML TextField accountNewName;
    @FXML TextField accountNewDescription;
    @FXML TextField accountNewOpeningBalance;
    @FXML ChoiceBox<AccountType> accountNewType;
    private ObservableList<AccountType> olAccountType;

    @FXML TableView<Map.Entry<Tag,Double>> tableBudget;
    @FXML TableColumn<Map.Entry<Tag,Double>,Integer> budgetTagID;
    @FXML TableColumn<Map.Entry<Tag,Double>,Double> budgetValue;
    private ObservableList<Map.Entry<Tag,Double>> olBudget;
    @FXML ChoiceBox<Tag> budgetNewTagId;
    @FXML TextField budgetNewValue;

    @FXML TableView<Tag> tableTag;
    @FXML TableColumn<Tag,Integer> tagID;
    @FXML TableColumn<Tag,String> tagName;
    @FXML TableColumn<Tag,String> tagDescription;
    private ObservableList<Tag> olTag;
    @FXML TextField tagNewName;
    @FXML TextField tagNewDescription;

    @FXML TableView<Transaction> tableTransaction;
    @FXML TableColumn<Transaction,Integer> transactionID;
    @FXML TableColumn<Transaction, LocalDate> transactionDate;
    @FXML TableColumn<Transaction,Double> transactionTAmount;
    private ObservableList<Transaction> olTransaction;
    @FXML RadioButton programTransaction;
    @FXML RadioButton instantTransaction;
    @FXML DatePicker transactionNewDate;

    @FXML ChoiceBox<Tag> tagSchedChoice;
    @FXML ToggleGroup scheduleType;
    @FXML RadioButton scheduleTag;
    @FXML RadioButton scheduleDate;
    @FXML DatePicker dateStart;
    @FXML DatePicker dateStop;

    @FXML TableView<Movement> tableMovement;
    @FXML TableColumn<Movement,Integer> movementID;
    @FXML TableColumn<Movement,Double> movementAmount;
    @FXML TableColumn<Movement, MovementType> movementType;
    @FXML TableColumn<Movement, LocalDate> movementDate;
    @FXML TableColumn<Movement,Integer> movementTagID;
    @FXML TableColumn<Movement,Integer> movementTransactionID;
    @FXML TableColumn<Movement,Integer> movementAccountID;
    @FXML TableColumn<Movement,String> movementDescription;
    private ObservableList<Movement> olMovement;

    @FXML
    public void removeAccount(ActionEvent actionEvent) throws IOException {
        Account a = tableAccount.getSelectionModel().getSelectedItem();
        if(!tableAccount.getItems().isEmpty()&&a!=null) {
            ledgerController.getLedger().removeAccount(a);
            initializeAccount();
            this.viewController.save();
        }
    }

    @FXML
    public void addAccount(ActionEvent actionEvent) throws IOException {
        if(accountNewType.getValue()!=null) {
            String account = accountNewName.getText() + "," + accountNewDescription.getText() + ","
                    + accountNewOpeningBalance.getText() + "," + accountNewType.getValue().toString();
            this.viewController.newAccount(account);
            initializeAccount();
            accountNewName.clear();
            accountNewDescription.clear();
            accountNewOpeningBalance.clear();
            this.viewController.save();
        }
    }

    @FXML
    public void removeTag(ActionEvent actionEvent) throws IOException {
        Tag t = tableTag.getSelectionModel().getSelectedItem();
        if(!tableTag.getItems().isEmpty()&&t!=null) {
            ledgerController.getLedger().removeTag(t);
            initializeTag();
            initializeBudget();
            this.viewController.save();
        }
    }

    @FXML
    public void addTag(ActionEvent actionEvent) throws IOException {
        String tag = tagNewName.getText()+","+tagNewDescription.getText();
        this.viewController.newTag(tag);
        initializeTag();
        tagNewName.clear();
        tagNewDescription.clear();
        this.viewController.save();
    }

    @FXML
    public void removeTransaction(ActionEvent actionEvent) throws IOException {
        Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
        if(!tableTransaction.getItems().isEmpty()&&t!=null) {
            ledgerController.getLedger().removeTransaction(t);
            initializeTransaction();
            this.viewController.save();
        }
    }

    @FXML
    public void addTransaction(ActionEvent actionEvent) throws IOException {
        Transaction transaction = null;
        if(instantTransaction.isSelected()){
            transaction = new InstantTransaction(this.ledgerController.getIDGenerator());
        }
        if(programTransaction.isSelected() && transactionNewDate.getValue()!=null){
            transaction = new ProgramTransaction
                    (LocalDateTime.of(transactionNewDate.getValue(),LocalTime.MIN)
                            ,this.ledgerController.getIDGenerator());
        }
        if(transaction!=null) {
            Stage stage = new Stage();
            stage.setTitle("New Movements");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JBudgetNewMovements.fxml"));
            loader.setController(new GUIViewMovementController(this.viewController, this.budgetReportController
                    , transaction,stage,this));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
    }

    @FXML
    public void removeBudget(ActionEvent actionEvent) throws IOException {
        Map.Entry b = tableBudget.getSelectionModel().getSelectedItem();
        if(!tableBudget.getItems().isEmpty()&&b!=null) {
            budgetController.getBudgetMap().remove(b.getKey());
            initializeBudget();
            this.viewController.save();
        }
    }

    @FXML
    public void addBudget(ActionEvent actionEvent) throws IOException {
        if(budgetNewTagId.getValue()!=null) {
            String budget = budgetNewTagId.getValue().getID() + "," + budgetNewValue.getText();
            this.viewController.newBudgetRecord(budget);
            initializeBudget();
            budgetNewValue.clear();
            this.viewController.save();
        }
    }

    @FXML
    public void schedule(ActionEvent actionEvent) {
        if(scheduleTag.isSelected() && tagSchedChoice.getValue()!=null) {
            olTransaction.removeAll(olTransaction);
            olTransaction.addAll(ledgerController.scheduleTransactionsTag(tagSchedChoice.getValue().getID()));
            tableTransaction.refresh();
        }
        if(scheduleDate.isSelected()&&dateStart.getValue()!=null&&dateStop.getValue()!=null) {
            olTransaction.removeAll(olTransaction);
            olTransaction.addAll(ledgerController
                    .scheduleTransactionsDate(LocalDateTime.of(dateStart.getValue(), LocalTime.MIN)
                            ,LocalDateTime.of(dateStop.getValue(), LocalTime.MIN)));
            tableTransaction.refresh();
        }
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        initializeTransaction();
    }

    @FXML
    public void tableAccountClicked(javafx.scene.input.MouseEvent mouseEvent) {
        if(!tableAccount.getItems().isEmpty()) {
            Account a = tableAccount.getSelectionModel().getSelectedItem();
            initializeMovement(a.getMovements());
            tableMovement.refresh();
        }
    }

    @FXML
    public void tableTransactionClicked(javafx.scene.input.MouseEvent mouseEvent) {
        if(!tableTransaction.getItems().isEmpty()) {
            Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
            initializeMovement(t.getMovements());
            tableMovement.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ledgerController = new LedgerBaseController();
        this.budgetController = new BudgetBaseController(this.ledgerController);
        this.budgetReportController =
                new BudgetReportBaseController(this.ledgerController,this.budgetController);
        this.viewController = new ViewBaseController(this.budgetReportController);
        olAccount = FXCollections.observableArrayList();
        olTag = FXCollections.observableArrayList();
        olBudget = FXCollections.observableArrayList();
        olTransaction = FXCollections.observableArrayList();
        olMovement = FXCollections.observableArrayList();
        initializeAccountType();
        initializeAccount();
        initializeTag();
        initializeBudget();
        initializeTransaction();
        tagSchedChoice.setItems(olTag);
        budgetNewTagId.setItems(olTag);
    }

    private void initializeAccountType(){
        olAccountType = FXCollections.observableArrayList();
        olAccountType.add(AccountType.ASSETS);
        olAccountType.add(AccountType.LIABILITIES);
        accountNewType.setItems(olAccountType);
    }

    public void initializeAccount(){
        olAccount.removeAll(olAccount);
        olAccount.addAll(this.ledgerController.getAccounts());
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
    }

    public void initializeTag(){
        olTag.removeAll(olTag);
        olTag.addAll(this.ledgerController.getTags());
        tableTag.setItems(olTag);
        tagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        tagName.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        tagDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableTag.refresh();
    }

    public void initializeBudget() {
        olBudget.removeAll(olBudget);
        olBudget.addAll(this.budgetController.getBudget().getBudgetMap().entrySet());
        tableBudget.setItems(olBudget);
        budgetTagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey().getID()));
        budgetValue.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));
        tableBudget.refresh();
    }

    public void initializeTransaction() {
        olTransaction.removeAll(olTransaction);
        olTransaction.addAll(this.ledgerController.getTransactions());
        tableTransaction.setItems(olTransaction);
        transactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        transactionDate.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().toLocalDate()));
        transactionTAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
        tableTransaction.refresh();
        avviso();
    }

    private void initializeMovement(Collection<Movement> movements) {
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
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTag().getID()));
        movementTransactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getID()));
        movementAccountID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccount().getID()));
        movementDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableMovement.refresh();
    }

    private void avviso(){
        String check = this.viewController.check();
        if(check!=""){
            Stage stage = new Stage();
            stage.setTitle("ATTENTION");
            stage.setScene(new Scene(new TextArea(check)));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(300);
            stage.setHeight(300);
            stage.show();
        }
    }
}
