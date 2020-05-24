package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementBasePrinterTest {

    @Test
    void stringOf() {
        Printer<Tag> tagP = new TagBasePrinter();
        Printer<Account> accountP = new AccountBasePrinter();
        Printer<Movement> movementP = new MovementBasePrinter(accountP,tagP);
        IDGenerator idGenerator = new IDGeneratorBase();
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500,AccountType.ASSETS,idGenerator);
        Transaction transaction = new InstantTransaction(idGenerator);
        Movement movement = new MovementBase(MovementType.CREDITS,870,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        String movementS = "\n[ID:"+movement.getID()+"] , "+movement.getType()+" , "+movement.getAmount()+" , "+
                accountP.stringOf(movement.getAccount())
                +" , "+tagP.stringOf(movement.getTag())
                +" , " +movement.getDate().toLocalDate().toString()+" , "
                +movement.getDescription();
        assertEquals(movementP.stringOf(movement),movementS);
    }
}