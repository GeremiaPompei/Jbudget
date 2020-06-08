package it.unicam.cs.pa.jbudget105333.Model.IDGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IDGeneratorBaseTest {

    private IDGenerator idGenerator;

    @BeforeEach
    void createIDGeneratorBase(){
        this.idGenerator = new IDGeneratorBase();
    }

    @RepeatedTest(20)
    void generate() {
        assertNotEquals(this.idGenerator.generate(),this.idGenerator.generate());
    }

}