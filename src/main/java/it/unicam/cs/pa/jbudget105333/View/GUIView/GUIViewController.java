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
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.ResourceBundle;

public class GUIViewController implements Initializable {

    private LedgerController ledgerController;
    private BudgetController budgetController;
    private BudgetReportController budgetReportController;

    @FXML TableView<Account> tableAccount;
    @FXML TableColumn<Account,Integer> accountID;
    @FXML TableColumn<Account,String> accountName;
    @FXML TableColumn<Account,Double> accountBalance;
    @FXML TableColumn<Account,Double> accountOpeningBalance;
    @FXML TableColumn<Account, AccountType> accountType;
    @FXML TableColumn<Account,String> accountDescription;
    private ObservableList<Account> olAccount;

    @FXML TableView<Map.Entry<Tag,Double>> tableBudget;
    @FXML TableColumn<Map.Entry<Tag,Double>,Integer> budgetTagID;
    @FXML TableColumn<Map.Entry<Tag,Double>,Double> budgetValue;
    private ObservableList<Map.Entry<Tag,Double>> olBudget;

    @FXML TableView<Tag> tableTag;
    @FXML TableColumn<Tag,Integer> tagID;
    @FXML TableColumn<Tag,String> tagName;
    @FXML TableColumn<Tag,String> tagDescription;
    private ObservableList<Tag> olTag;

    @FXML TableView<Transaction> tableTransaction;
    @FXML TableColumn<Transaction,Integer> transactionID;
    @FXML TableColumn<Transaction, LocalDate> transactionDate;
    @FXML TableColumn<Transaction,Double> transactionTAmount;
    private ObservableList<Transaction> olTransaction;
    @FXML ChoiceBox<Tag> tagSchedChoice;
    @FXML ToggleGroup scheduleType;
    @FXML RadioButton scheduleTag;
    @FXML RadioButton scheduleDate;
    @FXML RadioButton showAll;
    @FXML DatePicker dateStart;
    @FXML DatePicker dateStop;

    @FXML TableView<Movement> tableMovement;

    @FXML
    public void onTblDoubleClick(MouseEvent e){
    }

    @FXML
    public void removeAccount(ActionEvent actionEvent) {

    }

    @FXML
    public void newAccount(ActionEvent actionEvent) {
        
    }

    @FXML
    public void removeTag(ActionEvent actionEvent) {
    }

    @FXML
    public void newTag(ActionEvent actionEvent) {
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
        if(showAll.isSelected())
            initializeTransaction();
    }

    @FXML
    public void removeTransaction(ActionEvent actionEvent) {
    }

    @FXML
    public void newTransaction(ActionEvent actionEvent) {
    }

    @FXML
    public void removeBudget(ActionEvent actionEvent) {
    }

    @FXML
    public void newBudget(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ledgerController = new LedgerBaseController();
        this.budgetController = new BudgetBaseController(this.ledgerController);
        this.budgetReportController =
                new BudgetReportBaseController(this.ledgerController,this.budgetController);
        olAccount = FXCollections.observableArrayList();
        olTag = FXCollections.observableArrayList();
        olBudget = FXCollections.observableArrayList();
        olTransaction = FXCollections.observableArrayList();
        initializeAccount();
        initializeTag();
        initializeBudget();
        initializeTransaction();
        tagSchedChoice.setItems(olTag);
    }

    private void initializeAccount(){
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
    }

    private void initializeTag(){
        olTag.removeAll(olTag);
        olTag.addAll(this.ledgerController.getTags());
        tableTag.setItems(olTag);
        tagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        tagName.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        tagDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
    }

    private void initializeBudget() {
        olBudget.removeAll(olBudget);
        olBudget.addAll(this.budgetController.getBudget().getBudgetMap().entrySet());
        tableBudget.setItems(olBudget);
        budgetTagID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey().getID()));
        budgetValue.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));
    }

    private void initializeTransaction() {
        olTransaction.removeAll(olTransaction);
        olTransaction.addAll(this.ledgerController.getTransactions());
        tableTransaction.setItems(olTransaction);
        transactionID.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getID()));
        transactionDate.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate().toLocalDate()));
        transactionTAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalAmount()));
    }
}
