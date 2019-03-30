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
public class DebuggingQuiz1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    
    public void test() {
         //findAbc("abcd");
         findAbc("abcdabc");
    }
    
    public static void main(String[] args){
        DebuggingQuiz1 d = new DebuggingQuiz1();
        d.test();
    }
}
