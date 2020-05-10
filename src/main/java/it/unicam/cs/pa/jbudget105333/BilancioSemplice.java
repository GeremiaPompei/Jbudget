package it.unicam.cs.pa.jbudget105333;

public class BilancioSemplice implements Bilancio{

    private double value = 0.0;
    private boolean state = false;

    public BilancioSemplice() {
        this.value = 0.0;
        this.state = true;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    public void increment(double value){
        this.value += value;
    }

    public void decrement(double value){
        this.value -= value;
    }

    public double getValue() {
        return value;
    }

    public boolean isOn() {
        return state;
    }

    public void shutdown() {
        this.state = false;
    }

    @Override
    public void resetValue() {
        this.value = 0;
    }
}
