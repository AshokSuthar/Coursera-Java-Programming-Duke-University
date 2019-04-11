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
                if (coldestTemp > currColdestTemp) { //compare and if coldestTemp is less than currColdestTemp
                    coldestFile = currFile; // make File containing currColdestTemp, the coldestFile.

                }
            }
        }
        return coldestFile.getName();
    }

    public void testFileWithColdestTemperature() {
        String absolutePath = "/home/ashok/NetBeansProjects/Coursera Java Assignments/CSVProgExcercises/res/nc_weather/2014/";
        //getting FileResource with coldestFile
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(absolutePath+filename);
        //get CSVRecord containing coldest hour
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser()); 
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temp on that day was " + coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record : fr.getCSVParser()){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currRecord : parser) {
            if (!currRecord.get("Humidity").equals("N/A")) {
                if (lowestSoFar == null) {
                    lowestSoFar = currRecord;
                } else {
                    float lowestHumidity = Float.parseFloat(lowestSoFar.get("Humidity"));
                    float currHumidity = Float.parseFloat(currRecord.get("Humidity"));
                    if (lowestHumidity > currHumidity) { //only if lowestHumidity is > currHumidity
                        lowestSoFar = currRecord; // make record containing currHumidity as new lowestSoFar.
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + record.get("Humidity")+
                " at "+record.get("DateUTC"));

    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRecord = null; // initialize with null.
        for (File currFile : dr.selectedFiles()) {
            FileResource currFr = new FileResource(currFile);
            CSVRecord currRecord = lowestHumidityInFile(currFr.getCSVParser());
            if(lowestHumidityRecord == null){
                lowestHumidityRecord = currRecord; //if first such record, make it lowest
            }
            else{ //otherwise
                if(Float.parseFloat(lowestHumidityRecord.get("Humidity")) 
                        > Float.parseFloat(currRecord.get("Humidity"))){ //compare both humidity and assign lowest
                    lowestHumidityRecord = currRecord;
                }
            }
        }
        return lowestHumidityRecord;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + record.get("Humidity")+
                " at "+record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord currRecord : parser) {
            //if curr temp is not missing
            if (Double.parseDouble(currRecord.get("TemperatureF")) != (double) -9999) {
                totalTemp += Double.parseDouble(currRecord.get("TemperatureF")); // add to total temp
                count++; //increase total count
            }
        }
        if(count == 0){
            return (double)-1;
        }
        return totalTemp/count;//return avg.
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        if(avg == -1){
            System.out.println("No valid temp records found!");
        }
        else{
            System.out.println("Average temperature in file is "+avg);
        }
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord currRecord : parser) {
            double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
            if(currRecord.get("Humidity").equals("N/A")){ //if humidity is N/A goto next record
                continue;
            }
            double currHumidity = Double.parseDouble(currRecord.get("Humidity"));
            //if curr temp is not missing
            if (currTemp != (double) -9999 && currHumidity >= value) {
                totalTemp += Double.parseDouble(currRecord.get("TemperatureF")); // add to total temp
                count++; //increase total count
            }
        }
        
        if(count == 0){
            return (double)-1;
        }
        return totalTemp/count;//return avg.
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(int value){
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), value);
        if(avg == -1){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is "+avg);
        }
    }
    
    public static void main(String[] args) {
        WeatherData w = new WeatherData();
        //w.testColdestHourInFile();
        //w.testFileWithColdestTemperature();
        //w.testLowestHumidityInFile();
        //w.testLowestHumidityInManyFiles();
        //w.testAverageTemperatureInFile();
        w.testAverageTemperatureWithHighHumidityInFile(80);
    }
}
