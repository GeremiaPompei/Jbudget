package it.unicam.cs.pa.jbudget105333;

public class AccountBasePrinter implements Printer<Account>{

    //Converte account in stringa
    @Override
    public String stringOf(Account account){
        return account.getName()+"("+account.getAccountType()+")[ID:"+account.getID()+"]: "+account.getBalance();
    }
}
