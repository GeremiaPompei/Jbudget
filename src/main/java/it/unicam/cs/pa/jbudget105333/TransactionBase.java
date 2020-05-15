package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionBase implements Transaction{

    private static int ID = 0;
    private List<Movement> movements = null;
    private List<Tag> tags = null;
    private LocalDate localDate = null;

    public TransactionBase(LocalDate localDate, List<Tag> tags) {
        this.movements = new ArrayList<>();
        this.localDate = localDate;
        this.tags = tags;
        this.ID++;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void addMovement(Movement movement){
        this.movements.add(movement);
    }

    @Override
    public List<Movement> movements() {
        return this.movements;
    }

    @Override
    public List<Tag> tags() {
        return this.tags;
    }

    @Override
    public LocalDate getDate() {
        return this.localDate;
    }
}
