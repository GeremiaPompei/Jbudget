package it.unicam.cs.pa.jbudget105333;

import java.util.ArrayList;

public enum TagIn implements Tag {

    STIPENDIO,
    REGALO;

    private String descrizione = null;

    @Override
    public String getDescrizione(){
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
}
