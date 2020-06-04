package it.unicam.cs.pa.jbudget105333;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;

public interface Scanner <O>{
    O scanOf(String string, IDGenerator idGenerator);
}
