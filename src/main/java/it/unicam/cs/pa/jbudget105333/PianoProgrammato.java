package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PianoProgrammato<B extends Bilancio,T extends Tag> implements Piano<B> {

    private Movimento<T> movimento = null;
    private GestoreMovimenti<B,T> gestoreMovimenti = null;

    public PianoProgrammato(T tag, double value, GestoreMovimenti<B,T> gestoreMovimenti, LocalDate localDate) {
        this.movimento = new Movimento(value, tag, LocalDateTime.of(localDate, LocalTime.MIN));
        this.gestoreMovimenti = gestoreMovimenti;
    }

    public PianoProgrammato(ArrayList<? extends Tag> tags, double value, GestoreMovimenti<B,T> gestoreMovimenti
            , LocalDate localDate) {
        this.movimento = new Movimento(value, tags, LocalDateTime.of(localDate, LocalTime.MIN));
        this.gestoreMovimenti = gestoreMovimenti;
    }

    @Override
    public void move(){
        gestoreMovimenti.addMovimento(movimento);
        gestoreMovimenti.update();
    }

    public Movimento getMovimento() {
        return movimento;
    }
}
