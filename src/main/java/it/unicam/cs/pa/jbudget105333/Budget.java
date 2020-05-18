package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;
import java.util.Set;
import java.util.Map;

public interface Budget extends Serializable {
    void add(Tag tag,double value);
    Map<Tag,Double> getBudget();
    double getValue(Tag tag);
    Set<Tag> getTags();
}
