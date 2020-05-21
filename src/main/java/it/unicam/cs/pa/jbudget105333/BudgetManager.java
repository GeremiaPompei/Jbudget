package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;

public interface BudgetManager {
    static BudgetReport generateReport(){
        String pathBR = "src/file/BudgetReport.txt";
        IDGenerator idGenerator = idGenerator = new IDGeneratorBase();
        Ledger ledger = new LedgerBase(idGenerator);
        Budget budget = new BudgetBase();
        BudgetReport<LedgerBase,BudgetBase> budgetReport = null;
        try{
            Reader<BudgetReportBase> reader = new BudgetReportReader(pathBR);
            budgetReport = reader.read();
        } catch (IOException e) {
            budgetReport = new BudgetReportBase(ledger,budget);
        } catch (ClassNotFoundException e) {
            budgetReport = new BudgetReportBase(ledger,budget);
        }
        return budgetReport;
    }
}
