package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTransactionBase implements ScheduleTransaction{

    String description = "";
    List<Transaction> transactions = null;
    boolean state = false;

    public ScheduleTransactionBase(List<Transaction> transactions, String description) {
        this.description = description;
        this.transactions = transactions;
        this.state = toComplete()==0;
    }

    public long toComplete(){
        return this.transactions.stream().filter(t->t.getDate().compareTo(LocalDate.now())<=0).count();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<Transaction> getTransactions(LocalDate localDate) {
        List<Transaction> transactions = new ArrayList<>();
        this.transactions.stream().filter(t->t.getDate().equals(localDate)).forEach(t->transactions.add(t));
        return transactions;
    }

    @Override
    public boolean isCompleted() {
        return toComplete()==0;
    }
}
