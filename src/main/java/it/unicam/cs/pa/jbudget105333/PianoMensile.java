package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class PianoMensile<B extends Bilancio,T extends Tag> implements Piano<B> {

    private SortedSet<Movimento<T>> movimenti = null;
    private GestoreMovimenti<B,T> gestoreMovimenti = null;

    public PianoMensile(T tag, double value, LocalDate start, LocalDate stop
            , GestoreMovimenti<B,T> gestoreMovimenti) throws IllegalArgumentException {
        if(start.compareTo(stop) >= 0)
            throw new IllegalArgumentException();
        this.movimenti = new TreeSet<>();
        this.gestoreMovimenti = gestoreMovimenti;
        int mesi = (12*stop.getYear()+stop.getMonthValue())-(12*start.getYear()+start.getMonthValue());
        for (int i = 0; i<= mesi ;i++)
            this.movimenti.add(new Movimento(value,tag,LocalDateTime.of(start.plusMonths(i), LocalTime.MIN)));
    }

    public PianoMensile(ArrayList<? extends Tag> tags, double value, LocalDate start, LocalDate stop
            , GestoreMovimenti<B,T> gestoreMovimenti) throws IllegalArgumentException {
        if(start.compareTo(stop) >= 0)
            throw new IllegalArgumentException();
        this.movimenti = new TreeSet<>();
        this.gestoreMovimenti = gestoreMovimenti;
        int mesi = (12*stop.getYear()+stop.getMonthValue())-(12*start.getYear()+start.getMonthValue());
        for (int i = 0; i<= mesi ;i++)
            this.movimenti.add(new Movimento(value,tags,LocalDateTime.of(start.plusMonths(i), LocalTime.MIN)));
    }

    @Override
    public void move() {
        gestoreMovimenti.addMovimenti(movimenti);
        gestoreMovimenti.update();
    }

    public SortedSet<Movimento<T>> getMovimenti() {
        return movimenti;
    }
}
