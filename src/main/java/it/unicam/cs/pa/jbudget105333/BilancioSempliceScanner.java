package it.unicam.cs.pa.jbudget105333;

public class BilancioSempliceScanner implements BilancioScanner<BilancioSemplice> {
    @Override
    public BilancioSemplice scanOf(String stringa) {
        BilancioSemplice bs = new BilancioSemplice();
        bs.setValue(Double.parseDouble(stringa.substring(stringa.indexOf(':'),stringa.indexOf('\n')).trim()));
        return bs;
    }
}
