package it.unicam.cs.pa.jbudget105333;

public interface PianoPrinter<P extends Piano> extends Printer<P> {

    String stringOf(P piano);
}
