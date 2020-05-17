package it.unicam.cs.pa.jbudget105333;

public class AccountBasePrinter<A extends Account> implements Printer<A>{

    @Override
    public String stringOf(A account){
        return account.getName()+"("+account.getAccountType()+"): "+account.getBalance();
    }
}
