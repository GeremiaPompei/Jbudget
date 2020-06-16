package it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di definire una categoria di un movimento. Tale classe permette
 * di accedere al proprio id, nome, descrizione, ad una serie di movimenti etichettati con essa,
 * alla somma del saldo di questi e di aggiungere o rimuovere un movimento.
 */
public class TagBase implements Tag {

    /**
     * Variabile utile alla gestione del log dell'TagBase.
     */
    private static final Logger logger =Logger.getGlobal();

    /**
     * Nome del TagBase.
     */
    private final String name;

    /**
     * Descrizione del TagBase.
     */
    private String description;

    /**
     * ID del TagBase.
     */
    private final int ID;

    /**
     * Movimenti del TagBase.
     */
    private final Set<Movement> movements;

    /**
     * Costruttore del TagBase.
     * @param name Nome del TagBase.
     * @param description Descrizione del TagBase.
     * @param ID ID del TagBase.
     */
    public TagBase(String name, String description, int ID) {
        if(name.equalsIgnoreCase(""))
            throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.ID = ID;
        movements = new TreeSet<>();
        this.logger.finest("TagBase created.");
    }

    /**
     * Metodo responsabile di restituire il nome del Tag.
     * @return Nome del tag.
     */
    @Override
    public String getName() {
        this.logger.finest("Name getter.");
        return this.name;
    }

    /**
     * Metodo responsabile di restituire la descrizione del Tag.
     * @return Descrizione del tag.
     */
    @Override
    public String getDescription() {
        this.logger.finest("Description getter.");
        return this.description;
    }

    /**
     * Metodo che ha la responsabilità di modificare la descrizione del Tag.
     * @param description Descrizione con cui sostituire quella gia presente.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
        this.logger.finest("Description modified with: ["+description+"]");
    }

    /**
     * Metodo responsabile di restituire l'ID del Tag.
     * @return ID del tag.
     */
    @Override
    public int getID() {
        this.logger.finest("ID getter.");
        return this.ID;
    }

    /**
     * Metodo responsabile di restituire i movimenti che usano il Tag.
     * @return Movimenti del Tag.
     */
    @Override
    public Set<Movement> getMovements() {
        this.logger.finest("Movements getter.");
        return this.movements;
    }

    /**
     * Metodo responsabile di restituire il saldo totale del Tag dato dalla somma dei saldi dei suoi movimenti.
     * @return Saldo totale del tag.
     */
    @Override
    public double totalAmount(){
        AtomicReference<Double> amount = new AtomicReference<>();
        amount.set(0.0);
        this.movements.parallelStream()
                .filter(m->m.getDate().compareTo(LocalDateTime.now())<=0)
                .forEach(m->amount.set(m.getAmount()+amount.get()));
        this.logger.finest("TotalAmount getter.");
        return amount.get();
    }

    /**
     * Metodo responsabile di aggiungere un movimento al Tag.
     * @param movement Movimento da aggiungere.
     */
    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
        this.logger.finest("Addition of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un movimento dal Tag.
     * @param movement Movimento da rimuovere.
     */
    @Override
    public void removeMovement(Movement movement) {
        this.movements.remove(movement);
        this.logger.finest("Removal of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di comparare due Tag in base a Nome e ID.
     * @param o Tag da comparare.
     * @return 0 se i due Tag sono uguali, un numero negativo se questo Tag è minore di o
     * , un numero positivo altrimenti.
     */
    @Override
    public int compareTo(Tag o) {
        int comparator = this.name.compareTo(o.getName());
        if(comparator == 0)
            comparator = this.ID-o.getID();
        this.logger.finest("Tag compared with: ["+o.toString()+"]");
        return comparator;
    }

    /**
     * Metodo responsabile di dare una rappresentazione a stringa dell'TagBase.
     * @return Stringa formata da ID e nome di un Tag.
     */
    @Override
    public String toString(){
        this.logger.finest("Tag string.");
        return this.ID+": "+this.name;
    }
}
