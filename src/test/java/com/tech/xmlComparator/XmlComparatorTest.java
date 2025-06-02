package com.tech.xmlComparator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XmlComparatorTest {
    @Test
    public void testOrderInsensitiveComparison() throws Exception {
        String xml1 = "<root><child1>Text1</child1><child2>Text2</child2></root>";
        String xml2 = "<root><child2>Text2</child2><child1>Text1</child1></root>";
        XmlComparator comparator = new XmlComparator(new CheckSumGenerator());
        assertTrue(comparator.compare(xml1, xml2));
    }

    @Test
    public void testDifferentXmls() throws Exception {
        String xml1 = "<root><child1>Text1</child1><child2>Text2</child2></root>";
        String xml2 = "<root><child1>DifferentText</child1><child2>Text2</child2></root>";
        XmlComparator comparator = new XmlComparator(new CheckSumGenerator());
        assertFalse(comparator.compare(xml1, xml2));
    }
}
