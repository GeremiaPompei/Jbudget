package it.unicam.cs.pa.jbudget105333;

public class TagPrinter implements Printer<Tag> {

    @Override
    public String stringOf(Tag tag) {
        return tag.toString();
    }

    public static String stringTagIn(){
        String s = "TagIn: ";
        for(Tag t : TagIn.values()){
            s += t.toString() + ", ";
        }
        return s;
    }

    public static String stringTagOut(){
        String s = "TagOut: ";
        for(Tag t : TagOut.values()){
            s += t.toString() + ", ";
        }
        return s;
    }
}
