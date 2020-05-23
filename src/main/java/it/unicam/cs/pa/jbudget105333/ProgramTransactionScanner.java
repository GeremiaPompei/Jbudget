package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProgramTransactionScanner implements Scanner<ProgramTransaction>{

    @Override
    public ProgramTransaction scanOf(String string, IDGenerator idGenerator) {
        ProgramTransaction pt = null;
        try {
            pt = new ProgramTransaction(LocalDateTime.of(
                    LocalDate.parse(string.trim()),LocalTime.MIN),idGenerator);
        }catch (Exception e){
            pt = null;
        }
        return pt;
    }
}
