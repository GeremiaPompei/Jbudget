package it.unicam.cs.pa.jbudget105333;

public interface Printer<O extends Object>{
    String stringOf(O o);
}
