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
import accidentproject.Classes.SpeedingVehicle;
/**
 *
 * @author luoph
 */
public class Accident {

    public void accidentMovementAnimation(SpeedingVehicle vehicle){
        
        //Create variables
        int lane = (int)(Math.random() * 3);
        Rectangle accident = new Rectangle();
        
        //Set values for accident
        accident.setHeight(40);
        accident.setWidth(20);
        accident.setY(50);
        accident.setFill(Color.BLUE);
               
        //Randomize lane
        if (lane == 0){
            accident.setX(187);
        }
        else if (lane == 1){
            accident.setX(305);
        }
        else if (lane == 2){
            accident.setX(418);
        }
        //Add accident to pane        
        SpeedingVehicleController.speedingInstance.addAccident(accident);
        
        //Create accident movement
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while (accident.getY() < 1000 && distance(vehicle.getVehicle().getX(), accident.getX(), vehicle.getVehicle().getY(), accident.getY()) > 30){
                        Platform.runLater(() -> accident.setY(accident.getY() + 8));
                        Thread.sleep(50);
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    } 
    //Create method for distance between accidents and vehicle
    public double distance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
