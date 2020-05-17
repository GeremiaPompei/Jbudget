package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class TagBaseScanner implements Scanner<TagBase>{
    @Override
    public TagBase scanOf(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string,",");
        TagBase tag = new TagBase(
                stringTokenizer.nextToken().trim()
                ,stringTokenizer.nextToken().trim());
        return tag;
    }
}
