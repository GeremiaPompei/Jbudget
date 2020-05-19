package it.unicam.cs.pa.jbudget105333;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GUIView<B extends BudgetReport,C extends Controller<B>> extends Application implements View<C>{

    private C controller = null;

    @Override
    public void init() throws Exception {
        super.init();
        Budget budget = new BudgetBase();
        Ledger ledger = new LedgerBase();
        BudgetReportBase budgetReport = new BudgetReportBase(ledger,budget);
        FileStore<BudgetReportBase> store = new FileStore(budgetReport);
        Controller<BudgetReport> controller = new ControllerBase(store.read(),store);
        this.controller = (C)controller;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JBudget");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Button b1 = new Button("New Account");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        ComboBox cb1 = new ComboBox(FXCollections.observableArrayList(MovementType.values()));
        Button b2 = new Button("New Tag");
        TextField t5 = new TextField();
        TextField t6 = new TextField();
        Button b3 = new Button("New Budget");
        List<String> ls = new ArrayList<>();
        this.controller.getBudgetReport().getLedger().getTags().stream()
                .forEach(t->ls.add(t.getName()));
        ComboBox cb2 = new ComboBox(
                FXCollections.observableArrayList(ls));
        TextField t8 = new TextField();
        Button b4 = new Button("New Instant Transaction");
        Button b5 = new Button("New Program Transaction");
        AtomicReference<String> s2 = new AtomicReference<>();
        s2.set("");
        this.controller.getBudgetReport().getLedger().getTransactions().stream()
                .forEach(t->s2.set(s2.get()+new TransactionPrinter<>().stringOf(t)));
        Label l1 = new Label(printState());
        Label l2 = new Label(s2.get());
        grid.add(b1,2,3);
        grid.add(t1,3,3);
        grid.add(t2,4,3);
        grid.add(t3,5,3);
        grid.add(cb1,6,3);
        grid.add(b2,2,4);
        grid.add(t5,3,4);
        grid.add(t6,4,4);
        grid.add(b3,2,5);
        grid.add(cb2,3,5);
        grid.add(t8,4,5);
        grid.add(b4,2,6);
        grid.add(b5,2,7);
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(l1,2,2);
        gp.add(grid,2,3);
        gp.add(l2,2,4);
        primaryStage.setScene(new Scene(gp,1000,300));
        primaryStage.show();
    }

    @Override
    public void open(C controller) throws IOException {
        this.controller = controller;
        launch();
    }

    @Override
    public void close() {

    }

    private String printState(){
        AtomicReference<String> s = new AtomicReference<>();
        if(controller.getBudgetReport().getLedger().getAccounts().isEmpty())
            s.set("NO ACCOUNTS");
        else{
            s.set("ACCOUNTS:");
            controller.getBudgetReport().getLedger().getAccounts().stream()
                    .forEach(a -> s.set(s.get()+
                            ("   "+new AccountBasePrinter<>().stringOf(a))));
            controller.getBudgetReport().check().keySet().stream()
                    .filter(t->controller.getBudgetReport().check().get(t)<0)
                    .forEach(t->s.set(s.get()+("ATTENTION: "+t.getName()
                            +": "+controller.getBudgetReport().check().get(t))));
        }
        s.set(s.get()+(LocalDateTime.now()));
        return s.get();
    }
}
