/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.duke;

/**
 *
 * @author ashok
 */
public class Perimiter {
    public double getPerimiter(Shape s){
        double currPerimiter = 0;
        Point prevPoint = s.getLastPoint();
        for(Point p: s.getPoints()){
            currPerimiter += prevPoint.distance(p);
            prevPoint = p;
        }
        return currPerimiter;
    }
    
    public void readShape(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("Perimiter of this shape is: " + getPerimiter(s)/4);
        System.out.println("Max side is: " + getMaxSide(s));
    }
    
    public static void main(String[] args){
        Perimiter p = new Perimiter();
        p.readShape();
    }

    private double getMaxSide(Shape s) {
        double max = 0;
        Point prevPoint = s.getLastPoint();
        for(Point p: s.getPoints()){
            double temp = prevPoint.distance(p);
            if(max<temp){
                max = temp;
            }
            prevPoint = p;
        }
        return max;
    }
    
    
}
