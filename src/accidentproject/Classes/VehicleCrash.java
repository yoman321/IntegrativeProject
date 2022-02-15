/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

import accidentproject.VehicleCollisionsController;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.*;
import javafx.application.Platform;
import static java.lang.System.out;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

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
    private int vehicleNbre;
    private boolean isDrunk;
    private String startLocation;
    private String startPosition;
    private String trafficLight;
    private ImageView vehicle;
    
    //Create constructor
    public VehicleCrash(double mass, double velocity, double acceleration, double x, double y, int vehicleNbre, boolean isDrunk, String startLocation, String startPosition, String trafficLight, ImageView vehicle) {
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.x = x;
        this.y = y;
        this.vehicleNbre = vehicleNbre;
        this.isDrunk = isDrunk;
        this.startLocation = startLocation;
        this.startPosition = startPosition;
        this.trafficLight = trafficLight;
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
    public int getVehicleNbre(){
        return vehicleNbre;
    }
    public boolean getIsDrunk(){
        return isDrunk;
    }
    public String getStartLocation(){
        return startLocation;
    }
    public String getStartPosition(){
        return startPosition;
    }
    public String getTrafficLight(){
        return trafficLight;
    }
    public ImageView getVehicle(){
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
    public void setVehicleNbre(int vehicleNbre){
        this.vehicleNbre = vehicleNbre;
    }
    public void setIsDrunk(boolean isDrunk){
        this.isDrunk = isDrunk;
    }
    public void setStartLocation(String startLocation){
        this.startLocation = startLocation;
    }
    public void setStartPosition(String startPosition){
        this.startPosition = startPosition;
    }
    public void setTrafficLight(String trafficLight){
        this.trafficLight = trafficLight;
    }
    public void setVehicle(ImageView vehicle){
        this.vehicle = vehicle;
    }
    
    //Create methods
    public double crashSpeed(VehicleCrash vehicle){
        return (getVelocity() * getMass() + vehicle.getVelocity() * vehicle.getMass())
                / (getMass() + vehicle.getMass());
    }
    public synchronized double crashDistance(VehicleCrash vehicle){
        return Math.pow(crashSpeed(vehicle), 2) / (2 * 0.72 * 9.8);
    }
    public double crashDeceleration(double vi, double distance){
        if (vi < 0){
            return (Math.pow(vi, 2)) / (2 * distance);
        }
        else 
            return -(Math.pow(vi, 2)) / (2 * distance);
    }
    public int isCrash(VehicleCrash[] vehicles){
        for (int i=0; i<vehicles.length; i++){
            if ((Math.sqrt(Math.pow(vehicles[i].getX() - getX(), 2) + Math.pow(vehicles[i].getY() - getY(), 2))) < 40){ //Change value depending on vehicles
                return i;
            }
        }
        return -1;
    }
    //Create conversion methods
    public double conversionToG(double kg){
        return kg * 1000;
    }
    public double conversionMetersPerSeconds(double kilometersPerHours){
        return (kilometersPerHours * 1000) / 3600;
    }
    public double conversionKilometersPerHours(double metersPerSeconds){
        return (metersPerSeconds * 3600) / 1000;
    }
    //Create variables value change at each sleep
    public double newVelocity(double a, double vi, double t){
        return a * t + vi;
    }
    public double newPosition(double a, double vi, double t){
        return vi * t + 0.5 * a * Math.pow(t, 2);
    }
    //Check other vehicle drunk
    public boolean checkIsDrunk(VehicleCrash[] copyVehicles){
        for (int i=0; i<copyVehicles.length; i++){
            if (copyVehicles[i].getIsDrunk()){
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
                    //Change signs
                    if (getStartLocation().equals("down") || getStartLocation().equals("right")){
                       setVelocity(-getVelocity());
                       setAcceleration(-getAcceleration());
                    }

                    //Conversion
                    setVelocity(conversionMetersPerSeconds(getVelocity()));
                    setMass(conversionToG(getMass()));
                    
                    //Check if drunk
                    if (getIsDrunk()){
                        int random = (int)(Math.random() * 2);
                        setAcceleration(-6.86);
                        out.println("drunk");
                        
                        //Check side of road
                        if (random == 1 || VehicleCollisionsController.controllerInstance.getRunCount() > 2){
                            out.println("random: "+random);
                            setX(430);
                            getVehicle().setX(getX());
                        }
                        while(isCrash(copyVehicles) < 0 && getY() > -40 && getY() < 1100){
                            Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(getVelocity()), getAcceleration(), getVehicleNbre()));
                            setVelocity(newVelocity(getAcceleration(), getVelocity(), 0.01));
                            setY(getY()+newPosition(getAcceleration(), getVelocity(), 0.01));
                            //Set acceleration to 0 if speed is higher than 70
                            if (getVelocity() > 65 || getVelocity() < -65){
                                setAcceleration(0);
                                out.println("veloctiy"+ getVelocity());
                            }
//                            out.println("Position: "+getY()+" "+getStartLocation());
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            Thread.sleep(1);
//                            out.println("up");  
                        }
                    }
                    //Check for traffic light
                    else if (getTrafficLight().equals("green")){
                        while(isCrash(copyVehicles) < 0 && getY() > -40 && getY() < 1100){
                            if (getVelocity() > 65 || getVelocity() < -65){
                                setAcceleration(0);
                                out.println("veloctiy"+ getVelocity());
                            }
                            setVelocity(newVelocity(getAcceleration(), getVelocity(), 0.01));
                            setY(getY()+newPosition(getAcceleration(), getVelocity(), 0.01));
//                                out.println("Position: "+getY()+" "+getStartLocation());
                            Platform.runLater(() -> getVehicle().setY(getY()));
                            out.println(getY());
                            Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(getVelocity()), getAcceleration(), getVehicleNbre()));
                            Thread.sleep(1);
//                                out.println("up");  
                        }
                    }
                    else if (getTrafficLight().equals("red")){
                        //Create variables
                        double endPosition = 0;
                        double distance = 0;
                        
                        if (getStartPosition().equals("center")){
                            setVelocity(0);
                            setAcceleration(0);
                            Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(getVelocity()), getAcceleration(), getVehicleNbre()));
                            while (!VehicleCollisionsController.controllerInstance.isClickReset() && isCrash(copyVehicles) < 0){
                                Thread.sleep(1);
                            }
                        }
                        else if (getStartPosition().equals("back")){
                            if (getStartLocation().equals("up")){
                                distance  = 385 - getY();
                                endPosition = 385;
                            }
                            else if (getStartLocation().equals("down")){
                                distance  = (getY() - 574);
                                endPosition = 580;
                            }
                            setAcceleration(crashDeceleration(getVelocity(), distance));
                            if (getStartLocation().equals("down")){
                                while (getY() > endPosition + 1 && isCrash(copyVehicles) < 0){
                                    setVelocity(newVelocity(getAcceleration(), getVelocity(), 0.01));
                                    setY(getY()+newPosition(getAcceleration(), getVelocity(), 0.01));
                                    Platform.runLater(() -> getVehicle().setY(getY()));
                                    Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(getVelocity()), getAcceleration(), getVehicleNbre()));
                                    Thread.sleep(1);
                                }
                            }
                            else if (getStartLocation().equals("up")){
                                while (getY() < endPosition - 1 && isCrash(copyVehicles) < 0){
                                    setVelocity(newVelocity(getAcceleration(), getVelocity(), 0.01));
                                    setY(getY()+newPosition(getAcceleration(), getVelocity(), 0.01));
                                    Platform.runLater(() -> getVehicle().setY(getY()));
                                    Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(getVelocity()), getAcceleration(), getVehicleNbre()));
                                    Thread.sleep(1);
                                }
                            }
                        }
                    }
                    //Remove vehicle if oiut of thread
                    //Crash animation
                    if (isCrash(copyVehicles) >= 0){
                        int vehicleIndex = isCrash(copyVehicles);
                        double crashSpeed = crashSpeed(copyVehicles[vehicleIndex]);
                        double crashDistance = crashDistance(copyVehicles[vehicleIndex]);
                        double endPosition = 0;
                        out.println(crashDistance(copyVehicles[vehicleIndex])+"crashdistance "+getStartLocation());//test
                        out.println(getVelocity()+" speed "+" "+getStartLocation());
                        out.println(crashSpeed(copyVehicles[vehicleIndex])+"crashspeed "+getStartLocation());//test
                        if (crashSpeed < 0){//To be changed later on
                            //Get crash values
                            endPosition = getY() - crashDistance;
                        }
                        else{
                            endPosition = getY() + crashDistance;
                        }
                        double acceleration = crashDeceleration(crashSpeed, crashDistance);
                        double velocity = crashSpeed;
                        double y = getY();
                        out.println(endPosition+" endposition "+getStartLocation());//test
                        if (crashSpeed < 0){
                            while (y > endPosition + 1){ 
                                velocity = newVelocity(acceleration, velocity, 0.01);
                                out.println("crashvelocity "+velocity);
                                y = y + newPosition(acceleration, velocity, 0.01);
//                            setVelocity(newVelocity(getAcceleration(), getVelocity(), 0.01));
//                            setY(getY()+newPosition(getAcceleration(), getVelocity(), 0.01));
                                if (getY() < endPosition){
                                    out.println(getY()+" crashPosition "+getStartLocation());//test
                                }
//                          copyVehicles[vehicleIndex].setY(copyVehicles[vehicleIndex].getY() - 5);
                                final double finalY = y;
                                final double finalVelocity = velocity;
                                final double finalAcceleration = acceleration;
                                final double finalCrashDistance = crashDistance;
                                Platform.runLater(() -> getVehicle().setY(finalY));
//                              Platform.runLater(() -> copyVehicles[vehicleIndex].getVehicle().setY(copyVehicles[vehicleIndex].getY()));
                                Platform.runLater(() -> VehicleCollisionsController.controllerInstance.showCrashValues(conversionKilometersPerHours(crashSpeed), finalAcceleration, finalCrashDistance));
                                Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(finalVelocity), finalAcceleration, getVehicleNbre()));
                                Thread.sleep(1);
                            }
                        }
                        else if (crashSpeed > 0){
                            while (y < endPosition - 1){
                                velocity = newVelocity(acceleration, velocity, 0.01);
                                y = y + newPosition(acceleration, velocity, 0.01);
                                final double finalY = y;
                                final double finalVelocity = velocity;
                                final double finalAcceleration = acceleration;
                                final double finalCrashDistance = crashDistance;
                                Platform.runLater(() -> getVehicle().setY(finalY));
                                Platform.runLater(() -> VehicleCollisionsController.controllerInstance.showCrashValues(conversionKilometersPerHours(crashSpeed), finalAcceleration, finalCrashDistance));
                                Platform.runLater(() -> VehicleCollisionsController.controllerInstance.setPhysicsValues(conversionKilometersPerHours(finalVelocity), finalAcceleration, getVehicleNbre()));
                                Thread.sleep(1);
                            }
                        }
                        //Set correct values for vehicle at the end of loop
                        setY(y);
                        setVelocity(velocity);
                        
                        //Show collision message
                        VehicleCollisionsController.controllerInstance.collisionText("collision");
                        VehicleCollisionsController.controllerInstance.resetBtnVisible();
                    }
                    //Show end of simulation text
                    if (isCrash(copyVehicles) < 0 && (getIsDrunk() || checkIsDrunk(copyVehicles))){
                        VehicleCollisionsController.controllerInstance.collisionText("drunkNoCollision");
                        VehicleCollisionsController.controllerInstance.resetBtnVisible();
                    }
                    else if (isCrash(copyVehicles) < 0 && !getIsDrunk() && !checkIsDrunk(copyVehicles)){
                        VehicleCollisionsController.controllerInstance.collisionText("noCollisionText");
                        VehicleCollisionsController.controllerInstance.resetBtnVisible();
                    }
                }   
                catch (Exception ex){
                    ex.getStackTrace();
                }
                //End thread and remove vehicle
                try{
                    while (!VehicleCollisionsController.controllerInstance.isClickReset()){
                        Thread.sleep(500);
                    }
                    Platform.runLater(() -> VehicleCollisionsController.controllerInstance.removeVehicle(getVehicle())); 
                    Thread.currentThread().interrupt();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            } 
        });
        thread.start();
        
    } 
    
}
