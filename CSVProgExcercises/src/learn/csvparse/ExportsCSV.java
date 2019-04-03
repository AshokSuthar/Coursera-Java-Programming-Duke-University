/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.csvparse;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author ashok
 */
public class ExportsCSV {

    public void tester() {
        FileResource fr = new FileResource();
        
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");
        
        parser = fr.getCSVParser();
        System.out.println("Total Exporters: " + numberOfExporters(parser, "sugar"));
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public String countryInfo(CSVParser parser, String country) {
        String result = "";
        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                result += record.get("Country") + ": " + record.get("Exports") + ": "
                        + record.get("Value (dollars)");
                break;
            }
        }
        if (result.length() < 1) {
            return "NOT FOUND";
        }
        return result;
    }

    public void listExportersTwoProducts(CSVParser parser, String item1, String item2) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(item1) && record.get("Exports").contains(item2)) {
                count++;
                System.out.println(record.get("Country"));
            }
        }
        if (count == 0) {
            System.out.println("No Country with " + item1 + " and " + item2 + " as Exports!");
        }
    }

    public int numberOfExporters(CSVParser parser, String item) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(item)) {
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String value) {
        for (CSVRecord record : parser) {
            String result = "";
            if (record.get("Value (dollars)").length() > value.length()) {
                result += record.get("Country") + " "+ record.get("Value (dollars)");
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        ExportsCSV excsv = new ExportsCSV();
        excsv.tester();
    }

}
