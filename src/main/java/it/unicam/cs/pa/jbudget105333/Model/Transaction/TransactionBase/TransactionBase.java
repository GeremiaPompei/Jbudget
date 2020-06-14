package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * Questa classe astratta sarà estesa dalle classi che hanno la responsabilità di gestire una singola
 * transazione. Essa non è altro che un gruppo di movimenti eseguiti in una certa data. Tale classe astratta
 * permette alle classi che la implementano di accedere al proprio id, data, saldo totale (dato dalla
 * somma dei saldi dei movimenti che la compongono), la serie di movimenti che la popolano, di aggiungere
 * o rimuovere un movimento o aggiungere una serie di movimenti. Essa da l'implementazione di tutti i metodi
 * dell'interfaccia che implementa e mette a disposizione due costruttori.
 */
public abstract class TransactionBase implements Transaction {

    /**
     * Variabile utile alla gestione del log della TransactionBase.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Movimenti della transazione.
     */
    private final Set<Movement> movements;

    /**
     * Data della transazione.
     */
    private final LocalDateTime date;

    /**
     * ID della transazione.
     */
    private final int ID;

    /**
     * Descrizione della transazione.
     */
    private String description;

    /**
     * Costruttore di TransactionBase con un solo parametro ID.
     * @param ID ID della Transazione.
     */
    public TransactionBase(String description, int ID) {
        this(LocalDateTime.now(),description,ID);
        this.logger.finest("Transaction created");
    }

    /**
     * Costruttore di TransactionBase con due parametro data e ID.
     * @param date Data della Transazione.
     * @param ID ID della Transazione.
     */
    public TransactionBase(LocalDateTime date, String description, int ID) {
        if(date == null)
            throw new NullPointerException();
        this.description = description;
        this.ID = ID;
        this.date = date;
        this.movements = new TreeSet<>();
        this.logger.finest("Transaction created");
    }

    /**
     * Metodo responsabile di aggiungere il movimento alla Transazione.
     * @param movement Movimento aggiunto alla Transazione.
     */
    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
        this.logger.finest("Addition of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere il movimento alla Transazione.
     * @param movement Movimento rimosso alla Transazione.
     */
    @Override
    public void removeMovement(Movement movement){
        this.movements.remove(movement);
        this.logger.finest("Removal of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di aggiungere una serie di movimenti alla Transazione.
     * @param movements Serie di movimenti aggiunti alla Transazione.
     */
    @Override
    public void addMovements(Collection<Movement> movements){
        this.movements.addAll(movements);
        this.logger.finest("Addition of Movements: ["+movements.toString()+"]");
    }

    /**
     * Metodo che ha la responsabilità di ritornare i movimenti della Transazione.
     * @return Movimenti dela transazione.
     */
    @Override
    public Set<Movement> getMovements() {
        this.logger.finest("Movements getter");
        return this.movements;
    }

    /**
     * Metodo che ha la responsabilità di ritornare i tag della Transazione.
     * @return Tag della transazione.
     */
    @Override
    public Set<Tag> getTags() {
        Set<Tag> tags = new TreeSet<>();
        this.movements.parallelStream().forEach(m -> tags.add(m.getTag()));
        this.logger.finest("Tags getter");
        return tags;
    }

    /**
     * Metodo che ha la responsabilità di ritornare la data della Transazione.
     * @return Data della Transazione.
     */
    @Override
    public LocalDateTime getDate() {
        this.logger.finest("Data getter");
        return this.date;
    }

    /**
     * Metodo che ha la responsabilità di ritornare il saldo totale della Transazione.
     * @return Saldo totale della transazione.
     */
    @Override
    public double getTotalAmount() {
        AtomicReference<Double> totalAmount = new AtomicReference<>();
        totalAmount.set(0.0);
        this.movements.parallelStream().forEach(m->totalAmount.set(m.getAmount()+totalAmount.get()));
        this.logger.finest("TotalAmount getter");
        return totalAmount.get();
    }

    /**
     * Metodo che ha la responsabilità di ritornare l'ID della Transazione.
     * @return ID della transazione.
     */
    @Override
    public int getID(){
        this.logger.finest("ID getter");
        return this.ID;
    }

    /**
     * Metodo che ha la responsabilità di ritornare la descrizione della Transazione.
     * @return Descrizione della Transazione.
     */
    @Override
    public String getDescription(){
        return this.description;
    }

    /**
     * Metodo che ha la responsabilità di modificare la descrizione della Transazione.
     * @param description Descrizione con cui sostituire quella gia presente.
     */
    @Override
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Metodo responsabile di comparare due Transazioni in base a data e ID.
     * @param o Transazioni da comparare.
     * @return 0 se le due Transazioni sono uguali, un numero negativo se questa Transazione è minore di o
     * , un numero positivo altrimenti.
     */
    @Override
    public int compareTo(Transaction o) {
        int comparator = this.date.compareTo(o.getDate());
        if(comparator == 0)
            comparator = this.ID-o.getID();
        this.logger.finest("Transaction compared with: ["+o.toString()+"]");
        return comparator;
    }

    /**
     * Metodo responsabile di dare una rappresentazione a stringa della TransactionBase.
     * @return Stringa formata da ID,data, saldo totale e lista dei movimenti dell'Account.
     */
    @Override
    public String toString(){
        AtomicReference<String> t = new AtomicReference<>();
        t.set(this.ID+": "+this.date+" ["+this.getTotalAmount()+"]\n");
        this.movements.stream().forEach(m->t.set(t.get()+"    "+m.toString()+"\n"));
        this.logger.finest("Transaction string.");
        return t.get();
    }
}
