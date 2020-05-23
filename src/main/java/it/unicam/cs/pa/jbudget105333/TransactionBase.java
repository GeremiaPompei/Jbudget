package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public abstract class TransactionBase implements Transaction{

    private Set<Movement> movements = null;
    private final LocalDateTime localDate;
    private final int ID;

    public TransactionBase(IDGenerator idGenerator) {
        this.ID = idGenerator.generate();
        this.localDate = LocalDateTime.now();
        this.movements = new TreeSet<>();
    }

    public TransactionBase(LocalDateTime localDate,IDGenerator idGenerator) {
        this.ID = idGenerator.generate();
        this.localDate = localDate;
        this.movements = new TreeSet<>();
        idGenerator.store(this);
    }

    @Override
    public abstract void addMovement(Movement movement);

    @Override
    public Set<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public Set<Tag> getTags() {
        Set<Tag> tags = new TreeSet<>();
        this.movements.stream().forEach(m -> tags.add(m.getTag()));
        return tags;
    }

    @Override
    public LocalDateTime getDate() {
        return this.localDate;
    }

    @Override
    public double getTotalAmount() {
        Double value = 0.0;
        for(Movement m : this.movements)
            if(m.getType().equals(MovementType.CREDITS)
                    && m.getAccount().getAccountType().equals(AccountType.ASSETS)
                    ||m.getType().equals(MovementType.DEBIT)
                    && m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                value += m.getAmount();
            else
                value -= m.getAmount();
        return value;
    }

    @Override
    public int getID(){
        return this.ID;
    }

    @Override
    public int compareTo(Transaction o) {
        int comparator = this.localDate.compareTo(o.getDate());
        if(comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }
}
