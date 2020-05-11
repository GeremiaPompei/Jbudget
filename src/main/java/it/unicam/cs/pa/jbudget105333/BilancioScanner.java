package it.unicam.cs.pa.jbudget105333;

public interface BilancioScanner<B extends Bilancio> extends Scanner<B> {
    @Override
    B scanOf(String stringa);
}
