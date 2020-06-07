package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Controller.MainControllerManager;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountManager;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagManager;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.ProgramTransaction;
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
import javafx.scene.input.MouseEvent;
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
import java.util.Set;

public class GUIViewController implements Initializable {

    private MainController mainController;

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
    @FXML TableColumn<Map.Entry<Tag,Double>,Tag> budgetTagID;
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
    @FXML TableColumn<Movement,Tag> movementTagID;
    @FXML TableColumn<Movement,Integer> movementTransactionID;
    @FXML TableColumn<Movement,Account> movementAccountID;
    @FXML TableColumn<Movement,String> movementDescription;
    private ObservableList<Movement> olMovement;

    @FXML Label notificationLabel;

    public GUIViewController() {
        this.mainController = MainControllerManager.generateMainController();
    }

    @FXML
    public void removeAccount(ActionEvent actionEvent) throws IOException {
        Account a = tableAccount.getSelectionModel().getSelectedItem();
        if(!tableAccount.getItems().isEmpty()&&a!=null) {
            this.mainController.removeAccount(a);
            initializeAccount();
            this.mainController.save();
        }
    }

    @FXML
    public void addAccount(ActionEvent actionEvent) {
        try {
            Account account = AccountManager.generateAccount(accountNewName.getText(), accountNewDescription.getText()
                    , Double.parseDouble(accountNewOpeningBalance.getText()), accountNewType.getValue()
                    ,this.mainController.idGenerator().generate());
            this.mainController.addAccount(account);
            this.mainController.save();
            notificationLabel.setText("Success!");
        }catch (Exception e){
            notificationLabel.setText("Add Account Failed");
        }finally {
            initializeAccount();
            accountNewName.clear();
            accountNewDescription.clear();
            accountNewOpeningBalance.clear();
        }
    }

    @FXML
    public void removeTag(ActionEvent actionEvent) throws IOException {
        Tag t = tableTag.getSelectionModel().getSelectedItem();
        if(!tableTag.getItems().isEmpty()&&t!=null) {
            this.mainController.removeTag(t);
            initializeTag();
            initializeBudget();
            this.mainController.save();
        }
    }

    @FXML
    public void addTag(ActionEvent actionEvent) throws IOException {
        try{
            this.mainController.addTag(TagManager.generateTag(tagNewName.getText(),tagNewDescription.getText()
                    ,this.mainController.idGenerator().generate()));
            notificationLabel.setText("Success!");
        }catch (Exception e){
            notificationLabel.setText("Add Tag Failed");
        }finally {
            initializeTag();
            tagNewName.clear();
            tagNewDescription.clear();
            this.mainController.save();
        }
    }

    @FXML
    public void removeTransaction(ActionEvent actionEvent) throws IOException {
        Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
        if(!tableTransaction.getItems().isEmpty()&&t!=null) {
            this.mainController.removeTransaction(t);
            initializeTransaction();
            this.mainController.save();
        }
    }

    @FXML
    public void addTransaction(ActionEvent actionEvent) throws IOException {
        Transaction transaction = null;
        if(instantTransaction.isSelected())
            transaction = new InstantTransaction(this.mainController.idGenerator().generate());
        if(programTransaction.isSelected() && transactionNewDate.getValue()!=null
                &&transactionNewDate.getValue().compareTo(LocalDate.now())>=0)
            transaction = new ProgramTransaction
                    (LocalDateTime.of(transactionNewDate.getValue(),LocalTime.MIN)
                            ,this.mainController.idGenerator().generate());
        if(transaction!=null) {
            transactionStage(transaction);
            notificationLabel.setText("Success!");
        }else
            notificationLabel.setText("Add Transaction Failed");
    }

    private void transactionStage(Transaction transaction) throws IOException{
        Stage stage = new Stage();
        stage.setTitle("New Movements");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JBudgetNewMovements.fxml"));
        loader.setController(new GUIViewMovementController(this.mainController
                , transaction,stage,this));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 610, 400));
        stage.show();
    }

    @FXML
    public void removeBudget(ActionEvent actionEvent) throws IOException {
        Map.Entry<Tag,Double> b = tableBudget.getSelectionModel().getSelectedItem();
        if(!tableBudget.getItems().isEmpty()&&b!=null) {
            this.mainController.removeBudgetRecord(b.getKey());
            initializeBudget();
            this.mainController.save();
        }
    }

    @FXML
    public void addBudget(ActionEvent actionEvent) {
        try {
            this.mainController.addBudgetRecord(budgetNewTagId.getValue()
                    , Double.parseDouble(budgetNewValue.getText()));
            this.mainController.save();
            initializeBudget();
        }catch (Exception e){
            attention();
            notificationLabel.setText(notificationLabel.getText()+"Add Budget Failed");
        }finally {
            budgetNewValue.clear();
        }
    }

    @FXML
    public void schedule(ActionEvent actionEvent) {
        if(scheduleTag.isSelected() && tagSchedChoice.getValue()!=null)
            olTransaction.setAll(this.mainController.scheduleTransactionsTag(tagSchedChoice.getValue()));
        if(scheduleDate.isSelected()&&dateStart.getValue()!=null&&dateStop.getValue()!=null) {
            Set<Transaction> transactions =this.mainController
                    .scheduleTransactionsDate(LocalDateTime.of(dateStart.getValue(), LocalTime.MIN)
                            ,LocalDateTime.of(dateStop.getValue(), LocalTime.MIN));
            if(transactions != null)
                olTransaction.setAll(transactions);
        }
        tableTransaction.refresh();
    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        initializeTransaction();
    }

    @FXML
    public void tableAccountClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Account a = tableAccount.getSelectionModel().getSelectedItem();
        if(!tableAccount.getItems().isEmpty()&&a!=null) {
            initializeMovement(a.getMovements());
            tableMovement.refresh();
        }
    }

    @FXML
    public void tableTransactionClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Transaction t = tableTransaction.getSelectionModel().getSelectedItem();
        if(!tableTransaction.getItems().isEmpty()&&t!=null) {
            initializeMovement(t.getMovements());
            tableMovement.refresh();
        }
    }

    public void tableTagClicked(MouseEvent mouseEvent) {
        Tag t = tableTag.getSelectionModel().getSelectedItem();
        if(!tableTag.getItems().isEmpty()&&t!=null) {
            initializeMovement(t.getMovements());
            tableMovement.refresh();
        }
    }

    public void budgetTableClicked(MouseEvent mouseEvent) {
        Map.Entry<Tag, Double> t = tableBudget.getSelectionModel().getSelectedItem();
        if(!tableBudget.getItems().isEmpty()&&t!=null) {
            initializeMovement(t.getKey().getMovements());
            tableMovement.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        olAccount.addAll(this.mainController.getAccounts());
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
        olTag.addAll(this.mainController.getTags());
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
        olBudget.addAll(this.mainController.getBudgetRecords().entrySet());
        tableBudget.setItems(olBudget);
        budgetTagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));
        budgetValue.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));
        tableBudget.refresh();
        attention();
    }

    public void initializeTransaction() {
        olTransaction.removeAll(olTransaction);
        olTransaction.addAll(this.mainController.getTransactions());
        tableTransaction.setItems(olTransaction);
        transactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        transactionDate.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().toLocalDate()));
        transactionTAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
        tableTransaction.refresh();
        attention();
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
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTag()));
        movementTransactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransaction().getID()));
        movementAccountID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccount()));
        movementDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableMovement.refresh();
    }

    private void attention(){
        Map<Tag,Double> check = this.mainController.check();
        if(!check.isEmpty())
            notificationLabel.setText("ATTENTION: "+check.toString());
        else
            notificationLabel.setText("");
    }
}
