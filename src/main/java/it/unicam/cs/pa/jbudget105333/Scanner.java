package it.unicam.cs.pa.jbudget105333;

public interface Scanner <O extends Object>{
    O scanOf(String string);
    O scanOf(String string,IDGenerator idGenerator);
}
