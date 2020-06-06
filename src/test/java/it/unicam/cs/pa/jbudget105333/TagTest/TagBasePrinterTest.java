package it.unicam.cs.pa.jbudget105333.TagTest;

import it.unicam.cs.pa.jbudget105333.IDGenerator.IDGeneratorBase;
import it.unicam.cs.pa.jbudget105333.Tag.Tag;
import it.unicam.cs.pa.jbudget105333.Tag.TagBase;
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