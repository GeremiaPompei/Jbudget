package it.unicam.cs.pa.jbudget105333;

public class BilancioUltimoMovScanner implements BilancioScanner<BilancioUltimoMov> {
    @Override
    public BilancioUltimoMov scanOf(String stringa) {
        BilancioUltimoMov bum = new BilancioUltimoMov();
        try{
            bum.setValue(Double.parseDouble(stringa.substring(stringa.indexOf(':')+1).trim()));
        }catch (Exception e){
            bum.setValue(Double.parseDouble(stringa.substring(
                    stringa.indexOf(':')+1,stringa.indexOf(',')).trim()));
        }
        return bum;
    }
}
