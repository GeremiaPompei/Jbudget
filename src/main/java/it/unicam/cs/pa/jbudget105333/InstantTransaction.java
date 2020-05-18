package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstantTransaction implements Transaction{

    private static int IDStatic = 0;
    private int ID = 0;
    private List<Movement> movements = null;
    private LocalDate localDate = null;

    public InstantTransaction() {
        this.localDate = LocalDate.now();
        this.ID = this.IDStatic++;
        this.movements = new ArrayList<>();
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    @Override
    public List<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        this.movements.stream().forEach(m -> tags.add(m.getTag()));
        return tags;
    }

    @Override
    public LocalDate getDate() {
        return this.localDate;
    }
}
