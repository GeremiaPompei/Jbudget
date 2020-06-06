package it.unicam.cs.pa.jbudget105333.ModelTest.TransactionTest;

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

import java.util.concurrent.atomic.AtomicReference;

class TransactionBasePrinterTest {

    @Test
    void stringOf() {
        IDGenerator idGenerator = new IDGeneratorBase();
        Transaction transaction = new InstantTransaction(idGenerator);
        Account fondoCassa = new AccountBase("FondoCassa","personale"
                ,500, AccountType.ASSETS,idGenerator);
        Tag sport = new TagBase("Sport","tennis",idGenerator);
        Movement debito1 = new MovementBase(MovementType.DEBIT,800,transaction
                , fondoCassa,sport,"movimento",idGenerator);
        Printer<Movement> movementP = new MovementBasePrinter(new AccountBasePrinter(),new TagBasePrinter());
        AtomicReference<String> ar = new AtomicReference<>();
        ar.set(transaction.getDate().toLocalDate().toString()+" , [ID:"+transaction.getID()
                +"] , TotalAmount: "+transaction.getTotalAmount());
        transaction.getMovements().stream()
                .forEach(m->ar.set(ar.get()+movementP.stringOf(m)));
        String transactionS = "\n"+ar.get();
        Printer<Transaction> transactionP = new TransactionBasePrinter(movementP);
        assertEquals(transactionP.stringOf(transaction),transactionS);
    }
}