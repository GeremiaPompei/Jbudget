package it.unicam.cs.pa.jbudget105333.Model;

/**
 * Interfaccia che ha la responsabilità di mettere a disposizione alle classi che la implementano
 * metodi di utilità per accedere e modificare alcuni campi.
 */
public interface Utility {

    /**
     * Metodo che ha la responsabilità di ritornare l'ID di un oggetto che implementa tale classe.
     * @return ID dell'oggetto.
     */
    int getID();

    /**
     * Metodo che ha la responsabilità di ritornare la descrizione di un oggetto che implementa tale classe.
     * @return Descrizione dell'oggetto.
     */
    String getDescription();

    /**
     * Metodo che ha la responsabilità di modificare la descrizione di un oggetto che implementa tale classe.
     * @param description Descrizione con cui sostituire quella gia presente.
     */
    void setDescription(String description);
}
