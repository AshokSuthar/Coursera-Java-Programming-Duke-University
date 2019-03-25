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
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        while(stringb.contains(stringa)){
            count++;
            stringb = stringb.substring(stringb.indexOf(stringa)+stringa.length());
        }
        return count;
    }
    
    public void testHowMany(){
        //1
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("The number of times "+ stringa +" appears in "
         + stringb +" is "+ howMany(stringa, stringb));
        //2
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("The number of times "+ stringa +" appears in "
         + stringb +" is "+ howMany(stringa, stringb));
        //3
        stringa = "BB";
        stringb = "NNNNN";
        System.out.println("The number of times "+ stringa +" appears in "+ 
        stringb +" is "+ howMany(stringa, stringb));
        //4
        stringa = "BB";
        stringb = "BBBBB";
        System.out.println("The number of times "+ stringa +" appears in "+ 
        stringb +" is "+ howMany(stringa, stringb));
    }
    
    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testHowMany();
    }
}
