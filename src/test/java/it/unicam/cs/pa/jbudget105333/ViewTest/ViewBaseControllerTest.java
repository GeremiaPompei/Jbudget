package it.unicam.cs.pa.jbudget105333.ViewTest;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBasePrinter;
import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetBase.BudgetBaseController;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetController;
import it.unicam.cs.pa.jbudget105333.Budget.BudgetReader;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportBase.BudgetReportBaseController;
import it.unicam.cs.pa.jbudget105333.BudgetReport.BudgetReportController;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerBase.LedgerBaseController;
import it.unicam.cs.pa.jbudget105333.Ledger.LedgerController;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Movement.MovementBase.MovementBasePrinter;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Printer;
import it.unicam.cs.pa.jbudget105333.Reader;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase.TagBasePrinter;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.InstantTransaction.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.ProgramTransaction.ProgramTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.TransactionBasePrinter;
import it.unicam.cs.pa.jbudget105333.View.ViewBaseController;
import it.unicam.cs.pa.jbudget105333.View.ViewController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ViewBaseControllerTest {

    private BudgetReportController brcontroller;

    private ViewController viewController;
    private BudgetReportController budgetReportC;
    private LedgerController ledgerC;
    private BudgetController budgetC;
    private IDGenerator idGenerator;
    private Transaction transaction;
    private Transaction transaction2;
    private Account fondoCassa;
    private Tag sport;
    private Tag benzina;
    private Movement debito1;
    private Movement debito2;

    @BeforeEach
    void saveContext(){
        LedgerController lcontroller = new LedgerBaseController();
        BudgetController bcontroller = new BudgetBaseController(lcontroller);
        brcontroller = new BudgetReportBaseController(lcontroller,bcontroller);
    }

    @AfterEach
    void restoreContext(){
        try {
            brcontroller.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void createViewBaseController(){
        try {
            new ObjectOutputStream(new FileOutputStream("src/file/Ledger.txt"));
            new ObjectOutputStream(new FileOutputStream("src/file/Budget.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ledgerC = new LedgerBaseController();
        this.budgetC = new BudgetBaseController(ledgerC);
        this.budgetReportC = new BudgetReportBaseController(ledgerC,budgetC);
        this.idGenerator = ledgerC.getIDGenerator();
        this.viewController = new ViewBaseController(budgetReportC);
        this.transaction = new InstantTransaction(idGenerator);
        this.transaction2 = new ProgramTransaction(LocalDateTime
                .of(2020,9,9,0,0,0),idGenerator);
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator);
        this.sport = new TagBase("Sport","tennis",idGenerator);
        this.benzina = new TagBase("Viaggio","macchina",idGenerator);
        this.debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        this.debito2 = new MovementBase(MovementType.DEBIT,80,transaction2
                , fondoCassa,benzina,"movimento",idGenerator);
    }

    @Test
    void scheduleTransactionsDate() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newTag("Macchina,Benzina");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        this.viewController.newITransaction("credits,90," +this.ledgerC.getAccounts().iterator()
                .next().getID()+","+this.ledgerC.getTags().iterator().next().getID()+",itransaction");
        this.viewController.newPTransaction("2020-09-09/debit,89,"+this.ledgerC.getAccounts()
                .iterator().next().getID()+","+this.ledgerC.getTags().iterator().next().getID() +",ptransaction");
        Printer<Transaction> transactionP = new TransactionBasePrinter(new MovementBasePrinter
                (new AccountBasePrinter(),new TagBasePrinter()));
        Iterator<Transaction> iterator = this.ledgerC.getTransactions().iterator();
        iterator.next();
        String stringa = transactionP.stringOf(iterator.next());
        assertNotEquals(this.viewController.scheduleTransactionsDate("2020-01-08,2020-10-10"),"\n"+stringa);
        assertEquals(this.viewController.scheduleTransactionsDate("2020-08-08,2020-10-10"),"\n"+stringa);
    }

    @Test
    void scheduleTransactionsTag() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newTag("Macchina,Benzina");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        Iterator<Tag> tagi = this.ledgerC.getTags().iterator();
        String tagid1 = tagi.next().getID()+"";
        String tagid2 = tagi.next().getID()+"";
        this.viewController.newITransaction("credits,90," +this.ledgerC.getAccounts().iterator()
                .next().getID()+","+tagid1+",itransaction");
        this.viewController.newPTransaction("2020-09-09/debit,89,"+this.ledgerC.getAccounts()
                .iterator().next().getID()+","+tagid2 +",ptransaction");
        Printer<Transaction> transactionP = new TransactionBasePrinter(new MovementBasePrinter
                (new AccountBasePrinter(),new TagBasePrinter()));
        Iterator<Transaction> iterator = this.ledgerC.getTransactions().iterator();
        String stringa = transactionP.stringOf(iterator.next());
        String stringa2 = transactionP.stringOf(iterator.next());
        assertNotEquals(this.viewController.scheduleTransactionsTag(tagid1+""),"\n"+stringa+"\n"+stringa2);
        assertEquals(this.viewController.scheduleTransactionsTag(tagid2+""),"\n"+stringa2);
        assertEquals(this.viewController.scheduleTransactionsTag(tagid1+""),"\n"+stringa);
    }

    @Test
    void showTransactions() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newTag("Macchina,Benzina");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        int tagid=this.ledgerC.getTags().iterator().next().getID();
        this.viewController.newITransaction("credits,90," +this.ledgerC.getAccounts().iterator()
                .next().getID()+","+tagid+",itransaction");
        this.viewController.newPTransaction("2020-09-09/debit,89,"+this.ledgerC.getAccounts()
                .iterator().next().getID()+","+tagid +",ptransaction");
        Printer<Transaction> transactionP = new TransactionBasePrinter(new MovementBasePrinter
                (new AccountBasePrinter(),new TagBasePrinter()));
        Iterator<Transaction> iterator = this.ledgerC.getTransactions().iterator();
        String stringa = transactionP.stringOf(iterator.next());
        String stringa2 = transactionP.stringOf(iterator.next());
        assertEquals(this.viewController.showTransactions(),"\n"+stringa+"\n"+stringa2);
        assertNotEquals(this.viewController.showTransactions(),"\n"+stringa);
        assertNotEquals(this.viewController.showTransactions(),"\n"+stringa2);
    }

    @Test
    void newITransaction() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        int tagid=this.ledgerC.getTags().iterator().next().getID();
        String success = this.viewController.newITransaction("credits,90," +this.ledgerC.getAccounts()
                .iterator().next().getID()+","+tagid+",itransaction");
        Printer<Transaction> transactionP = new TransactionBasePrinter(new MovementBasePrinter
                (new AccountBasePrinter(),new TagBasePrinter()));
        Iterator<Transaction> iterator = this.ledgerC.getTransactions().iterator();
        String stringa = transactionP.stringOf(iterator.next());
        assertEquals(this.viewController.showTransactions(),"\n"+stringa);
        assertEquals(success,"Success!");
        String fail = this.viewController.newITransaction("credits,90,account,id,descrizione");
        assertEquals(fail,"Incorrect ITransaction Format");
    }

    @Test
    void newPTransaction() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        int tagid=this.ledgerC.getTags().iterator().next().getID();
        String success = this.viewController.newPTransaction("2020-09-09/credits,90,"
                +this.ledgerC.getAccounts().iterator().next().getID()+","+tagid+",itransaction");
        Printer<Transaction> transactionP = new TransactionBasePrinter(new MovementBasePrinter
                (new AccountBasePrinter(),new TagBasePrinter()));
        Iterator<Transaction> iterator = this.ledgerC.getTransactions().iterator();
        String stringa = transactionP.stringOf(iterator.next());
        assertEquals(this.viewController.showTransactions(),"\n"+stringa);
        assertEquals(success,"Success!");
        String fail = this.viewController.newPTransaction("2020-09-09/credits,90,account,id,descrizione");
        assertEquals(fail,"Incorrect PTransaction Format");
    }

    @Test
    void showAccounts() {
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        Printer<Account> accountP = new AccountBasePrinter();
        assertEquals("\n"+accountP.stringOf(this.ledgerC.getLedger().getAccounts().iterator().next())
                ,this.viewController.showAccounts());
        this.viewController.newAccount("Prepagata,personale,32,liabilities");
        Iterator<Account> accountI = this.ledgerC.getLedger().getAccounts().iterator();
        assertEquals("\n"+accountP.stringOf(accountI.next())+"\n"+accountP.stringOf(accountI.next())
                ,this.viewController.showAccounts());
    }

    @Test
    void newAccount() {
        String success = this.viewController.newAccount("FondoCassa,personale,500,assets");
        assertEquals(this.ledgerC.getAccounts().iterator().next().getName(),"FondoCassa");
        assertEquals(this.ledgerC.getAccounts().iterator().next().getDescription(),"personale");
        assertEquals(this.ledgerC.getAccounts().iterator().next().getOpeningBalance(),500);
        assertEquals(this.ledgerC.getAccounts().iterator().next().getAccountType(),AccountType.ASSETS);
        assertEquals(success,"Success!");
        String fail = this.viewController.newAccount("Prepagata,personale,cinquecento,liabilities");
        assertEquals(fail,"Incorret Account Format");
    }

    @Test
    void showTags() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newTag("Macchina,Benzina");
        Iterator<Tag> tagI = this.ledgerC.getLedger().getTags().iterator();
        Printer<Tag> tagP = new TagBasePrinter();
        Tag tag1 = tagI.next();
        Tag tag2 = tagI.next();
        assertEquals("\n"+tagP.stringOf(tag1)+"\n"+tagP.stringOf(tag2),this.viewController.showTags());

    }

    @Test
    void newTag() {
        String success = this.viewController.newTag("Sport,tennis");
        assertEquals(this.ledgerC.getTags().iterator().next().getName(),"Sport");
        assertEquals(this.ledgerC.getTags().iterator().next().getDescription(),"tennis");
        assertEquals(success,"Success!");
        String fail = this.viewController.newTag("Macchina");
        assertEquals(fail,"Incorret Tag Format");
    }

    @Test
    void showBudgets() {
        this.viewController.newTag("Sport,tennis");
        Tag tag = this.ledgerC.getTags().iterator().next();
        this.viewController.newBudgetRecord(tag.getID()+",60");
        Printer<Tag> tagP = new TagBasePrinter();
        String budgetS = "\n"+tagP.stringOf(tag)+": 60.0";
        assertEquals(this.viewController.showBudgetRecords(),budgetS);
    }

    @Test
    void newBudgetRecord() {
        this.viewController.newTag("Sport,tennis");
        String success = this.viewController.newBudgetRecord(this.ledgerC.getTags().iterator().next().getID()+",60");
        Printer<Tag> tagP = new TagBasePrinter();
        String budgetS = "\n"+tagP.stringOf(this.ledgerC.getLedger().getTags().iterator().next())+": 60.0";
        assertEquals(this.budgetC.getBudget().getValue(this.ledgerC.getTags().iterator().next()),60);
        assertEquals(success,"Success!");
        String fail1 = this.viewController.newBudgetRecord("400,60");
        assertEquals(fail1,"Tag not found");
        String fail2 = this.viewController.newBudgetRecord("tag,60");
        assertEquals(fail2,"Incorret Budget Format");
    }

    @Test
    void check() {
        IDGenerator idGenerator = new IDGeneratorBase();
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Tag benzina = new TagBase("Viaggio","macchina",idGenerator);
        Transaction transaction = new InstantTransaction(idGenerator);
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento1",idGenerator);
        Movement debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento2",idGenerator);
        this.ledgerC.addTag(sport);
        this.ledgerC.addTag(benzina);
        this.ledgerC.addAccount(fondoCassa);
        this.ledgerC.addTransaction(transaction,transaction.getMovements());
        this.budgetC.addBudgetRecord(sport.getID(),100.0);
        this.budgetC.addBudgetRecord(benzina.getID(),100.0);
        double somma = this.budgetC.getBudgetMap().get(sport)-debito1.getAmount();
        assertEquals(this.viewController.check(),"\n"+new TagBasePrinter().stringOf(sport)+": " +somma+"\n");
        assertNotEquals(this.viewController.check(),"\n"+new TagBasePrinter().stringOf(sport)+": "
                +(-debito1.getAmount())+"\n");
    }

    @Test
    void update() {
        this.viewController.newTag("Sport,Tennis");
        this.viewController.newTag("Macchina,Benzina");
        this.viewController.newAccount("FondoCassa,personale,500,assets");
        this.viewController.newITransaction("credits,90," +this.ledgerC.getAccounts().iterator()
                .next().getID()+","+this.ledgerC.getTags().iterator().next().getID()+",itransaction");
        assertNotEquals(this.ledgerC.getAccounts().iterator().next().getBalance()
                ,this.ledgerC.getAccounts().iterator().next().getOpeningBalance()
                        +this.ledgerC.getTransactions().iterator().next().getTotalAmount());
        this.viewController.update();
        assertEquals(this.ledgerC.getAccounts().iterator().next().getBalance()
                ,this.ledgerC.getAccounts().iterator().next().getOpeningBalance()+
                        +this.ledgerC.getTransactions().iterator().next().getTotalAmount());
    }

    @Test
    void save() {
        try {
            Tag sport = new TagBase("Sport","tennis",new IDGeneratorBase());
            this.budgetReportC.getLedgerC().addTag(sport);
            this.budgetReportC.getBudgetC().addBudgetRecord(sport.getID(),60.0);
            this.viewController.save();
            Reader<Budget> reader2 = new BudgetReader("src/file/Budget");
            Budget budget = reader2.read();
            reader2.close();
            assertEquals(this.budgetReportC.getLedgerC().getTag(sport.getID()).getName(),sport.getName());
            assertEquals(this.budgetReportC.getLedgerC().getTag(sport.getID()).getID(),sport.getID());
            assertEquals(this.budgetReportC.getBudgetC().getBudgetMap().keySet().toArray().length
                    ,budget.getBudgetMap().keySet().toArray().length);
            assertEquals(this.budgetReportC.getBudgetC().getBudget().getBudgetMap().get(sport)
                    ,budget.getBudgetMap().get(budget.getTags().toArray()[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}