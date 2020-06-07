package it.unicam.cs.pa.jbudget105333.Model.Transaction.TransactionBase;

import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su un TransactionBase.
 */
public abstract class TransactionBase implements Transaction {

    /**
     * Variabile utile alla gestione del log della TransactionBase.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

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
     * Costruttore di TransactionBase con un solo parametro ID.
     * @param ID ID della Transazione.
     */
    public TransactionBase(int ID) {
        this.ID = ID;
        this.date = LocalDateTime.now();
        this.movements = new TreeSet<>();
        this.logger.finest("Transaction created");
    }

    /**
     * Costruttore di TransactionBase con due parametro data e ID.
     * @param date Data della Transazione.
     * @param ID ID della Transazione.
     */
    public TransactionBase(LocalDateTime date, int ID) {
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
     * Metodo responsabile di aggiungere una serie di movimenti alla Transazione.
     * @param movements Serie di movimenti aggiunti alla Transazione.
     */
    @Override
    public void addMovements(Set<Movement> movements){
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
