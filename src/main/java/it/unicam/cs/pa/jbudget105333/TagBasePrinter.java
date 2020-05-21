package it.unicam.cs.pa.jbudget105333;

public class TagBasePrinter implements Printer<Tag>{

    @Override
    public String stringOf(Tag tag) {
        return tag.getName()+"("+tag.getDescription()+")[ID:"+tag.getID()+"]";
    }
}
