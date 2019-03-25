/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.strings;

/**
 *
 * @author ashok
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if((stopIndex-startIndex)%3 == 0){
            return stopIndex;
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        //1 test
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
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int stopIndex = Integer.MAX_VALUE;
        /*
        Find the index of the first occurrence of the stop codon “TAA” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”. 
        */
        int stopIndexTAA = findStopCodon(dna, startIndex, "TAA"); 
        if(stopIndexTAA != -1 && stopIndexTAA < dna.length()){
            stopIndex = stopIndexTAA;
        }
        /*
        Find the index of the first occurrence of the stop codon “TAG” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”.
        */
        int stopIndexTAG = findStopCodon(dna, startIndex, "TAG"); 
        if(stopIndexTAG != -1 && stopIndexTAG < dna.length() && stopIndexTAG < stopIndex){
            stopIndex = stopIndexTAG;
        }
        /*
        Find the index of the first occurrence of the stop codon “TGA” 
        after the first occurrence of “ATG” 
        that is a multiple of three away from the “ATG”.
        */
        int stopIndexTGA = findStopCodon(dna, startIndex, "TGA"); 
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
    
    public static void main(String[] args){
        Part1 p = new Part1();
        p.testFindStopCodon();
        p.testFindGene();
        String dna = "ALSJJATGLSGTAGLKSATGTAALSKSJKATGLSKJDJSJKTAATGATAGLSKJDATGLKJJDSJKATGTAATGATAGKJTAGTAATGA";
        p.printAllGenes(dna);
    }
    
}
