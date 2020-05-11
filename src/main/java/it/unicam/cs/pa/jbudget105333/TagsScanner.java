package it.unicam.cs.pa.jbudget105333;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TagsScanner implements Scanner<ArrayList<Tag>> {
    @Override
    public ArrayList<Tag> scanOf(String stringa) {
        ArrayList<Tag> tag = new ArrayList<>();
        StringTokenizer st2 = new StringTokenizer(stringa,",");
        for(int j = 0;j<st2.countTokens();j++){
            String tagS = "";
            tagS = st2.nextToken();
            tagS = tagS.substring(tagS.indexOf('(')+1,tagS.indexOf(')'));
            try{
                tag.add(TagIn.valueOf(tagS));
            }catch(Exception e){
                tag.add(TagOut.valueOf(tagS));
            }
        }
        return tag;
    }
}
