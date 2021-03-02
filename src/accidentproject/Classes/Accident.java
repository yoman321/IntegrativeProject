/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

/**
 *
 * @author luoph
 */
public abstract class Accident {
    
    //Create datafield
    private double xPos;
    private double yPos;
    
    //Create constructors
    public Accident(){
        
    }
    public Accident(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    //Create getters and setters
    public double getXPos(){
        return xPos;
    }
    public double getYPos(){
        return yPos;
    }
    public void setXPos(double xPos){
        this.xPos = xPos;
    }
    public void setYpos(double yPos){
        this.yPos = yPos;
    }
    //Create abstract methods
    protected abstract void accidentAction();
}
