/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentproject;

import java.util.concurrent.*;
import accidentproject.Classes.SpeedingVehicle;
import accidentproject.Classes.Accident;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.System.out;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.application.Platform;

/**
 *
 * @author luoph
 */
public class SpeedingVehicleController {
    
    public static SpeedingVehicleController speedingInstance;
    
    //Create FXML variables
    @FXML private Pane pane;
    
    //Create variables
    private ExecutorService vehicleExecutor = Executors.newFixedThreadPool(1);
    private ScheduledExecutorService accidentExecutor = Executors.newScheduledThreadPool(3);
    private SpeedingVehicle vehicle = new SpeedingVehicle(new Rectangle(305, 500, 40, 60));
    private Accident accident = new Accident();
    
    public void initialize(){
        
    }
    public SpeedingVehicleController(){
        speedingInstance = this;
    }
    public void onclickStart(){
        pane.getChildren().add(vehicle.getVehicle());
        vehicleExecutor.execute(new vehicleMovementTask());
        accidentExecutor.scheduleWithFixedDelay(new accidentMovementTask(), 0, 1, TimeUnit.SECONDS);
//        accidentExecutor.execute(new accidentMovementTask());
    }
    private class vehicleMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                vehicle.vehicleMovmentAnimation();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    private class accidentMovementTask implements Runnable{
        @Override
        public void run(){
            try{
                accident.accidentMovementAnimation(vehicle);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void addAccident(Rectangle accident){
        Platform.runLater(() -> pane.getChildren().add(accident));
    }
    
}
