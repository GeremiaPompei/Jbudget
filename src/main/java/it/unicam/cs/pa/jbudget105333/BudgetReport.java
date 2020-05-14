package it.unicam.cs.pa.jbudget105333;

import java.util.List;
import java.util.Map;

public interface BudgetReport {
    List<Tag> tags();
    Map<Tag,Double> report();
    Budget getBudget();
    double get(Tag t);
}
