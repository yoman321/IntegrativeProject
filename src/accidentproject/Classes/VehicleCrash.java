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
    //Create methods
    public double crashSpeed(VehicleCrash vehicle){
//        if (vehicle.getStartLocation().equals("down")){
//            vehicle.setY(-vehicle.getY());
//            out.println(vehicle.getY());
//        }
//        if (vehicle.getStartLocation().equals("right")){
//            vehicle.setX(-vehicle.getX());
//            out.println(vehicle.getX());
//        }
        //Get final speed after collision
        out.println(getVelocity()+"getVelocity");
        out.println(getMass()+"getMsss");
        out.println(vehicle.getVelocity()+"vehicle getvelocity");
        out.println(vehicle.getMass()+"vehicle getMass");
        out.println(((getVelocity() * conversionToG(getMass())) + (vehicle.getVelocity() * conversionToG(vehicle.getMass()))) / (conversionToG(getMass()) + conversionToG(vehicle.getMass()))+"method crashspeed");
        return ((getVelocity() * conversionToG(getMass())) + (vehicle.getVelocity() * conversionToG(vehicle.getMass()))) / (conversionToG(getMass()) + conversionToG(vehicle.getMass()));
        
    }
    public double crashDistance(VehicleCrash vehicle){
        return Math.pow(crashSpeed(vehicle), 2) / (2 * 0.72 * 9.8);
    }
    public double conversionToG(double kg){
        return kg * 1000;
    }
    public int isCrash(VehicleCrash[] vehicles){
        for (int i=0; i<vehicles.length; i++){
            if ((Math.sqrt(Math.pow(vehicles[i].getX() - getX(), 2) + Math.pow(vehicles[i].getY() - getY(), 2))) == 20){ //Change value depending on vehicles
                
                return i;
            }
        }
        return -1;
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
                        while(isCrash(copyVehicles) < 0){
                            setY(getY() + 5);
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            Thread.sleep(3);
                            out.println("up");
                        }
                    }
                    else if (getStartLocation().equals("down")){
                        while (isCrash(copyVehicles) < 0){
                            setY(getY() - 5);
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            Thread.sleep(3);
                            out.println("down");
                       }
                    }
                    else if (getStartLocation().equals("left")){
                        while (isCrash(copyVehicles) < 0){
                            setX(getX() + 5);
                            Platform.runLater(() -> getVehicle().setX(getX()));
                            Thread.sleep(3);
                        }
                    }
                    else if (getStartLocation().equals("right")){
                        while (isCrash(copyVehicles) < 0){
                            setX(getX() - 5);
                            Platform.runLater(() -> getVehicle().setX(getX()));
                            Thread.sleep(3);
                        }
                    }
                                        
                    //Crash animation
                    if (isCrash(copyVehicles) >= 0){
                        //Change signs
                        if (getStartLocation().equals("down") || getStartLocation().equals("right")){
                            setVelocity(-getVelocity());
                        }
                        int vehicleIndex = isCrash(copyVehicles);
                        out.println(crashDistance(copyVehicles[vehicleIndex])+"crashdistance");//test
                        out.println(crashSpeed(copyVehicles[vehicleIndex])+"crashspeed");//test
                        if (crashSpeed(copyVehicles[vehicleIndex]) < 0){//To be changed later on
                            double crashDistance = getY() - crashDistance(copyVehicles[vehicleIndex]);
                            while (getY() > crashDistance){ 
                                setY(getY() - 5);
//                                copyVehicles[vehicleIndex].setY(copyVehicles[vehicleIndex].getY() - 5);
                                Platform.runLater(() -> getVehicle().setY(getY()));
//                                Platform.runLater(() -> copyVehicles[vehicleIndex].getVehicle().setY(copyVehicles[vehicleIndex].getY()));
                                Thread.sleep(3);
                            }
                        }
                        else if (crashSpeed(copyVehicles[vehicleIndex]) > 0){//To be changed later on
                            out.println(crashDistance(copyVehicles[vehicleIndex]));//test
                            double crashDistance = getY() + crashDistance(copyVehicles[vehicleIndex]);
                            while (getY() < crashDistance){ 
                                setY(getY() + 5);
//                                copyVehicles[vehicleIndex].setY(copyVehicles[vehicleIndex].getY() + 5);
                                Platform.runLater(() -> getVehicle().setY(getY()));
//                                Platform.runLater(() -> copyVehicles[vehicleIndex].getVehicle().setY(copyVehicles[vehicleIndex].getY()));
                                Thread.sleep(3);
                            }
                        }
                    }

                }
                catch (Exception ex){
                    ex.getStackTrace();
                }
            }
               
        });
        thread.start();
        
//        out.println("nothing");//test
//        Thread collisionThread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                //Actions when crash
//                try{
//                    out.println("something");//test
//                    if (isCrash(copyVehicles) >= 0){
//                        int vehicleIndex = isCrash(copyVehicles);
//                        out.println(crashDistance(copyVehicles[vehicleIndex]));//test
//                        if (crashSpeed(copyVehicles[vehicleIndex]) < 0){//To be changed later on
//                            while (getY() != getY() - crashDistance(copyVehicles[vehicleIndex])){ 
//                                setY(getY() - 5);
//                                copyVehicles[vehicleIndex].setY(getY() - 5);
//                                Platform.runLater(() -> getVehicle().setY(getY()));
//                                Platform.runLater(() -> copyVehicles[vehicleIndex].getVehicle().setY(getY()));
//                                Thread.sleep(3);
//                            }
//                        }
//                        else if (crashSpeed(copyVehicles[vehicleIndex]) > 0){//To be changed later on
//                            out.println(crashDistance(copyVehicles[vehicleIndex]));//test
//                            while (getY() != getY() + crashDistance(copyVehicles[vehicleIndex])){ 
//                                setY(getY() + 5);
//                                copyVehicles[vehicleIndex].setY(getY() + 5);
//                                Platform.runLater(() -> getVehicle().setY(getY()));
//                                Platform.runLater(() -> copyVehicles[vehicleIndex].getVehicle().setY(getY()));
//                                Thread.sleep(3);
//                            }
//                        }
//                    }
//                    
//                }
//                catch (Exception ex){
//                    ex.getStackTrace();
//                }
//            }
//        });
//        collisionThread.start();
         
    }
                
    
    
}
