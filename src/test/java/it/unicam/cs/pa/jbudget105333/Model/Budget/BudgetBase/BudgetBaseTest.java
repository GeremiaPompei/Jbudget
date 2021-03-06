package it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase;

import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BudgetBaseTest {

    private BudgetBase budget;
    private TagBase sport;
    private TagBase macchina;

    @BeforeEach
    void createBudgetBase(){
        IDGenerator idGenerator = new IDGeneratorBase();
        budget = new BudgetBase();
        sport = new TagBase("Sport", "Tennis", idGenerator.generate());
        macchina = new TagBase("Macchina", "Revisione", idGenerator.generate());
    }

    @Test
    void add() {
        assertTrue(this.budget.getBudgetMap().isEmpty());
        this.budget.add(this.sport,200);
        assertFalse(this.budget.getBudgetMap().isEmpty());
        assertTrue(this.budget.getBudgetMap().containsKey(this.sport));
        assertEquals(this.budget.getValue(this.sport),200);
    }

    @Test
    void remove() {
        this.budget.add(this.sport,200);
        assertFalse(this.budget.getBudgetMap().isEmpty());
        this.budget.remove(this.sport);
        assertTrue(this.budget.getBudgetMap().isEmpty());
    }

    @Test
    void getBudget() {
        Map<Tag,Double> budgetMap = new HashMap<>();
        budgetMap.put(this.sport,200.0);
        this.budget.add(this.sport,200);
        assertEquals(budgetMap.get(this.sport),this.budget.getValue(this.sport));
    }

    @Test
    void getValue() {
        this.budget.add(this.sport,200);
        this.budget.add(this.macchina,400);
        assertEquals(this.budget.getValue(this.sport),200);
        assertEquals(this.budget.getValue(this.macchina),400);
    }

    @Test
    void getTags() {
        this.budget.add(this.sport,200);
        this.budget.add(this.macchina,400);
        Set<Tag> setTag = new TreeSet<>();
        setTag.add(this.sport);
        setTag.add(this.macchina);
        assertEquals(setTag,this.budget.getTags());
        this.budget.remove(this.macchina);
        assertNotEquals(setTag,this.budget.getTags());
    }
}