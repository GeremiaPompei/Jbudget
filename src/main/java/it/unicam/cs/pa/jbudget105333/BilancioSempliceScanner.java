package it.unicam.cs.pa.jbudget105333;

public class BilancioSempliceScanner implements BilancioScanner<BilancioSemplice> {
    @Override
    public BilancioSemplice scanOf(String stringa) {
        BilancioSemplice bs = new BilancioSemplice();
        try{
            bs.setValue(Double.parseDouble(stringa.substring(stringa.indexOf(':')+1).trim()));
        }catch (Exception e){
            bs.setValue(Double.parseDouble(stringa.substring(
                    stringa.indexOf(':')+1,stringa.indexOf(',')).trim()));
        }
        return bs;
    }
}
