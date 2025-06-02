package com.tech.xmlComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import java.io.StringReader;

@Service
public class XmlComparator {
    private final CheckSumGenerator checkSumGenerator;

    @Autowired
    public XmlComparator(CheckSumGenerator checkSumGenerator) {
        this.checkSumGenerator = checkSumGenerator;
    }

    public boolean compare(String xmlContent1, String xmlContent2) throws Exception {
        Document doc1 = parseXml(xmlContent1);
        Document doc2 = parseXml(xmlContent2);
        return compare(doc1, doc2);
    }

    public boolean compare(Document doc1, Document doc2) {
        long checksum1 = checkSumGenerator.computeChecksum(doc1);
        long checksum2 = checkSumGenerator.computeChecksum(doc2);
        return checksum1 == checksum2;
    }

    private Document parseXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        doc.getDocumentElement().normalize();
        return doc;
    }
}
