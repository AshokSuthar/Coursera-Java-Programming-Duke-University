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
    public boolean twoOccurrences(String stringa, String stringb){
        if(stringb.contains(stringa)){
            stringb = stringb.substring(stringb.indexOf(stringa)+stringa.length(),stringb.length());
            if(stringb.contains(stringa)){
                return true;
            }
            return false;
        }
        return false;
    }
    public void testing(){
        System.out.println("Returns "+ twoOccurrences("by","A Story by Abby Long")); //returns true as there are two occurrences of “by”, 
        System.out.println("Returns "+ twoOccurrences("a","banana"));//the call twoOccurrences(“a”, “banana”) returns true as there are three occurrences of “a” so “a” occurs at least twice, 
        System.out.println("Returns "+ twoOccurrences("atg","ctgtatgta"));//and the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is only one occurence of “atg”.
        System.out.println("Returns "+ lastPart("an", "banana"));//The part of the string after an in banana is ana.
        System.out.println("Returns "+ lastPart("zoo","Forest"));//The part of the string after zoo in forest is forest
    }
    public String lastPart(String stringa, String stringb){
        if(stringb.contains(stringa)){
            stringb = stringb.substring(stringb.indexOf(stringa)+stringa.length(),stringb.length());
        }
        return stringb;
    }
    
    public static void main(String[] args){
        Part3 p3 = new Part3();
        p3.testing();
    }
    
}