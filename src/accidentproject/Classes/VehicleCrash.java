/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

import javafx.scene.shape.Rectangle;
import java.util.concurrent.*;
import javafx.application.Platform;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
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
    public double crashDistance(VehicleCrash vehicle){
        return ((getVelocity() * getMass()) + (vehicle.getVelocity() * vehicle.getMass())) / (getMass() + vehicle.getMass());   
    }
    public boolean isCrash(VehicleCrash[] vehicles){
        for (int i=0; i<vehicles.length; i++){
            if ((Math.sqrt(Math.pow(vehicles[i].getX() - getX(), 2) + Math.pow(vehicles[i].getY() - getY(), 2))) == 20){
                
                return true;
            }
        }
        return false;
    }
    //Animation method
    public synchronized void vehicleAnimation(VehicleCrash[] vehicles, VehicleCrash duplicateVehicle){
        
        //Create copu array
        VehicleCrash[] copyVehicles = new VehicleCrash[vehicles.length - 1];
        
        //Remove duplicate
        for (int i=0; i<vehicles.length; i++){
            if (getX() == vehicles[i].getX() && getY() == vehicles[i].getY()){
                System.arraycopy(vehicles, 0, copyVehicles, 0, i);
                System.arraycopy(vehicles, i+1, copyVehicles, i, vehicles.length - i -1);
                break;
            }
        }
        Thread thread = new Thread(new Runnable(){
            @Override 
            public void run(){
                try{
                    if (getStartLocation().equals("up")){
                        while(!isCrash(copyVehicles)){
                            setY(getY() + 5);
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            Thread.sleep(3);
                        }
                    }
                    if (getStartLocation().equals("down")){
                        while (!isCrash(copyVehicles)){
                            setY(getY() - 5);
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            Thread.sleep(3);
              
                       }
                    }
                    if (getStartLocation().equals("left")){
                        while (!isCrash(copyVehicles)){
                            setX(getX() + 5);
                            Platform.runLater(() -> getVehicle().setX(getX()));
                            Thread.sleep(3);
                        }
                    }
                    if (getStartLocation().equals("right")){
                        while (!isCrash(copyVehicles)){
                            setX(getX() - 5);
                            Platform.runLater(() -> getVehicle().setX(getX()));
                            Thread.sleep(3);
                        }
                    }
                }
                catch (Exception ex){
                    ex.getStackTrace();
                }
            }
               
        });
        thread.start();
    }
                
    
    
}
