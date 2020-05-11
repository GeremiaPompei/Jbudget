package it.unicam.cs.pa.jbudget105333;

public class GestoreMovimentiPrinter<B extends Bilancio,T extends Tag> implements Printer<GestoreMovimenti<B>> {

    public String stringOf(GestoreMovimenti<B> gestoreMovimenti) {
        MovimentoPrinter mp = new MovimentoPrinter();
        String s = "";
        if(gestoreMovimenti.getMovimenti().isEmpty())
            s = "Empty";
        else
            for(Movimento m : gestoreMovimenti.getMovimenti())
                s +=  mp.stringOf(m) + "\n";
        return s;
    }
}
