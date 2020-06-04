package it.unicam.cs.pa.jbudget105333.MovementTest;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Account.AccountBase.AccountBasePrinter;
import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Movement.MovementBase.MovementBasePrinter;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Printer;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase.TagBasePrinter;
import it.unicam.cs.pa.jbudget105333.Transaction.TransactionBase.InstantTransaction.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Transaction.Transaction;
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
                ,500, AccountType.ASSETS,idGenerator);
        Transaction transaction = new InstantTransaction(idGenerator);
        Movement movement = new MovementBase(MovementType.CREDITS,870,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        String movementS = "\n   [ID:"+movement.getID()+"] , "+movement.getType()+" , "+movement.getAmount()+" , "+
                accountP.stringOf(movement.getAccount())
                +" , "+tagP.stringOf(movement.getTag())
                +" , " +movement.getDate().toLocalDate().toString()+" , "
                +movement.getDescription();
        assertEquals(movementP.stringOf(movement),movementS);
    }
}