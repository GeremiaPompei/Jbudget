package it.unicam.cs.pa.jbudget105333;

public interface BilancioPrinter<B extends Bilancio> extends Printer<B> {
    String stringOf(B bilancio);
}
