package it.unicam.cs.pa.jbudget105333.Transaction;

import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public abstract class TransactionBase implements Transaction {

    private final Set<Movement> movements;
    private final LocalDateTime date;
    private final int ID;

    //Costruttore utilizzato da classi con transazioni eseguite al momento
    public TransactionBase(int ID) {
        this.ID = ID;
        this.date = LocalDateTime.now();
        this.movements = new TreeSet<>();
    }

    //Costruttore utilizzato da classi con transazioni eseguite nella data specificata
    public TransactionBase(LocalDateTime date, int ID) {
        this.ID = ID;
        this.date = date;
        this.movements = new TreeSet<>();
    }

    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    @Override
    public void addMovements(Set<Movement> movements){
        this.movements.addAll(movements);
    }

    @Override
    public Set<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public Set<Tag> getTags() {
        Set<Tag> tags = new TreeSet<>();
        this.movements.stream().forEach(m -> tags.add(m.getTag()));
        return tags;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    //Metodo che permette di restituire la somma del saldo dei movimenti per un resoconto
    @Override
    public double getTotalAmount() {
        Double value = 0.0;
        for(Movement m : this.movements)
            value += m.getAmount();
        return value;
    }

    @Override
    public int getID(){
        return this.ID;
    }

    //Metodo che compara due transazioni tramite data e poi id per garantirne l'univocità
    @Override
    public int compareTo(Transaction o) {
        int comparator = this.date.compareTo(o.getDate());
        if(comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }

    @Override
    public String toString(){
        AtomicReference<String> t = new AtomicReference<>();
        t.set(this.ID+": "+this.date+" ["+this.getTotalAmount()+"]\n");
        this.movements.stream().forEach(m->t.set(t.get()+"    "+m.toString()+"\n"));
        return t.get();
    }
}
