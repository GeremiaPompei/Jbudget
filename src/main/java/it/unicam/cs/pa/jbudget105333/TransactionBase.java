package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionBase implements Transaction{

    private static int ID = 0;
    private List<Movement> movements = null;
    private LocalDate localDate = null;

    public TransactionBase(List<Movement> movements, LocalDate localDate) {
        this.movements = movements;
        this.localDate = localDate;
        this.ID++;
        this.movements.stream().forEach(m->m.setTransaction(this));
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public List<Movement> movements() {
        return this.movements;
    }

    @Override
    public List<Tag> tags() {
        List<Tag> lm = new ArrayList<>();
        this.movements.stream().forEach(m->lm.addAll(m.tags()));
        return lm;
    }

    @Override
    public LocalDate getDate() {
        return this.localDate;
    }
}
