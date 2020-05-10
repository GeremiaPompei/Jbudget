package it.unicam.cs.pa.jbudget105333;

public class BilancioSemplicePrinter implements BilancioPrinter<BilancioSemplice> {

    @Override
    public String stringOf(BilancioSemplice bilancio) {
        return "Bilancio: " + bilancio.getValue();
    }
}
