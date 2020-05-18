package it.unicam.cs.pa.jbudget105333;

public class InstantTransaction extends TransactionBase{

    @Override
    public void addMovement(Movement movement) {
        this.getMovements().add(movement);
    }
}
