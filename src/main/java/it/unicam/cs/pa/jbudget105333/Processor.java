package it.unicam.cs.pa.jbudget105333;

public interface Processor <A extends Account,I extends Transaction
        ,P extends Transaction,T extends Tag>{
    String scheduleTransactionsDate(String string);
    String scheduleTransactionsTag(String string);
    String newITransaction(String string);
    String newPTransaction(String string);
    String newAccount(String string);
    String newBudgetRecord(String string);
    String newTag(String string);
}
