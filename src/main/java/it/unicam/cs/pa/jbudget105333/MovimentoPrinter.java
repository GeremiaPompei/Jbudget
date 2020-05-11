package it.unicam.cs.pa.jbudget105333;

public class MovimentoPrinter implements Printer<Movimento<? extends Tag>> {

    public String stringOf(Movimento<? extends Tag> m) {
        TagPrinter tp = new TagPrinter();
        String s = "[ " + m.getLocalDateTime().toLocalDate().toString() + " ] : [ " + m.getValue() + " ] : [ ";
        for(Tag t : m.getTags())
            s += " (" + tp.stringOf(t) + ") ,";
        s += " ] : [ " + m.getCount() + " ]";
        return s;
    }
}
