package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;

public interface Bilancio extends Serializable {
    void setValue(double value);
    void increment(double value);
    void decrement(double value);
    double getValue();
    boolean isOn();
    void shutdown();
    void resetValue();
}
