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
public class VehicleCrash {
    
    //Create datafields
    private double mass;
    private double velocity;
    private double acceleration;
    private double position;
    
    //Create constructor
    public VehicleCrash(){
        
    }
    public VehicleCrash(double mass, double velocity, double acceleration, double position){
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.position = position;
    }
    //Create getter and setters
    public double getMass(){
        return mass;
    }
    public double getVelocity(){
        return velocity;
    }
    public double getAcceleration(){
        return acceleration;
    }
    public double getPosition(){
        return position;
    }
    public void setMass(double mass){
        this.mass = mass;
    }
    public void setVelocity(double velocity){
        this.velocity = velocity;
    }
    public void setAcceleration(double acceleration){
        this.acceleration = acceleration;
    }
    public void setPosition(double posisiton){
        this.position = position;
    }
    //Create methods
    public double getDistance(VehicleCrash vehicle){
        return Math.abs(vehicle.getPosition() - getPosition());
    }
    public double crashDistance(double m1, double v1, double m2, double v2){
        return ((m1 * v1) + (m2 * v2))/(m1 + m2);
    }
    
}
