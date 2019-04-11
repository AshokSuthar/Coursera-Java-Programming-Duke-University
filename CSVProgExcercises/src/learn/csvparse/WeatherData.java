/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.csvparse;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author ashok
 */
public class WeatherData {

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currRecord : parser) {
            if (Float.parseFloat(currRecord.get("TemperatureF")) != (float) -9999) {
                if (coldestSoFar == null) {
                    coldestSoFar = currRecord;
                } else {
                    float coldestTemp = Float.parseFloat(coldestSoFar.get("TemperatureF"));
                    float currTemp = Float.parseFloat(currRecord.get("TemperatureF"));
                    if (coldestTemp > currTemp) {
                        coldestSoFar = currRecord;
                    }
                }
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("Coldest Temp was " + coldestRecord.get("TemperatureF")
                + " and occured at " + coldestRecord.get("TimeEST"));

    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        for (File currFile : dr.selectedFiles()) {
            if (coldestFile == null) { // if coldestFile is null, make this file the coldestFile
                coldestFile = currFile;
            } else {
                FileResource coldestFr = new FileResource(coldestFile);
                CSVRecord coldestRecord = coldestHourInFile(coldestFr.getCSVParser()); //get coldestRecord from coldestFile
                float coldestTemp = Float.parseFloat(coldestRecord.get("TemperatureF")); //take coldestTemp from coldestRecord
                FileResource currFr = new FileResource(currFile);
                CSVRecord currColdestRecord = coldestHourInFile(currFr.getCSVParser());// get currColdestRecord from currFile
                float currColdestTemp = Float.parseFloat(currColdestRecord.get("TemperatureF")); // take currTemp from currColdestRecord
                if (coldestTemp < currColdestTemp) { //compare and if coldestTemp is less than currColdestTemp
                    coldestFile = currFile; // make File containing currColdestTemp, the coldestFile.

                }
            }
        }
        return coldestFile.getName();
    }

    public void testFileWithColdestTemperature() {
        String filename = "/home/ashok/NetBeansProjects/Coursera Java Assignments/CSVProgExcercises/res/nc_weather/2014/";
        //getting FileResource with coldestFile
        FileResource fr = new FileResource(filename + fileWithColdestTemperature());
        //print coldestHour
        coldestHourInFile(fr.getCSVParser());
    }

    public static void main(String[] args) {
        WeatherData w = new WeatherData();
        //w.testColdestHourInFile();
        w.testFileWithColdestTemperature();
    }
}
