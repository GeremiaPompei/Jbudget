package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;

public class PianoIstantaneo<B extends Bilancio,T extends Tag> implements Piano<B> {

    private Movimento<T> movimento = null;
    private GestoreMovimenti<B,T> gestoreMovimenti = null;

    public PianoIstantaneo( T tag, double value,GestoreMovimenti<B,T> gestoreMovimenti) {
        this.movimento = new Movimento(value,tag, LocalDate.now());
        this.gestoreMovimenti = gestoreMovimenti;
    }

    public PianoIstantaneo( ArrayList<? extends Tag> tags, double value,GestoreMovimenti<B,T> gestoreMovimenti) {
        this.movimento = new Movimento(value,tags,LocalDate.now());
        this.gestoreMovimenti = gestoreMovimenti;
    }

    @Override
    public void move(){
        gestoreMovimenti.addMovimento(movimento);
        gestoreMovimenti.update();
    }

    public Movimento<T> getMovimento() {
        return movimento;
    }

}
