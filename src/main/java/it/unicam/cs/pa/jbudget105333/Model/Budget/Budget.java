package it.unicam.cs.pa.jbudget105333.Model.Budget;

import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.io.Serializable;
import java.util.Set;
import java.util.Map;

public interface Budget extends Serializable {
    void add(Tag tag,double value);
    void remove(Tag tag);
    Map<Tag,Double> getBudgetMap();
    double getValue(Tag tag);
    Set<Tag> getTags();
}
