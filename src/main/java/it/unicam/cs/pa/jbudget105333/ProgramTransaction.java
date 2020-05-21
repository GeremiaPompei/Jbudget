package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;

public class ProgramTransaction extends TransactionBase{

    public ProgramTransaction(LocalDateTime localDate, IDGenerator idGenerator) {
        super(localDate, idGenerator);
    }

    public ProgramTransaction(LocalDateTime localDate, int ID) {
        super(localDate, ID);
    }

    @Override
    public void addMovement(Movement movement){
        if(super.getDate().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException();
        else
            this.getMovements().add(movement);
    }
}
