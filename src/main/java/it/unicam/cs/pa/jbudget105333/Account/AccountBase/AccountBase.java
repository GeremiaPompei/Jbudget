package it.unicam.cs.pa.jbudget105333.Account.AccountBase;

import it.unicam.cs.pa.jbudget105333.Account.Account;
import it.unicam.cs.pa.jbudget105333.Account.AccountType;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Movement.Movement;
import it.unicam.cs.pa.jbudget105333.Movement.MovementType;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountBase implements Account {

    private final int ID;
    private final String name;
    private final String description;
    private final double openingBalance;
    private final AccountType accountType;
    private final SortedSet<Movement> movements;
    private double balance = 0.0;
    private LocalDateTime lastUpdate = null;

    /*Il costruttore di AccountBase assegna ad opening balance il valore amount in positivo o negativo
    in base al tipo di account e assegna al bilancio il bilancio iniziale. Oltre a venire assegnati nome,
    descrizione e tipo viene inizializzato il set dei movimenti, generato l'id (e memorizzato tale account
    nella tabella degli id) e inizializzata la variabile dell'ultimo aggiornamento al minimo
     */
    public AccountBase(String name, String description, double openingBalance, AccountType accountType
            , IDGenerator idGenerator) {
        this.name = name;
        this.description = description;
        this.accountType = accountType;
        this.lastUpdate = LocalDateTime.MIN;
        if(accountType.equals(AccountType.ASSETS))
            this.openingBalance = openingBalance;
        else
            this.openingBalance = -openingBalance;
        this.balance = this.openingBalance;
        this.movements = new TreeSet<>();
        this.ID = idGenerator.generate();
        idGenerator.store(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getOpeningBalance() {
        return this.openingBalance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    //Aggiunta di un movimento all'account
    @Override
    public void addMovement(Movement movement) {
        for(Movement m : this.movements)
            if(m.getDate().compareTo(LocalDateTime.now())<=0
                    && m.getDate().compareTo(this.lastUpdate)>=0)
                if(m.getType().equals(MovementType.CREDITS)
                        && m.getAccount().getAccountType().equals(AccountType.ASSETS)
                        ||m.getType().equals(MovementType.DEBIT)
                        && m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                    this.balance+=m.getAmount();
                else
                    this.balance-=m.getAmount();
        this.movements.add(movement);
    }

    /*Metodo che permette di aggiornare il bilancio incrementandolo (se il tipo di account è un asset e
    il tipo di movimento un credito o se il tipo dell'account è un liability e il tipo di movimento un
    debito) o decrementandolo se presente tra la data dell'ultimo aggiornamento e ora, dopodichè aggiorna
    la data dell'ultimo aggiornamento ad ora
     */
    @Override
    public void update(){
        for(Movement m : this.movements)
            if(m.getDate().compareTo(LocalDateTime.now())<=0
                    && m.getDate().compareTo(this.lastUpdate)>=0)
                if(m.getType().equals(MovementType.CREDITS)
                        && m.getAccount().getAccountType().equals(AccountType.ASSETS)
                        ||m.getType().equals(MovementType.DEBIT)
                        && m.getAccount().getAccountType().equals(AccountType.LIABILITIES))
                    this.balance+=m.getAmount();
                else
                    this.balance-=m.getAmount();
        this.lastUpdate = LocalDateTime.now();
    }

    @Override
    public SortedSet<Movement> getMovements() {
        return this.movements;
    }

    @Override
    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    //Permette di comparare due account per nome, dopodichè per ID
    @Override
    public int compareTo(Account o) {
        int comparator = this.name.compareTo(o.getName());
        if (comparator == 0)
            comparator = this.ID-o.getID();
        return comparator;
    }

    @Override
    public String toString(){
        return this.ID+": "+this.name;
    }
}
