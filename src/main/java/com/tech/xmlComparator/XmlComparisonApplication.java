package com.tech.xmlComparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class XmlComparisonApplication implements CommandLineRunner {
    @Autowired
    private XmlComparator xmlComparator;

    public static void main(String[] args) {
        SpringApplication.run(XmlComparisonApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length < 2) {
            System.out.println("Usage: XmlComparisonApplication <file1> <file2>");
            return;
        }
        try {
            String xml1 = new String(Files.readAllBytes(Paths.get(args[0])));
            String xml2 = new String(Files.readAllBytes(Paths.get(args[1])));
            boolean equal = xmlComparator.compare(xml1, xml2);
            System.out.println(equal ? "XMLs are equal" : "XMLs are NOT equal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
