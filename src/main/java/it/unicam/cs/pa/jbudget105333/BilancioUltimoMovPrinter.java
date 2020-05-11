package it.unicam.cs.pa.jbudget105333;

public class BilancioUltimoMovPrinter implements BilancioPrinter<BilancioUltimoMov> {


    @Override
    public String stringOf(BilancioUltimoMov bilancioUltimoMov) {
        return "Bilancio: " + bilancioUltimoMov.getValue() + " , UltimaModifica: " + bilancioUltimoMov.getModifica();
    }
}
