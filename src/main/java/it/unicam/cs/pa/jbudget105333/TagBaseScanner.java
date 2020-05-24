package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class TagBaseScanner implements Scanner<TagBase>{

    //Tale metodo converte una stringa e un idgenerator in tagBase
    @Override
    public TagBase scanOf(String string, IDGenerator idGenerator) {
        StringTokenizer st = new StringTokenizer(string,",");
        TagBase tag = null;
        try{
            tag = new TagBase(st.nextToken().trim(),st.nextToken().trim(),idGenerator);
        }catch (Exception e){
            tag = null;
        }
        return tag;
    }
}
