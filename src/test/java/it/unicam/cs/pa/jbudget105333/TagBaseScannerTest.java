package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagBaseScannerTest {

    @Test
    void scanOf() {
        IDGenerator idGenerator = new IDGeneratorBase();
        String tagS = "Sport,Tennis";
        Scanner<TagBase> tagSc = new TagBaseScanner();
        Tag tag = new TagBase("Sport","Tennis",idGenerator);
        assertEquals(tagSc.scanOf(tagS,idGenerator).getName(),tag.getName());
        assertEquals(tagSc.scanOf(tagS,idGenerator).getDescription(),tag.getDescription());
    }
}