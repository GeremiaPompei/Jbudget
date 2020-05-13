package it.unicam.cs.pa.jbudget105333;

import java.io.Serializable;

public class BilancioUltimoMov implements Bilancio {

    private double value = 0.0;
    private double modifica = 0.0;
    private boolean state = false;

    public BilancioUltimoMov() {
        this.value = 0.0;
        this.modifica = 0.0;
        this.state = true;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
        this.modifica = 0.0;
    }

    @Override
    public void increment(double value) {
        this.value += value;
        this.modifica = value;
    }

    @Override
    public void decrement(double value) {
        this.value -= value;
        this.modifica = -value;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public double getModifica() {
        return this.modifica;
    }

    public void setModifica(double modifica) {
        this.modifica = modifica;
    }

    @Override
    public boolean isOn() {
        return this.state;
    }

    @Override
    public void shutdown() {
        this.state = false;
    }

    @Override
    public void resetValue() {
        this.value = 0.0;
        this.modifica = 0.0;
    }
}
