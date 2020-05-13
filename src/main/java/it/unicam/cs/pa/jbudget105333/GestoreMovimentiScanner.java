package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class GestoreMovimentiScanner<B extends Bilancio> implements Scanner {

    private B bilancio;

    public GestoreMovimentiScanner(B bilancio) {
        this.bilancio = bilancio;
    }

    @Override
    public GestoreMovimenti scanOf(String stringa) {
        StringTokenizer st = new StringTokenizer(stringa,"\n");
        String tmp = st.nextToken();
        GestoreMovimenti gestoreMovimenti = new GestoreMovimenti(this.bilancio);
        while (st.hasMoreTokens()){
            tmp = st.nextToken();
            gestoreMovimenti.addMovimento(new MovimentoScanner().scanOf(tmp));
        }
        return gestoreMovimenti;
    }
}
