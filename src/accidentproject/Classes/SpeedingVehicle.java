/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject.Classes;

import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import static java.lang.System.out;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
/**
 *
 * @author luoph
 */
public class SpeedingVehicle {
    
    //Create datafields
    private double xPos;
    private double yPos;
    private Rectangle vehicle;
    
    //Create constructor
    public SpeedingVehicle(){
        
    }
    public SpeedingVehicle(double xPos, double yPos, Rectangle vehicle){
        this.xPos = xPos;
        this.yPos = yPos;
        this.vehicle = vehicle;
    }
    //Create getters and setters
    public double getX(){
        return xPos;
    }
    public double getY(){
        return yPos;
    }
    public Rectangle getVehicle(){
        return vehicle;
    }
    public void setX(double xPos){
        this.xPos = xPos;
    }
    public void setY(double yPos){
        this.yPos = yPos;
    }
    public void setVehicle(Rectangle vehicle){
        this.vehicle = vehicle;
    }
//    public double getDistanceAccident(Accident accident){
//        return Math.sqrt(Math.pow(accident.getXPos() - getXPos(), 2) + Math.pow(accident.getYPos() - getYPos(), 2));
//    }
    //Create animation methods
    public void vehicleMovmentAnimation(){
        
//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                getVehicle().setOnKeyPressed(e -> {
//                    switch (e.getCode()){
//                        case DOWN: setY(getY() + 5); 
//                            Platform.runLater(() -> getVehicle().setY(getY())); out.println("DOWN"); break;
//                        case UP: setY(getY() - 5); 
//                            Platform.runLater(() -> getVehicle().setY(getY())); break;
//                        case LEFT: setX(getX() - 5);
//                            Platform.runLater(() -> getVehicle().setX(getX())); break;
//                        case RIGHT: setX(getX() + 5);
//                            Platform.runLater(() -> getVehicle().setX(getX())); break;
//                    }
//                });
//            }
//        });
//        thread.start();

        vehicle.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case DOWN: Platform.runLater(() -> getVehicle().requestFocus());
                    setY(getY() + 100); 
                    Platform.runLater(() -> getVehicle().setY(getY())); break;
                case UP: Platform.runLater(() -> getVehicle().requestFocus());
                    setY(getY() - 100); 
                    Platform.runLater(() -> getVehicle().setY(getY())); break;
                case LEFT: Platform.runLater(() -> getVehicle().requestFocus());
                    setX(getX() - 100);
                    Platform.runLater(() -> getVehicle().setX(getX())); break;
                case RIGHT: Platform.runLater(() -> getVehicle().requestFocus());
                    setX(getX() + 100);
                    Platform.runLater(() -> getVehicle().setX(getX())); break;
        }
                    
    });
//        while(true){
//            Platform.runLater(() -> getVehicle().requestFocus());
//        }
          Platform.runLater(() -> getVehicle().requestFocus());

//        vehicle.setOnMousePressed(e -> {
//            vehicle.setX(0);
//            vehicle.setY(0);
//        });
    }
}
