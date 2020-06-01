package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.Map;

public interface BudgetController {
    Budget getBudget();

    Map<Tag, Double> getBudgetMap();
    boolean addBudgetRecord(int tagID, Double amount);
    void save() throws IOException;
}
