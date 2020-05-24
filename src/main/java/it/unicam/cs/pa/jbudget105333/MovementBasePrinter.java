package it.unicam.cs.pa.jbudget105333;

public class MovementBasePrinter implements Printer<Movement>{

    private final Printer<Account> accountPrinter;
    private final Printer<Tag> tagPrinter;

    public MovementBasePrinter(Printer<Account> accountPrinter, Printer<Tag> tagPrinter) {
        this.accountPrinter = accountPrinter;
        this.tagPrinter = tagPrinter;
    }

    //Converte movement in stringa
    @Override
    public String stringOf(Movement movement) {
        return "\n[ID:"+movement.getID()+"] , "+movement.getType()+" , "+movement.getAmount()+" , "+
                this.accountPrinter.stringOf(movement.getAccount())
                +" , "+this.tagPrinter.stringOf(movement.getTag())
                +" , " +movement.getDate().toLocalDate().toString()+" , "
                +movement.getDescription();
    }
}
