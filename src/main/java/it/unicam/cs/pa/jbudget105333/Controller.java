package it.unicam.cs.pa.jbudget105333;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;

public interface Controller<B extends Bilancio> {
    void processCommand(String s) throws IOException;
    void addComando(String s, Consumer<Controller<B>> comando);
    void addComandi(HashMap hm);
    boolean isOn();
    void shutdown();
    B getStato();
    HashMap<String, Consumer<Controller<B>>> getComandi();
    GestoreMovimenti<B> getGestoreMovimenti();
    void setGestoreMovimenti(GestoreMovimenti<B> gestoreMovimenti);
}
