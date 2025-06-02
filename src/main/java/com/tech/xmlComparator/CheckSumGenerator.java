package com.tech.xmlComparator;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;

@Service
public class CheckSumGenerator {
    public long computeChecksum(Element element) {
        long checksum = 0;
        // incorporate tag name
        checksum += element.getTagName().hashCode();
        // incorporate attributes
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attr = attributes.item(i);
            checksum += attr.getNodeName().hashCode();
            checksum += attr.getNodeValue().hashCode();
        }
        // incorporate child elements and text nodes
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                checksum += computeChecksum((Element) node);
            } else if (node instanceof Text) {
                String text = node.getTextContent().trim();
                if (!text.isEmpty()) {
                    checksum += text.hashCode();
                }
            }
        }
        return checksum;
    }

    public long computeChecksum(Document doc) {
        Element root = doc.getDocumentElement();
        return computeChecksum(root);
    }
}
