package it.unicam.cs.pa.jbudget105333;

public class BudgetBasePrinter<B extends Budget> implements Printer<B>{
    @Override
    public String stringOf(B budget) {
        String s = "";
        for(Tag t : budget.getTags())
            s += new TagBasePrinter().stringOf(t) +": "+ budget.getValue(t)+"\n";
        return s;
    }
}
