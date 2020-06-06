package it.unicam.cs.pa.jbudget105333.ModelTest.IDGeneratorTest;

import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IDGeneratorBaseTest {

    private IDGenerator idGenerator;

    @BeforeEach
    void createIDGenerator(){
        this.idGenerator = new IDGeneratorBase();
    }

    @Test
    void generate() {
        assertNotEquals(this.idGenerator.generate(),this.idGenerator.generate());
    }

    @Test
    void store() {
        assertTrue(this.idGenerator.getMap().isEmpty());
        Object o = new Object();
        this.idGenerator.generate();
        this.idGenerator.store(o);
        assertEquals(this.idGenerator.IDSearch(1),o);
        assertFalse(this.idGenerator.getMap().isEmpty());
    }

    @Test
    void getMap() {
        assertTrue(this.idGenerator.getMap() instanceof Map);
        assertFalse(this.idGenerator.getMap() instanceof IDGenerator);
    }

    @Test
    void IDSearch() {
        Object o1 = new Object();
        this.idGenerator.generate();
        this.idGenerator.store(o1);
        assertEquals(this.idGenerator.IDSearch(1),o1);
        Object o2 = new Object();
        this.idGenerator.generate();
        this.idGenerator.store(o2);
        assertEquals(this.idGenerator.IDSearch(2),o2);
    }
}