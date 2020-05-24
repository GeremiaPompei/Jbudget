package it.unicam.cs.pa.jbudget105333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagBasePrinterTest {

    @Test
    void stringOf() {
        Tag tag = new TagBase("Sport","Tennis",new IDGeneratorBase());
        String tagS = tag.getName()+"("+tag.getDescription()+")[ID:"+tag.getID()+"]";
        Printer<Tag> tagp = new TagBasePrinter();
        assertEquals(tagp.stringOf(tag),tagS);
    }
}