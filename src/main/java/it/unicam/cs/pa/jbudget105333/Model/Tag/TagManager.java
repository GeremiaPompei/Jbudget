package it.unicam.cs.pa.jbudget105333.Model.Tag;

import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;

/**
 * Interfaccia responsabile della creazione un Tag.
 */
public interface TagManager {

    /**
     * Metodo responsabile della generazione un Tag.
     * @param name Nome del Tag.
     * @param description Descrizione del Tag.
     * @param ID ID del Tag.
     * @return Tag generato.
     */
    static Tag generateTag(String name,String description,int ID) {
        return new TagBase(name,description,ID);
    }
}
