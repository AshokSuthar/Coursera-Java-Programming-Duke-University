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
public class Part3 {
    
    //Finds the index of stopCodon and returns it, if not found returns length of the dna.
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if((stopIndex-startIndex)%3 == 0){
            return stopIndex;
        }
        return dna.length();
    }
    
    //Finds and returns a Gene with valid length and any of the 3 stopCodons. "TAA","TAG" and "TGA".
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
    
    //Prints all the genes in a given dna String.
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
    
    public int countGenes(String dna){
        int count = 0;
        while(dna.length() >= 6){ //as a gene could be at minimum of length 6.
            String gene = findGene(dna);
            if(gene.length() >= 6){
                count++;
            }
            else{
                break;
            }
            dna = dna.substring(dna.indexOf(gene)+gene.length());
        }
        return count;
    }
    

    private void testCountGenes() {
        //1
        String dna = "ALSJJATGLSGTAGLKSATGTAALSKSJKATGLSKJDJSJKTAATGATAGLSKJDATGLKJJDSJKATGTAATGATAGKJTAGTAATGA";
        System.out.println("Number of Genes found is: " + countGenes(dna));
        //2
        dna = "ATGTAAGATGCCCTAGT";
        System.out.println("Number of Genes found is: " + countGenes(dna));
        //3
        dna = "ATGLSKFJATGLKSTAA"; 
        /*
        if startCodon was found at 8th index, there exists a gene. 
        but the startCodon found at 0th index covers the 8th index startCodon, 
        hence resulting in no valid gene. Is it supposed to be this way? 
        */
        System.out.println("Number of Genes found is: " + countGenes(dna));
    }
    
    public static void main(String[] args){
        Part3 p = new Part3();
        p.testCountGenes();
    }
}
