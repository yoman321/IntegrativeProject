/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

import javafx.scene.shape.Rectangle;
/**
 *
 * @author luoph
 */
public class VehicleCrash {
    
    //Create datafields
    private double mass;
    private double velocity;
    private double acceleration;
    private double x;
    private double y;
    private String startLocation;
    private Rectangle vehicle;
    
    //Create constructor
    public VehicleCrash(){
        
    }
    public VehicleCrash(double mass, double velocity, double acceleration, double x, double y, String startLocation,Rectangle vehicle) {
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.x = x;
        this.y = y;
        this.startLocation = startLocation;
        this.vehicle = vehicle;
    }
    //Create getters
    public double getMass(){
        return mass;
    }
    public double getVelocity(){
        return velocity;
    }
    public double getAcceleration(){
        return acceleration;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public String getStartLocation(){
        return startLocation;
    }
    public Rectangle getVehicle(){
        return vehicle;
    }
    
    //Create setters
    public void setMass(double mass){
        this.mass = mass;
    }
    public void setVelocity(double velocity){
        this.velocity = velocity;
    }
    public void setAcceleration(double acceleration){
        this.acceleration = acceleration;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setStartLocation(String startLocation){
        this.startLocation = startLocation;
    }
    public void setVehicle(Rectangle vehicle){
        this.vehicle = vehicle;
    }
//    //Create methods
//    public double getDistance(VehicleCrash vehicle){
//        return Math.abs(vehicle.getPosition() - getPosition());
//    }
    public double crashDistance(VehicleCrash vehicle){
        return ((getVelocity() * getMass()) + (vehicle.getVelocity() * vehicle.getMass())) / (getMass() + vehicle.getMass());
    }
    
    
}
