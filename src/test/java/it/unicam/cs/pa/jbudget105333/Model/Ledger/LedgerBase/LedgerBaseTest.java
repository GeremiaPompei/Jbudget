package it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class LedgerBaseTest {

    private Ledger ledger;
    private IDGenerator idGenerator;
    private Transaction transaction;
    private Account fondoCassa;
    private Account prepagata;
    private Tag sport;

    @BeforeEach
    void createLedgerBase(){
        this.ledger = new LedgerBase();
        this.idGenerator = ledger.getIDGenerator();
        this.transaction = new InstantTransaction(null, idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        this.sport = new TagBase("Sport","tennis",idGenerator.generate());
    }

    @Test
    void getAccounts() {
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.addAccount(this.fondoCassa);
        assertTrue(this.ledger.getAccounts().contains(this.fondoCassa));
    }

    @Test
    void getAccount() {
        int id = fondoCassa.getID();
        assertNotEquals(this.ledger.getAccount(id),fondoCassa);
        this.ledger.addAccount(fondoCassa);
        assertEquals(this.ledger.getAccount(id),fondoCassa);
    }

    @Test
    void addAccount() {
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.addAccount(this.fondoCassa);
        assertTrue(this.ledger.getAccounts().contains(this.fondoCassa));
    }

    @Test
    void addAccounts() {
        Set<Account> accounts = new TreeSet<>();
        accounts.add(this.fondoCassa);
        accounts.add(this.prepagata);
        assertFalse(ledger.getAccounts().contains(this.fondoCassa));
        assertFalse(ledger.getAccounts().contains(this.prepagata));
        ledger.addAccounts(accounts);
        assertTrue(ledger.getAccounts().contains(this.fondoCassa));
        assertTrue(ledger.getAccounts().contains(this.prepagata));
    }

    @Test
    void removeAccount() {
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.addAccount(this.fondoCassa);
        assertTrue(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.removeAccount(this.fondoCassa);
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
    }

    @Test
    void getTransactions() {
        assertFalse(this.ledger.getTransactions().contains(this.transaction));
        this.ledger.addTransaction(this.transaction);
        assertTrue(this.ledger.getTransactions().contains(this.transaction));
    }

    @Test
    void getTransaction() {
        int id = transaction.getID();
        assertNotEquals(this.ledger.getTransaction(id),transaction);
        this.ledger.addTransaction(transaction);
        assertEquals(this.ledger.getTransaction(id),transaction);
    }

    @Test
    void addTransaction() {
        assertFalse(this.ledger.getTransactions().contains(this.transaction));
        this.ledger.addTransaction(this.transaction);
        assertTrue(this.ledger.getTransactions().contains(this.transaction));
    }

    @Test
    void addTransactions() {
        Transaction transaction = new InstantTransaction(null, idGenerator.generate());
        Set<Transaction> transactions = new TreeSet<>();
        transactions.add(this.transaction);
        transactions.add(transaction);
        assertFalse(ledger.getTransactions().contains(this.transaction));
        assertFalse(ledger.getTransactions().contains(transaction));
        ledger.addTransactions(transactions);
        assertTrue(ledger.getTransactions().contains(this.transaction));
        assertTrue(ledger.getTransactions().contains(transaction));
    }

    @Test
    void removeTransaction() {
        assertFalse(this.ledger.getTransactions().contains(this.transaction));
        this.ledger.addTransaction(this.transaction);
        assertTrue(this.ledger.getTransactions().contains(this.transaction));
        this.ledger.removeTransaction(this.transaction);
        assertFalse(this.ledger.getTransactions().contains(this.transaction));
    }

    @Test
    void getTags() {
        assertFalse(this.ledger.getTags().contains(this.sport));
        this.ledger.addTag(this.sport);
        assertTrue(this.ledger.getTags().contains(this.sport));
    }

    @Test
    void getTag() {
        int id = sport.getID();
        assertNotEquals(this.ledger.getTag(id),sport);
        this.ledger.addTag(sport);
        assertEquals(this.ledger.getTag(id),sport);
    }

    @Test
    void addTag() {
        assertFalse(this.ledger.getTags().contains(this.sport));
        this.ledger.addTag(this.sport);
        assertTrue(this.ledger.getTags().contains(this.sport));
    }

    @Test
    void addTags() {
        Tag utenza = new TagBase("Utenza","Luce",idGenerator.generate());
        Set<Tag> tags = new TreeSet<>();
        tags.add(this.sport);
        tags.add(utenza);
        assertFalse(ledger.getTags().contains(sport));
        assertFalse(ledger.getTags().contains(utenza));
        ledger.addTags(tags);
        assertTrue(ledger.getTags().contains(sport));
        assertTrue(ledger.getTags().contains(utenza));
    }

    @Test
    void removeTag() {
        assertFalse(this.ledger.getTags().contains(this.sport));
        this.ledger.addTag(this.sport);
        assertTrue(this.ledger.getTags().contains(this.sport));
        this.ledger.removeTag(this.sport);
        assertFalse(this.ledger.getTags().contains(this.sport));
    }

    @Test
    void getIDGenerator() {
        assertTrue(this.ledger.getIDGenerator() instanceof IDGenerator);
        assertFalse(this.ledger.getIDGenerator() instanceof Ledger);
    }

    @Test
    void setIdGenerator(){
        assertEquals(this.ledger.getIDGenerator(),idGenerator);
        this.ledger.setIdGenerator(new IDGeneratorBase());
        assertNotEquals(this.ledger.getIDGenerator(),idGenerator);
    }

    @Test
    void update() {
        //Creo e aggiungo il primo movimento credito aggiornando
        Movement credito1 = new MovementBase(MovementType.CREDITS,88,this.transaction
                , this.prepagata,sport,"movimento credito aggiornando",idGenerator.generate());
        this.ledger.addAccount(this.prepagata);
        this.ledger.addTag(this.sport);
        this.ledger.addTransaction(this.transaction);
        this.ledger.update();
        assertEquals(this.ledger.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount());
        //Creo e aggiungo il secondo movimento credito non aggiornando
        Transaction transaction2 = new InstantTransaction(null, this.idGenerator.generate());
        Movement credito2 = new MovementBase(MovementType.CREDITS,30,transaction2
                , this.prepagata,sport,"movimento credito non aggiornando",idGenerator.generate());
        this.ledger.addTransaction(transaction2);
        ;
        //Senza update
        assertNotEquals(this.ledger.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount()-credito2.getAmount());
        assertEquals(this.ledger.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount());
        //Con update
        this.ledger.update();
        assertEquals(this.ledger.getAccounts().stream().iterator().next().getBalance()
                ,this.prepagata.getOpeningBalance()+credito1.getAmount()+credito2.getAmount());
    }
}