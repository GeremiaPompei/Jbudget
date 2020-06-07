package it.unicam.cs.pa.jbudget105333.Model.Account.AccountBase;

import it.unicam.cs.pa.jbudget105333.JBLogger;
import it.unicam.cs.pa.jbudget105333.Model.Account.Account;
import it.unicam.cs.pa.jbudget105333.Model.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.Model.Movement.Movement;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Classe che ha la responsabilità di gestire e dare informazioni su un Account.
 */
public class AccountBase implements Account {

    /**
     * Variabile utile alla gestione del log dell'Account.
     */
    private Logger logger = new JBLogger(this.getClass()).getLogger();

    /**
     * ID dell'Account.
     */
    private final int ID;

    /**
     * Nome dell'Account.
     */
    private final String name;

    /**
     * Tipo dell'Account.
     */
    private final AccountType accountType;

    /**
     * Bilancio iniziale dell'Account.
     */
    private final double openingBalance;

    /**
     * Descrizione dell'Account.
     */
    private final String description;

    /**
     * Movimenti dell'Account.
     */
    private final Set<Movement> movements;

    /**
     * Bilancio dell'Account.
     */
    private double balance;

    /**
     * Ultimo aggiornamento dell'Account.
     */
    private LocalDateTime lastUpdate;

    /**
     * Costruttore dell'Account.
     * @param name Nome dell'Account.
     * @param description Descrizione dell'Account.
     * @param openingBalance Bilancio iniziale dell'Account, se l'Account ha tipo LIABILITIES
     *                       il Bilancio iniziale sarà impostato in negativo.
     * @param accountType Tipo dell'Account.
     * @param ID ID dell'Account.
     * @throws IllegalAccessException Eccezione dovuta all'aggiunta di una stringa vuota al parametro Nome.
     */
    public AccountBase(String name, String description, double openingBalance, AccountType accountType
            , int ID) throws IllegalAccessException {
        if(name==null || description==null || accountType==null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalAccessException();
        this.name = name;
        this.description = description;
        this.accountType = accountType;
        this.lastUpdate = LocalDateTime.MIN;
        this.movements = new TreeSet<>();
        this.ID = ID;
        if(accountType.equals(AccountType.LIABILITIES))
            this.openingBalance = openingBalance;
        else
            this.openingBalance = -openingBalance;
        this.balance = this.openingBalance;
        this.logger.fine("Account created.");
    }

    /**
     * Metodo responsabile di restituire il nome dell'Account.
     * @return nome dell'Account.
     */
    @Override
    public String getName() {
        this.logger.finest("Name getter.");
        return this.name;
    }

    /**
     * Metodo responsabile di restituire la descrizione dell'Account.
     * @return descrizione dell'Account.
     */
    @Override
    public String getDescription() {
        this.logger.finest("Description getter.");
        return this.description;
    }

    /**
     * Metodo responsabile di restituire il bilancio iniziale dell'Account.
     * @return bilancio iniziale dell'Account.
     */
    @Override
    public double getOpeningBalance() {
        this.logger.finest("Opening Balance getter.");
        return this.openingBalance;
    }

    /**
     * Metodo responsabile di restituire il bilancio dell'Account.
     * @return bilancio dell'Account.
     */
    @Override
    public double getBalance() {
        this.logger.finest("Balance getter.");
        return this.balance;
    }

    /**
     * Metodo responsabile di aggiungere un movimento all'Account.
     * @param movement Movimento da aggiungere all'Account.
     */
    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
        this.logger.info("Addition of Movement: ["+movement.toString()+"]");
        update();
    }

    /**
     * Metodo responsabile di restituire i movimenti dell'Account.
     * @return Movimenti dell'Account.
     */
    @Override
    public Set<Movement> getMovements() {
        this.logger.finest("Movements getter.");
        return this.movements;
    }

    /**
     * Metodo responsabile di restituire il tipo dell'Account.
     * @return Tipo dell'Account.
     */
    @Override
    public AccountType getAccountType() {
        this.logger.finest("Account Type getter.");
        return this.accountType;
    }

    /**
     * Metodo responsabile di restituire l'ID dell'Account.
     * @return ID dell'Account.
     */
    @Override
    public int getID() {
        this.logger.finest("ID getter.");
        return this.ID;
    }

    /**
     * Metodo responsabile di aggiornare il bilancio dell'Account in base al saldo dei Movimenti.
     */
    @Override
    public void update(){
        for(Movement m : this.movements)
            if(m.getDate().compareTo(LocalDateTime.now())<=0
                    && m.getDate().compareTo(this.lastUpdate)>=0)
                this.balance+=m.getAmount();
        this.lastUpdate = LocalDateTime.now();
        this.logger.info("Account updated.");
    }

    /**
     * Metodo responsabile di comparare due Account in base a Nome e ID.
     * @param o Account da comparare.
     * @return 0 se i due Account sono uguali, un numero negativo se questo Account è minore di o
     * , un numero positivo altrimenti.
     */
    @Override
    public int compareTo(Account o) {
        this.logger.finer("Account comparation with: ["+o.toString()+"]");
        int comparator = this.name.compareTo(o.getName());
        if (comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }

    /**
     * Metodo responsabile di dare una rappresentazione a stringa dell'Account.
     * @return Stringa formata da ID e nome dell'Account.
     */
    @Override
    public String toString(){
        this.logger.finest("Account string.");
        return this.ID+": "+this.name;
    }
}
