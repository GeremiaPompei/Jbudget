package it.unicam.cs.pa.jbudget105333.Tag;

public interface TagManager {
    static Tag generateTag(String name,String description,int ID){
        return new TagBase(name,description,ID);
    }
}
