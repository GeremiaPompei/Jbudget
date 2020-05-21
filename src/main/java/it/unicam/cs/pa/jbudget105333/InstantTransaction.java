package it.unicam.cs.pa.jbudget105333;

public class InstantTransaction extends TransactionBase{

    public InstantTransaction(IDGenerator idGenerator) {
        super(idGenerator);
    }

    public InstantTransaction(int ID) {
        super(ID);
    }

    @Override
    public void addMovement(Movement movement) {
        this.getMovements().add(movement);
    }
}
