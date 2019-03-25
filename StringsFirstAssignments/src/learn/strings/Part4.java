/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.strings;
import edu.duke.URLResource;
/**
 *
 * @author ashok
 */
public class Part4 {
    public static void main(String[] args){
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word : file.words()) {
            String temp = word.toLowerCase();
            if(temp.contains("youtube.com")){ //checking if lower_case word contains youtube.com in it. if it does, we need to print the actual word.
                int start_index = temp.indexOf("\""); //finding start index
                int end_index = temp.indexOf("\"",start_index+1); //finding end_index
                System.out.println(word.substring(start_index,end_index+1)); //Printing actual link stored in word
            }
        }
    }
    
}
