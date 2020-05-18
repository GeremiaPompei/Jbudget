package it.unicam.cs.pa.jbudget105333;

public class MovementBasePrinter implements Printer<Movement>{
    @Override
    public String stringOf(Movement movement) {
        return movement.type()+" , "+movement.amount()+" , "+
                new AccountBasePrinter<>().stringOf(movement.getAccount())
                +" , "+new TagBasePrinter<>().stringOf(movement.getTag())
                +" , " +movement.getDate().toString()+" , "
                +movement.getDescription();
    }
}
