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
import javafx.scene.image.ImageView;
/**
 *
 * @author luoph
 */
public class SpeedingVehicle {
    
    //Create datafields
    private ImageView vehicle;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private double[] upLeftCorner = {305, 500};  
    private double[] upRightCorner = {345, 500};
    private double[] bottomLeftCorner = {305, 560};
    private double[] bottomRightCorner = {345, 600};
    
    //Create constructor
    public SpeedingVehicle(){
        
    }
    public SpeedingVehicle(ImageView vehicle){
        this.vehicle = vehicle;
    }
    //Create getters and setters
    public ImageView getVehicle(){
        return vehicle;
    }
    public void setVehicle(ImageView vehicle){
        this.vehicle = vehicle;
    }
//    public double getDistanceAccident(Accident accident){
//        return Math.sqrt(Math.pow(accident.getXPos() - getXPos(), 2) + Math.pow(accident.getYPos() - getYPos(), 2));
//    }
    //Create methods
    //Create corner location methods
//    public double[] getUpLeftCorner(){
//        return upLeftCorner;
//    }
//    public double[] getUpRightCorner(){
//        return upRightCorner;
//    }
//    public double[] getBottomLeftCorner(){
//        return bottomLeftCorner;
//    }
//    public double[] getBottomRightCorner(){
//        return bottomRightCorner;
//    }
//    public void setUpLeftCorner(double x, double y){
//        this.upLeftCorner[0] += x;
//        this.upLeftCorner[1] += y;
//    }
//    public void setUpRightCorner(double x, double y){
//        this.upRightCorner[0] += x;
//        this.upRightCorner[1] += y;
//    }
//    public void setBottomLeftCorner(double x, double y){
//        this.bottomLeftCorner[0] += x;
//        this.bottomLeftCorner[1] += y;
//    }
//    public void setBottomRightCorner(double x, double y){
//        this.bottomRightCorner[0] += x;
//        this.bottomRightCorner[1] += y;
//    }
    //Create animation methods
    public void vehicleMovmentAnimation(){
        
        getVehicle().setFitWidth(50);
        getVehicle().setFitHeight(90);
        getVehicle().setX(305);
        getVehicle().setY(500);
        getVehicle().setOpacity(1);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                try{
                    if (SpeedingVehicleController.speedingInstance.vehicleExceutorIsShutdown() || 
                            getVehicle().getX() < 131 || getVehicle().getX() > 466){
                        if (getVehicle().getX() < 131 || getVehicle().getX() > 466){
                            SpeedingVehicleController.speedingInstance.endgameText("offroad");
                        }
                        SpeedingVehicleController.speedingInstance.removeVehicle(vehicle);
                        SpeedingVehicleController.speedingInstance.vehicleExecutorShutdown();
                        timer.cancel();
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                if (up){
//                    setUpLeftCorner(0, -10);
//                    out.println(getUpLeftCorner()[1]);
//                    setUpRightCorner(0, -10);
//                    setBottomLeftCorner(0, -10);
//                    setBottomRightCorner(0, -10);
                    Platform.runLater(() -> getVehicle().setY(getVehicle().getY() - 5));
                   
                }
                if (down){
//                    setUpLeftCorner(0, 10);
//                    setUpRightCorner(0, 10);
//                    setBottomLeftCorner(0, 10);
//                    setBottomRightCorner(0, 10);
                    Platform.runLater(() -> getVehicle().setY(getVehicle().getY() + 5));   
                }
                if (right){
//                   setUpLeftCorner(10, 0);
//                   setUpRightCorner(10, 0);
//                   setBottomLeftCorner(10, 0);
//                   setBottomRightCorner(10, 0);
                   Platform.runLater(() -> getVehicle().setX(getVehicle().getX() + 5)); 
                }
                if (left){
//                    setUpLeftCorner(-10, 0);
//                    setUpRightCorner(-10, 0);
//                    setBottomLeftCorner(-10, 0);
//                    setBottomRightCorner(-10, 0);
                    Platform.runLater(() -> getVehicle().setX(getVehicle().getX() - 5));
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
}
