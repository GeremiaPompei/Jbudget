package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InstantTransaction implements Transaction{

    private List<Movement> movements = null;
    private LocalDateTime localDate = null;

    public InstantTransaction() {
        this.localDate = LocalDateTime.now();
        this.movements = new ArrayList<>();
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
    public LocalDateTime getDate() {
        return this.localDate;
    }
}
