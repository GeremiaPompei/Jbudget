package it.unicam.cs.pa.jbudget105333.ModelTest.TagTest;

import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGenerator;
import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import org.junit.jupiter.api.Test;

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