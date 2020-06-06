package it.unicam.cs.pa.jbudget105333.TagTest;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagBaseTest {

    private Tag sport;
    private Tag macchina;

    @BeforeEach
    void createTagBase(){
        IDGenerator idGenerator = new IDGeneratorBase();
        this.sport = new TagBase("Sport","Tennis",idGenerator);
        this.macchina = new TagBase("Macchina","Benzina",idGenerator);
    }

    @Test
    void getName() {
        assertTrue(this.sport.getName() instanceof String);
        assertEquals(this.sport.getName(),"Sport");
        assertEquals(this.macchina.getName(),"Macchina");
    }

    @Test
    void getDescription() {
        assertTrue(this.sport.getDescription() instanceof String);
        assertEquals(this.sport.getDescription(),"Tennis");
        assertEquals(this.macchina.getDescription(),"Benzina");
    }

    @Test
    void getID() {
        assertEquals(this.sport.getID(),1);
        assertNotEquals(this.sport.getID(),this.macchina.getID());
    }

    @Test
    void compareTo() {
        assertTrue(this.sport.compareTo(this.macchina)>0);
        assertTrue(this.sport.compareTo(this.sport)==0);
    }
}