/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn.csvparse;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

/**
 *
 * @author ashok
 */
public class ConvertImage {
    
    public ImageResource makeGray(ImageResource inImg){
        ImageResource newImg = new ImageResource(inImg.getWidth(), inImg.getHeight());
        for(Pixel newPixel : newImg.pixels()){
            Pixel inPixel = inImg.getPixel(newPixel.getX(), newPixel.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            newPixel.setRed(avg);
            newPixel.setGreen(avg);
            newPixel.setBlue(avg);
        }
        newImg.draw();
        return newImg;
    }
    
    public ImageResource makeInverted(ImageResource inImg){
        ImageResource newImg = new ImageResource(inImg.getWidth(), inImg.getHeight());
        for(Pixel newPixel : newImg.pixels()){
            Pixel inPixel = inImg.getPixel(newPixel.getX(), newPixel.getY());
            newPixel.setRed(255 - inPixel.getRed());
            newPixel.setGreen(255 - inPixel.getGreen());
            newPixel.setBlue(255 - inPixel.getBlue());
        }
        newImg.draw();
        return newImg;
    }
    
    public static void main(String[] args) {
        ConvertImage ci = new ConvertImage();
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()){
            ImageResource img = new ImageResource(file);
            String name = file.getName();
            // original image is drawn
            img.draw();
            // making image gray, drawing and saving.
            ImageResource grayImg = ci.makeGray(img);
            String grayName = "gray copy of " + name;
            grayImg.setFileName(grayName);
            grayImg.save();
            // inverting image, drawing and saving
            ImageResource invImg = ci.makeInverted(img);
            String invName = "inverted copy of " + name;
            invImg.setFileName(invName);
            invImg.save();
        }
    }
    
}
