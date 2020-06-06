package it.unicam.cs.pa.jbudget105333.ModelTest.MovementTest;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase.AccountBase;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase.MovementBase;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase.InstantTransaction;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;
import org.junit.jupiter.api.Test;

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