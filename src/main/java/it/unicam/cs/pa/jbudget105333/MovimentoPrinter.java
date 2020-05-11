package it.unicam.cs.pa.jbudget105333;

public class MovimentoPrinter<T extends Tag> implements Printer<Movimento<T>> {

    public String stringOf(Movimento<T> m) {
        TagPrinter tp = new TagPrinter();
        String s = "[ " + m.getLocalDateTime().toLocalDate().toString() + " ] : [ " + m.getValue() + " ] : [ ";
        for(Tag t : m.getTags())
            s += " (" + tp.stringOf(t) + ") ,";
        s += " ] : [ " + m.getCount() + " ]";
        return s;
    }
}
