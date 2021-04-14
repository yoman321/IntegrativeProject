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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javafx.scene.paint.Color;
import accidentproject.SpeedingVehicleController;
/**
 *
 * @author luoph
 */
public class SpeedingVehicle {
    
    //Create datafields
    private Rectangle vehicle;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    
    //Create constructor
    public SpeedingVehicle(){
        
    }
    public SpeedingVehicle(Rectangle vehicle){
        this.vehicle = vehicle;
    }
    //Create getters and setters
    public Rectangle getVehicle(){
        return vehicle;
    }
    public void setVehicle(Rectangle vehicle){
        this.vehicle = vehicle;
    }
//    public double getDistanceAccident(Accident accident){
//        return Math.sqrt(Math.pow(accident.getXPos() - getXPos(), 2) + Math.pow(accident.getYPos() - getYPos(), 2));
//    }
    //Create methods
    public void move (String direction){
         switch (direction){
            case "up": Platform.runLater(() -> getVehicle().setY(getVehicle().getY() + 100)); break;
            case "down": Platform.runLater(() -> getVehicle().setY(getVehicle().getY() - 100)); break;
            case "left": Platform.runLater(() -> getVehicle().setX(getVehicle().getX() - 100)); break;
            case "right": Platform.runLater(() -> getVehicle().setX(getVehicle().getX() + 100)); break;
            default: break;
        
        }
    }
    //Create animation methods
    public void vehicleMovmentAnimation(){
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if (up){
                    Platform.runLater(() -> getVehicle().setY(getVehicle().getY() - 10));
                }
                if (down){
                    Platform.runLater(() -> getVehicle().setY(getVehicle().getY() + 10));
                }
                if (right){
                   Platform.runLater(() -> getVehicle().setX(getVehicle().getX() + 10)); 
                }
                if (left){
                    Platform.runLater(() -> getVehicle().setX(getVehicle().getX() - 10));
                }
            }
        }, 0, 20);
        
        vehicle.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case DOWN: Platform.runLater(() -> getVehicle().requestFocus());
                    down = true; break;
                case UP: Platform.runLater(() -> getVehicle().requestFocus());
                    up = true; break;
                case LEFT: Platform.runLater(() -> getVehicle().requestFocus());
                    left = true; break;
                case RIGHT: Platform.runLater(() -> getVehicle().requestFocus());
                    right = true; break;
            }
                    
        });
        vehicle.setOnKeyReleased(e -> {
           switch (e.getCode()){
                case DOWN: Platform.runLater(() -> getVehicle().requestFocus());
                    down = false; break;
                case UP: Platform.runLater(() -> getVehicle().requestFocus());
                    up = false; break;
                case LEFT: Platform.runLater(() -> getVehicle().requestFocus());
                    left = false; break;
                case RIGHT: Platform.runLater(() -> getVehicle().requestFocus());
                    right = false; break;
            }
        });
        
         Platform.runLater(() -> getVehicle().requestFocus());
    }
//    public void accidentMovementAnimation(){
//        
////        Timer timer = new Timer();
////        timer.schedule(new TimerTask(){
////            @Override
////            public void run(){
////                out.println("hello");
////                //Create variables
////                int lane = (int)(Math.random() * 3);
////                getVehicle().setHeight(40);
////                getVehicle().setWidth(20);
////                getVehicle().setY(50);
////                getVehicle().setFill(Color.BLUE);
////                
////                //Randomize lane
////                if (lane == 0){
////                    getVehicle().setX(187);
////                }
////                else if (lane == 1){
////                    getVehicle().setX(305);
////                }
////                else if (lane == 2){
////                    getVehicle().setX(418);
////                }
////                //Accident movement
////                try{
////                    while (getVehicle().getY() < 1000){
////                        out.println(getVehicle().getY());
////                        Platform.runLater(() -> getVehicle().setY(getVehicle().getY() + 8));
////                        Thread.sleep(50);
//////                    out.println("something");
////                    }
////                }
////                catch(Exception ex){
////                    ex.printStackTrace();
////                }
////            }
////        }, 0, 1000);
//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                out.println("hello");
//                //Create variables
//                int lane = (int)(Math.random() * 3);
//                getVehicle().setHeight(40);
//                getVehicle().setWidth(20);
//                getVehicle().setY(50);
//                getVehicle().setFill(Color.BLUE);
//                
//                //Randomize lane
//                if (lane == 0){
//                    getVehicle().setX(187);
//                }
//                else if (lane == 1){
//                    getVehicle().setX(305);
//                }
//                else if (lane == 2){
//                    getVehicle().setX(418);
//                }
//                //Accident movement
//                try{
//                    while (getVehicle().getY() < 1000){
//                        out.println(getVehicle().getY());
//                        Platform.runLater(() -> getVehicle().setY(getVehicle().getY() + 8));
//                        Thread.sleep(50);
////                    out.println("something");
//                    }
//                }
//                catch(Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//        
//    }
}
