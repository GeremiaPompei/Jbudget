package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringTokenizer;

public class ProgramTransactionScanner implements Scanner<ProgramTransaction>{

    private final Ledger ledger;

    public ProgramTransactionScanner(Ledger ledger) {
        this.ledger = ledger;
    }

    @Override
    public ProgramTransaction scanOf(String string) {
        StringTokenizer st1 = new StringTokenizer(string,";");
        StringTokenizer st2 = new StringTokenizer(st1.nextToken(),",");
        ProgramTransaction pt = null;
        try {
            pt = new ProgramTransaction
                    (LocalDateTime.of(LocalDate.parse(st2.nextToken().trim()), LocalTime.MIN)
                            ,Integer.parseInt(st2.nextToken().trim()));
            Scanner<MovementBase> movementS = new MovementBaseScanner(pt,this.ledger);
            while (st1.hasMoreTokens())
                pt.addMovement(movementS.scanOf(st1.nextToken()));
        }catch (Exception e){
            pt = null;
        }
        if(!pt.getMovements().isEmpty())
            this.ledger.addTransaction(pt);
        return pt;
    }

    @Override
    public ProgramTransaction scanOf(String string, IDGenerator idGenerator) {
        StringTokenizer st = new StringTokenizer(string,";");
        ProgramTransaction pt = null;
        try {
            pt = new ProgramTransaction
                (LocalDateTime.of(LocalDate.parse(st.nextToken().trim()), LocalTime.MIN),idGenerator);
            Scanner<MovementBase> movementS = new MovementBaseScanner(pt,this.ledger);
            while (st.hasMoreTokens())
                pt.addMovement(movementS.scanOf(st.nextToken(),idGenerator));
        }catch (Exception e){
            pt = null;
        }
        if(!pt.getMovements().isEmpty())
            this.ledger.addTransaction(pt);
        return pt;
    }
}
