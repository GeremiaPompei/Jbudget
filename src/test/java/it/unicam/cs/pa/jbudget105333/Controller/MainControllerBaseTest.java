package it.unicam.cs.pa.jbudget105333.Controller;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainControllerBaseTest {

    private Ledger ledger;
    private IDGenerator idGenerator;
    private Transaction transaction;
    private Account fondoCassa;
    private Account prepagata;
    private Tag sport;

    @BeforeEach
    void createMainControllerBase(){
        this.ledger = new LedgerBase();
        this.idGenerator = ledger.getIDGenerator();
        this.transaction = new InstantTransaction(idGenerator.generate());
        this.fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator.generate());
        this.prepagata = new AccountBase("Prepagata","personale"
                ,200,AccountType.LIABILITIES,idGenerator.generate());
        this.sport = new TagBase("Sport","tennis",idGenerator.generate());
    }

    @Test
    void getAccounts() {

    }

    @Test
    void getAccount() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void removeAccount() {
    }

    @Test
    void getTags() {
    }

    @Test
    void getTag() {
    }

    @Test
    void addTag() {
    }

    @Test
    void removeTag() {
    }

    @Test
    void getTransactions() {
    }

    @Test
    void getTransaction() {
    }

    @Test
    void addTransaction() {
    }

    @Test
    void removeTransaction() {
    }

    @Test
    void scheduleTransactionsDate() {
    }

    @Test
    void scheduleTransactionsTag() {
    }

    @Test
    void getBudgetRecords() {
    }

    @Test
    void addBudgetRecord() {
    }

    @Test
    void removeBudgetRecord() {
    }

    @Test
    void check() {
    }

    @Test
    void update() {
    }

    @Test
    void save() {
    }

    @Test
    void idGenerator() {
    }
}