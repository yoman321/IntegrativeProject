/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.scene.shape.Circle;

/**
 *
 * @author Liam
 */
public class FallingCircle extends FallingObject{
    
    private Circle circle; 
    private double radius;
    
    //Constructors
    public FallingCircle(Circle circle,double radius, double x, double y){ //added this constructor
        this.radius = radius;
        circle = new Circle(radius);
        setX(x);
        setY(y);
    }
    public FallingCircle(Circle circle, double radius){
        this.radius = radius;
        circle = new Circle(radius);
    }
    public FallingCircle(){
    }
    
    //Setters and getters
    public void setRadius(double radius){
        this.radius = radius;
        circle.setRadius(radius);
    }
    public double getRadius(){
        return radius;
    }
}
