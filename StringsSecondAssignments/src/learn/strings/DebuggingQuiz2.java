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
public class DebuggingQuiz2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println("index " + index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);//changed index+4 to index+3
           System.out.println("index after updating " + index);
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdabc");
       findAbc("abcabcabcabca");
   }
   
   public static void main(String[] args){
        DebuggingQuiz2 d = new DebuggingQuiz2();
        d.test();
    }
    
}
