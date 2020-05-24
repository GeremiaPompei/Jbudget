package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface ViewController {

    String scheduleTransactionsDate(String string);
    String scheduleTransactionsTag(String string);
    String showTransactions();
    String newITransaction(String string);
    String newPTransaction(String string);
    String showAccounts();
    String newAccount(String string);
    String showTags();
    String newTag(String string);
    String showBudgetRecords();
    String newBudgetRecord(String string);
    String check();
    void update();
    void save() throws IOException;
}
