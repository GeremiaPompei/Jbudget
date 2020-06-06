package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementManager;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIViewMovementController implements Initializable {

    @FXML TableView<Movement> tableNewMovements;
    @FXML TableColumn<Movement,MovementType> movementType;
    @FXML TableColumn<Movement,Double> movementAmount;
    @FXML TableColumn<Movement, Account> movementAccountId;
    @FXML TableColumn<Movement, Tag> movementTagId;
    @FXML TableColumn<Movement,String> movementDescription;
    @FXML ChoiceBox<MovementType> movementNewType;
    @FXML TextField movementNewAmount;
    @FXML ChoiceBox<Account> movementNewAccountId;
    @FXML ChoiceBox<Tag> movementNewTagId;
    @FXML TextField movementNewDescription;
    private ObservableList<MovementType> olMovementsType;
    private ObservableList<Tag> olTags;
    private ObservableList<Account> olAccounts;
    private ObservableList<Movement> olMovements;
    private MainController mainController;
    private Transaction transaction;
    private Stage stage;
    private GUIViewController guiViewController;

    public GUIViewMovementController(MainController mainController, Transaction transaction
            , Stage stage, GUIViewController guiViewController) {
        this.mainController = mainController;
        this.transaction = transaction;
        this.stage = stage;
        this.guiViewController = guiViewController;
    }

    public void removeMovement(ActionEvent actionEvent) {
        Movement m = this.tableNewMovements.getSelectionModel().getSelectedItem();
        if(!olMovements.isEmpty()&&m!=null)
                transaction.getMovements().remove(m);
        initializeMovement();
    }

    public void addMovement(ActionEvent actionEvent) {
        try {
            transaction.addMovement(MovementManager.generateMovement(movementNewType.getValue()
                    , Double.parseDouble(movementNewAmount.getText())
                    , transaction, movementNewAccountId.getValue(), movementNewTagId.getValue()
                    , movementNewDescription.getText(), this.mainController.idGenerator().generate()));
            initializeMovement();
        }catch (Exception e){ }
        movementNewAmount.clear();
        movementNewDescription.clear();
    }

    public void saveMovements(ActionEvent actionEvent) throws IOException {
        if(!transaction.getMovements().isEmpty()) {
            this.mainController.addTransaction(transaction);
            guiViewController.initializeTransaction();
            guiViewController.initializeAccount();
            this.mainController.update();
            this.mainController.save();
        }
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        olMovements = FXCollections.observableArrayList();
        olTags = FXCollections.observableArrayList();
        olTags.addAll(this.mainController.getTags());
        movementNewTagId.setItems(olTags);
        olAccounts = FXCollections.observableArrayList();
        olAccounts.addAll(this.mainController.getAccounts());
        movementNewAccountId.setItems(olAccounts);
        olMovementsType = FXCollections.observableArrayList();
        olMovementsType.addAll(MovementType.CREDITS,MovementType.DEBIT);
        movementNewType.setItems(olMovementsType);
    }

    private void initializeMovement(){
        olMovements.removeAll(olMovements);
        olMovements.addAll(transaction.getMovements());
        tableNewMovements.setItems(olMovements);
        movementType.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));
        movementAmount.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        movementAccountId.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getAccount()));
        movementTagId.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getTag()));
        movementDescription.setCellValueFactory
                (cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescription()));
        tableNewMovements.refresh();
    }
}
