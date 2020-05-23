package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetReportBaseTest {

    private Ledger ledger;
    private Budget budget;
    private BudgetReport<Ledger,Budget> budgetReport;

    @BeforeEach
    void createBudgetReport(){
        this.ledger = new LedgerBase();
        this.budget = new BudgetBase();
        this.budgetReport = new BudgetReportBase<>(ledger,budget);
    }

    @Test
    void getLedger() {
        assertEquals(this.ledger,this.budgetReport.getLedger());
        assertTrue(this.ledger instanceof Ledger);
        assertFalse(this.ledger instanceof BudgetReport);
    }

    @Test
    void getBudget() {
        assertEquals(this.budget,this.budgetReport.getBudget());
        assertTrue(this.budget instanceof Budget);
        assertFalse(this.budget instanceof BudgetReport);
    }

    /*Aggiungo una somma ad un tag nel budget e faccio una transazione di un movimento maggiore alla
    somma impostata per lo stesso tag su cui viene fatto il movimento
     */
    @Test
    void check() {
        Ledger ledger = new LedgerBase();
        Budget budget = new BudgetBase();
        BudgetReport budgetReport = new BudgetReportBase<>(ledger,budget);
        IDGenerator idGenerator = new IDGeneratorBase();
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Tag benzina = new TagBase("Viaggio","macchina",idGenerator);
        Transaction transaction = new InstantTransaction(idGenerator);
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        Movement debito2 = new MovementBase(MovementType.DEBIT,80,transaction
                , fondoCassa,benzina,"movimento",idGenerator);
        ledger.addTransaction(transaction);
        budget.add(sport,100);
        budget.add(benzina,100);
        assertEquals(budgetReport.check().get(sport),budget.getValue(sport)-debito1.getAmount());
        assertEquals(budgetReport.check().get(benzina),budget.getValue(benzina)-debito2.getAmount());
    }
}