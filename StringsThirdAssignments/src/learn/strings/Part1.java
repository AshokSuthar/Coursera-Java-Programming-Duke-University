/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.strings;
import edu.duke.FileResource;
import edu.duke.StorageResource;
/**
 *
 * @author ashok
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        dna = dna.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        while(stopIndex != -1){
            if((stopIndex-startIndex)%3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon,stopIndex+1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        //1 testz
        String dna = "ASATGLSDJFPWJTAA";
        int startIndex = 2;
        String stopCodon = "TAA";
        System.out.println("Result : "+findStopCodon(dna, startIndex, stopCodon));
        //2 test
        dna = "ATGALSJFJTAGAS";
        startIndex = 0;
        stopCodon = "TAG";
        System.out.println("Result : "+findStopCodon(dna, startIndex, stopCodon));
        //3 test
        dna = "ASJFJATGTGA";
        startIndex = 5;
        stopCodon = "TGA";
        System.out.println("Result : "+findStopCodon(dna, startIndex, stopCodon));
        //4th test
        dna = "ATGSLASJDJSKDASDLASDSSDLKSA";
        startIndex = 0;
        stopCodon = "TAG";
        System.out.println("Result : "+findStopCodon(dna, startIndex, stopCodon));
        //5th test
        dna = "ATGSLASTAGJhTAGDJSKDASDLASDSSDLKSA";
        startIndex = 0;
        stopCodon = "TAG";
        System.out.println("Result : "+findStopCodon(dna, startIndex, stopCodon));
    }
    
    public String findGene(String dna){
        dna = dna.toLowerCase();
        int startIndex = dna.indexOf("atg");
        if(startIndex == -1){
            return "";
        }
        int stopIndex = Integer.MAX_VALUE;
        /*
        Find the index of the first occurrence of the stop codon “TAA” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”. 
        */
        int stopIndexTAA = findStopCodon(dna, startIndex, "taa"); 
        if(stopIndexTAA != -1 && stopIndexTAA < dna.length()){
            stopIndex = stopIndexTAA;
        }
        /*
        Find the index of the first occurrence of the stop codon “TAG” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”.
        */
        int stopIndexTAG = findStopCodon(dna, startIndex, "tag"); 
        if(stopIndexTAG != -1 && stopIndexTAG < dna.length() && stopIndexTAG < stopIndex){
            stopIndex = stopIndexTAG;
        }

        /*
        Find the index of the first occurrence of the stop codon “TGA” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”.
        */
        int stopIndexTGA = findStopCodon(dna, startIndex, "tga"); 
        if(stopIndexTGA != -1 && stopIndexTGA < dna.length() && stopIndexTGA < stopIndex){
            stopIndex = stopIndexTGA;
        }

        if(stopIndex == -1){
            return "";
        }
        
        if(stopIndex - startIndex <= dna.length()){
            return dna.substring(startIndex, stopIndex+3);
        }
        
        return "";
    }
    
    public void testFindGene(){
        //DNA with no “ATG”
        String dna = "ALSJGTHSAT";
        System.out.println("DNA is: "+dna);
        System.out.println("Gene found with closest stopCodon is: "+findGene(dna));
        //DNA with “ATG” and one valid stop codon
        dna = "ALSJGTHSATGLSKDLASORPQGTAASLFS";
        System.out.println("DNA is: "+dna);
        System.out.println("Gene found with closest stopCodon is: "+findGene(dna));
        //DNA with “ATG” and multiple valid stop codons
        dna = "ALATGALSGSLTAGLSPTGATAA";
        System.out.println("DNA is: "+dna);
        System.out.println("Gene found with closest stopCodon is: "+findGene(dna));
        //DNA with “ATG” and no valid stop codons
        dna = "ALSJATGLSJKDJA";
        System.out.println("DNA is: "+dna);
        System.out.println("Gene found with closest stopCodon is: "+findGene(dna));
        //DNA with one invlid length stopCodon and two valid length stopCodons
        dna = "AATGLSKATAALGTGATAG";
        System.out.println("DNA is: "+dna);
        System.out.println("Gene found with closest stopCodon is: "+findGene(dna));
        
    }
    
    public void printAllGenes(String dna){
        while(dna.length() >= 6){ //as a gene could be at minimum of length 6.
            String gene = findGene(dna);
            System.out.println("Gene found: "+gene);
            if(gene.length() < 1){
                break;
            }
            dna = dna.substring(dna.indexOf(gene)+gene.length());
            System.out.println("Remaining DNA: "+dna);
        }
        System.out.println("No more genes strands found!");
    }
    
    public void testPrintAllGenes(){
        String dna = "ALSJJATGLSGTAGLKSATGTAALSKSJKATGLSKJDJSJKTAATGATAGLSKJDATGLKJJDSJKATGTAATGATAGKJTAGTAATGA";
        printAllGenes(dna);
    }
    
    public StorageResource getAllGenes(String dna){
        dna = dna.toLowerCase();
        StorageResource s = new StorageResource();
        while(dna.length() >= 6){ //as a gene could be at minimum of length 6.
            String gene = findGene(dna);
            if(gene.length() < 1){
                break;
            }
            dna = dna.substring(dna.indexOf(gene)+gene.length());
            s.add(gene);
        }
        return s;
    }
    
    public void testGetAllGenes(){
        StorageResource s = new StorageResource();
        String dna = "CTGONEATgONECCCGGGAAAXXXYYYGGGGTAGYYCTGCCCATGENDZZZTAAONEXXXYYYZZZtAaXXXXXTWOATGTWOYYYZZZCCCATGATGENDZZZTAGTWOXXTHREEATGATGTAATHREESTOPTAGAGGGCCCCCFOURATGTAGGXXXFIVEATGYYYFIVZZZAAAXXXFIVENDZZZTGAFIVESTOPSIXATGSIXCGGGCCGGGATCAAASIXENDTAASEVATGSIXCGGGCCGGGATCAAASEVENDENDTAAEIGSTOPTAGAGLASTONEATGtAACTG";
        s = getAllGenes(dna);
        System.out.println(dna.length());
        System.out.println("Printing All the genes found in the dna: ");
        for(String str: s.data()){
            System.out.println(str);
        }
    }
    
    public float cgRatio(String dna){
        dna = dna.toLowerCase();
        float numerator = dna.length()-dna.replaceAll("c", "").length();
        numerator += dna.length()-dna.replaceAll("g", "").length();
        float denominator = dna.length();
        return numerator/denominator;
    }
    
    //test method for cgRatio()
    public void testCGRatio(){
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dna){
        dna = dna.toLowerCase();
        int index = dna.indexOf("ctg");
        int count = 0;
        while(index != -1){
            index = dna.indexOf("ctg",index+3);
            count++;
        }
        return count;
    }
    
    //test method for countCTG()
    public void testCountCTG(){
        String dna = "CTGLSJSJCTGLASTGATGCTGTAG";
        System.out.println(countCTG(dna));
    }
    
    //processGenes Method
    public void processGenes(StorageResource sr){
        //print all the Strings in sr that are longer than 9 characters
        System.out.println("Printing genes longer than 60 characters");
        int count_all = 0;
        int count_60 = 0;
        for(String str: sr.data()){
            count_all++;
            if(str.length()>60){
                count_60++;
                //System.out.println(str);
            }
        }
        System.out.println("\nTotal No. of all genes is: "+count_all);

        //print the number of Strings in sr that are longer than 60 characters
        System.out.println("\nNo. of genes longer than 60 char is: "+count_60);
        //print the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("\ngenes whose CGRatior is > 0.35 are as follows:");
        int countCGRatio = 0;
        for(String str : sr.data()){
            if(cgRatio(str)>0.35){
                countCGRatio++;
                //System.out.println(str);
            }
        }
        //print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("\nNumber of strings in sr whose C-G-ratio is higher than 0.35: "+countCGRatio);
        //print the length of the longest gene in sr
        int maxLen = 0;
        for(String str: sr.data()){
            if(str.length()>maxLen){
                maxLen = str.length();
            }
        }
        System.out.println("\nLength of the longest gene: "+maxLen);
    }
    
    //test method for processGenes()
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        String filename = "/home/ashok/NetBeansProjects/Coursera Java Assignments/StringsThirdAssignments/res/dna/GRch38dnapart.fa";
        FileResource fr = new FileResource(filename);
        String dna = fr.asString();
        sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("CTG Count is: "+countCTG(dna));
    }
    
    public static void main(String[] args){
        Part1 p = new Part1();
        //p.testFindStopCodon();
        //p.testFindGene();
        //p.testPrintAllGenes();
        //p.testGetAllGenes();
        //p.testCGRatio();
        //p.testCountCTG();
        p.testProcessGenes();
    }
    
}
