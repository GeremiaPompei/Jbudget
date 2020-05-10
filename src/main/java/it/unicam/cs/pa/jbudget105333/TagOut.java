package it.unicam.cs.pa.jbudget105333;

public enum TagOut implements Tag{

    UTENZA,
    MACCHINA;

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
