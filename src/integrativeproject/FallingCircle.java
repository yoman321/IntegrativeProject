/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject;

import javafx.scene.shape.Circle;

/**
 *
 * @author liamd
 */
public class FallingCircle extends FallingObject{ 
    private Circle circle;
    private double radius;
    
    public FallingCircle(double radius){
        this.radius = radius;
        circle.setRadius(radius);
    }
    
    public void setRadius(double radius){
        this.radius = radius;
    }
    public double getRadius(){
        return radius;
    }
    
}
