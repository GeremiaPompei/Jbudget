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
 * Classe che ha la responsabilità di gestire e dare informazioni su un AccountBase.
 */
public class AccountBase implements Account {

    /**
     * Variabile utile alla gestione del log dell'AccountBase.
     */
    private Logger logger = JBLogger.generateLogger(this.getClass());

    /**
     * ID dell'AccountBase.
     */
    private final int ID;

    /**
     * Nome dell'AccountBase.
     */
    private final String name;

    /**
     * Tipo dell'AccountBase.
     */
    private final AccountType accountType;

    /**
     * Bilancio iniziale dell'AccountBase.
     */
    private final double openingBalance;

    /**
     * Descrizione dell'AccountBase.
     */
    private final String description;

    /**
     * Movimenti dell'AccountBase.
     */
    private final Set<Movement> movements;

    /**
     * Bilancio dell'AccountBase.
     */
    private double balance;

    /**
     * Ultimo aggiornamento dell'AccountBase.
     */
    private LocalDateTime lastUpdate;

    /**
     * Costruttore dell'AccountBase.
     * @param name Nome dell'AccountBase.
     * @param description Descrizione dell'AccountBase.
     * @param openingBalance Bilancio iniziale dell'AccountBase, se l'AccountBase ha tipo LIABILITIES
     *                       il Bilancio iniziale sarà impostato in negativo.
     * @param accountType Tipo dell'AccountBase.
     * @param ID ID dell'AccountBase.
     */
    public AccountBase(String name, String description, double openingBalance, AccountType accountType, int ID){
        if(name==null || description==null || accountType==null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.accountType = accountType;
        this.lastUpdate = LocalDateTime.MIN;
        this.movements = new TreeSet<>();
        this.ID = ID;
        if(accountType.equals(AccountType.ASSETS))
            this.openingBalance = openingBalance;
        else
            this.openingBalance = -openingBalance;
        this.balance = this.openingBalance;
        this.logger.finest("AccountBase created.");
    }

    /**
     * Metodo responsabile di restituire il nome dell'Account.
     * @return nome dell'AccountBase.
     */
    @Override
    public String getName() {
        this.logger.finest("Name getter.");
        return this.name;
    }

    /**
     * Metodo responsabile di restituire la descrizione dell'Account.
     * @return descrizione dell'AccountBase.
     */
    @Override
    public String getDescription() {
        this.logger.finest("Description getter.");
        return this.description;
    }

    /**
     * Metodo responsabile di restituire il bilancio iniziale dell'AccountBase.
     * @return bilancio iniziale dell'AccountBase.
     */
    @Override
    public double getOpeningBalance() {
        this.logger.finest("Opening Balance getter.");
        return this.openingBalance;
    }

    /**
     * Metodo responsabile di restituire il bilancio dell'Account.
     * @return bilancio dell'AccountBase.
     */
    @Override
    public double getBalance() {
        this.logger.finest("Balance getter.");
        return this.balance;
    }

    /**
     * Metodo responsabile di aggiungere un movimento all'AccountBase.
     * @param movement Movimento da aggiungere all'AccountBase.
     */
    @Override
    public void addMovement(Movement movement) {
        this.movements.add(movement);
        this.logger.finest("Addition of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di rimuovere un movimento dall'Account.
     * @param movement Movimento da rimuovere dall'Account.
     */
    @Override
    public void removeMovement(Movement movement) {
        this.movements.remove(movement);
        this.logger.finest("Removal of Movement: ["+movement.toString()+"]");
    }

    /**
     * Metodo responsabile di restituire i movimenti dell'AccountBase.
     * @return Movimenti dell'AccountBase.
     */
    @Override
    public Set<Movement> getMovements() {
        this.logger.finest("Movements getter.");
        return this.movements;
    }

    /**
     * Metodo responsabile di restituire il tipo dell'AccountBase.
     * @return Tipo dell'AccountBase.
     */
    @Override
    public AccountType getAccountType() {
        this.logger.finest("Account Type getter.");
        return this.accountType;
    }

    /**
     * Metodo responsabile di restituire l'ID dell'AccountBase.
     * @return ID dell'AccountBase.
     */
    @Override
    public int getID() {
        this.logger.finest("ID getter.");
        return this.ID;
    }

    /**
     * Metodo responsabile di aggiornare il bilancio dell'AccountBase in base al saldo dei Movimenti.
     */
    @Override
    public void update(){
        this.movements.parallelStream()
                .filter(m->m.getDate().compareTo(this.lastUpdate)>=0)
                .filter(m->m.getDate().compareTo(LocalDateTime.now())<=0)
                .forEach(m->this.balance+=m.getAmount());
        this.lastUpdate = LocalDateTime.now();
        this.logger.info("AccountBase updated.");
    }

    /**
     * Metodo responsabile di comparare due Account in base a Nome e ID.
     * @param o Account da comparare.
     * @return 0 se i due Account sono uguali, un numero negativo se questo Account è minore di o
     * , un numero positivo altrimenti.
     */
    @Override
    public int compareTo(Account o) {
        int comparator = this.name.compareTo(o.getName());
        if (comparator == 0)
            comparator = this.ID-o.getID();
        this.logger.finest("Account compared with: ["+o.toString()+"]");
        return comparator;
    }

    /**
     * Metodo responsabile di dare una rappresentazione a stringa dell'AccountBase.
     * @return Stringa formata da ID e nome dell'Account.
     */
    @Override
    public String toString(){
        this.logger.finest("Account string.");
        return this.ID+": "+this.name;
    }
}
