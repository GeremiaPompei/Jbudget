package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LedgerBaseTest {

    private Ledger ledger;
    private IDGenerator idGenerator;
    private Transaction transaction;
    private Account fondoCassa;
    private Tag sport;
    private Tag benzina;
    private Movement debito1;
    private Movement debito2;

    @BeforeEach
    void createLedgerBase(){
        this.ledger = new LedgerBase();
        this.idGenerator = new IDGeneratorBase();
        this.transaction = new InstantTransaction(idGenerator);
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        this.sport = new TagBase("Sport","tennis",idGenerator);
        this.benzina = new TagBase("Viaggio","macchina",idGenerator);
        this.debito1 = new MovementBase(MovementType.DEBIT,800,new InstantTransaction(idGenerator)
                , fondoCassa,sport,"movimento",idGenerator);
        this.debito2 = new MovementBase(MovementType.DEBIT,80,new InstantTransaction(idGenerator)
                , fondoCassa,benzina,"movimento",idGenerator);
    }

    @Test
    void getAccounts() {
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.addAccount(this.fondoCassa);
        assertTrue(this.ledger.getAccounts().contains(this.fondoCassa));
    }

    @Test
    void addAccount() {
        assertFalse(this.ledger.getAccounts().contains(this.fondoCassa));
        this.ledger.addAccount(this.fondoCassa);
        assertTrue(this.ledger.getAccounts().contains(this.fondoCassa));
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
    void addTransaction() {
        assertFalse(this.ledger.getTransactions().contains(this.transaction));
        this.ledger.addTransaction(this.transaction);
        assertTrue(this.ledger.getTransactions().contains(this.transaction));
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
    void addTag() {
        assertFalse(this.ledger.getTags().contains(this.sport));
        this.ledger.addTag(this.sport);
        assertTrue(this.ledger.getTags().contains(this.sport));
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
    void getTagsAmount() {
    }

    @Test
    void getIDGenerator() {
    }

    @Test
    void update() {
    }
}