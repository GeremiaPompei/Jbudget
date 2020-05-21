package it.unicam.cs.pa.jbudget105333;

import java.util.StringTokenizer;

public class InstantTransactionScanner implements Scanner<InstantTransaction>{

    private final Ledger ledger;

    public InstantTransactionScanner(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public InstantTransaction scanOf(String string) {
        StringTokenizer st = new StringTokenizer(string,";");
        InstantTransaction it = new InstantTransaction(Integer.parseInt(st.nextToken().trim()));
        Scanner<MovementBase> movementS = new MovementBaseScanner(it,this.ledger);
        try {
            while (st.hasMoreTokens())
                it.addMovement(movementS.scanOf(st.nextToken()));
        }catch (Exception e){
            it = null;
        }
        if(!it.getMovements().isEmpty())
            this.ledger.addTransaction(it);
        return it;
    }

    @Override
    public InstantTransaction scanOf(String string,IDGenerator idGenerator) {
        StringTokenizer st = new StringTokenizer(string,";");
        InstantTransaction it = new InstantTransaction(idGenerator);
        Scanner<MovementBase> movementS = new MovementBaseScanner(it,this.ledger);
        try {
            while (st.hasMoreTokens())
                it.addMovement(movementS.scanOf(st.nextToken(),idGenerator));
        }catch (Exception e){
            it = null;
        }
        if(!it.getMovements().isEmpty())
            this.ledger.addTransaction(it);
        return it;
    }
}
