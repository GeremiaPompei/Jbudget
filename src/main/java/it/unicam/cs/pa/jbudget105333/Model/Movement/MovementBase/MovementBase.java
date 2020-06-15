package it.unicam.cs.pa.jbudget105333.Model.Movement.MovementBase;

import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Model.Movement.MovementType;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Transaction.Transaction;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire un singolo movimento che verrà associato ad una
 * transazione da cui ne prenderà la data. Implementazione che permette di accedere al proprio id,
 * data, descrizione, tipo, saldo, transazione, account e tag.
 */
public class MovementBase implements Movement {

    /**
     * Variabile utile alla gestione del log del MovimentoBase.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Descrizione del MovimentoBase.
     */
    private String description;

    /**
     * Tipo del MovimentoBase.
     */
    private final MovementType movementType;

    /**
     * saldo del MovimentoBase.
     */
    private final double amount;

    /**
     * Transazione del MovimentoBase.
     */
    private final Transaction transaction;

    /**
     * Account del MovimentoBase.
     */
    private final Account account;

    /**
     * Data del MovimentoBase.
     */
    private final LocalDateTime date;

    /**
     * Tag del MovimentoBase.
     */
    private final Tag tag;

    /**
     * ID del MovimentoBase.
     */
    private final int ID;

    /**
     * Costruttore del MovimentoBase.
     * @param movementType Tipo del MovimentoBase.
     * @param amount Saldo del MovimentoBase.
     * @param transaction Transazione del MovimentoBase.
     * @param account Account del MovimentoBase.
     * @param tag Tag del MovimentoBase.
     * @param description Descrizione del MovimentoBase.
     * @param ID ID del MovimentoBase.
     */
    public MovementBase(MovementType movementType, double amount, Transaction transaction
            , Account account, Tag tag, String description, int ID) {
        if(movementType==null || transaction==null || account==null || tag==null || description ==null)
            throw new NullPointerException();
        this.description = description;
        this.movementType = movementType;
        if(movementType.equals(MovementType.CREDITS))
            this.amount = amount;
        else
            this.amount = -amount;
        this.transaction = transaction;
        this.account = account;
        this.date = transaction.getDate();
        this.tag = tag;
        this.ID = ID;
        transaction.addMovement(this);
        account.addMovement(this);
        tag.addMovement(this);
        this.logger.finest("MovementBase created.");
    }

    /**
     * Metodo che ha la responsabilità di ritornare la descrizione del MovimentoBase.
     * @return Descrizione del MovimentoBase.
     */
    @Override
    public String getDescription() {
        this.logger.finest("Description getter.");
        return this.description;
    }

    /**
     * Metodo che ha la responsabilità di modificare la descrizione del Movimento.
     * @param description Descrizione con cui sostituire quella gia presente.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
        this.logger.finest("Description modified with: ["+description+"]");
    }

    /**
     * Metodo che ha la responsabilità di ritornare il tipo del MovimentoBase.
     * @return Tipo del MovimentoBase.
     */
    @Override
    public MovementType getType() {
        this.logger.finest("Type getter.");
        return this.movementType;
    }

    /**
     * Metodo che ha la responsabilità di ritornare il saldo del MovimentoBase.
     * @return Saldo del MovimentoBase.
     */
    @Override
    public double getAmount() {
        this.logger.finest("Amount getter.");
        return this.amount;
    }

    /**
     * Metodo che ha la responsabilità di ritornare la transazione del MovimentoBase.
     * @return Transazione del MovimentoBase.
     */
    @Override
    public Transaction getTransaction() {
        this.logger.finest("Transaction getter.");
        return this.transaction;
    }

    /**
     * Metodo che ha la responsabilità di ritornare l'account del MovimentoBase.
     * @return Account del MovimentoBase.
     */
    @Override
    public Account getAccount() {
        this.logger.finest("Account getter.");
        return this.account;
    }

    /**
     * Metodo che ha la responsabilità di ritornare la data del MovimentoBase.
     * @return Data del MovimentoBase.
     */
    @Override
    public LocalDateTime getDate() {
        this.logger.finest("Date getter.");
        return this.date;
    }

    /**
     * Metodo che ha la responsabilità di ritornare il tag del MovimentoBase.
     * @return Tag del MovimentoBase.
     */
    @Override
    public Tag getTag() {
        this.logger.finest("Tag getter.");
        return this.tag;
    }

    /**
     * Metodo che ha la responsabilità di ritornare l'ID' del MovimentoBase.
     * @return ID del MovimentoBase.
     */
    @Override
    public int getID() {
        this.logger.finest("ID getter.");
        return this.ID;
    }

    /**
     * Metodo responsabile di comparare tale Movimento con un altro.
     * @param o Movimento che viene comparato con il medesimo.
     * @return 0 se i movimenti sono uguali, un numero negativo se tale movimento è minore di o
     * , un numero positivo altrimenti.
     */
    @Override
    public int compareTo(Movement o) {
        int comparator = this.date.compareTo(o.getDate());
        if(comparator==0)
            comparator = this.ID-o.getID();
        this.logger.finest("Movement compared with: ["+o.toString()+"]");
        return comparator;
    }

    /**
     * Metodo responsabile di ritornare una stringa corrispondente al Movimento.
     * @return Stringa composta da ID, tipo e saldo.
     */
    @Override
    public String toString(){
        this.logger.finest("Movement string.");
        return this.ID+": "+ this.movementType+" [" + this.amount+"]";
    }
}
