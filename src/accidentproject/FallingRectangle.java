/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Liam
 */
public class FallingRectangle extends FallingObject{
    private double width, height;
    private Rectangle rectangle;
    
    //Constructors
    public FallingRectangle(Rectangle rectangle, double width, double height, double x, double y){
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(width, height);
        setX(x);
        setY(y);
    }
    public FallingRectangle(Rectangle rectangle, double width, double height){
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(width, height);
    }
    public FallingRectangle(){
    }
    
    //Setters and getters
    public void setHeight(double height){
        this.height = height;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
}
