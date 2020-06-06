package it.unicam.cs.pa.jbudget105333.ModelTest.TagTest;

import it.unicam.cs.pa.jbudget105333.Model.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Model.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Model.Tag.TagBase.TagBase;
import org.junit.jupiter.api.Test;

class TagBasePrinterTest {

    @Test
    void stringOf() {
        Tag tag = new TagBase("Sport","Tennis",new IDGeneratorBase());
        String tagS = tag.getName()+"("+tag.getDescription()+")[ID:"+tag.getID()+"]";
        Printer<Tag> tagp = new TagBasePrinter();
        assertEquals(tagp.stringOf(tag),tagS);
    }
}