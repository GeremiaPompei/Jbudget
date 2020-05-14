package it.unicam.cs.pa.jbudget105333;

import java.util.List;

public interface Budget {
    List<Tag> tags();
    void set(Tag tag);
    Tag get();
}
