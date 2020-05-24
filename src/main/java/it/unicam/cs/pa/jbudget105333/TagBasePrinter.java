package it.unicam.cs.pa.jbudget105333;

public class TagBasePrinter implements Printer<Tag>{

    //Metodo che converte un tag in stringa
    @Override
    public String stringOf(Tag tag) {
        return tag.getName()+"("+tag.getDescription()+")[ID:"+tag.getID()+"]";
    }
}
