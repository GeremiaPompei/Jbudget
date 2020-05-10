package it.unicam.cs.pa.jbudget105333;

public interface Bilancio {
    void setValue(double value);
    void increment(double value);
    void decrement(double value);
    double getValue();
    boolean isOn();
    void shutdown();
    void resetValue();
}
