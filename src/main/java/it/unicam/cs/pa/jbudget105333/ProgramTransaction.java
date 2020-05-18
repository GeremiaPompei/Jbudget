package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramTransaction implements Transaction{

    private static int IDStatic = 0;
    private int ID = 0;
    private List<Movement> movements = null;
    private LocalDate localDate = null;

    public ProgramTransaction(LocalDate localDate){
        this.movements = new ArrayList<>();
        this.localDate = localDate;
        this.ID = this.IDStatic++;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void addMovement(Movement movement){
        if(this.localDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException();
        else
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
