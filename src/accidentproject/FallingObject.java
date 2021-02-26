/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrativeproject.FallingObjectClasses;

/**
 *
 * @author Liam
 */
public abstract class FallingObject {
    private double x, y, mass; //added the mass variable
    
    //Constructor
    public FallingObject(){
    }
    
    //Basic setters and getters
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setMass(double mass){
        this.mass = mass;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getMass(){
        return mass;
    }
}
