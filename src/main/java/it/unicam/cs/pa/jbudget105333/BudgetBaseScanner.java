package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class BudgetBaseScanner implements Scanner<Budget>{

    private BudgetReport budgetReport = null;

    public BudgetBaseScanner(BudgetReport budgetReport) {
        this.budgetReport = budgetReport;
    }

    @Override
    public Budget scanOf(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string,",");
        String id = stringTokenizer.nextToken();
        AtomicReference<Tag> tag = new AtomicReference();
        this.budgetReport.getLedger().getTags().stream()
                .filter(t->t.getName().equalsIgnoreCase(id.trim()))
                .forEach(t-> tag.set(t));
        if(tag.get()!=null)
            this.budgetReport.getBudget().add(tag.get(),Double.parseDouble(stringTokenizer.nextToken()));
        return this.budgetReport.getBudget();
    }
}
