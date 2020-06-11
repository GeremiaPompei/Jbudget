package it.unicam.cs.pa.jbudget105333.View.GUIView;

import it.unicam.cs.pa.jbudget105333.Controller.MainController;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementManager;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di fare da controller alla view dell'aggiunta dei movimenti
 * permettendo di renderla dinamica e gestire l'interazione con il controller della view principale
 * così da consentire l'utente di gestire i movimenti per una certa transazione.
 */
public class GUIViewMovementController implements Initializable {

    /**
     * Variabile utile alla gestione del log del MainController.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Tabella che presenta le informazioni relative ai movimenti aggiunti.
     */
    @FXML TableView<Movement> tableNewMovements;

    /**
     * Colonna della tabella dei Movimenti che memorizza i tipi dei Movimenti.
     */
    @FXML TableColumn<Movement,MovementType> movementType;

    /**
     * Colonna della tabella dei Movimenti che memorizza i saldi dei Movimenti.
     */
    @FXML TableColumn<Movement,Double> movementAmount;

    /**
     * Colonna della tabella dei Movimenti che memorizza gli account dei Movimenti.
     */
    @FXML TableColumn<Movement, Account> movementAccountId;

    /**
     * Colonna della tabella dei Movimenti che memorizza i tag dei Movimenti.
     */
    @FXML TableColumn<Movement, Tag> movementTagId;

    /**
     * Colonna della tabella dei Movimenti che memorizza le descrizioni dei Movimenti.
     */
    @FXML TableColumn<Movement,String> movementDescription;

    /**
     * Campo da scegliere per selezionare il tipo del nuovo movimento.
     */
    @FXML ChoiceBox<MovementType> movementNewType;

    /**
     * Campo da inserire per impostare il saldo del nuovo movimento.
     */
    @FXML TextField movementNewAmount;

    /**
     * Campo da scegliere per selezionare l'account del nuovo movimento.
     */
    @FXML ChoiceBox<Account> movementNewAccountId;

    /**
     * Campo da scegliere per selezionare il tag del nuovo movimento.
     */
    @FXML ChoiceBox<Tag> movementNewTagId;

    /**
     * Campo da inserire per impostare la descrizione del nuovo movimento.
     */
    @FXML TextField movementNewDescription;

    /**
     * ObservableList contenente i tipi di movimento.
     */
    private ObservableList<MovementType> olMovementsType;

    /**
     * ObservableList contenente i tag presenti nel ledger.
     */
    private ObservableList<Tag> olTags;

    /**
     * ObservableList contenente gli account presenti nel ledger.
     */
    private ObservableList<Account> olAccounts;

    /**
     * ObservableList contenente i movimenti aggiunti.
     */
    private ObservableList<Movement> olMovements;

    /**
     * Controller dell'MVC usato per avviare le diverse funzionalità
     * richieste dall'utente.
     */
    private MainController mainController;

    /**
     * Transazione alla quale aggiungere i movimenti.
     */
    private Transaction transaction;

    /**
     * Variabile collegata a questo stage per essere chiuso al salvataggio.
     */
    private Stage stage;

    /**
     * Controller della view principale utile per aggiornare il suo stato.
     */
    private GUIViewController guiViewController;

    /**
     * Costruttore della view dei movimenti.
     * @param mainController Controller dell'MVC.
     * @param transaction Transazione alla quale aggiungere movimenti.
     * @param stage Riferimento a tale stage.
     * @param guiViewController Riferimento alla View principale.
     */
    public GUIViewMovementController(MainController mainController, Transaction transaction
            , Stage stage, GUIViewController guiViewController) {
        this.mainController = mainController;
        this.transaction = transaction;
        this.stage = stage;
        this.guiViewController = guiViewController;
        logger.info("GUIViewMovementController created.");
    }

    /**
     * Metodo che ha la responsabilità di eliminare un movimento.
     */
    public void removeMovement() {
        Movement m = this.tableNewMovements.getSelectionModel().getSelectedItem();
        if(!olMovements.isEmpty()&&m!=null){
            this.olMovements.remove(m);
            this.mainController.removeMovement(m);
            logger.info("Removal of movement: ["+m.toString()+"]");
        }
        updateMovements();
    }

    /**
     * Metodo che ha la responsabilità di aggiungere un movimento.
     */
    public void addMovement() {
        try {
            Movement m = MovementManager.generateMovement(movementNewType.getValue()
                    , Double.parseDouble(movementNewAmount.getText())
                    , transaction, movementNewAccountId.getValue(), movementNewTagId.getValue()
                    , movementNewDescription.getText(), this.mainController.idGenerator().generate());
            olMovements.add(m);
            logger.info("Addition of movement: ["+m.toString()+"]");
        }catch (Exception e){
            logger.warning("Failed in Addition of movement.");
        }
        updateMovements();
        movementNewAmount.clear();
        movementNewDescription.clear();
    }

    /**
     * Metodo che ha la responsabilità di salvare i movimenti nella transazione data
     * , chiudere lo stage e aggiornare lo stato.
     */
    public void saveMovements() {
        if(!olMovements.isEmpty()) {
            this.mainController.addTransaction(transaction, olMovements);
            guiViewController.updateTransactions();
            guiViewController.updateAccounts();
            this.mainController.update();
            this.mainController.save();
        }
        stage.close();
        logger.info("GUIViewMovementController closed.");
    }

    /**
     * Metodo che ha la responsabilità di inizializzare le variabili della View dei movimenti.
     * @param location
     * @param resources
     */
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
        logger.info("GUIViewMovementController initialized.");
    }

    /**
     * Metodo che ha la responsabilità di aggiornare le variabili riguardanti i movimenti.
     */
    private void updateMovements(){
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
        logger.finest("Movements updated.");
    }
}
