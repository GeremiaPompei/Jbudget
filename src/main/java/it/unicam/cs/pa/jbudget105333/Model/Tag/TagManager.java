package it.unicam.cs.pa.jbudget105333.Model.Tag;

import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;

public interface TagManager {
    static Tag generateTag(String name,String description,int ID){
        return new TagBase(name,description,ID);
    }
}
