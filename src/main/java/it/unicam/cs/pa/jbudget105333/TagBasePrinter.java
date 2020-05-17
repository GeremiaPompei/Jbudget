package it.unicam.cs.pa.jbudget105333;

public class TagBasePrinter<T extends Tag> implements Printer<T>{

    @Override
    public String stringOf(T tag) {
        return tag.getName()+"("+tag.getID()+")";
    }
}
