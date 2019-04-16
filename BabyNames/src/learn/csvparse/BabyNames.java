/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package learn.csvparse;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author ashok
 */
public class BabyNames {

    // Method that prints total births, Total girl names, Total Boy names, and Total names overall
    public void totalBirths(CSVParser parser) {
        int totalBirths = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        for (CSVRecord currRecord : parser) {
            totalBirths += Integer.parseInt(currRecord.get(2));
            if (currRecord.get(1).equals("F")) {
                totalGirlNames += 1;
            } else if (currRecord.get(1).equals("M")) {
                totalBoyNames += 1;
            }
        }
        System.out.println("Total Births: " + totalBirths + ", Total Girl Names: "
                + totalGirlNames + ", Total Boy Names: " + totalBoyNames + ", Total Names: "
                + (totalGirlNames + totalBoyNames));

    }

    // test Method for totalBirths()
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr.getCSVParser(false));
    }

    // Given year, gender, name, finds the rank of the name in that year for specific gender.
    public int getRank(String name, String gender, int year) {
        String absPath = "/home/ashok/NetBeansProjects/Coursera Java Assignments/BabyNames/res/us_babynames/us_babynames_by_year/yob";
        String fullPath = absPath + year + ".csv";
        FileResource fr = new FileResource(fullPath);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        boolean found = false;
        for (CSVRecord currRecord : parser) {
            if (currRecord.get(1).equals(gender)) {
                rank++;
                if (currRecord.get(0).toLowerCase().equals(name.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            return rank;
        }
        return -1;
    }

    // test method for getRank()
    public void testGetRank() {
        System.out.println("Rank: " + getRank("Frank", "M", 1971));
    }

    // finds name corresponding to a given rank, gender and year.
    public String getName(String gender, int year, int rank) {
        String absPath = "/home/ashok/NetBeansProjects/Coursera Java Assignments/BabyNames/res/us_babynames/us_babynames_by_year/yob";
        String fullPath = absPath + year + ".csv";
        FileResource fr = new FileResource(fullPath);
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        boolean found = false;
        for (CSVRecord currRecord : parser) {
            if (currRecord.get(1).equals(gender)) {
                currRank++;
                if (currRank == rank) {
                    return currRecord.get(0);
                }
            }
        }
        return "NO NAME";
    }

    // test Method for getName()
    public void testGetName() {
        System.out.println("Name: " + getName("M", 1982, 450));
    }

    // finds what a your name would be in different year.
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(name, gender, year); // finds rank of name in given year.
        String newName = getName(gender, newYear, rank); // finds a name with same rank in newYear
        if (gender.equals("F")) {
            System.out.println(name + " born in " + year + " would be " + newName
                    + " if she was born in " + newYear + ".");
        } else {
            System.out.println(name + " born in " + year + " would be " + newName
                    + " if he was born in " + newYear + ".");
        }
    }

    //tester method for WhatIsNameInYear()
    public void testWhatIsYourNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int yearOfHighestRank = -1;
        int HighestRank = Integer.MAX_VALUE;
        for (File file : dr.selectedFiles()) {
            int currYear = Integer.parseInt(file.getName().replaceAll("[^0-9]", ""));
            int currRank = getRank(name, gender, currYear);
            // update rank and year only if currRank is not -1 and is < HighestRank.
            if (currRank != -1 && HighestRank > currRank) {
                HighestRank = currRank;
                yearOfHighestRank = currYear;
            }
        }
        return yearOfHighestRank;
    }

    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }

    // finds and returns avg rank of a name in given gender, across selected files.
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        double rankTotal = 0;
        for (File file : dr.selectedFiles()) {
            int currYear = Integer.parseInt(file.getName().replaceAll("[^0-9]", ""));
            int currRank = getRank(name, gender, currYear);
            // update rank and year only if currRank is not -1 and is < HighestRank.
            if (currRank != -1) {
                count++;
                rankTotal += currRank;
            }
        }
        if (count == 0) {
            return -1; // name doesn't exist in any file.
        }
        return rankTotal / count;
    }

    // test method for getAverageRank()
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }

    public int getTotalBirthsRankedHigher(String name, String gender, int year) {
        String absPath = "/home/ashok/NetBeansProjects/Coursera Java Assignments/BabyNames/res/us_babynames/us_babynames_by_year/yob";
        String fullPath = absPath + year + ".csv";
        FileResource fr = new FileResource(fullPath);
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int upTorank = getRank(name, gender, year);
        for (CSVRecord currRecord : parser) {
            if (currRecord.get(1).equals(gender)) { // only if gender of this name equals given gender.
                int currRank = getRank(currRecord.get(0), gender, year); // find rank of this name 
                if (currRank != -1 && currRank < upTorank) { // if its not -1 and less than upTorank
                    totalBirths += Integer.parseInt(currRecord.get(2)); // add these many births to totalBirths
                }
            }
        }
        return totalBirths;
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher("Drew", "M", 1990));
    }

    public static void main(String[] args) {
        BabyNames b = new BabyNames();
        //b.testTotalBirths();
        //b.testGetRank();
        //b.testGetName();
        //b.testWhatIsYourNameInYear();
        //b.testYearOfHighestRank();
        //b.testGetAverageRank();
        b.testGetTotalBirthsRankedHigher();

    }
}
