package it.unicam.cs.pa.jbudget105333;

public class BilancioUltimoMovScanner implements BilancioScanner<BilancioUltimoMov> {
    @Override
    public BilancioUltimoMov scanOf(String stringa) {
        BilancioUltimoMov bum = new BilancioUltimoMov();
        bum.setValue(Double.parseDouble(stringa.substring(stringa.indexOf(':'),stringa.indexOf(',')).trim()));
        bum.setModifica(Double.parseDouble(stringa.substring(stringa.indexOf(','),stringa.indexOf('\n')).trim()));
        return bum;
    }
}
