/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.strings;

/**
 *
 * @author ashok
 *
 */
public class Part1 {
    
    public String findSimpleGene(String dna){
        String codon_start = "ATG";
        String codon_end = "TAA";
        int start_index = dna.indexOf(codon_start);
        int end_index = dna.indexOf(codon_end, start_index+3);
        if(start_index == -1 || end_index == -1){
            return "";
        }
        String str =  dna.substring(start_index, end_index+3);
        if(str.length()%3 == 0){
            return str;
        }
        return "";
    }
    
    public void testSimpleGene(){

        String DNA_1 = "ALSJHFHTAA"; //DNA with no “ATG”, 
        String DNA_2 = "AKSATGALSFHWATA"; //DNA with no “TAA”, 
        String DNA_3 = "ALSLFJKKASDLIEQP"; //DNA with no “ATG” or “TAA”
        String DNA_4 = "DNATGGPASLFSLWTAA"; // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene), 
        String DNA_5 = "DNATGGPASLFSLTAA"; ////and DNA with ATG, TAA and the substring between them is not a multiple of 3. 
    
        //Print the DNA string.
        System.out.println(DNA_1);
        System.out.println(DNA_2);
        System.out.println(DNA_3);
        System.out.println(DNA_4);
        System.out.println(DNA_5);
        
        //See if there is a gene by calling findSimpleGene with this string as the parameter. If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
        System.out.println("Gene is: "+ findSimpleGene(DNA_1));
        System.out.println("Gene is: "+ findSimpleGene(DNA_2));
        System.out.println("Gene is: "+ findSimpleGene(DNA_3));
        System.out.println("Gene is: "+ findSimpleGene(DNA_4));
        System.out.println("Gene is: "+ findSimpleGene(DNA_5));

    }
    
    public static void main(String[] args){
        Part1 run = new Part1();
        run.testSimpleGene();
    }
}
