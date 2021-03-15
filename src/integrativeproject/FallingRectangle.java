/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author liamd
 */
public class FallingRectangle extends FallingObject{
    
    private Rectangle rectangle;
    private double width, height;
    
    public FallingRectangle(double width, double height){
        this.width = width;
        this.height = height;
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }
    
    public void setWidth(double width){
        this.width = width;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
}
