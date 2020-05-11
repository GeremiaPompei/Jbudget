package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class GestoreMovimentiScanner implements Scanner {

    @Override
    public GestoreMovimenti scanOf(String stringa) {
        Bilancio b = null;
        StringTokenizer st = new StringTokenizer(stringa,"\n");
        String tmp = stringa.substring(0,stringa.indexOf('\n'));
        if(tmp.contains("Bilancio")&&tmp.contains(",")){
            b = new BilancioUltimoMovScanner().scanOf(tmp);
        }else{
            if(tmp.contains("Bilancio")){
                b = new BilancioSempliceScanner().scanOf(tmp);
            }
        }
        GestoreMovimenti gestoreMovimenti = new GestoreMovimenti(b);
        while (st.hasMoreTokens()){
            tmp = st.nextToken();
            gestoreMovimenti.addMovimento(new MovimentoScanner().scanOf(tmp));
        }
        return gestoreMovimenti;
    }
}
