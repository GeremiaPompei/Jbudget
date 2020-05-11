package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;
import java.util.*;

public class GestoreMovimenti <B extends Bilancio,T extends Tag> {

    private SortedSet<Movimento<T>> movimenti = null;
    private B bilancio = null;

    public GestoreMovimenti(B bilancio) {
        this.movimenti = new TreeSet<>();
        this.bilancio = bilancio;
    }

    public void update(){
        LocalDateTime localDateTime = LocalDateTime.now();
        this.bilancio.resetValue();
        for(Movimento m : this.movimenti){
            if(m.getLocalDateTime().compareTo(localDateTime) <= 0){
                if(m.getTags().get(0) instanceof TagIn)
                    this.bilancio.increment(m.getValue()*m.getCount());
                else
                    this.bilancio.decrement(m.getValue()*m.getCount());
            }
        }
    }

    public void addMovimento(Movimento<T> movimento){
        if(movimenti.contains(movimento)){
            for (Movimento m : movimenti)
                if(movimento.equals(m))
                    m.incrementCount();
        }else
            movimenti.add(movimento);
    }

    public void addMovimenti(SortedSet<Movimento<T>> movimenti){
        for(Movimento<T> movimento : movimenti){
            for (Movimento m : this.movimenti)
                if(movimento.equals(m))
                    m.incrementCount();
        }
        this.movimenti.addAll(movimenti);
    }

    public SortedSet<Movimento<T>> getMovimenti() {
        return movimenti;
    }

    public B getBilancio() {
        return bilancio;
    }
}
