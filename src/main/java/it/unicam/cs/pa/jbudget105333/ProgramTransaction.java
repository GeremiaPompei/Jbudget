package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;

public class ProgramTransaction extends TransactionBase{

    public ProgramTransaction(LocalDateTime localDate) {
        super(localDate);
    }

    @Override
    public void addMovement(Movement movement){
        if(super.getDate().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException();
        else
            this.getMovements().add(movement);
    }
}
