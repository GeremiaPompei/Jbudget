package it.unicam.cs.pa.jbudget105333;

import java.util.concurrent.atomic.AtomicReference;

public class BudgetBasePrinter implements Printer<Budget>{

    private final Printer<Tag> tag;

    public BudgetBasePrinter(Printer<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public String stringOf(Budget budget) {
        AtomicReference<String> ar = new AtomicReference<>();
        ar.set("[ID"+budget.getID()+"]\n");
        budget.getTags().stream().
                forEach(t->ar.set(ar.get()+this.tag.stringOf(t) +": "+ budget.getValue(t)+"\n"));
        return ar.get();
    }
}
