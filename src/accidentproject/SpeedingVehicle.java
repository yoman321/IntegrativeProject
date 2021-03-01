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
public class SpeedingVehicle {
    
    //Create datafields
    public double xPos;
    public double yPos;
    
    //Create constructor
    public SpeedingVehicle(){
        
    }
    public SpeedingVehicle(double xPos, double yPos){
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
    public void setYPos(double yPos){
        this.yPos = yPos;
    }
    public double getDistanceAccident(Accident accident){
        return Math.sqrt(Math.pow(accident.getXPos() - getXPos(), 2) + Math.pow(accident.getYPos() - getYPos(), 2));
    }
}
